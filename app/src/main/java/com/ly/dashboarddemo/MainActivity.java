package com.ly.dashboarddemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import java.util.Random;

public class MainActivity extends Activity {

    private DashboardView mDashboardView;

    private boolean isAnimFinished = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDashboardView = findViewById(R.id.dashBoard);

        if (isAnimFinished) {
            ObjectAnimator animator = ObjectAnimator.ofInt(mDashboardView, "rotate",
                    mDashboardView.getVelocity(), new Random().nextInt(180));
            animator.setDuration(1500).setInterpolator(new LinearInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimFinished = false;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimFinished = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAnimFinished = true;
                }
            });
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    mDashboardView.setVelocity(value);
                }
            });
            animator.start();
        }
    }
}
