package com.daniel.custom.pic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by daniel.xiao on 2016/10/19.
 */
public class ImageUtil {

    // Bitmap转为Bytes数组
    public static byte[] bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    // Bytes转为Bitmap数组
    public static Bitmap bytes2Bitmap(byte[] newArray){
        return BitmapFactory.decodeByteArray(newArray, 0, newArray.length);
    }
}
