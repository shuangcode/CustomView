package com.daniel.custom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daniel.custom.R;
import com.daniel.custom.design.fourprinciple.DoubleCache;
import com.daniel.custom.design.fourprinciple.ImageCache;
import com.daniel.custom.design.fourprinciple.ImageLoader;

public class ImageLoadActivity extends AppCompatActivity {

    private ImageCache mImageCache;

    public String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        // 1.初始化缓存策略
        mImageCache = new DoubleCache();
        // 2.初始化图片加载器
        ImageLoader imageLoader = new ImageLoader();
        // 3.设置缓存策略
        imageLoader.setImageCache(mImageCache);
        // 4.显示图片
        imageLoader.displayImage(imgUrl, (ImageView) findViewById(R.id.imageView));
    }
}
