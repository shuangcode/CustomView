package com.daniel.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daniel.custom.design.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageLoader mImageLoader = new ImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        mImageLoader.setImageCache(null);
    }
}
