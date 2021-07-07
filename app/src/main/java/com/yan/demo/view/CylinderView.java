package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yan.demo.bean.CylinderBean;

import java.util.ArrayList;
import java.util.List;

public class CylinderView extends View {

    private List<CylinderBean> cylinderBeans;
    private int lineColor = Color.parseColor("#D6D6D6");
    private int verticalTextColor = Color.parseColor("#F9696A");
    private Paint paint = new Paint();
   private Paint ylineTextPaint = new Paint();
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
    private float frontCircley = 0;
    private float frontCircleX = 0;
    //黄色圆半径
    private float yellowCircleRadius = arcRadius / 2;
    private float tem;
    private float tem1;
    private float tem2;
    private RectF rectGraybuttom;


    public CylinderView(Context context) {
        super(context);
    }

    public CylinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CylinderView setCylinderBeans(List<CylinderBean> cylinderBeans) {
        this.cylinderBeans = cylinderBeans;

        return this;
    }

    public CylinderView setyMaxValue(float yMaxValue) {
        this.yMaxValue = yMaxValue;

        return this;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cylinderLeng = getHeight() - marginButtom - marginTop;
        linesHight = cylinderLeng / 5;
        linesValue = yMaxValue / 5;
        darwYlineText(canvas);
        paint.setStyle(Paint.Style.FILL);
        for (int j = 0; j < cylinderBeans.size(); j++) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(cylinderColorGray);
            leftForIndex = marginLeft + cylinderMargin * j + cylinderWith * j + firstMarginLeft;
            rightForIndex = marginLeft + cylinderMargin * j + cylinderWith * j + cylinderWith + firstMarginLeft;
            drawGrayCylinder(canvas);
            drawButtomText(canvas, j);
            if(cylinderBeans.get(j).getBlueValue()>0){
                drawBlueCylinder(canvas, j);
            }

            //循环画X轴顶部坐标标签
            paint.setColor(topTextColor);
            canvas.drawText(new Float(yMaxValue).intValue() + "", leftForIndex, topTextOffset, paint);
            drawyellowCircle(canvas, j);

        }


    }

    private void drawyellowCircle(Canvas canvas, int j) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);//设置空心
        circleY = cylinderLeng - cylinderBeans.get(j).getYellowValue() / yMaxValue * cylinderLeng + marginTop;
        circleX = leftForIndex + arcRadius;
        //循环画黄色圆之间的蓝色线
        if (j > 0) {
            canvas.drawLine(frontCircleX + yellowCircleRadius, frontCircley, circleX - yellowCircleRadius, circleY, paint);
        }
        frontCircley = circleY;
        frontCircleX = circleX;
        //循环画黄色圆
        canvas.drawCircle(circleX, circleY, yellowCircleRadius, paint);
    }

    private void drawBlueCylinder(Canvas canvas, int j) {
        paint.setColor(cylinderColorBlue);
        Log.d(getClass().getSimpleName(), "cylinderBeans.get(from).getBlueValue()==" + cylinderBeans.get(j).getBlueValue());
        //灰色部分高度
        float blueRectHight = cylinderLeng - (cylinderBeans.get(j).getBlueValue() / yMaxValue * cylinderLeng);
        //循环画蓝色柱状图顶部半圆
        RectF rectFBlueTop = new RectF(leftForIndex, marginTop + blueRectHight, rightForIndex, marginTop + cylinderWith + blueRectHight);
        canvas.drawArc(rectFBlueTop, -180, 180, true, paint);
        //循环画蓝色画柱状图中间方形
        RectF rectBlueMiddle = new RectF(leftForIndex, marginTop + blueRectHight + arcRadius, rightForIndex, getHeight() - marginButtom - arcRadius);
        canvas.drawRect(rectBlueMiddle, paint);
        //循环画蓝色画柱状图底部半圆
        canvas.drawArc(rectGraybuttom, 0, 180, true, paint);
    }

    private void drawButtomText(Canvas canvas, int j) {
        paint.setColor(buttomTextColor);
        paint.setTextSize(35);
        canvas.drawText(cylinderBeans.get(j).getNo() + "", leftForIndex - verticalTextLeftOffset, getHeight() - marginButtom + bottomTextDownOffset, paint);
        canvas.drawText(cylinderBeans.get(j).getName(), leftForIndex - verticalTextLeftOffset, getHeight() - marginButtom + bottomTextDownOffset * 2.2f, paint);
    }

    private void drawGrayCylinder(Canvas canvas) {
        //循环画灰色柱状图顶部半圆
        RectF rectGrayTop = new RectF(leftForIndex, marginTop,
                rightForIndex, marginTop + cylinderWith);
        canvas.drawArc(rectGrayTop, -180, 180, true, paint);
        //循环画灰色柱状图中间方形
        RectF rectGrayMiddle = new RectF(leftForIndex, marginTop + cylinderWith / 2,
                rightForIndex,
                getHeight() - marginButtom - arcRadius);
        canvas.drawRect(rectGrayMiddle, paint);
        //循环画灰色柱状图底部半圆
        rectGraybuttom = new RectF(leftForIndex, getHeight() - marginButtom - cylinderWith, rightForIndex,
                getHeight() - marginButtom);
        canvas.drawArc(rectGraybuttom, 0, 180, true, paint);
    }

    private void darwYlineText(Canvas canvas) {
        ylineTextPaint.setAntiAlias(true);
        ylineTextPaint.setTextSize(30);
        //画顶部Y轴第一根坐标线
        ylineTextPaint.setColor(lineColor);
        canvas.drawLine(marginLeft, marginTop, getWidth() - marginRight, marginTop, ylineTextPaint);
        //画顶部Y轴第一根坐标值
        ylineTextPaint.setColor(verticalTextColor);
        int start=new Float(  yMaxValue/5).intValue();
        canvas.drawText(new Float(yMaxValue).intValue()+ "", verticalTextLeftOffset, marginTop + verticalTextButtomOffset, ylineTextPaint);


        //画剩余Y轴的坐标线和坐标值
        for (int i = 1; i < 6; i++) {
            ylineTextPaint.setColor(lineColor);
            canvas.drawLine(marginLeft, marginTop + linesHight * i, getWidth() - marginRight, marginTop + linesHight * i, ylineTextPaint);
            ylineTextPaint.setColor(verticalTextColor);
            canvas.drawText( new Float(yMaxValue).intValue()-start*i+ "", verticalTextLeftOffset, marginTop + linesHight * i + verticalTextButtomOffset, ylineTextPaint);

        }
    }


}
