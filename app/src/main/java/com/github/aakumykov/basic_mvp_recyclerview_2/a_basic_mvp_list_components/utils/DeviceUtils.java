package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils;

import android.content.Context;
import android.content.res.Configuration;

public class DeviceUtils {

    public static int getOrientation(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.orientation;
    }
}
