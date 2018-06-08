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
public class NewThread implements Runnable {

    private Handler handler = null;

    public NewThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        Message message = Message.obtain();
        message.obj = "NewThread开始handler";
        handler.sendMessageDelayed(message, 3000);
    }
}
