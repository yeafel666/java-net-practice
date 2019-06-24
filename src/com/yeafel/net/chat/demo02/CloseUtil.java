package com.yeafel.net.chat.demo02;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Yeafel
 * 关闭流的方法
 * 2019/6/24 11:42
 * Do or Die,To be a better man!
 */
public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            if (temp != null) {
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
