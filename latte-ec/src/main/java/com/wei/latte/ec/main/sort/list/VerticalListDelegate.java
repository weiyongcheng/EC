package com.wei.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.ec.R;

/**
 * Created by Administrator on 2017/11/5.
 */

public class VerticalListDelegate extends LatteDelegate{


    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
