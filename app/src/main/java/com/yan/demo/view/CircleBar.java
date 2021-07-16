package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.yan.demo.bean.Circlebean;


public class CircleBar extends View {
    //圆起始点0度位置为3点钟位置  https://www.jianshu.com/p/daf009dd04a5
    //传入值总计
    private float total;

    //已经画好的传入值占据圆环的度数
    private float[] sweepAngle;
    private Paint paint = new Paint();
    private Paint centerPaint = new Paint();
    //圆环间的间隔
    private float mar = 0;
    private float alreadSweepAngle;
    private float lineLeng = 300;
    private float textOffsetForY = 10;
    private int centerUpColor = Color.parseColor("#E93E4C");
    private int centerDowColor = Color.parseColor("#E93948");
    private Circlebean circlebean;
    private float firstLineLengForX;
    private float firstLineLengForY;
    private String lineUptext;
    private String lineDowntext;
    private float lineUpTextStartForX;
    private float lineDowTextStartForX;
    private float downTextLineOutWidth;
    private int centerTextOffsetForX;
    private int centerSizeOffsetForX;
    private int centerTextOffsetForY;
    private int centerSizeOffsetForY;
    private Path path;
    private float[] coords;
    private int crevice;

    public CircleBar(Context context) {
        super(context);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCirclebean(Circlebean circlebean) {
        this.circlebean = circlebean;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(getClass().getSimpleName(), "onDraw==");

        if (circlebean == null) {
            //     Toast.makeText(getContext(), "请传入相对应", Toast.LENGTH_SHORT).show();
            return;
        }
        sweepAngle = new float[circlebean.getCirclePartBeanList().size()];
        //计算圆环显示的的总数值
        if (total == 0.0f) {
            for (Circlebean.CirclePartBean circlePartBean : circlebean.getCirclePartBeanList()) {
                total += circlePartBean.getNumericalValue();
            }
        }

        /*
         *  计算每部分圆环的弧度
         *  (360 - mar * values.length)：整个圆360度-圆环之间间隔的总弧度
         *  values[i] / total   每一部分圆环占整个圆的度数
         * */

        for (int i = 0; i < circlebean.getCirclePartBeanList().size(); i++) {
            sweepAngle[i] = circlebean.getCirclePartBeanList().get(i).getNumericalValue() / total * (360 - mar * circlebean.getCirclePartBeanList().size());
        }

        //采用默认设置创建一个画笔

        paint.setAntiAlias(true);//使用抗锯齿功能
        paint.setStyle(Paint.Style.STROKE);//

        float diameter = getWidth() / 3;

        RectF rectF = new RectF(diameter, diameter, diameter * 2, diameter * 2);

        darwCenterText(canvas, rectF);
        alreadSweepAngle = 0.0f;

        //圆弧开始画的起点XY轴坐标
        float startDrawX = diameter + diameter / 2;
        float startDrawy = diameter;
        /* 循环画圆弧，
         * -90  表示逆时针方向从0点位置开始画
         * alreadSweepAngle + mar * j     已经画好的弧度加上圆弧之间的间隔弧度
         * weepAngle[j]          需要画圆弧的度数
         * */
        for (int j = 0; j < circlebean.getCirclePartBeanList().size(); j++) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(90);
            path = new Path();
            path.addArc(rectF, -90 + alreadSweepAngle + mar * j, sweepAngle[j]);
            PathMeasure measure = new PathMeasure(path, false);
            coords = new float[]{0f, 0f};
            measure.getPosTan(measure.getLength(), coords, null);
            LinearGradient linearGradient = new LinearGradient(startDrawX, startDrawy, coords[0], coords[1],
                    circlebean.getCirclePartBeanList().get(j).getColors(), null, Shader.TileMode.CLAMP);
            paint.setShader(linearGradient);
            //补充每部分圆弧之间的缝隙
            crevice = 5;
            canvas.drawArc(rectF, -90 + alreadSweepAngle + mar * j, sweepAngle[j]+ crevice, false, paint);//绘制圆弧，不含圆心
            startDrawX = coords[0];
            startDrawy = coords[1];
            drawLineAndText(canvas, rectF, j);
            alreadSweepAngle += sweepAngle[j];
        }

    }

    private void drawLineAndText(Canvas canvas, RectF rectF, int j) {
        PathMeasure measure;
        path = new Path();
        path.addArc(rectF, -90 + alreadSweepAngle + mar * j, sweepAngle[j] / 2);
        measure = new PathMeasure(path, false);
        coords = new float[]{0f, 0f};
        measure.getPosTan(measure.getLength(), coords, null);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(55);
        //描述第一根线X轴的偏移量
        firstLineLengForX = 100;
        //描述第一根线Y轴的偏移量
        firstLineLengForY = 100;
        lineUptext = circlebean.getCirclePartBeanList().get(j).getLineUptext();
        lineDowntext = circlebean.getCirclePartBeanList().get(j).getLineDowntext();
        lineUpTextStartForX = Math.abs((lineLeng - paint.measureText(lineUptext)) / 2);
        lineDowTextStartForX = Math.abs((lineLeng - paint.measureText(lineDowntext))) / 2;

        if (coords[1] > rectF.centerY()) {
            //占比描述在下半圆
            if (coords[0] > rectF.centerX()) {
                //占比描述在下半圆右边
                canvas.drawLine(coords[0], coords[1], coords[0] + firstLineLengForX, coords[1] + firstLineLengForY, paint);
                canvas.drawLine(coords[0] + firstLineLengForX, coords[1] + firstLineLengForY, coords[0] + firstLineLengForX + lineLeng, coords[1] + firstLineLengForY, paint);
                canvas.drawText(lineUptext, lineUpTextStartForX + coords[0] + firstLineLengForX, coords[1] + firstLineLengForY - textOffsetForY, paint);

                //下面文字超出屏幕宽度数
                downTextLineOutWidth = coords[0] + firstLineLengForX + paint.measureText(lineDowntext) + lineDowTextStartForX - getWidth();
                if (downTextLineOutWidth > 0) {
                    canvas.drawText(lineDowntext, lineDowTextStartForX + coords[0] + firstLineLengForX - downTextLineOutWidth, coords[1] + firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);
                } else {
                    canvas.drawText(lineDowntext, lineDowTextStartForX + coords[0] + firstLineLengForX, coords[1] + firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);
                }
            } else {
                //占比描述在下半圆左边
                canvas.drawLine(coords[0], coords[1], coords[0] - firstLineLengForX, coords[1] + firstLineLengForY, paint);
                canvas.drawLine(coords[0] - firstLineLengForX, coords[1] + firstLineLengForY,
                        coords[0] - firstLineLengForX - lineLeng, coords[1] + firstLineLengForY, paint);
                canvas.drawText(lineUptext, coords[0] - firstLineLengForX - lineUpTextStartForX - paint.measureText(lineUptext), coords[1] + firstLineLengForY - textOffsetForY, paint);
                //下面文字超出屏幕宽度数
                downTextLineOutWidth = coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext);
                //paint.measureText(lineDowntext)  文字从左往右写 所以左边描述文字开始X轴坐标需要往左前移描述文字的长度
                if (downTextLineOutWidth < 0) {
                    canvas.drawText(lineDowntext, coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext) - downTextLineOutWidth, coords[1] + firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);

                } else {
                    canvas.drawText(lineDowntext, coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext), coords[1] + firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);

                }
            }

        } else {
            //占比描述在上半圆
            if (coords[0] > rectF.centerX()) {
                //占比描述在上半圆右边
                canvas.drawLine(coords[0], coords[1], coords[0] + firstLineLengForX, coords[1] - firstLineLengForY, paint);
                canvas.drawLine(coords[0] + firstLineLengForX, coords[1] - firstLineLengForY, coords[0] + firstLineLengForX + lineLeng, coords[1] - firstLineLengForY, paint);
                canvas.drawText(lineUptext, coords[0] + firstLineLengForX + lineUpTextStartForX, coords[1] - firstLineLengForY - textOffsetForY, paint);
                downTextLineOutWidth = coords[0] + firstLineLengForX + lineDowTextStartForX + paint.measureText(lineDowntext) - getWidth();


                if (downTextLineOutWidth > 0) {
                    canvas.drawText(lineDowntext, coords[0] + firstLineLengForX + lineDowTextStartForX - downTextLineOutWidth, coords[1] - firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);

                } else {
                    canvas.drawText(lineDowntext, coords[0] + firstLineLengForX + lineDowTextStartForX, coords[1] - firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);

                }
            } else {
                //占比描述在上半圆左边
                canvas.drawLine(coords[0], coords[1], coords[0] - firstLineLengForX, coords[1] - firstLineLengForY, paint);
                canvas.drawLine(coords[0] - firstLineLengForX, coords[1] - firstLineLengForY, coords[0] - firstLineLengForX - lineLeng, coords[1] - firstLineLengForY, paint);
                canvas.drawText(lineUptext, coords[0] - firstLineLengForX - lineUpTextStartForX - paint.measureText(lineUptext),
                        coords[1] - firstLineLengForY - textOffsetForY, paint);

                downTextLineOutWidth = coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext);
                //paint.measureText(lineDowntext)  文字从左往右写 所以左边描述文字开始X轴坐标需要往左前移描述文字的长度
                if (downTextLineOutWidth > 0) {
                    canvas.drawText(lineDowntext, coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext),
                            coords[1] - firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);
                } else {
                    canvas.drawText(lineDowntext, coords[0] - firstLineLengForX - lineDowTextStartForX - paint.measureText(lineDowntext) - downTextLineOutWidth,
                            coords[1] - firstLineLengForY + textOffsetForY + paint.getTextSize(), paint);
                }

            }

        }
    }

    private void darwCenterText(Canvas canvas, RectF rectF) {
        centerPaint.setTextSize(50);
        centerPaint.setColor(centerUpColor);
        //中心描述文字相对原点往左偏移量
        centerTextOffsetForX = 90;
        //中心描述文字相对原点往上偏移量
        centerTextOffsetForY = 30;
        canvas.drawText(circlebean.getCenterText(), rectF.centerX() - centerTextOffsetForX, rectF.centerY() - centerTextOffsetForY, centerPaint);
        centerPaint.setColor(centerDowColor);
        centerPaint.setTextSize(55);
        //中心数量文字相对原点往左偏移量
        centerSizeOffsetForX = 60;
        //中心数量文字相对原点往上偏移量
        centerSizeOffsetForY = 20;
        canvas.drawText(circlebean.getCenterSize(), rectF.centerX() - centerSizeOffsetForX, rectF.centerY() + centerPaint.getTextSize() - centerTextOffsetForY + centerSizeOffsetForY, centerPaint);
    }
}
