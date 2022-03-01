package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {
    private Paint paint = new Paint();
    private float viewMarg;//线和圆的距离
    private float textMarg;//文字和圆的距离
    private float radius;
    private int lineLeng;
    private float secondCirleX;
    private int currentIndex;
    private int textSize;
     private float startX;
    private float thireClireStartX;
    private float smallCirleRadius;
    private float bigToSmallMar;
    private float twoSmallCirleMar;
    private float leftSmallCirleStartX;
    private boolean isMoreThree;
    private String [] stepsName;

    public void setStepsName(String[] stepsName) {
        this.stepsName = stepsName;
    }

    public void setMoreThree(boolean moreThree) {
        isMoreThree = moreThree;
    }

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public ProgressView setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;

        postInvalidate();
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        if(stepsName==null){
            return;
        }

        radius = new Double(getHeight() * 0.09).floatValue();
        textMarg = new Double(getHeight() * 0.09).floatValue();
        textSize = new Double(getHeight() * 0.15).intValue();

        viewMarg = new Double(getWidth() * 0.007).floatValue();
        lineLeng = new Double(getWidth() * 0.08).intValue();

        smallCirleRadius = new Double(getHeight() * 0.06).floatValue();//小圆的半径
        bigToSmallMar = smallCirleRadius * 4;//大圆和小圆的间距
        twoSmallCirleMar = smallCirleRadius * 1.3f;//两小圆的间距离
        //头部三点
        leftSmallCirleStartX = (startX - radius) - (smallCirleRadius * 2 * 3 + bigToSmallMar + twoSmallCirleMar * 2);


        super.draw(canvas);
        paint.setColor(Color.parseColor("#2D71A2"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

        paint.setTextSize(textSize);

        startX = (new Float(getWidth()).floatValue() - radius * 2 * 3 - lineLeng * 2 - viewMarg * 4) / 2 + radius;
        if(isMoreThree){
            if(currentIndex!=0 ){

                for (int i = 0; i < 3; i++) {
                    canvas.drawCircle(leftSmallCirleStartX + twoSmallCirleMar * i + smallCirleRadius * 2 * i, getHeight() / 2, smallCirleRadius, paint);
                }

            }
        }



        canvas.drawCircle(startX, getHeight() / 2, radius, paint);
        if (currentIndex == 0) {
            canvas.drawText(stepsName[0], startX - paint.measureText(stepsName[currentIndex]) / 2,
                    getHeight() / 2 + radius + paint.getTextSize() + textMarg, paint);
        }

        //第一个线
        canvas.drawLine(startX + radius + viewMarg, getHeight() / 2,
                startX + radius + viewMarg + lineLeng, getHeight() / 2, paint);

        //第二个圆 文字
        secondCirleX = startX + radius + viewMarg + lineLeng + viewMarg + radius;
        canvas.drawCircle(secondCirleX
                , getHeight() / 2, radius, paint);
        if ( currentIndex<stepsName.length-1&&currentIndex>0) {
            canvas.drawText(stepsName[currentIndex], secondCirleX - paint.measureText(stepsName[currentIndex]) / 2,
                    getHeight() / 2 + radius + paint.getTextSize() + textMarg, paint);
        }
        //第二个线
        canvas.drawLine(secondCirleX + radius + viewMarg, getHeight() / 2,
                secondCirleX + radius + viewMarg + lineLeng, getHeight() / 2, paint);
        //第三个圆 文字
        thireClireStartX = secondCirleX + radius + viewMarg + lineLeng + viewMarg + radius;
        canvas.drawCircle(thireClireStartX,
                getHeight() / 2, radius, paint);
        if (currentIndex ==stepsName.length-1) {
            canvas.drawText(stepsName[currentIndex], thireClireStartX -
                            paint.measureText(stepsName[currentIndex]) / 2, getHeight() / 2 + radius + paint.getTextSize() + textMarg,
                    paint
            );
        }
        if(isMoreThree) {

            if (currentIndex+1 !=stepsName.length) {
                float rightSmallCirleStartX = (thireClireStartX + radius) + bigToSmallMar;
                for (int i = 0; i < 3; i++) {
                    canvas.drawCircle(rightSmallCirleStartX + twoSmallCirleMar * i + smallCirleRadius * 2 * i, getHeight() / 2, smallCirleRadius, paint);
                }
            }
        }


    }
}
