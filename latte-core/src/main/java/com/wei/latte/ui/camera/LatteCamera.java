package com.wei.latte.ui.camera;

import android.net.Uri;

import com.wei.latte.delegates.PermissionCheckerDelegate;
import com.wei.latte.util.file.FileUtil;

/**
 * Created by Administrator on 2017/11/19.
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
