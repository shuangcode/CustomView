package com.daniel.custom.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.daniel.custom.R;
import com.daniel.custom.utils.CLog;

/**
 * 虚线
 */
public class DotView extends View {
    private static final String TAG = "DotView";
    private Paint p;
	private int width;
	private int height;
	private int dash;
    private int dashGap;
	
	public DotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DotView(Context context) {
		super(context);
		init(context);
	}
	private void init(Context context){
		p = new Paint(Paint.ANTI_ALIAS_FLAG);  
		p.setStyle(Style.FILL);  
		p.setColor(context.getResources().getColor(R.color.grey_divide));

		final float scale = context.getResources().getDisplayMetrics().density;
		dash=(int) (8 * scale + 0.5f);
        dashGap = (int) (4 * scale + 0.5f);
	}
	@Override
	protected void onDraw(Canvas canvas) {
        CLog.d(TAG, "StrokeWidth = "+ p.getStrokeWidth());
		if(width>10){
			for(int i=0;i<width;i+=dashGap){
//				canvas.drawLine(i, 0, i+=dash, 0, p);
				RectF r=new RectF();
				r.left = i;
				r.right = i+=dash;
				r.top = 1;
				r.bottom = 16;
				canvas.drawRoundRect(r, 8, 8, p);
			}
		}
		super.onDraw(canvas);
	}
	
	@Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
        p.setStrokeWidth(height);
        CLog.d(TAG, "height = " + height);
        this.postInvalidate();
    }
	
	
	

}
