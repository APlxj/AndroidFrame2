package com.ap.handler;

import android.app.Application;

import com.ap.handler.handler.TestLog;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TestLog.d("BaseApplication");
    }
}
