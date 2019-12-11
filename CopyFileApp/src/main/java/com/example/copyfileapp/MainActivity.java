package com.example.copyfileapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author xiayiye
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvShow = findViewById(R.id.tv_show);
        FileReader fileReader = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "");
            /*String[] names = file.list();
            for (String name : names) {
                Log.e("打印文件", name);
            }*/

            File[] names = file.listFiles();
            StringBuffer s = new StringBuffer();
            for (File name : names) {
                Log.e("打印文件", name.getName());
                if (name.getName().endsWith(".txt")) {
                    String readFile = name.getName();
                    fileReader = new FileReader(Environment.getExternalStorageDirectory() + "/" + readFile);
                    int chan = 0;
                    while ((chan = fileReader.read()) != -1) {
                        s.append(String.valueOf((char) chan));
                    }
                    Log.e("打印", s.toString());
                }
            }
            assert fileReader != null;
            fileReader.close();
            tvShow.setText(s);
            File file1 = new File(Environment.getExternalStorageDirectory() + "/SD卡所有txt内容集合.txt");
            if (file1.exists()) {
                return;
            }
            FileWriter fileWriter = new FileWriter(new File(Environment.getExternalStorageDirectory() + "/SD卡所有txt内容集合.txt"));
            fileWriter.write(s.toString());
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("提示").
                    setMessage("SD卡所有txt文件内容集合已写入数据成功,\r\n请到手机根目录查看此文件\"SD卡所有txt内容集合.txt\"").
                    setNegativeButton("确定", null).setCancelable(false).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
