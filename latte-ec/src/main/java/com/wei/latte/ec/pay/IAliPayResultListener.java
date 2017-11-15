package com.wei.latte.ec.pay;

/**
 * Created by Administrator on 2017/11/13.
 */

public interface IAliPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
