package com.yan.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yan.demo.view.ProgressView;

import androidx.annotation.Nullable;

public class ProgressActivity extends Activity {
    private ProgressView nev;
    private int i = 0;
    private String moreThreeSteps[] = new String[]{"开始", "进行中", "继续进行中","努力进行中", "结束"};
    private String onlyThreeSteps[] = new String[]{"开始", "进行中",  "结束"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        nev = findViewById(R.id.nev);
        findViewById(R.id.b_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i>0) {
                    i--;
                    nev.setCurrentIndex(i);
                }
            }
        });
        findViewById(R.id.b_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < moreThreeSteps.length - 1) {
                    i++;
                    nev.setCurrentIndex(i);
                }


            }
        });
        findViewById(R.id.b_more_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nev.setMoreThree(true);
                nev.setStepsName(moreThreeSteps);
                i=0;
                nev.setCurrentIndex(i);


            }
        });
        findViewById(R.id.b_only_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nev.setMoreThree(false);
                nev.setStepsName(onlyThreeSteps);
                i=0;
                nev.setCurrentIndex(i);




            }
        });
    }


}
