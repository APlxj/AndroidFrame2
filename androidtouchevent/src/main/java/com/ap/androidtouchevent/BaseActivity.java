package com.ap.androidtouchevent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("Registered")
public class BaseActivity extends Activity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.add("Activity_dispatchTouchEvent_Action_" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.add("Activity_onTouchEvent_Action_" + event.getAction());
        return super.onTouchEvent(event);
    }

}
