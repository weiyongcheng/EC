package com.wei.latte.ec.main;

import android.graphics.Color;

import com.wei.latte.delegates.bottom.BaseBottomDelegate;
import com.wei.latte.delegates.bottom.BottomItemDelegtate;
import com.wei.latte.delegates.bottom.BottomTabBean;
import com.wei.latte.delegates.bottom.ItemBuilder;
import com.wei.latte.ec.main.index.IndexDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/31.
 */

public class EcBottomDelegate extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegtate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegtate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
