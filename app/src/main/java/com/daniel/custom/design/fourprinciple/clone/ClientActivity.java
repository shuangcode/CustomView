package com.daniel.custom.design.fourprinciple.clone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.daniel.custom.R;
import com.daniel.custom.example.SuperActivity;

/**
 * Created by daniel.xiao on 2016/10/25.
 */
public class ClientActivity extends SuperActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anim);
        button = (Button) findViewById(R.id.button2);

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


        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClientActivity.this.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        function2();
                    }
                });
            }
        }).start();
    }

    @Override protected void onDestroy() {
        Toast.makeText(ClientActivity.this, "onDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
        System.gc();
    }

    public void function(){

    }
}
