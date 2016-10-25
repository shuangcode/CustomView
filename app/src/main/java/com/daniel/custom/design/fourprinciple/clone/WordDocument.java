package com.daniel.custom.design.fourprinciple.clone;

import com.daniel.custom.utils.CLog;

import java.util.ArrayList;

/**
 * Created by daniel.xiao on 2016/10/25.
 * 使程序运行更高效
 * 使用场景：1.类初始化需要消化非常多的资源，这个资源包括数据，硬件资源等，通过原型拷贝避免这些消耗。
 *         2.保护性拷贝。
 */
public class WordDocument implements Cloneable{

    public static final String TAG = "原型模型";

    private String mText;
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument(){
        CLog.i(TAG, "--------------WordDocument 构造函数----------------");
    }

    @Override
    protected WordDocument clone(){
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
            doc.mImages = this.mImages;
            return doc;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void addImages(String img) {
        this.mImages.add(img);
    }

    public void showDocument(){
        CLog.i(TAG, "" + mText );
        for (int i = 0; i<this.mImages.size(); i++){
            CLog.i(TAG, "" + this.mImages.get(i) +"/n");
        }
    }
}
