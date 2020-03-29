package com.deepanshu.android.techapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer=new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
            }
        },500);
    }
}
