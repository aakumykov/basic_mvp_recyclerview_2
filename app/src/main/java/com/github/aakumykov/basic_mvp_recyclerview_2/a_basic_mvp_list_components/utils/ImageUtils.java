package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

public class ImageUtils {

    public static void setDrawableColor(@NonNull Context context, Drawable drawable, int colorId) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, context.getResources().getColor(colorId));
    }
}
