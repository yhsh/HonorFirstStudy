package com.xiayiye.honorfirst;

import android.util.Log;

import com.xiayiye.honorfirst.utils.MoreThreadDownload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xiayiye
 */
public class DownLoadMain {
    public static void main(String[] args) {
//        MoreThreadDownload.startDownload("http://192.168.43.252:8080/test.zip");
        try {
            URL url = new URL("https://www.baidu.com");
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(1000);
                if (urlConnection.getResponseCode() == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    StringBuilder sb = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
