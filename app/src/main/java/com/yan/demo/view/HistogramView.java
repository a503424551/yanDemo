package com.yan.demo.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.yan.demo.bean.ColumnBean;

import java.util.List;


public class HistogramView extends View {
    private float yValueMarn = 20;//Y轴刻度值距离左边的编辑
    private float yMaxValue;
    private float yLabelConut;//从0 开始表示3个
    private float yHeight;   //Y轴两点之间距离=整个宽度-左右两边的边距
    private int xLabelConut;//柱状总个数
    private float columnMarn = 20;// 柱状间隔
    private float columnWidth = 50;//柱状宽度
    private final int xTextMarnBottom = 20;//x轴距离底部长度
    private int marg = 50;//整个图表距离父控件长度
    private float indexY;
    private Paint  paintWiriteYX = new Paint(),//画xy轴画笔
            paintWiriteYLines = new Paint(),//画y轴刻度线画笔
            paintWiriteRect = new Paint(),//画柱形画笔
           paintWiriteXName = new Paint();;//画x轴名称画笔

    private int oneWidth;
    private List<ColumnBean> columnBeans;

    public void setXYData(float yMaxValue, float yLabelConut, int xLabelConut) {
        this.yMaxValue = yMaxValue;
        this.yLabelConut = yLabelConut;
        this.xLabelConut = xLabelConut;
    }

    public void setColumnBeans(List<ColumnBean> columnBeans) {
        this.columnBeans = columnBeans;
    }

    public HistogramView(Context context) {
        super(context);
        setBackgroundColor(Color.BLACK);//new 时调用
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);//加载布局时调用
    }

    public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (columnBeans == null) {
            return;
        }
        if(xLabelConut*(columnMarn+columnWidth)>getWidth()-marg*2){
            xLabelConut=((getWidth()-marg*2)/(int)(columnMarn+columnWidth));
            Toast.makeText(getContext(),"传入柱状总数过多，无法按照默认柱状宽度的间隔画出，" +
                    "顾按默认定义柱状宽度和间隔画最大柱状个数，",1).show();
        }

        paintXY(canvas);
        paintYLines(canvas);
        drawXRects(canvas);

    }

    private void drawXRects(Canvas canvas) {
        if (xLabelConut == 0) {
            Toast.makeText(getContext(), "请设置x轴显示柱状图个数", Toast.LENGTH_SHORT).show();
            return;
        }

        paintWiriteRect.setColor(Color.BLUE);
         paintWiriteXName.setColor(Color.WHITE);

        if ((columnMarn * xLabelConut + columnWidth * xLabelConut) > getWidth() - 2 * marg) {
            oneWidth = (getWidth() - 2 * marg) / xLabelConut;//一个柱状和柱状之间距离的总长度
            columnMarn = (columnMarn / (columnMarn + columnWidth)) * oneWidth;
            columnWidth = (columnWidth / (columnMarn + columnWidth)) * oneWidth;
        }
        for (int i = 1; i <= xLabelConut; i++) {

            float tem = columnBeans.get(i - 1).getColumnValue();//200刻度
            float tem1 = (tem / yMaxValue) * (getHeight() - marg * 2 - yHeight);//Y轴自上而下第一根线开始算,每根柱子长度

            float tem2 = (getHeight() - marg * 2 - yHeight) - tem1;//Y轴自上而下第一根线开始算，距离柱子的长度
            float tem3 = tem2 + yHeight + marg;//柱子的Y轴坐标
            canvas.drawRect(marg + columnMarn * i + columnWidth * (i - 1),
                    // tem
                    tem3
                    ,
                    marg + columnMarn * i + columnWidth * i,
                    getHeight() - marg, paintWiriteRect);

            canvas.drawText(tem + "", (marg + columnMarn * i + columnWidth * (i - 1)) + columnWidth / 4, tem3 - 5, paintWiriteXName);
            canvas.drawText(columnBeans.get(i - 1).getColumnName(), (marg + columnMarn * i + columnWidth * (i - 1)) + columnWidth / 4, getHeight() - marg + xTextMarnBottom, paintWiriteXName);

        }
    }

    private void paintYLines(Canvas canvas) {
        if (new Float(yLabelConut).intValue() == 0 || new Float(yMaxValue).intValue() == 0) {
            Toast.makeText(getContext(), "请设置Y轴刻度线条数或Y轴最大值", Toast.LENGTH_SHORT).show();
            return;
        }
        //Y轴刻度线自上向下画，第一条不画  所以y轴刻度线多画一条

        paintWiriteYLines.setStrokeWidth(2);
        paintWiriteYLines.setColor(Color.WHITE);
        yHeight = (getHeight() - marg * 2) / (yLabelConut + 1);//Y轴两点之间距离=整个宽度-左右两边的边距
        float yValue = yMaxValue / yLabelConut;
        //Y轴两点之间距离的刻度值

        for (int i = 0; i < yLabelConut + 1; i++) {
            if (i != 0) {
                paintWiriteYLines.setColor(Color.GRAY);
                canvas.drawLine(marg, yHeight * i + marg, getWidth() - marg, yHeight * i + marg, paintWiriteYLines);
                //画y轴相应的刻度值，自上而下，所以第一个实际是Y轴最后第二根刻度值。刻度值是自下而上看，(yLabelConut+1-i) 表示实际自下而上是画第几根刻度线
                indexY = yLabelConut + 1 - i;//总的Y轴刻度线-第几根Y轴刻度线（自上而下）=第几根刻度线（自下而上）
                canvas.drawText(new Float(yValue * indexY).intValue() + "", yValueMarn, getHeight() - marg - (yHeight * indexY), paintWiriteYLines);


            }

        }
    }

    private void paintXY(Canvas canvas) {

        //开始直方图绘制
        //我们首先绘制竖向的一条黑色直线
        paintWiriteYX.setColor(Color.WHITE);
        //线的粗细先设置一下
        paintWiriteYX.setStrokeWidth(3);
        //坐标系都是针对自己view的顶点坐标(注意不是屏幕坐标哦) 点的坐标 x轴的值是一样的 所以可以得知这肯定是一条笔直的竖线
        canvas.drawLine(marg, marg, marg, getHeight() - marg, paintWiriteYX);//y轴
        //然后依葫芦画瓢画一条横向的黑色直线 与上面这条竖着的直线 组成一个x轴和y轴
        canvas.drawLine(marg, getHeight() - marg, getWidth() - marg, getHeight() - marg, paintWiriteYX);

    }
}


