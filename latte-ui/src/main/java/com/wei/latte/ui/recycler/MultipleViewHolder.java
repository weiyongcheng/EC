package com.wei.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/11/3.
 */

public class MultipleViewHolder extends BaseViewHolder{

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder crete(View view) {
        return new MultipleViewHolder(view);
    }
}
