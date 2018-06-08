package com.ap.ipc;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@SuppressLint("Registered")
public class AidlService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("西游记");
        book.setPrice("80.00");
        book.setProduce("中央卫视");
        book.setAuthor("吴承恩");
        books.add(book);
    }

    private IReturn mIReturn = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();

    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
            if (null != mIReturn) mIReturn.complete(books);
        }

        @Override
        public void registerListener(IReturn returnListener) throws RemoteException {
            mIReturn = returnListener;
        }

        @Override
        public void unRegisterListener() throws RemoteException {
            mIReturn = null;
        }
    };
}
