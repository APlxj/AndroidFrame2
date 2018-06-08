package com.ap.androidtouchevent;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class Log {

    public static StringBuffer buffer = new StringBuffer();

    public static void add(String str) {
        buffer.append("\t").append(str).append("\n");
    }

    public static void clear() {
        buffer = new StringBuffer();
    }
}
