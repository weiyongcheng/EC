package com.wei.latte.wechat.template;

import com.wei.latte.activities.ProxyActivity;
import com.wei.latte.delegates.LatteDelegate;
import com.wei.latte.wechat.BaseWXEntryActivity;
import com.wei.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2017/10/27.
 */

public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);    //消失无动画
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
