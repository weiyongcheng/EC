package com.wei.ec.generators;

import com.wei.annotation.EntryGenerator;
import com.wei.latte.wechat.template.WXEntryTemplate;

/**
 * Created by Administrator on 2017/10/27.
 */

@EntryGenerator(
        packageName = "com.wei.ec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry  {
}
