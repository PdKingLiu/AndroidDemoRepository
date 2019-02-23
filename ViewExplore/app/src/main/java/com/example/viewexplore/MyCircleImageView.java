package com.example.viewexplore;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyCircleImageView extends View {

    private Drawable mDrawable;

    private int mRingColor;

    private int mRingWidth;

    private float mAlpha;

    private Paint mPaint;

    private Paint mIconPaint;

    private Bitmap mBitmap;

    private BitmapShader mBitmapShader;

    private Matrix mMatrix;

    private String TAG = "Lpp";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCircleImageView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();

    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaint.setAntiAlias(true);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha((int) (mAlpha * 255));
        mIconPaint.setAlpha((int) (mAlpha * 255));
        mIconPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mRingColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mRingWidth);
        mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
        mMatrix = new Matrix();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //处理content
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);

        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();

        //设置为图片的大小
        if (widthSpecMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(bitmapWidth, bitmapHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(bitmapWidth, heightMeasureSize);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, bitmapHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable != null) {

            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();


            float scale = Math.max((float) getMeasuredWidth() / (float) mBitmap.getWidth(),
                    (float) getMeasuredHeight() / (float) mBitmap.getHeight());

            int width = (getWidth() - paddingLeft - paddingRight);
            int height = (getHeight() - paddingTop - paddingBottom);
            int radius = Math.min(width, height) / 2;
            mMatrix.setScale(scale, scale);
            mBitmapShader.setLocalMatrix(mMatrix);
            mIconPaint.setShader(mBitmapShader);
            Log.d(TAG, "onDraw: " + paddingLeft);
            canvas.drawCircle(paddingLeft + width / 2, paddingTop +
                    height / 2, radius - mPaint.getStrokeWidth() / 2, mPaint);
            canvas.drawCircle(paddingLeft + width / 2, paddingTop +
                    height / 2, radius - mPaint.getStrokeWidth(), mIconPaint);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCircleImageView);

        mDrawable = array.getDrawable(R.styleable.MyCircleImageView_icon);

        mDrawable = mDrawable == null ? getContext().getDrawable(R.mipmap.ic_launcher):mDrawable;

        mRingColor = array.getColor(R.styleable.MyCircleImageView_ring_color, Color.RED);

        mRingWidth = (int) array.getDimension(R.styleable.MyCircleImageView_ring_width, 10);

        mAlpha = array.getFloat(R.styleable.MyCircleImageView_view_alpha, 1) > 1 ? 1 : array
                .getFloat(R.styleable.MyCircleImageView_view_alpha, 1);

        array.recycle();

    }
}
