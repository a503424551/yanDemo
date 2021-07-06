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
    //左边垂直坐标文字X轴坐标
    private float verticalTextLeftOffset = 20;
    private int cylinderColorBlue = Color.parseColor("#1CCFFA");
    private int cylinderColorGray = Color.parseColor("#D3D3D3");
    private int buttomTextColor = Color.parseColor("#3FC282");
    private int topTextColor = Color.parseColor("#E89D23");
    //柱状图宽度
    private float cylinderWith = 50;
    //柱状图顶部半圆半径
    private float arcRadius = cylinderWith / 2;
    //柱状图间的间隔
    private float cylinderMargin = 80;
    //批量画柱状图时左边的X轴坐标
    private float leftForIndex;
    //批量画柱状图时右边边的X轴坐标
    private float rightForIndex;
    //柱状图底部标签文字相对X轴向下的偏移量
    private float bottomTextDownOffset = 35;

    private float yMaxValue = 25;
    //柱状图表四周边距，x y轴标签除外
    private float marginTop = 80;
    private float marginLeft = 60;
    private float marginRight = 20;
    private float marginButtom = 100;
    //柱状图最大高度
    private float cylinderLeng;
    //柱状图表两两条线间的高度
    private float linesHight;
    private float linesValue;
    //顶部标签距离视图顶部的Y轴坐标
    private int topTextOffset = 55;
    //从左往右数第一个柱状图距离Y轴便签的距离
    private int firstMarginLeft = 45;
    private float circleY;
    private float circleX;
    //前一个黄色圆的原点XY轴坐标
    private float frontCircley=0;
    private float frontCircleX=0;
    //黄色圆半径
    private float yellowCircleRadius = arcRadius / 2;


    public CylinderView(Context context) {
        super(context);
    }

    public CylinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         cylinderLeng = getHeight() - marginButtom - marginTop;
        linesHight = cylinderLeng / 5;
        linesValue = yMaxValue / 5;

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
        float[] tem = new float[]{8, 15, 18, 4, 20};
        float[] tem2 = new float[]{12.5f, 12.5f, 12.5f, 12.5f, 12.5f};
        for (int j = 0; j < 5; j++) {
            paint.setStyle(Paint.Style.FILL);//设置空心
            paint.setColor(cylinderColorGray);

            leftForIndex = marginLeft + cylinderMargin * j + cylinderWith * j + firstMarginLeft;
            rightForIndex = marginLeft + cylinderMargin * j + cylinderWith * j + cylinderWith + firstMarginLeft;
            //循环画灰色柱状图顶部半圆
            RectF rectF = new RectF(leftForIndex, marginTop,
                    rightForIndex, marginTop + cylinderWith);
            canvas.drawArc(rectF, -180, 180, true, paint);
            //循环灰色画柱状图中间方形
            RectF rectF2 = new RectF(leftForIndex, marginTop + cylinderWith / 2,
                    rightForIndex,
                    getHeight() - marginButtom - arcRadius);
            canvas.drawRect(rectF2, paint);
            //循环灰色画柱状图底部半圆
            RectF rectF3 = new RectF(leftForIndex, getHeight() - marginButtom - cylinderWith, rightForIndex,
                    getHeight() - marginButtom);
            canvas.drawArc(rectF3, 0, 180, true, paint);

            paint.setColor(buttomTextColor);
            paint.setTextSize(35);
            canvas.drawText("3001", leftForIndex - verticalTextLeftOffset, getHeight() - marginButtom + bottomTextDownOffset, paint);
            canvas.drawText("张大嘴", leftForIndex - verticalTextLeftOffset, getHeight() - marginButtom + bottomTextDownOffset * 2.2f, paint);

            paint.setColor(cylinderColorBlue);
            //灰色部分高度
            float blueRectHight = cylinderLeng - (tem[j] / yMaxValue * cylinderLeng);
            //循环画蓝色柱状图顶部半圆
            RectF rectFBlueTop = new RectF(leftForIndex, marginTop + blueRectHight, rightForIndex, marginTop + cylinderWith + blueRectHight);
            canvas.drawArc(rectFBlueTop, -180, 180, true, paint);
            //循环画蓝色画柱状图中间方形
            RectF rectBlueMiddle = new RectF(leftForIndex, marginTop + blueRectHight + arcRadius, rightForIndex, getHeight() - marginButtom - arcRadius);
            canvas.drawRect(rectBlueMiddle, paint);
            //循环画蓝色画柱状图底部半圆
            canvas.drawArc(rectF3, 0, 180, true, paint);
            //循环画X轴坐标标签
            paint.setColor(topTextColor);
            canvas.drawText(new Float(yMaxValue).intValue() + "", leftForIndex, topTextOffset, paint);

            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);//设置空心
            circleY = cylinderLeng - tem2[j] / yMaxValue * cylinderLeng + marginTop;
            circleX = leftForIndex + arcRadius;
            //循环画黄色圆之间的蓝色线
           if(j>0){
               canvas.drawLine(frontCircleX+ yellowCircleRadius,frontCircley,circleX-yellowCircleRadius,circleY,paint);
           }
             frontCircley=circleY;
             frontCircleX=circleX;
            //循环画黄色圆
            canvas.drawCircle(circleX, circleY, yellowCircleRadius, paint);

        }

    }

}
