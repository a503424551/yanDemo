package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CylinderView extends View {


    private int lineColor = Color.parseColor("#D6D6D6");
    private int verticalTextColor = Color.parseColor("#F9696A");
    private Paint paint = new Paint();
    private int[] verticalTexts = new int[]{25, 20, 15, 10, 5, 0};
    //左边垂直坐标文字向下偏移量
    private int verticalTextButtomoffset = 10;

    public CylinderView(Context context) {
        super(context);
    }

    public CylinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // demo(canvas);

        float marginTop = 80;
        float marginLeft = 60;
        float marginRight = 20;
        float marginButtom = 100;
        float linesHight = (getHeight() - marginButtom - marginTop) / 5;

        paint.setAntiAlias(true);
        paint.setTextSize(30);
        //画顶部第一根坐标线
        paint.setColor(lineColor);
        canvas.drawLine(marginLeft, marginTop, getWidth() - marginRight, marginTop, paint);
        //画顶部第一根坐标值
        paint.setColor(verticalTextColor);
        canvas.drawText(verticalTexts[0] + "", 20, marginTop + verticalTextButtomoffset, paint);
        //画剩余第坐标线和坐标值
        for (int i = 1; i < 6; i++) {
            paint.setColor(lineColor);
            canvas.drawLine(marginLeft, marginTop + linesHight * i, getWidth() - marginRight, marginTop + linesHight * i, paint);
            paint.setColor(verticalTextColor);
            canvas.drawText(verticalTexts[i] + "", 20, marginTop + linesHight * i + verticalTextButtomoffset, paint);
        }
    }

    private void demo(Canvas canvas) {

        //顶部画圆弧方形的左边距离app左边
        float left = 100;
        //顶部画圆弧方形的顶部距离视图顶部的距离
        float firstTop = 50;
        //顶部画圆弧方形的右边距离app左边
        float right = 160;
        //顶部画圆弧方形的底部距离视图顶部的距离
        float firstBottom = 100;
        //中间柱形高度
        float height = 300;
        float arcRadius;

        //圆弧半径
        arcRadius = (firstBottom - firstTop) / 2;
        RectF rectF = new RectF(left, firstTop, right, firstBottom);
        //   canvas.drawRect(rectF, paint);

        //顶部圆弧
        // paint.setColor(ContextCompat.getColor(getContext(),R.color.tem));
        paint.setColor(Color.parseColor("#1CCFFA"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, -180, 180, true, paint);

        //中间柱形
        // paint.setColor(Color.RED);

        RectF rectF2 = new RectF(left, firstBottom - arcRadius, right, firstBottom + height + arcRadius);
        canvas.drawRect(rectF2, paint);

        //底部圆弧方形
        RectF rectF3 = new RectF(left, firstBottom + height, right, firstBottom + height + firstBottom - firstTop);
        //   paint.setColor(Color.BLUE);
        //   canvas.drawRect(rectF3, paint);
        // paint.setColor(Color.GRAY);
        canvas.drawArc(rectF3, 0, 180, true, paint);
    }
}
