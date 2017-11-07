package com.wei.latte.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wei.latte.delegates.web.WebDelegate;
import com.wei.latte.delegates.web.route.Router;
import com.wei.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2017/11/7.
 */

public class WebViewClientImple extends WebViewClient{

    private final WebDelegate DELEGATE;

    public WebViewClientImple(WebDelegate delegatre) {
        this.DELEGATE = delegatre;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    //上面的方法避免有的机型没有实现
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
