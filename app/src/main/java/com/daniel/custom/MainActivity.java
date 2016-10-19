package com.daniel.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daniel.custom.bean.Person;
import com.daniel.custom.design.fourprinciple.ImageLoader;
import com.daniel.custom.utils.CLog;
import com.daniel.custom.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageLoader mImageLoader = new ImageLoader();

    public String filename = "test.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test2();
    }

    /**
     * 使用缓存
     * 显示网络图片
     */
    private void test1() {
        ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        mImageLoader.setImageCache(null);
    }

    /**
     * 保存到本地
     */
    private void test2(){
        Person per1 = new Person();
        per1.name = "Daniel";
        per1.age = 26;
        per1.sex = "male";
        per1.isSingle = false;
        Person per2 = new Person();
        per2.name = "Rose";
        per2.age = 26;
        per2.sex = "male";
        per2.isSingle = false;
        Person per3 = new Person();
        per3.name = "Jim";
        per3.age = 26;
        per3.sex = "male";
        per3.isSingle = false;
        Person per4 = new Person();
        per4.name = "Jackson";
        per4.age = 26;
        per4.sex = "male";
        per4.isSingle = false;
        List list = new ArrayList();
        list.add(per1);
        list.add(per2);
        list.add(per3);
        list.add(per4);
        FileUtil.writeObjectToFile(list, filename);
    }

    /**
     * onClick
     * @param view
     */
    public void readFromFile(View view){
        test3();
    }

    /**
     * 读取指定文件的内容
     */
    private void test3() {
        List list = (List) FileUtil.readObjectFromFile(filename);
        Toast.makeText(this.getApplicationContext(), list.size() + "",Toast.LENGTH_LONG).show();

        for(Object per : list){
            CLog.d(TAG, per.toString());
        }


    }
}
