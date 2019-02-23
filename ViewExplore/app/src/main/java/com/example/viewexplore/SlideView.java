package com.example.viewexplore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlideView extends View {

    private float lastX;
    private float lastY;
    private Scroller mScroller;
    private String TAG = "Lpp";

    public SlideView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();

        float y = event.getY();

        Log.d(TAG, "onTouchEvent: x:" + x + "y" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = x;

                lastY = y;

                Log.d(TAG, "onTouchEvent ACTION_DOWN: x:" + x + "y" + y);

                Log.d(TAG, "onTouchEvent ACTION_DOWN: lastX:" + lastX + "lastY" + lastY);

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                float offSetX = x - lastX;

                float offSetY = y - lastY;


                //通过layout实现view的滑动
                layout((int) (getLeft() + offSetX), (int) (getTop() + offSetY),
                        (int) (getRight() + offSetX), (int) (getBottom() + offSetY));


                //通过设置偏移量进行滑动
                /*offsetLeftAndRight((int) offSetX);
                offsetTopAndBottom((int) offSetY);*/


                // 通过改变布局参数实现滑动
                // xxx.LayoutParams xxx是指父控件
                // 除了LayoutParams 外 还可以使用ViewGroup.MarginLayoutParams 用法和下面的一致
                /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                getLayoutParams();
                layoutParams.leftMargin = (int) (getLeft() + offSetX);
                layoutParams.topMargin = (int) (getTop() + offSetY);
                setLayoutParams(layoutParams);*/


//                 通过scrollBy/scrollTo实现滑动
//                ((View)getParent()).scrollBy((int) -offSetX,(int)-offSetY);

                break;

        }

        return true;

    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, 5000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
