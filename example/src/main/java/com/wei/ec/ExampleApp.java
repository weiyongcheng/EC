package com.wei.ec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wei.latte.app.Latte;
import com.wei.latte.net.interceptors.DebugInterceptor;
import com.wei.latte.ec.database.DatabaseManager;
import com.wei.latte.ec.icon.FontEcModule;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://192.168.0.100:3000/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatSecret("")
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
