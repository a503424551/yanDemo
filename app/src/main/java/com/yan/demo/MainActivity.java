package com.yan.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.histogram_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intent=new Intent(MainActivity.this,HistogramViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.circle_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,CircleActivity.class);
                startActivity(intent);

            }
        });

    }
}