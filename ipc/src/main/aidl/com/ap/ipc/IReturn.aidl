// IReturn.aidl
package com.ap.ipc;

import com.ap.ipc.Book;

// Declare any non-default types here with import statements
interface IReturn {
    void complete(in List<Book> books);
}
