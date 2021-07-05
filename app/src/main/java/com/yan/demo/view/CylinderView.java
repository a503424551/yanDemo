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


    private int arcRadius;

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
        //顶部画圆弧方形的左边距离app左边
        left = 100;
        //顶部画圆弧方形的顶部距离视图顶部的距离
        firstTop = 50;
        //顶部画圆弧方形的右边距离app左边
        right = 160;
        //顶部画圆弧方形的底部距离视图顶部的距离
        firstBottom = 100;
        //中间柱形高度
        height = 300;
        //圆弧半径
        arcRadius = (firstBottom - firstTop) / 2;
        RectF rectF = new RectF(left, firstTop, right, firstBottom);
     //   canvas.drawRect(rectF, paint);

        //顶部圆弧
        paint.setColor(ContextCompat.getColor(getContext(),R.color.tem));
        paint.setStyle(Paint.Style.FILL);
          canvas.drawArc(rectF,-180,180,true,paint);

        //中间柱形
       // paint.setColor(Color.RED);

        RectF rectF2 = new RectF(left, firstBottom- arcRadius, right, firstBottom + height+arcRadius);
        canvas.drawRect(rectF2, paint);

        //底部圆弧方形
        RectF rectF3 = new RectF(left, firstBottom + height, right, firstBottom + height + firstBottom - firstTop);
     //   paint.setColor(Color.BLUE);
     //   canvas.drawRect(rectF3, paint);
       // paint.setColor(Color.GRAY);
         canvas.drawArc(rectF3,0,180,true,paint);

    }
}
