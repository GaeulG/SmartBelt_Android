package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    // Set Duration of the Splash Screen
    long Delay = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

        // ?좊땲硫붿씠???ㅽ뻾
        ImageView imageView01 = (ImageView)findViewById(R.id.imageView01);
        final AnimationDrawable drawable = (AnimationDrawable)imageView01.getBackground();
        drawable.start();


        // ??대㉧ ?앹꽦
        Timer RunSplash = new Timer();
        // ??대㉧ ?쒓컙?숈븞 ?ㅽ뻾
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {

                finish();

                Intent myIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        };

        // ??대㉧ ?쒖옉
        RunSplash.schedule(ShowSplash, Delay);
    }
}