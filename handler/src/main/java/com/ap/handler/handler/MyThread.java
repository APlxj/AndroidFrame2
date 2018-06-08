package com.ap.handler.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class MyThread extends Thread {

    private Handler handler = null;

    public MyThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        Message message = Message.obtain();
        message.obj = "MyThread开始handler";
        handler.sendMessageDelayed(message, 3000);
    }
}
