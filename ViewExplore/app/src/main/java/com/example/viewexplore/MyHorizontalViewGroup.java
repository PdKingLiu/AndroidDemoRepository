package com.example.viewexplore;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyHorizontalViewGroup extends ViewGroup {

    private int lastX;

    private int lastY;

    private int lastInterceptX;

    private int lastInterceptY;

    private int currentIndex = 0;

    private int childWidth;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;


    public MyHorizontalViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MyHorizontalViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }


    public MyHorizontalViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs);
    }


    private void init(Context context) {
        mScroller = new Scroller(context);
        mVelocityTracker = VelocityTracker.obtain();
    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMeasureMode == MeasureSpec.AT_MOST
                && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getChildAt(0).getWidth() * getChildCount(),
                    getChildAt(0).getHeight());
        } else if (widthMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getChildAt(0).getWidth() * getChildCount(), heightMeasureSize);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSize, getChildAt(0).getMeasuredHeight());
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;

        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = x - lastInterceptX;
                int dY = y - lastInterceptY;
                if (Math.abs(dX) - Math.abs(dY) > 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            default:
                break;
        }

        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;

        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = x - lastX;
                if (currentIndex == 0 && dX > 0) {
                    break;
                }
                if (currentIndex == getChildCount() - 1 && dX < 0) {
                    break;
                }
                scrollBy(-dX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float vX = mVelocityTracker.getXVelocity();
                    if (Math.abs(vX) > 50) {
                        if (vX > 0) {
                            currentIndex++;
                        } else {
                            currentIndex--;
                        }
                    }
                }
                smoothScrollTo(currentIndex * childWidth, 0);
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ?
                        getChildCount() - 1 : currentIndex;
                mVelocityTracker.clear();
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int i, int i1) {
        mScroller.startScroll(getScrollX(), getScrollY(),   //并不开始进行滑动 保存一些数据
                i - getScrollX(), i1 - getScrollY(), 1000);
        invalidate();   //这个方法会导致view重绘 调用draw 方法 然后 draw 调用computeScroll
    }

    @Override
    public void computeScroll() {
        Log.d("Lpp", "computeScroll: "+mScroller.getCurrX());
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }
}
