package com.wei.ec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wei.latte.app.Latte;
import com.wei.ec.event.TestEvent;
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
                .withApiHost("http://www.1chacha.com/random_cms/public_api/jsonApi/jsonName/")
//                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatSecret("")
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
