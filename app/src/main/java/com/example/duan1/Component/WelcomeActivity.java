package com.example.duan1.Component;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.Component.User.LoginActivity;
import com.example.duan1.MainActivity;
import com.example.duan1.R;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imganh;
    private TextView tieude, tieude2, xinchao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        imganh = findViewById(R.id.imganh);
        tieude = findViewById(R.id.tieude);
        tieude2 = findViewById(R.id.tieude2);
        xinchao = findViewById(R.id.xinchao);

        // Animation for ImageView (imganh)
        ObjectAnimator imganhAnimation = ObjectAnimator.ofFloat(imganh, "translationY", -200f, 0f);
        imganhAnimation.setDuration(1000);
        imganhAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        imganhAnimation.start();

        // Animation for TextView (tieude)
        ObjectAnimator tieudeAnimation = ObjectAnimator.ofFloat(tieude, "alpha", 0f, 1f);
        tieudeAnimation.setDuration(1000);
        tieudeAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        tieudeAnimation.start();

        // Animation for TextView (tieude2)
        ObjectAnimator tieude2Animation = ObjectAnimator.ofFloat(tieude2, "alpha", 0f, 1f);
        tieude2Animation.setDuration(1000);
        tieude2Animation.setInterpolator(new AccelerateDecelerateInterpolator());
        tieude2Animation.start();

        // Animation for TextView (xinchao)
        ObjectAnimator xinchaoAnimation = ObjectAnimator.ofFloat(xinchao, "translationY", 200f, 0f);
        xinchaoAnimation.setDuration(1000);
        xinchaoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        xinchaoAnimation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        },3000);
    }


}