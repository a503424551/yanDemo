package com.yan.demo;

import android.app.Activity;
import android.os.Bundle;

import com.yan.demo.view.ScoreCirle;

import androidx.annotation.Nullable;

public class ScoreCirleActivity extends Activity {

    private ScoreCirle mSc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      setContentView(R.layout.activity_score_cirle);
        mSc = findViewById(R.id.sc);
        mSc.setCurrentData(25,"合格");


    }


}
