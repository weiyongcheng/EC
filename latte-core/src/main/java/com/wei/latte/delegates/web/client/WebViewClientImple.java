package com.wei.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wei.latte.app.Latte;
import com.wei.latte.delegates.IPageLoadListener;
import com.wei.latte.delegates.web.WebDelegate;
import com.wei.latte.delegates.web.route.Router;
import com.wei.latte.ui.loader.LatteLoader;
import com.wei.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2017/11/7.
 */

public class WebViewClientImple extends WebViewClient{

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

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

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
               LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
