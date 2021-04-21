package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RightAngleProgress extends View {
    private int horizontalSize;
    private int cruuentPosition;
    //半径
    private float radius;
    //第一个圆x、y坐标
    private float firstCircleX;
    private float firstCircleY;
    //线长度
    private float lineLength = 100;
    private Paint paint = new Paint();
    private float startX;
    private float secondCireY;

    public RightAngleProgress(Context context) {
        super(context);
    }

    public RightAngleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RightAngleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHorizontalSize(int horizontalSize) {
        this.horizontalSize = horizontalSize;
    }

    public void setCruuentPosition(int cruuentPosition) {
        this.cruuentPosition = cruuentPosition;
        postInvalidate();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (cruuentPosition == 0) {
            return;
        }
        if (cruuentPosition > horizontalSize + 1) {
            Toast.makeText(getContext(), "传值在大于进度总和，请重新传入", Toast.LENGTH_SHORT).show();
            return;
        }
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);


        radius = 20;
        firstCircleX = 25;
        firstCircleY = 25;
            //如果整个布局高度小于 默认圆的直径+线长时,按比例缩小半径和线长,
         if (getHeight()-20 < firstCircleY + radius + lineLength) {
            lineLength = getHeight() / 2;
            firstCircleX = firstCircleY = lineLength / 4;
            radius = firstCircleX - 5;
        }


        //横向线路Y轴坐标
        secondCireY = firstCircleY + radius + lineLength;
        if (cruuentPosition > 0) {
            paint.setColor(Color.RED);

        }
        canvas.drawCircle(firstCircleX, firstCircleY, radius, paint);
        if (cruuentPosition >1) {
            paint.setColor(Color.RED);
        } else {
            paint.setColor(Color.BLACK);
        }
        canvas.drawLine(firstCircleX, firstCircleY + radius, firstCircleX, secondCireY, paint);//画第一个圆下的竖线

      //  开始画水平方向圈和线
        for (int i = 0; i < horizontalSize; i++) {
            if (cruuentPosition - 1 > i) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLACK);
            }

            if (i == 0) {
                canvas.drawLine(firstCircleX, secondCireY, firstCircleX + lineLength, secondCireY, paint);

                canvas.drawCircle(firstCircleX + lineLength + radius, secondCireY, radius, paint);
            } else {

                //记录上一套圈和线的x轴坐标（一条线和圈称为一套）
                startX = firstCircleX + lineLength * i + radius * 2 * i;
                canvas.drawLine(startX, secondCireY,
                        startX + lineLength, secondCireY, paint);
                canvas.drawCircle(startX + lineLength + radius, secondCireY, radius, paint);
            }

        }

    }
}
