package com.wei.latte.wechat.template;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.wei.latte.activities.ProxyActivity;
import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.wechat.BaseWXPayEntryActivity;

/**
 * Created by Administrator on 2017/10/27.
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity{
    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
