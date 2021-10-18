package com.example.onnews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView on;
    private static int splashTimeOut=5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        logo= (ImageView)findViewById(R.id.logo);
        on = (TextView) findViewById(R.id.on);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        logo.startAnimation(myanim);
        Animation onn = AnimationUtils.loadAnimation(this, R.anim.side_slide2);
        on.startAnimation(onn);
    }
}