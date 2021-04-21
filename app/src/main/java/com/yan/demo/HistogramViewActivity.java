package com.yan.demo;

import android.app.Activity;
import android.os.Bundle;


import com.yan.demo.bean.ColumnBean;
import com.yan.demo.view.HistogramView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HistogramViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 /*  HistogramView histogramView =new HistogramView(this);
       getHistogramView(histogramView);
        setContentView(histogramView); */


        setContentView(R.layout.histogram_view);
        HistogramView histogramView = findViewById(R.id.hv);
        getHistogramView(histogramView);
    }


    private void getHistogramView(HistogramView histogramView) {

        List<ColumnBean> columnBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ColumnBean columnBean = new ColumnBean(new Float(new Random().nextInt(1200)), "华为");
            columnBeans.add(columnBean);
        }
        histogramView.setXYData(1200, 6, columnBeans.size());

        histogramView.setColumnBeans(columnBeans);
        histogramView.postInvalidate();
    }
}
