package com.wei.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.ec.R;

/**
 * Created by Administrator on 2017/10/31.
 */

public class SortDelegate extends BottomItemDelegtate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
