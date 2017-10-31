package com.example.yang.testkotlin.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * @author YangCihang
 * @since 17/10/18.
 * email yangcihang@hrsoft.net
 */

public class CircleAnimView extends View {
    public static final int RADIUS = 80;
    private Point currentPoint;
    private Paint mPaint;
    private int mColor;//必须写这个属性的get和set，否则动画无法识别color属性

    public CircleAnimView(Context context) {
        super(context);
    }

    public CircleAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.x;
        float y = currentPoint.y;
        //用canvas draw，因此此控件大小应该为全屏大小才可以从左上到右下
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new BounceInterpolator());
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                Color.BLUE, Color.RED);
        //动画集合将两个动画加到一起，with同时播放
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(anim).with(objectAnimator);
        animatorSet.setStartDelay(1000L);
        animatorSet.setDuration(3000L);
        animatorSet.start();
    }

    /**
     * 坐标变化的Evaluator
     */
    class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            //初始值 + 完成度 * (结束值 - 初始值)
//            int x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
            int x = startValue.x;
            int y = (int) (startValue.y + fraction * (endValue.y - startValue.y));
            return new Point(x, y);
        }
    }

    /**
     * 颜色变化的Evaluator
     */
    public class ColorEvaluator implements TypeEvaluator<Integer> {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int alpha = (int) (Color.alpha(startValue) + fraction *
                    (Color.alpha(endValue) - Color.alpha(startValue)));
            int red = (int) (Color.red(startValue) + fraction *
                    (Color.red(endValue) - Color.red(startValue)));
            int green = (int) (Color.green(startValue) + fraction *
                    (Color.green(endValue) - Color.green(startValue)));
            int blue = (int) (Color.blue(startValue) + fraction *
                    (Color.blue(endValue) - Color.blue(startValue)));
            return Color.argb(alpha, red, green, blue);
        }
    }

    //color的get和set方法~
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(color);
        invalidate();
    }
}
