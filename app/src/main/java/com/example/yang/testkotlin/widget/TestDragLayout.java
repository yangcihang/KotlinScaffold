package com.example.yang.testkotlin.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author YangCihang
 * @since 17/10/24.
 * email yangcihang@hrsoft.net
 */

public class TestDragLayout extends LinearLayout {
    private ViewDragHelper dragHelper;

    public TestDragLayout(Context context) {
        super(context);
    }

    public TestDragLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            //限制水平方向
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                final int leftBound = getPaddingLeft();
//                final int rightBound = getWidth() - getChildAt(0).getWidth() - leftBound;
//
//                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
//
//                return newLeft;
                return left;
            }

            //限制竖直方向
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });
    }

    public TestDragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }
}
