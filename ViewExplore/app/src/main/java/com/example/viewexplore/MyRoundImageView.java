package com.example.viewexplore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyRoundImageView extends View {

    private RectF mRectF;

    private Bitmap mBitmap;

    private Drawable mDrawable;

    private Paint mPaint;

    private int mWidth;

    private int mHeight;

    private int mRadius;

    private Canvas mCanvas;


    public MyRoundImageView(Context context) {
        this(context, null);
    }

    public MyRoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(context, attrs);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        int bitMapWidth = mBitmap.getWidth();
        int bitMapHeight = mBitmap.getHeight();

        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(bitMapWidth, bitMapHeight);
        } else if (widthMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(bitMapWidth, heightMeasureSize);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSize, bitMapHeight);
        }

    }

    @SuppressLint("NewApi")
    private void initAttrs(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRoundImageView);

        mDrawable = typedArray.getDrawable(R.styleable.MyRoundImageView_icon);

        mDrawable = mDrawable == null ? getContext().getDrawable(R.mipmap.ic_launcher) : mDrawable;

        mRadius = typedArray.getInteger(R.styleable.MyRoundImageView_round_radius, 80);

        mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

    }

    public MyRoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, null);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /* 方式一 使用参数canvas */


        /* saveLayer可以为canvas创建一个新的透明图层，在新的图层上绘制，
        并不会直接绘制到屏幕上，而会在restore之后，绘制到上一个图层或者屏幕上（如果没有上一个图层）。
        为什么会需要一个新的图层，例如在处理xfermode的时候，
        原canvas上的图（包括背景）会影响src和dst的合成，这个时候，
        使用一个新的透明图层是一个很好的选择。
        又例如需要当前绘制的图形都带有一定的透明度，
        那么创建一个带有透明度的图层，也是一个方便的选择。*/


        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mRectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, null, mRectF, mPaint);
        canvas.restoreToCount(sc);



        /* 方式二 使用新的 mCanvas*/
//        Bitmap bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap
//                .Config.ARGB_8888);
//        mCanvas = new Canvas(bitmap);
//        mRectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
//        mCanvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        mCanvas.drawBitmap(mBitmap, null, mRectF, mPaint);
//        canvas.drawBitmap(bitmap, 0, 0, null);

    }
}
