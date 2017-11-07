package com.wei.latte.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/11/7.
 */

public class LatteWebInterface {

    private final WebDelegate DELEGATRE;

    public LatteWebInterface(WebDelegate delegatre) {
        this.DELEGATRE = delegatre;
    }

    static LatteWebInterface create(WebDelegate delegatre) {
        return new LatteWebInterface(delegatre);
    }

    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
