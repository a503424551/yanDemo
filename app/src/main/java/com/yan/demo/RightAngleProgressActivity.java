package com.yan.demo;

import android.app.Activity;
import android.os.Bundle;

import com.yan.demo.view.RightAngleProgress;

import androidx.annotation.Nullable;

public class RightAngleProgressActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*     RightAngleProgress rightAngleProgress=new RightAngleProgress(this);
        rightAngleProgress.setHorizontalSize(4);
        //设置进度值最后设置
        rightAngleProgress.setCruuentPosition(3);
        setContentView(rightAngleProgress);*/


        setContentView(R.layout.right_angle_progress);
        RightAngleProgress rightAngleProgress=findViewById(R.id.rap);
        rightAngleProgress.setHorizontalSize(5);
        rightAngleProgress.setCruuentPosition(1);
    }
}
