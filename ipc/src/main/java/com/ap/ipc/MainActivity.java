package com.ap.ipc;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * 类描述：IPC
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindMessenger();
        bindBinder();
        bindAIDL();
        bindFile();
    }

    /**
     * 文件
     */
    private void bindFile() {
        Book book = new Book();
        book.setName("Android第一行代码");
        book.setPrice("59.80");
        book.setProduce("CSDN");
        book.setAuthor("鸿阳");
        Book.writeSerializable(book);
    }

    public void callFile(View v) {
        Intent intent = new Intent("com.ap.ipc.FileService");
        startService(intent);
    }

    /**
     * Binder
     */
    private IBinder serviceBinder = null;

    //绑定service，获取IBinder对象
    public void bindBinder() {
        Intent intent = new Intent("com.ap.ipc.MyService");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceBinder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                serviceBinder = null;
            }
        }, BIND_AUTO_CREATE);
    }

    //发送消息&接收消息
    public void callBinder(View v) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeInt(20);
        data.writeInt(31);
        try {
            serviceBinder.transact(101, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        int k = reply.readInt();
        Toast.makeText(MainActivity.this, "返回数字：" + k, Toast.LENGTH_SHORT).show();
    }

    /**
     * Messenger
     */
    private Messenger serviceMessenger = null;

    public void bindMessenger() {
        Intent intent = new Intent("com.ap.ipc.MessengerService");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceMessenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void callMessenger(View v) {
        Message message = Message.obtain();
        Bundle data = new Bundle();
        data.putString("msg", "MainActivity发送消息：callMessenger");
        message.replyTo = mainMessenger;
        message.arg1 = 101;
        message.setData(data);

        try {
            serviceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("HandlerLeak")
    private Messenger mainMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("TAG", msg.getData().getString("msg"));
            super.handleMessage(msg);
        }
    });

    /**
     * aidl
     */
    private IBookManager manager = null;

    private void bindAIDL() {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                manager = IBookManager.Stub.asInterface(service);
                try {
                    manager.registerListener(new kk());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                manager = null;
            }
        }, BIND_AUTO_CREATE);
    }

    public void callAIDL(View v) {
        Book book = new Book();
        book.setName("Android第一行代码");
        book.setPrice("59.80");
        book.setProduce("CSDN");
        book.setAuthor("鸿阳");
        try {
            manager.addBook(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            List<Book> books = manager.getBookList();
            Log.d("TAG", "get-->>" + books.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //数据返回
    public class kk extends IReturn.Stub {

        @Override
        public void complete(List<Book> books) throws RemoteException {
            Log.d("TAG", "complete-->>" + books.toString());
        }
    }
}
