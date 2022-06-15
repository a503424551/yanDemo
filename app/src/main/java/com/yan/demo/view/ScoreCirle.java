package com.yan.demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class ScoreCirle extends View {
    private Paint paint=new Paint();

    private float value=0;
    private String text;
    public void setCurrentData(int value,String text) {
        this.value = value;
        this.text=text;
     setAnimation(0,value,2000);

    }

    public ScoreCirle(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    public ScoreCirle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);


    }

    public ScoreCirle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(value!=0){
         //大圆
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            float centerX=getWidth()/2;
            float centerY=getHeight()/2;
            float mar=getWidth()*0.2f;
            //除以高度 得出百分比
            float bigRadius=(getHeight()-20)/2;//-20  预留阴影宽度
            float smallRadius=getHeight()*0.43f;
            canvas.drawCircle(centerX,centerY,bigRadius,paint);

            paint.reset();
            paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER));//阴影宽度
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(Color.parseColor("#999999"));
            canvas.drawCircle(centerX,centerY,bigRadius,paint);



            //安全指数   文字Y轴距离都是以文字距离圆心长度 除以高度比例  计算得来
            // 以下文字大小都是高度比例计算
            RectF rectF = new RectF(centerX - bigRadius, centerY - bigRadius,
                    centerX + bigRadius, smallRadius + bigRadius);

            paint.reset();
            paint.setTextSize(getHeight()*0.06f);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(Color.parseColor("#999999"));
            String conten="安全指数";
            //距离=（大方形顶部距离字体底部长度/高度）*高度
            canvas.drawText(conten,centerX-paint.measureText(conten)/2,rectF.top+getHeight()*0.24f,paint);
            //分数值

            paint.reset();
            paint.setTextSize(getHeight()*0.23f);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(Color.parseColor("#999999"));


            canvas.drawText(Math.round(value)+"",centerX-paint.measureText(Math.round(value)+"")/2,rectF.top+getHeight()*0.59f,paint);

            //评级
            paint.reset();

            paint.setTextSize(getHeight()*0.09f);
            paint.setStyle(Paint.Style.FILL);

            paint.setAntiAlias(true);
            paint.setColor(Color.parseColor("#4CD423"));

            canvas.drawText(text,centerX-paint.measureText(text)/2,rectF.top+getHeight()*0.75f,paint);



            paint.reset();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.parseColor("#4CD423"));
            paint.setStrokeWidth(getHeight()*0.04f);
            rectF = new RectF(centerX - smallRadius, centerY - smallRadius, centerX + smallRadius, smallRadius + centerY);
            canvas.drawArc(rectF,-90,(value/100f)*360f,false,paint);

        }

    }

    private void setAnimation(float startAngle, float currentAngle , int time) {
//绘制当前数据对应的圆弧的动画效果
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(startAngle, currentAngle);
        progressAnimator.setDuration(time);
        progressAnimator.setTarget(value);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
//重新绘制，不然不会出现效果
                postInvalidate();
            }
        });
//开始执行动画
        progressAnimator.start();

    }
}
