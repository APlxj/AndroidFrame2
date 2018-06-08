package com.ap.ipc;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("Registered")
public class MessengerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @SuppressLint("HandlerLeak")
    Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("TAG", msg.getData().getString("msg") + "---" + msg.arg1);
            // 接收MainActivity的Messenger对象
            // 通过此对象向MainActivity发送消息
            mainMessenger = msg.replyTo;
            if (null != mainMessenger) {
                try {
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "MessengerService回执：已收到消息");
                    message.setData(bundle);
                    mainMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    });

    private Messenger mainMessenger = null;
}
