package com.daniel.custom.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.daniel.custom.R;
import com.daniel.custom.design.fourprinciple.clone.ClientActivity;
import com.daniel.custom.example.TestB;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class AnimActivity extends AppCompatActivity {

    private Button button;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ClientActivity.class);
                startActivity(intent);

            }
        });
        TestB b = new TestB();
    }

    // nineold
    public void anim2(View view){
        // 水平移动动画
        ObjectAnimator.ofFloat(button, "translationX", 100).setDuration(1000).start();
    }

    public void anim1(View view){
        // 从0到100
        ValueAnimator animator = ValueAnimator.ofFloat(0,100).setDuration(2000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float curVal = (float) animation.getAnimatedValue();
                // 垂直移动动画
                ViewHelper.setTranslationY(button,curVal);//注意不要使用btn.setTranslationY
            }
        });
    }
}
