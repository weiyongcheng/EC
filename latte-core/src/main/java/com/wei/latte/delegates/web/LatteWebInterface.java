package com.wei.latte.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/11/7.
 */

public class LatteWebInterface {

    private final WebDelegatre DELEGATRE;

    public LatteWebInterface(WebDelegatre delegatre) {
        this.DELEGATRE = delegatre;
    }

    static LatteWebInterface create(WebDelegatre delegatre) {
        return new LatteWebInterface(delegatre);
    }

    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}