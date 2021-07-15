package com.yan.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.yan.demo.bean.Circlebean;
import com.yan.demo.view.CircleBar;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CircleActivity extends Activity {
    private float[] values = {70f, 20f};
    private int[] color2 = {Color.parseColor("#F9B009"), Color.parseColor("#F9AB09"), Color.parseColor("#F07317")};
    private int[] color3 = {Color.parseColor("#0350FE"), Color.parseColor("#0186FF"), Color.parseColor("#02A7FF")};

    private CircleBar circleBar;
    private List<Integer> integers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*     CircleBar circleBar = new CircleBar(this);
        circleBar.setValueAndColors(values, color);
        circleBar.postInvalidate();
        setContentView(circleBar);*/


        setContentView(R.layout.circle);
        circleBar  =findViewById(R.id.circle);
        Circlebean circlebean=new Circlebean();
        circlebean.setCenterSize(90 + "件");
        circlebean.setCenterText("质检数量");
        List<Circlebean.CirclePartBean> circlePartBeanList = new ArrayList<>();


        Circlebean.CirclePartBean circlePartBean = new Circlebean.CirclePartBean();
        circlePartBean.setNumericalValue(values[0]);
        circlePartBean.setLineUptext(Math.round(values[0]) + "件");
        circlePartBean.setLineDowntext("不良率：" + Math.round(((values[0] / 90) * 100)) + "%");
        circlePartBean.setColors(color2);
        circlePartBeanList.add(circlePartBean);

        circlePartBean = new Circlebean.CirclePartBean();
        circlePartBean.setNumericalValue(values[1]);
        circlePartBean.setLineUptext(Math.round(values[1]) + "件");
        circlePartBean.setLineDowntext("合格率：" + Math.round(((values[1] / 90) * 100)) + "%");
        circlePartBean.setColors(color3);
        circlePartBeanList.add(circlePartBean);


        circlebean.setCirclePartBeanList(circlePartBeanList);


        circleBar.setCirclebean(circlebean);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
