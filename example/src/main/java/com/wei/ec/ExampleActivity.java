package com.wei.ec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.wei.latte.app.LogUtil;
import com.wei.latte.app.activities.ProxyActivity;
import com.wei.latte.app.delegates.LatteDelegate;
import com.wei.latte.ec.launcher.LaunchScorllDelegate;
import com.wei.latte.ec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
