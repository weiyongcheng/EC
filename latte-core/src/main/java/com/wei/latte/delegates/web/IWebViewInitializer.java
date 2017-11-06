package com.wei.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    //针对于页面的控制
    WebViewClient initWebViewClicent();

    //针对于页面内部的控制
    WebChromeClient initWebChromClient();
}
