package com.ap.androidtouchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("AppCompatCustomView")
public class MyView extends ViewGroup implements View.OnTouchListener, View.OnClickListener {

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.add("ViewGroup_dispatchTouchEvent_Action_" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.add("ViewGroup_onInterceptTouchEvent_Action_" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        Log.add("ViewGroup_onTouchEvent_Action_" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.add("tv_onTouch_Action_" + motionEvent.getAction());
        return false;
    }

    @Override
    public void onClick(View view) {
        Log.add("tv_onClick");
        iSetText.setText();
    }

    public interface ISetText {
        void setText();
    }

    private ISetText iSetText = null;

    public void setiSetText(ISetText iSetText) {
        this.iSetText = iSetText;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

}
