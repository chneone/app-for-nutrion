package cn.ui.bltdemo;
import android.app.Application;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class dataapter extends Application {


    public  void getString() {

        String filePath = null;
        filePath = Environment.getDownloadCacheDirectory().toString() + File.separator +"weight.txt";

        try {

            File file = new File(filePath);

            if (!file.exists()) {

                File dir = new File(file.getParent());

                dir.mkdirs();

                file.createNewFile();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    //删除文件
    public void removeFile() {
        String[] files = fileList();
        for (String str : files) {
            if (str.equals("weight.text")) {
                deleteFile(str);
                break;
            }
        }
    }

    //从内存储中读取文件
    public String readFile() {
        // BufferedReader包装流（字符流 字节流）  带缓冲区的
        BufferedReader reader = null;
        FileInputStream fis = null;
        StringBuilder sbd = new StringBuilder();
        try {
            fis = openFileInput("weight.text");
            reader = new BufferedReader(new InputStreamReader(fis));
            sbd.append(getFilesDir().getCanonicalPath());

            String row = "";
            while ((row = reader.readLine()) != null) {
                sbd.append(row);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getBaseContext(), "文件不存在", Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sbd.toString();
    }

    //保存文件到内存储
    public void saveFile(String str) {

        FileOutputStream fos = null;
        try {
            /*
            openFileOutput返回一个输出字节流
            指向的路径为data/data/包名/files
            参数1：文件名称（如果不存在则自动创建）
            参数2:模式MODE_APPEND文件内容可樶加
                  模式MODE_PRIVATE文件内容被覆盖
             */
            fos = openFileOutput("weight.text", MODE_APPEND);
//            String str = content.getText().toString();
            fos.write(str.getBytes());
            Toast.makeText(getBaseContext(), "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
