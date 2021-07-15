package com.yan.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    /**
     * 自定义柱状图2
     */
    private Button mCylinderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        findViewById(R.id.histogram_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, HistogramViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.circle_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.right_angle_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, RightAngleProgressActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        mCylinderView = (Button) findViewById(R.id.CylinderView);
        mCylinderView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.CylinderView:
                intent = new Intent(MainActivity.this, CylinderActivity.class);
                startActivity(intent);
                break;

        }
    }
}