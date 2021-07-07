package com.yan.demo;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yan.demo.bean.CylinderBean;
import com.yan.demo.view.CylinderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CylinderActivity extends Activity {
    private List<CylinderBean> cylinderBeans = new ArrayList<>();
    private CylinderView mCylinderView;
    private int yMaxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder);
        initView();
        yMaxValue = 60;

        for (int i = 0; i < 10; i++) {
            CylinderBean cylinderBean = new CylinderBean();
            cylinderBean.setBlueValue( new Random().nextInt(yMaxValue) );
            cylinderBean.setName("张大"+i);
            cylinderBean.setNo(3001);
            cylinderBean.setYellowValue(24);
            cylinderBeans.add(cylinderBean);
        }
       mCylinderView.
                setCylinderBeans(cylinderBeans)
                .setyMaxValue(yMaxValue)
                .postInvalidate();

    }

    private void initView() {
        mCylinderView = (CylinderView) findViewById(R.id.CylinderView);
    }
}