package com.ap.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class MyView extends View {

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int mWidth = 0;
    private int mHeight = 0;
    private Paint gPaint;
    private Paint bPaint;
    private RectF rectF;

    private void init() {
        gPaint = new Paint(Paint.UNDERLINE_TEXT_FLAG);
        gPaint.setARGB(255, 60, 63, 65);
        gPaint.setStyle(Paint.Style.STROKE);
        gPaint.setStrokeWidth(2);

        bPaint = new Paint();
        bPaint.setARGB(255, 66, 145, 241);
        bPaint.setStyle(Paint.Style.STROKE);
        bPaint.setStrokeWidth(2);

        rectF = new RectF();
    }

    public void setMeasure(int width, int height) {
        mWidth = width;
        mHeight = height;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
        mHeight = getHeight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        layout(0, 0, mWidth * 2 / 3, mWidth * 2 / 3);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 3, gPaint);
        rectF.set(mWidth / 6, mHeight / 6, mWidth * 5 / 6, mHeight * 5 / 6);
        canvas.drawArc(rectF, 270, 120, true, bPaint);
    }
}
