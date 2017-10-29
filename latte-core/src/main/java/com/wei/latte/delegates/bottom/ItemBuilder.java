package com.wei.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/30.
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegtate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegtate delegtate) {
        ITEMS.put(bean, delegtate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegtate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegtate> build() {
        return ITEMS;
    }
}
