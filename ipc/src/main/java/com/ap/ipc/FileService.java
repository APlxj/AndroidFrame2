package com.ap.ipc;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("Registered")
public class FileService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = Book.readSerializable();
        Log.d("TAG", "FileService-->" + book.toString());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
