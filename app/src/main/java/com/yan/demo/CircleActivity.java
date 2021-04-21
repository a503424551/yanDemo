package com.yan.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.yan.demo.view.CircleBar;

import androidx.annotation.Nullable;

public class CircleActivity extends Activity {
    private int[] color = {Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW};
    private float[] values = {30f, 60f, 120f, 240f};
    private CircleBar circleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CircleBar circleBar = new CircleBar(this);
        circleBar.setValueAndColors(values, color);
        circleBar.postInvalidate();
        setContentView(circleBar);

/*
        setContentView(R.layout.circle);
        circleBar  =findViewById(R.id.circle);
        circleBar.setValueAndColors(values,color);
        circleBar.postInvalidate();*/
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
