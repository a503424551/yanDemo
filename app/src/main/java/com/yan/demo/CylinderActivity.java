package com.yan.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yan.demo.bean.CylinderBean;
import com.yan.demo.view.CylinderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CylinderActivity extends AppCompatActivity {
    private List<CylinderBean> cylinderBeans = new ArrayList<>();
    private CylinderView mCylinderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder);
        initView();
        for (int i = 0; i < 10; i++) {
            CylinderBean cylinderBean = new CylinderBean();
            cylinderBean.setBlueValue(24/*new Random().nextInt(25)*/);
            cylinderBean.setName("张大"+i);
            cylinderBean.setNo(3001);
            cylinderBean.setYellowValue(12);
            cylinderBeans.add(cylinderBean);
        }
        mCylinderView.
                setCylinderBeans(cylinderBeans)
                .setyMaxValue(60)
                .postInvalidate();

    }

    private void initView() {
        mCylinderView = (CylinderView) findViewById(R.id.CylinderView);
    }
}