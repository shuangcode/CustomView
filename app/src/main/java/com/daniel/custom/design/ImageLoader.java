package com.daniel.custom.design;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.daniel.custom.utils.CLog;
import com.daniel.custom.utils.UIUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by daniel.xiao on 2016/10/17.
 */
public class ImageLoader {

    private final static String LOG_TAG = "ImageLoader";

    private ImageCache mImageCache;

    // 线程池，线程数量为cpu的数量
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    // 注入实现缓存
    public void setImageCache(ImageCache imageCache){
        this.mImageCache = imageCache;
    }

    public void displayImage(String url, ImageView imageView){
        // 从缓存中拿图片
        Bitmap bitmap =  mImageCache.get(url);
        // 说明缓存中有图片
        if(null != bitmap){
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(url, imageView);
    }

    // 从网络中获取图片
    private void submitLoadRequest(final String url,final ImageView imageView){
        CLog.d(LOG_TAG, "非run 方法中 currentThreadId = "+ Thread.currentThread().getId());
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                // 这个是在子线程吗？
                CLog.d(LOG_TAG, "run 方法中 currentThreadId = "+ Thread.currentThread().getId());
                Bitmap bitmap = downloadImage(url);
                if(null == bitmap){
                    return;
                }
                // 存放到缓存中
                mImageCache.put(url, bitmap);
                showImage(imageView, bitmap);
            }
        });
    }

    /**
     * 显示图片
     * @param imageView
     * @param bitmap
     */
    private void showImage(final ImageView imageView,final Bitmap bitmap){
        UIUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    /**
     * 从网络中加载图片
     * @param imageUrl
     * @return
     */
    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
