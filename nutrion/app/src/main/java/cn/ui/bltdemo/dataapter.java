package cn.ui.bltdemo;
import android.app.Application;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class dataapter extends Application {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    boolean state=false;
    public  void getString() {
        File sdcard = Environment.getExternalStorageDirectory();//这里返回storage/sdcard0
        File directory = new File(sdcard+"/TEST");
        //      filePath = Environment.getDownloadCacheDirectory().toString() + File.separator +"device.txt";
        String  filecontent =directory +"/weight.txt";
        try {

            File Mfile = new File(filecontent);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if(!Mfile.exists())
            {
                Mfile.createNewFile();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
//        String filePath = null;
//        filePath = Environment.getDownloadCacheDirectory().toString() + File.separator +"weight.txt";
//
//        try {
//
//            File file = new File(filePath);
//
//            if (!file.exists()) {
//
//                File dir = new File(file.getParent());
//
//                dir.mkdirs();
//
//                file.createNewFile();
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
    }
    public  void getdevice() {

//        File directory = new File(sdcard+"/TEST");
//
//        if(!directory.exists())
//
//        {
//
//           state= directory.mkdirs();//一直返回false
//
//        }
//
//
//
//        if(directory.isDirectory())//因为前一步未穿件目录成功，if语句不成立
//
//        {
//
//            File file = new File(directory,"device.txt");
////            savedevice("helloworld");
//            //..........往文件中写
//
//        }else
//
//        {
//
//            Log.i("Fuck","failed to create directory!");
//
//        }
        File sdcard = Environment.getExternalStorageDirectory();//这里返回storage/sdcard0
        File directory = new File(sdcard+"/TEST");
  //      filePath = Environment.getDownloadCacheDirectory().toString() + File.separator +"device.txt";
         String  filecontent =directory +"/device.txt";
        try {

            File Mfile = new File(filecontent);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if(!Mfile.exists())
            {
                Mfile.createNewFile();
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

    public String readdevice() {
        // BufferedReader包装流（字符流 字节流）  带缓冲区的
        BufferedReader reader = null;
        FileInputStream fis = null;
        StringBuilder sbd = new StringBuilder();
        try {
            fis = openFileInput("device.text");
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
    public void savedevice(String str) {
        FileOutputStream fos = null;
        try {
            /*
            openFileOutput返回一个输出字节流
            指向的路径为data/data/包名/files
            参数1：文件名称（如果不存在则自动创建）
            参数2:模式MODE_APPEND文件内容可樶加
                  模式MODE_PRIVATE文件内容被覆盖
             */
            fos = openFileOutput("device.text", MODE_PRIVATE);  //覆盖
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

    public static boolean sdCardIsAvailable() {
        //首先判断存储是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            Log.e("qq", "sd = " + sd);//sd = /storage/emulated/0
            return sd.canWrite();
        } else {
            return false;
        }
    }


}
