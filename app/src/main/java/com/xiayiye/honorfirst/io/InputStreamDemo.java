package com.xiayiye.honorfirst.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author xiayiye
 */
public class InputStreamDemo {
    public static void main(String[] args) {
//        readFile1();
//        readFile2();
//        readFile3();
//        readFile4();
//        readFile5();
//        readFile6();
//        readFile7();
        readFile8();
        readFile9();
        readFile10();
    }
    /**
     * 读写方式十
     * 转换流方式一次一个数组字符一个字符的读写
     */
    private static void readFile10() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\io\\InputStreamDemo.java"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("copy10.java"));
            int len = 0;
            char[] chars = new char[1024];
            try {
                while ((len = inputStreamReader.read(chars)) != -1) {
                    outputStreamWriter.write(chars, 0, len);
                }
                outputStreamWriter.flush();
                outputStreamWriter.close();
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读写方式九
     * 转换流方式一次一个字符一个字符的读写
     */
    private static void readFile9() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\io\\InputStreamDemo.java"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("copy9.java"));
            try {
                int ch = 0;
                while ((ch = inputStreamReader.read()) != -1) {
                    outputStreamWriter.write(ch);
                }
                outputStreamWriter.flush();
                outputStreamWriter.close();
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式八
     * 高效字符流方式一个char数组字符一个字符的读写
     */
    private static void readFile8() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\io\\InputStreamDemo.java"));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("copy8.java"));
            //代表读取到的数据的实际长度
            int len = 0;
            char[] chars = new char[1024];
            while ((len = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
            fileWriter.flush();
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式七
     * 高效字符流方式一个字符一个字符的读写
     */
    private static void readFile7() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\io\\InputStreamDemo.java"));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("copy7.java"));
            //代表读取到的数据的底层int值
            int ch = 0;
            while ((ch = bufferedReader.read()) != -1) {
                bufferedWriter.write(ch);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式六
     * 字节流方式一个byte数组一个byte的读写
     */
    private static void readFile6() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\xiayiye\\Desktop\\demo.avi"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("copy6.avi"));
            //代表读取到的数据的实际长度
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式五
     * 高效字节流方式一个字节一个字节的读写
     */
    private static void readFile5() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\xiayiye\\Desktop\\demo.pdf"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("copy5.pdf"));
            //代表读取到的数据的底层int值
            int ch = 0;
            while ((ch = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(ch);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式四
     * 字符流方式一个char数组一个数组的读写
     */
    private static void readFile4() {
        try {
            FileReader fileReader = new FileReader("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\utils\\MoreThreadDownload.java");
            FileWriter fileWriter = new FileWriter("copy4.java");
            //代表读取到的数据的实际长度
            int len = 0;
            char[] chars = new char[1024];
            while ((len = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式三
     * 字节流方式一个byte数组一个byte的读写
     */
    private static void readFile3() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\xiayiye\\Desktop\\demo.avi");
            FileOutputStream fileOutputStream = new FileOutputStream("copy3.avi");
            //代表读取到的数据的实际长度
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式二
     * 字符流方式一个字节一个字符的读写
     */
    private static void readFile2() {
        try {
            FileReader fileReader = new FileReader("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\utils\\MoreThreadDownload.java");
            FileWriter fileWriter = new FileWriter("copy2.java");
            //代表读取到的数据的int值
            int ch = 0;
            while ((ch = fileReader.read()) != -1) {
                fileWriter.write(ch);
            }
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写方式一
     * 字节流方式一个字节一个字节的读写
     */
    private static void readFile1() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\Android\\StudioProjects\\HonorFirst\\app\\src\\main\\java\\com\\xiayiye\\honorfirst\\utils\\MoreThreadDownload.java");
            FileOutputStream fileOutputStream = new FileOutputStream("copy1.java", true);
            //代表读取到的数据的int值
            int ch = 0;
            while ((ch = fileInputStream.read()) != -1) {
                fileOutputStream.write(ch);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
