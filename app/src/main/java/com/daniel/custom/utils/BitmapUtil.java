package com.daniel.custom.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by daniel.xiao on 2016/10/29.
 */
public class BitmapUtil {

    /**
     * Bitmap转为字节数组
     * @param bitmap
     * @return
     */
    public static byte[] bmp2Bytes(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos); // 保证不失真
        return bos.toByteArray();
    }

    /**
     * 字节数组转为Bitmap
      *@param newArray
     * @return
     */
    public static Bitmap bytes2Bmp(byte[] newArray){
        return BitmapFactory.decodeByteArray(newArray, 0, newArray.length);
    }

    /**
     * 计算图片的缩放值
     * int reqWidth: 控件的宽度
     * int reqHeight: 控件的高度
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        final int height = options.outHeight; // 原始高度 = 图片的高度
        final int width = options.outWidth; // 原始宽度 = 图片的宽度
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 哪个缩放比例小，就选择使用哪个
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800); // 480,800手机的像素值

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //把bitmap转换成String
    public static String bitmapToString(String filePath) {

        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * 获取图片的角度
     * @param filepath
     * @return
     */
    public int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch(orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle 选择角度
     * @param bitmap
     * @return Bitmap
     */
    public Bitmap rotaingImageView(int angle , Bitmap bitmap) {

        if (angle == 0) {
            return bitmap;
        }
        //旋转图片 动作
        Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        //matirx肯定不为null, resizedBitmap和bitmap不为同一对象
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return resizedBitmap;
    }

}
