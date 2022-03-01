package com.yan.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    /**
     * 自定义柱状图2
     */
    private Button mCylinderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        findViewById(R.id.CylinderView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, CylinderActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });

    }


}