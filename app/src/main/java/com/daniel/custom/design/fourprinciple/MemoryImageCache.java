package com.daniel.custom.design.fourprinciple;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by daniel.xiao on 2016/10/17.
 * 内存缓存
 */
public class MemoryImageCache implements ImageCache{

    LruCache<String ,Bitmap> mMemoryCache;

    public MemoryImageCache(){
        // 计算可以使用的最大内存 单位M
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemory/4;
        mMemoryCache = new LruCache<String ,Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 单位M
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024; // 这句话什么意思
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
