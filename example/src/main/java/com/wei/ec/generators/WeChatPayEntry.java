package com.wei.ec.generators;

import com.wei.annotation.EntryGenerator;
import com.wei.annotation.PayEntryGenerator;
import com.wei.latte.wechat.template.WXPayEntryTemplate;

/**
 * Created by Administrator on 2017/10/27.
 */

@SuppressWarnings("DefaultFileTemplate")
@PayEntryGenerator(
        packageName = "com.wei.ec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
