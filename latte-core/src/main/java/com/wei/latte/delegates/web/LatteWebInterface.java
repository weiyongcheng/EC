package com.wei.latte.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.wei.latte.delegates.web.event.Event;
import com.wei.latte.delegates.web.event.EventManager;

/**
 * Created by Administrator on 2017/11/7.
 */

final class LatteWebInterface {

    private final WebDelegate DELEGATRE;

    public LatteWebInterface(WebDelegate delegatre) {
        this.DELEGATRE = delegatre;
    }

    static LatteWebInterface create(WebDelegate delegatre) {
        return new LatteWebInterface(delegatre);
    }

    //必须有注解
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATRE);
            event.setContext(DELEGATRE.getContext());
            event.setUrl(DELEGATRE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
