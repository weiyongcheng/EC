package com.wei.latte.delegates.web.event;

import com.wei.latte.app.Latte;
import com.wei.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2017/11/8.
 */

public class UndefinedEvent extends Event{
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefinedEvent", params);
        return null;
    }
}
