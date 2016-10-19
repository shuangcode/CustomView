package com.daniel.custom.design.fourprinciple;

import android.graphics.Bitmap;

/**
 * Created by daniel.xiao on 2016/10/17.
 * 图片缓存
 */
public interface ImageCache {

    Bitmap get(String url); 
    void put(String url, Bitmap bitmap);
}
