package com.xiayiye.honorfirst.httpstudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiayiye.honorfirst.R;
import com.xiayiye.honorfirst.utils.ThreadUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xiayiye
 */
public class HttpStudyActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * 你自己的电脑IP地址注意：手机和电脑必须在同一个网络下才行
     */
    private static final String LOCAL_HOST_IP = "http://192.168.0.111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_study);
        ListView lvHttpStudy = findViewById(R.id.lv_http_study);
        lvHttpStudy.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                httpGetMethod();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void httpGetMethod() {
        ThreadUtils.startThread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(LOCAL_HOST_IP + ":9102/get/text");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String data = bufferedReader.readLine();
                        if (data != null) {
                            Log.e("打印返回数据", data);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
