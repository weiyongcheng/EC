package com.wei.latte.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wei.latte.R;
import com.wei.latte.delegates.LatteDelegate;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BottomItemDelegtate extends LatteDelegate implements View.OnKeyListener{

    private long mExitTime = 0;
    private static final int EXIT_ITEM = 2000;

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {             //注释掉双击退出会出问题
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_ITEM) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }
}
