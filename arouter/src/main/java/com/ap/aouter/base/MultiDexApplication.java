package com.ap.aouter.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class MultiDexApplication extends Application {

    private static MultiDexApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        if (isDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return true;
    }

    public static MultiDexApplication getApplication() {
        return application;
    }
}
