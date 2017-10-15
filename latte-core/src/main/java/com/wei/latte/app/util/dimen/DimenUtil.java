package com.wei.latte.app.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wei.latte.app.Latte;

/**
 * Created by Administrator on 2017/10/12.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
