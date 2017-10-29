package com.wei.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wei.latte.util.log.LogUtil;
import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.IError;
import com.wei.latte.net.callback.IFailure;
import com.wei.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {
//        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
//                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtil.writeLog(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
