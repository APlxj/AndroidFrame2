package com.ap.ipc;

import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class Book implements Serializable, Parcelable {

    /**
     * 序列化和反序列的关键
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String price;
    private String produce;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //文件共享
    //序列化：将对象转化为可保存的字节序列
    public static void writeSerializable(Book book) {
        try {
            String path = Environment.getExternalStorageDirectory() + "/IPC/";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path, "book.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            // 构造序列化输出字节流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "book.txt"));
            // 序列化对象
            oos.writeObject(book);
            // 关闭流
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //反序列化：将字节序列恢复为对象的过程。
    public static Book readSerializable() {
        Book book = null;
        try {
            String path = Environment.getExternalStorageDirectory() + "/IPC/";
            // 创建序列化读取字节流
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "book.txt"));
            // 反序列化（读取）对象
            book = (Book) ois.readObject();
            // 关闭流
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }


    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getPrice());
        dest.writeString(getProduce());
        dest.writeString(getAuthor());
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            //从序列化后的对象中创建原始的值
            Book book = new Book();
            book.setName(source.readString());
            book.setPrice(source.readString());
            book.setProduce(source.readString());
            book.setAuthor(source.readString());

            return book;
        }

        @Override
        public Book[] newArray(int size) {
            //创建指定长度的原始对象数组
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", produce='" + produce + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
