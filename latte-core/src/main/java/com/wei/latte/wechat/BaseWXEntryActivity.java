package com.wei.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wei.latte.app.Latte;
import com.wei.latte.net.RestClient;
import com.wei.latte.net.callback.IError;
import com.wei.latte.net.callback.IFailure;
import com.wei.latte.net.callback.ISuccess;
import com.wei.latte.util.log.LatteLogger;
import com.wei.latte.wechat.BaseWXActivity;
import com.wei.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2017/10/28.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity{

    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {

        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_ECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        LatteLogger.d("authUrl", authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient
                .builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject authObject = JSON.parseObject(response);
                        final String accessToken = authObject.getString("access_token");
                        final String openId = authObject.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?aucccess_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang")
                                .append("zh_CN");

                        LatteLogger.d("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
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
