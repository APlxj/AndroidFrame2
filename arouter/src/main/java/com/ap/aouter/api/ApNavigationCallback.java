package com.ap.aouter.api;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.ap.aouter.base.MultiDexApplication;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class ApNavigationCallback implements NavigationCallback {

    private Activity activity;

    public ApNavigationCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onFound(Postcard postcard) {
        //发现
    }

    @Override
    public void onLost(Postcard postcard) {
        //未发现
        Toast.makeText(activity, "uri error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onArrival(Postcard postcard) {
        //完成
        Toast.makeText(activity, "界面跳转完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInterrupt(final Postcard postcard) {
        //被拦截
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MultiDexApplication.getApplication(), "此功能正在开发，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
