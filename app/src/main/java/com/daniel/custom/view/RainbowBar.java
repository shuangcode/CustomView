package com.daniel.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.daniel.custom.R;
import com.daniel.custom.utils.Utils;

/**
 * 谷歌彩虹进度条
 */
public class RainbowBar extends View {

  //progress bar color
  int barColor = Color.parseColor("#1E88E5");
  //every bar segment width
  int hSpace = Utils.dpToPx(getContext(), 80);
  //every bar segment height
  int vSpace = Utils.dpToPx(getContext(), 4);
  //space among bars
  int space = Utils.dpToPx(getContext(), 10);
  float startX = 0;
  float delta = 10f;
  Paint mPaint;

  public RainbowBar(Context context) {
    super(context);
  }

  public RainbowBar(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RainbowBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    //read custom attrs
    TypedArray t = context.obtainStyledAttributes(attrs,
            R.styleable.rainbowbar, 0, 0);
    hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
    vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
    barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
    t.recycle(); // we should always recycle after used
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setColor(barColor);
    mPaint.setStrokeWidth(vSpace); // 设置画壁的高度
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  //draw be invoke numbers.
  int index = 0;
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //get screen width
    float sw = this.getMeasuredWidth(); // 获取view的宽度
    if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
      startX = 0;
    } else {
      startX += delta;
    }
    float start = startX;
    // draw latter parse
    while (start < sw) {
      canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
      start += (hSpace + space);
    }

    start = startX - space - hSpace;

    // draw front parse
    while (start >= -hSpace) {
      canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
      start -= (hSpace + space);
    }
    if (index >= 700000) {
      index = 0;
    }
    invalidate(); // 重新调用一次onDraw方法
  }

}