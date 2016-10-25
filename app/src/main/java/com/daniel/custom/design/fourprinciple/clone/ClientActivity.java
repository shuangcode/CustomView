package com.daniel.custom.design.fourprinciple.clone;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by daniel.xiao on 2016/10/25.
 */
public class ClientActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1.构建文档对象
        WordDocument originDoc = new WordDocument();
        // 2.编辑文档，添加图片
        originDoc.setmText("这是一篇文档");
        originDoc.addImages("图片1");
        originDoc.addImages("图片2");
        originDoc.addImages("图片3");
        // 以原始文档为原型，拷贝一份副本
        WordDocument doc2 = originDoc.clone();
        doc2.showDocument();
        // 修改文档副本，不会影响原始文档
        doc2.setmText("这是修改的Doc2文档");
        doc2.showDocument();

        originDoc.showDocument();
    }
}
