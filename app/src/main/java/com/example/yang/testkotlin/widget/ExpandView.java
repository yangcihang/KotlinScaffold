package com.example.yang.testkotlin.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yang.testkotlin.R;

/**
 * @author YangCihang
 * @since 17/10/19.
 * email yangcihang@hrsoft.net
 */

public class ExpandView extends RelativeLayout {
    private ObjectAnimator mainAnimator;
    private ObjectAnimator leftAnimator;
    private ObjectAnimator rightAnimator;
    private ObjectAnimator bottomAnimator;
    private ObjectAnimator topAnimator;
    private AnimatorSet startAnimatorSet;
    private AnimatorSet endAnimatorSet;
    private ImageView mainImg;
    private ImageView leftImg;
    private ImageView rightImg;
    private ImageView topImg;
    private ImageView bottomImg;
    private boolean isPlaying = false;
    private boolean isShowing = false;

    public ExpandView(Context context) {
        super(context);
    }

    public ExpandView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.expand_view, this);
        init();
    }


    public ExpandView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mainImg = findViewById(R.id.img_main);
        leftImg = findViewById(R.id.img_left);
        rightImg = findViewById(R.id.img_right);
        topImg = findViewById(R.id.img_top);
        bottomImg = findViewById(R.id.img_bottom);
        startAnimatorSet = new AnimatorSet();
        endAnimatorSet = new AnimatorSet();
    }

    public void playAnim() {
        mainAnimator = ObjectAnimator.ofFloat(mainImg, "alpha", 1F, 0.5F);
        mainAnimator.setDuration(300);
        leftAnimator = ObjectAnimator.ofFloat(leftImg, "translationX", -200);
        leftAnimator.setDuration(300);
        rightAnimator = ObjectAnimator.ofFloat(rightImg, "translationX", 200);
        rightAnimator.setDuration(300);
        topAnimator = ObjectAnimator.ofFloat(topImg, "translationY", -200);
        topAnimator.setDuration(300);
        bottomAnimator = ObjectAnimator.ofFloat(bottomImg, "translationY", 200);
        bottomAnimator.setDuration(300);
        startAnimatorSet.playTogether(
                mainAnimator,
                leftAnimator,
                rightAnimator,
                topAnimator,
                bottomAnimator
        );
        startAnimatorSet.start();
        startAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isPlaying = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isPlaying = false;
                isShowing = true;
            }
        });
    }

    public void stopAnim() {
        mainAnimator = ObjectAnimator.ofFloat(mainImg, "alpha", 0.5F, 1F);
        mainAnimator.setDuration(300);
        leftAnimator = ObjectAnimator.ofFloat(leftImg, "translationX", 0);
        leftAnimator.setDuration(300);
        rightAnimator = ObjectAnimator.ofFloat(rightImg, "translationX", 0);
        rightAnimator.setDuration(300);
        topAnimator = ObjectAnimator.ofFloat(topImg, "translationY", 0);
        topAnimator.setDuration(300);
        bottomAnimator = ObjectAnimator.ofFloat(bottomImg, "translationY", 0);
        bottomAnimator.setDuration(300);
        endAnimatorSet.playTogether(
                mainAnimator,
                leftAnimator,
                rightAnimator,
                topAnimator,
                bottomAnimator
        );
        endAnimatorSet.start();
        endAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isPlaying = false;
                isShowing = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isPlaying = true;
            }
        });
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isShowing() {
        return isShowing;
    }
}
