package com.yan.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CircleBar extends View {
    //圆起始点0度位置为3点钟位置  https://www.jianshu.com/p/daf009dd04a5
    //传入值总计
    private float total;
    /*   private int[] color = {Color.GREEN, Color.BLUE,Color.RED};
       private float[] values = {30f, 60f,120};*/
    private int[] colors;
    private float[] values;
    //已经画好的传入值占据圆环的度数
    private float[] sweepAngle;
    private Paint paint = new Paint();
    //圆环间的间隔
    private float mar = 5;
    private float alreadSweepAngle;

    public CircleBar(Context context) {
        super(context);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setValueAndColors(float[] values, int[] colors) {
        this.values = values;
        this.colors = colors;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(getClass().getSimpleName(), "onDraw==");
        if (colors.length != values.length) {
            Toast.makeText(getContext(), "请传入相对应的数值和颜色值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (values == null) {
            Toast.makeText(getContext(), "请传入相对应的数值", Toast.LENGTH_SHORT).show();
            return;
        }
        sweepAngle = new float[values.length];
        //计算圆环显示的的总数值
        if (total == 0.0f) {
            for (float f : values) {
                total += f;
            }
        }

        /*
         *  计算每部分圆环的弧度
         *  (360 - mar * values.length)：整个圆360度-圆环之间间隔的总弧度
         *  values[i] / total   每一部分圆环占整个圆的度数
         * */

        for (int i = 0; i < values.length; i++) {

            sweepAngle[i] = values[i] / total * (360 - mar * values.length);

        }


        //采用默认设置创建一个画笔
        //   paint.setPathEffect(new DashPathEffect(new float[]{14, 24}, 0));
        paint.setAntiAlias(true);//使用抗锯齿功能
        paint.setStyle(Paint.Style.STROKE);//设置画笔类型为填充
        paint.setStrokeWidth(20);

        //计算中心坐标
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF rectf_head = new RectF(x, y,
                getWidth() - x, getHeight() - y);


        //防止从重桌面返回，又开叠加
        alreadSweepAngle = 0.0f;
        /* 循环画圆弧，
         * -90  表示逆时针方向从0点位置开始画
         * alreadSweepAngle + mar * j     已经画好的弧度加上圆弧之间的间隔弧度
         * weepAngle[j]          需要画圆弧的度数
         * */
        for (int j = 0; j < values.length; j++) {
            paint.setColor(colors[j]);
            canvas.drawArc(rectf_head, -90 + alreadSweepAngle + mar * j, sweepAngle[j], false, paint);//绘制圆弧，不含圆心
            alreadSweepAngle += sweepAngle[j];

        }

    }
}
