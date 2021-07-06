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
    private float verticalTextButtomOffset = 10;
    private float verticalTextLeftOffset = 20;
    private int cylinderColorBlue = Color.parseColor("#1CCFFA");
    private int cylinderColorGray = Color.parseColor("#D3D3D3");
    private int buttomTextColor=Color.parseColor("#3FC282");
    private float cylinderWith = 50;
    private float arcRadius = cylinderWith / 2;
    private float cylinderMargin = 80;
    private float leftForIndex;
    private float rightForIndex;
    private float  bottomTextDownOffset = 35;

    private float yMaxValue=25;
    //柱状图表四周边距，x y轴标签除外
    private float marginTop = 80;
    private float marginLeft = 60;
    private float marginRight = 20;
    private float marginButtom = 100;
    //柱状图最大高度
    private float cylinderHight;
    //柱状图表两两条线间的高度
    private float linesHight;
    private float linesValue;
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
        cylinderHight=getHeight() - marginButtom - marginTop;
        linesHight = cylinderHight/ 5;
        linesValue=yMaxValue/5;

        paint.setAntiAlias(true);
        paint.setTextSize(30);
        //画顶部第一根坐标线
        paint.setColor(lineColor);
        canvas.drawLine(marginLeft, marginTop, getWidth() - marginRight, marginTop, paint);
        //画顶部第一根坐标值
        paint.setColor(verticalTextColor);

        canvas.drawText(verticalTexts[0] + "", verticalTextLeftOffset, marginTop + verticalTextButtomOffset, paint);

        //画剩余第坐标线和坐标值
        for (int i = 1; i < 6; i++) {
            paint.setColor(lineColor);
            canvas.drawLine(marginLeft, marginTop + linesHight * i, getWidth() - marginRight, marginTop + linesHight * i, paint);
            paint.setColor(verticalTextColor);
            canvas.drawText(verticalTexts[i] + "", verticalTextLeftOffset, marginTop + linesHight * i + verticalTextButtomOffset, paint);


        }


        paint.setStyle(Paint.Style.FILL);
        float [] tem=new float[]{ 8,15,18,4,20,22,};
        for (int j = 0; j < 5; j++) {
            paint.setColor(cylinderColorGray);
            leftForIndex = marginLeft + cylinderMargin * j + cylinderWith * j;
            rightForIndex = marginLeft + cylinderMargin * j + cylinderWith * j + cylinderWith;
            //循环画柱状图顶部半圆
            RectF rectF = new RectF(leftForIndex, marginTop,
                    rightForIndex, marginTop + cylinderWith);
            canvas.drawArc(rectF, -180, 180, true, paint);
            //循环画柱状图中间方形
            RectF rectF2 = new RectF(leftForIndex, marginTop + cylinderWith / 2,
                    rightForIndex,
                    getHeight() - marginButtom - arcRadius);
            canvas.drawRect(rectF2, paint);
            //循环画柱状图底部半圆
            RectF rectF3 = new RectF(leftForIndex, marginTop + linesHight * 4 + linesHight - cylinderWith, rightForIndex,
                    getHeight() - marginButtom);
            canvas.drawArc(rectF3, 0, 180, true, paint);

            paint.setColor(buttomTextColor);
            paint.setTextSize(35);
            canvas.drawText("3001",leftForIndex-verticalTextLeftOffset,getHeight()-marginButtom+ bottomTextDownOffset,paint);
            canvas.drawText("张大嘴",leftForIndex-verticalTextLeftOffset,getHeight()-marginButtom+   bottomTextDownOffset*2.2f ,paint);

            paint.setColor(cylinderColorBlue);
            //灰色部分高度
            float blueRectHight=cylinderHight-(tem[j]/yMaxValue*cylinderHight);

            RectF rectFBlueTop=new RectF(leftForIndex,marginTop+blueRectHight,rightForIndex,marginTop + cylinderWith+blueRectHight);
            canvas.drawArc(rectFBlueTop,-180,180,true,paint);

            RectF rectBlueMiddle=new RectF(leftForIndex,marginTop+blueRectHight+arcRadius,rightForIndex,getHeight()-marginButtom-arcRadius);
            canvas.drawRect(rectBlueMiddle,paint);

             canvas.drawArc(rectF3,0,180,true,paint);

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
