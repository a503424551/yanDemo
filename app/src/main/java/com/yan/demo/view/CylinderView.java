package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.yan.demo.R;

public class CylinderView extends View {



    public CylinderView(Context context) {
        super(context);
    }

    public CylinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
          int left;
          int firstTop;
          int right;
          int firstBottom;
          int height;
        Paint paint = new Paint();
        //顶部圆弧方形
        left = 100;
        firstTop = 50;
        right = 160;
        firstBottom = 100;
        height = 300;
        RectF rectF = new RectF(left, firstTop, right, firstBottom);
     //   canvas.drawRect(rectF, paint);

        //顶部圆弧
        paint.setColor(ContextCompat.getColor(getContext(),R.color.tem));
        paint.setStyle(Paint.Style.FILL);
          canvas.drawArc(rectF,-180,180,true,paint);

        //中间柱形
       // paint.setColor(Color.RED);
        RectF rectF2 = new RectF(left, firstBottom-((firstBottom-firstTop)/2), right, firstBottom + height+((firstBottom-firstTop)/2));
        canvas.drawRect(rectF2, paint);

        //底部圆弧方形
        RectF rectF3 = new RectF(left, firstBottom + height, right, firstBottom + height + firstBottom - firstTop);
     //   paint.setColor(Color.BLUE);
     //   canvas.drawRect(rectF3, paint);
       // paint.setColor(Color.GRAY);
         canvas.drawArc(rectF3,0,180,true,paint);

    }
}
