package com.wei.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wei.latte.delegates.IPageLoadListener;
import com.wei.latte.delegates.web.chromClient.WebChromClientImple;
import com.wei.latte.delegates.web.client.WebViewClientImple;
import com.wei.latte.delegates.web.route.RouteKeys;
import com.wei.latte.delegates.web.route.Router;

/**
 * Created by Administrator on 2017/11/7.
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {

        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClicent() {
        final WebViewClientImple client = new WebViewClientImple(this);
        client.setIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromClient() {
        return new WebChromClientImple();
    }
}
