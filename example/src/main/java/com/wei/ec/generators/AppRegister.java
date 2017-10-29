package com.wei.ec.generators;

import com.wei.annotation.AppRegisterGenerator;
import com.wei.annotation.EntryGenerator;
import com.wei.latte.wechat.template.AppRegisterTemplate;

/**
 * Created by Administrator on 2017/10/27.
 */

@AppRegisterGenerator(
        packageName = "com.wei.ec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister{
}
