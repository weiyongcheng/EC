package com.wei.ec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.wei.latte.app.LogUtil;
import com.wei.latte.app.activities.ProxyActivity;
import com.wei.latte.app.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
