package com.wei.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.wei.latte.app.Latte;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.IError;
import com.wei.latte.net.callback.IFailure;
import com.wei.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/11/1.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true); //开始加载
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求

                REFRESH_LAYOUT.setRefreshing(false);    //结束刷新
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
//                        Toast.makeText(Latte.getApplicationContext(), "failer", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
//                        Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
