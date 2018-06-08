package com.ap.handler.binder;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ap.handler.R;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("Registered")
public class FirstActivity extends AppCompatActivity {

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = service;
            call();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    private void call() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        data.writeInt(1);
        data.writeInt(2);
        try {
            binder.transact(1000, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        int result = reply.readInt();
        data.recycle();
        reply.recycle();
        Toast.makeText(FirstActivity.this, "" + result, Toast.LENGTH_SHORT).show();
    }

    IBinder binder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Intent intent = new Intent();
        intent.setAction("com.ap.handler.binder.AddService");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }
}
