package com.daniel.custom.view;

import android.content.Context;

/**
 * Created by daniel.xiao on 2016/10/14.
 */
public class Utils {


    public static int dpToPx(Context context, float dipValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
