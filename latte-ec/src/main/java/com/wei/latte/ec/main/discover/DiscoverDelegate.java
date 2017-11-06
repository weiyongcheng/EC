package com.wei.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.ec.R;

/**
 * Created by Administrator on 2017/11/7.
 */

public class DiscoverDelegate extends BottomItemDelegtate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
