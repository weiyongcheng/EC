package com.wei.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.ec.R;
import com.wei.latte.ec.main.sort.content.ContentDelegate;
import com.wei.latte.ec.main.sort.list.VerticalListDelegate;

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
