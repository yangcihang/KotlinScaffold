package com.example.yang.testkotlin.widget;

import android.content.Context;
import android.graphics.Point;
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

public class VDHLayout extends LinearLayout {
    private ViewDragHelper mDragger;
    //三个View代表正常拖动的，自动回弹的，侧面拉动的
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    //自动回弹的坐标
    private Point mAutoBackOriginPos = new Point();

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            /**
             * 捕获子View，返回true的话都可以拖动
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //mEdgeTrackerView禁止直接移动
                return child == mDragView || child == mAutoBackView;
            }

            /**
             * 横向边界值
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            /**
             * 纵向的边界值
             */
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            /**
             *手指释放的时候回调
             *
             */
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView) {
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
            }

            /**
             * 在边界拖动时回调
             */
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                //调用captureChildView方法指定捕获的View，当在边界拖动时，捕获View来进行响应的处理
                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }

            /**
             *  当边界点击时
             */
            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            /**
             * 当用户点击view时回调
             * @param activePointerId
             */
            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            //当view位置改变时回调，常用于更改scale进行缩放等动作
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            //在view拖拽状态改变时回调,比如idle,dragging等状态,state表示状态
            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }
        });
        //设置在哪个边界可以拖动
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }


    /**
     * 模板代码
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    /**
     * 模板代码
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    /**
     * 模板代码(用于滑动后回弹效果，底层是基于Scroller类来实现的)
     */
    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    //获取回弹View的坐标
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}