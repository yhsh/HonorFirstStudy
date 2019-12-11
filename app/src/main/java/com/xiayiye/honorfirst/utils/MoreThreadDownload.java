package com.xiayiye.honorfirst.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xiayiye
 * 多线程下载文件的工具类
 */
public class MoreThreadDownload {
    public static void startDownload(String path) {
        try {
            URL url = new URL(path);
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int contentLength = urlConnection.getContentLength();
                RandomAccessFile randomAccessFile = new RandomAccessFile("copyFile.exe", "rws");
                OutputStream outputStream = new FileOutputStream("copy.zip");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("copy.zip"));
//                randomAccessFile.setLength(contentLength);
                if (urlConnection.getResponseCode() == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    int len = -1;
                    char[] b = new char[1024];
                    while ((len = bufferedReader.read(b)) != -1) {
                        bufferedWriter.write(b, 0, len);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
