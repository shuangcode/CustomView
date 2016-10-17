package com.daniel.custom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daniel.custom.R;
import com.daniel.custom.design.DoubleCache;
import com.daniel.custom.design.ImageCache;
import com.daniel.custom.design.ImageLoader;

public class ImageLoadActivity extends AppCompatActivity {

    private ImageCache mImageCache;

    public String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        mImageCache = new DoubleCache();
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setImageCache(mImageCache);
        imageLoader.displayImage(imgUrl, (ImageView) findViewById(R.id.imageView));
    }
}
