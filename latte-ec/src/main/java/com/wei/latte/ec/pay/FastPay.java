package com.wei.latte.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.wei.latte.app.ConfigKeys;
import com.wei.latte.app.Latte;
import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.ec.R;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.IError;
import com.wei.latte.net.callback.IFailure;
import com.wei.latte.net.callback.ISuccess;
import com.wei.latte.ui.loader.LatteLoader;
import com.wei.latte.util.log.LatteLogger;
import com.wei.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2017/11/13.
 */

public class FastPay implements View.OnClickListener{

    //设置支付回调监听
    private IAliPayResultListener mIAliPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderId = -1;

    private FastPay(LatteDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);

        }
    }

    public FastPay setPayResultListener(IAliPayResultListener listener) {
        this.mIAliPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderId = orderId;
        return this;
    }

    public final void alPay(int orderId) {
        final String url = "";
        //获取签名信息
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        LatteLogger.d("PAY_SIGN", paySign);
                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIAliPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Latte.getApplicationContext(), "请求失败", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .post();
    }

    private final void weChatPay(int orderId) {
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "";
        LatteLogger.d("WX_PAY", weChatPrePayUrl);

        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
            alPay(mOrderId);
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }
    }
}
