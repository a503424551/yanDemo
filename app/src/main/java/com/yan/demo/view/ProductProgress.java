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
    private int gouSecondPointYOffset;
    private int gouLengForSecondPointXOffset;
    private int lineLeng;

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
        //√底部角度交叉点X轴距离 勾第一笔左上边点x轴坐标的x轴距离
        gouLengForFirstPointX = 20;
        //√底部角度交叉点Y轴距离勾第一笔左上边点Y坐标的Y轴距离
        gouLengForFirstPointY = 20;
        //√第二笔左下边X轴距离第一笔右下边X轴的距离
        gouSecondPointXOffset = 5;
        //勾第二笔右上边点Y轴相对第一笔左上边点Y轴的距离
        gouSecondPointYOffset = 30;
        //勾第二笔右上边点X轴距离底部角度交叉点X轴的距离
        gouLengForSecondPointXOffset = 50;
        //直线长
        lineLeng = 220;
        //直线距离勾的长度
        float lineMarginGou = 50;
        canvas.drawLine(gouFirstPointX, gouFirstPointY, gouFirstPointX + gouLengForFirstPointX, gouLengForFirstPointY + gouFirstPointY, paint);
        canvas.drawLine(gouFirstPointX + gouLengForFirstPointX - gouSecondPointXOffset, gouFirstPointY + gouLengForFirstPointY,
                gouFirstPointX + gouLengForFirstPointX + gouLengForSecondPointXOffset, gouFirstPointY - gouSecondPointYOffset, paint);
        canvas.drawLine(gouFirstPointX + gouLengForFirstPointX + gouLengForSecondPointXOffset + lineMarginGou, (gouFirstPointY + gouLengForFirstPointY) / 2 + gouSecondPointYOffset,
                (gouFirstPointX + gouLengForFirstPointX + gouLengForSecondPointXOffset) + lineLeng + lineMarginGou, (gouFirstPointY + gouLengForFirstPointY) / 2 + gouSecondPointYOffset, paint);

    }
}
