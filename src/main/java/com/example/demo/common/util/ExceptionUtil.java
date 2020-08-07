package com.example.demo.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

// 打印异常的堆栈信息
public class ExceptionUtil {

    public static String getMessage(Exception e){
        // 流
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            // 将出错的信息输出到 PrintWriter！
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (sw!=null){
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw!=null){
                pw.close();
            }
        }
        return sw.toString();
    }

}

