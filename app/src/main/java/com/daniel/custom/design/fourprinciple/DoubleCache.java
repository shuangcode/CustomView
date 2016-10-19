package com.daniel.custom.design.fourprinciple;

import android.graphics.Bitmap;

/**
 * Created by daniel.xiao on 2016/10/17.
 * 双缓存
 */
public class DoubleCache implements ImageCache{

    MemoryImageCache mMemoryCache = new MemoryImageCache();
    DiskCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if(null == bitmap){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
