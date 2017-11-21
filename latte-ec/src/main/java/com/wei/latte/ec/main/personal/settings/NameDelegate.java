package com.wei.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.ec.R;

/**
 * Created by Administrator on 2017/11/17.
 */

public class NameDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
