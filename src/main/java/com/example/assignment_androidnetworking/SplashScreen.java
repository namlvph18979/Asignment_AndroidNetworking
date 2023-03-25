package com.example.assignment_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;
    TextView name;
    public static final String urlll = "192.168.1.169";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.img_logo);
        name = findViewById(R.id.tv_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                    finish();
            }
        },5000);

        Animation ani1 = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.animation_logo);
        logo.startAnimation(ani1);
        Animation ani2 = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.animation_text_splash);
        name.startAnimation(ani2);
    }
}