// IBookManager.aidl
package com.ap.ipc;

import com.ap.ipc.Book;
import com.ap.ipc.IReturn;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IReturn returnListener);
    void unRegisterListener();
}
