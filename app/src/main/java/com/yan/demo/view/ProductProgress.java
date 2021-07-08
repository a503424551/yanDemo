package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ProductProgress extends View {
    private Paint paint = new Paint();
    private int greenColor = Color.parseColor("#3ABE7F");
    private int gouFirstPointX;
    private int gouFirstPointY;
    private int gouLengForFirstPointX;
    private int gouLengForFirstPointY;
    private int gouSecondPointXOffset;
    private int gouThirdPointY;
    private int gouLengForSecondPointXOffset;

    public ProductProgress(Context context) {
        super(context);
    }

    public ProductProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(6);
        paint.setColor(greenColor);
        //勾第一笔左上边点x轴坐标
        gouFirstPointX = 30;
        //勾第一笔左上边点Y轴坐标
        gouFirstPointY = 70;
        //√底部角度交叉点X轴距离左上边x坐标的x轴距离
        gouLengForFirstPointX = 20;
        //√底部角度交叉点Y轴距离左上边Y坐标的Y轴距离
        gouLengForFirstPointY = 20;
        //√第二笔左下边距离第一笔右下边的距离
        gouSecondPointXOffset = -5;
        //勾第二笔右上边点Y轴相对第一笔左上边点Y轴的距离
        gouThirdPointY = -30;
        //勾第二笔左上边点X轴距离底部角度交叉点X轴的距离
        gouLengForSecondPointXOffset = 50;
        canvas.drawLine(gouFirstPointX, gouFirstPointY, gouFirstPointX + gouLengForFirstPointX, gouLengForFirstPointY + gouFirstPointY, paint);
    canvas.drawLine(gouFirstPointX + gouLengForFirstPointX +gouSecondPointXOffset, gouFirstPointY + gouLengForFirstPointY, gouFirstPointX + gouLengForFirstPointX + gouLengForSecondPointXOffset, gouFirstPointY+gouThirdPointY, paint);
        //   canvas.drawLine(gouFirstPointX+gouLengForX+gouFirstPointY+gouLengForY,65,30+50-30+90-45+120,65,paint);
        //  canvas.drawLine(30+50-30+90-45,65,30+50-30+90-45+120,65,paint);
    }
}
