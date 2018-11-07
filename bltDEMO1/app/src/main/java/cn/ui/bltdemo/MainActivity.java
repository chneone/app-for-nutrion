package cn.ui.bltdemo;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends Activity {

    private List<String> contentList;
    private ListView mListView;
    //定义组件
    TextView statusLabel;
    Button btnConnect,btnSend,btnQuit,btnserach;
    Button qupi,set,contrast,logon;
    EditText etReceived,etSend,etname,etdata;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothAdapter BA;
    //device var
    private BluetoothSocket btSocket = null;

    private OutputStream outStream = null;

    private InputStream inStream = null;

    //这条是蓝牙串口通用的UUID，不要更改
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //private static String address = "20:15:12:15:02:32"; // <==要连接的目标蓝牙设备MAC地址
    String dizhi,IDnumber,diffnum;
  //  98d3:32:2171d3

    private ReceiveThread rThread=null;  //数据接收线程

    public static boolean  flag_contrast=false;
    //接收到的字符串
    String ReceiveData="";
    String dataget="";
    int numbe;
    MyHandler handler;
    dataapter appUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //首先调用初始化函数
        appUtil  = (dataapter) MainActivity.this.getApplication();
        appUtil.getString();//新建数据
        Init();
        InitBluetooth();
        handler=new MyHandler();

    }

    public void connect_click(View v) {
        try {
            //判断蓝牙是否打开
            if(!BA.isEnabled())
            {
//                    BA.enable();
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(),"Turned on"
                        ,Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Already on",
                        Toast.LENGTH_LONG).show();
            }
            //创建连接 ---------------------------------

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void send_click(View v) {
        try {
            Intent intent = new Intent(MainActivity.this,savedata.class);
            startActivityForResult(intent, 2);//界面2
           // new SendInfoTask().execute(etSend.getText().toString());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void quit_click(View v) {
        try {

            if(btSocket!=null)
            {
                try {
                    btSocket.close();
                    btSocket=null;
                    if(rThread!=null)
                    {
                        rThread.join();
                    }
                    statusLabel.setText("当前连接已断开");
//                      etReceived.setText("");
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void exchange_click(View v)
    {
        try{
            //指令校准功能
            String  msgBuffer = "fa0101fc";
            outStream=btSocket.getOutputStream();
            outStream.write(msgBuffer.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void search_click(View v) {
        try {
            if(btSocket!=null)
            {
                try {
                    btSocket.close();
                    btSocket=null;
                    if(rThread!=null)
                    {
                        rThread.join();
                    }
                    statusLabel.setText("当前连接已断开");
//                      etReceived.setText("");
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(MainActivity.this,address.class);
            startActivityForResult(intent, 1);//界面1
//            startActivity(intent);
            //获取设备列表
//            pairedDevices =BA.getBondedDevices();
//            ArrayList list = new ArrayList();
//            contentList = new ArrayList<String>();
//            for(BluetoothDevice bt : pairedDevices)
//                contentList.add(bt.getName()+" "+bt.getAddress() );
//            Toast.makeText(getApplicationContext(),"Showing Paired Devices",
//                    Toast.LENGTH_SHORT).show();
////            final ArrayAdapter adapter = new ArrayAdapter
////                    (this,android.R.layout.simple_list_item_1, list);
////            mListView.setAdapter(adapter);
//            mListView.setAdapter(new ContentAdapter(this,  contentList, this));
//            mListView.setOnItemClickListener(this);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void Init()
    {
        statusLabel=(TextView)this.findViewById(R.id.textView1);
        btnConnect=(Button)this.findViewById(R.id.button1);
        btnSend=(Button)this.findViewById(R.id.button2);
        btnQuit=(Button)this.findViewById(R.id.button3);
//        etSend=(EditText)this.findViewById(R.id.editText1);
//        etReceived=(EditText)this.findViewById(R.id.editText2);
        btnserach=(Button)this.findViewById(R.id.button5);
        qupi= (Button)this.findViewById(R.id.button_qupi);
        set=(Button)this.findViewById(R.id.button_set);
        logon=(Button)this.findViewById(R.id.button_logon);
        etname=(EditText)this.findViewById(R.id.edit_z);
        etdata=(EditText)this.findViewById(R.id.edit_g);
//        BA = BluetoothAdapter.getDefaultAdapter();
    }
    public void InitBluetooth()
    {
        //得到一个蓝牙适配器
        BA = BluetoothAdapter.getDefaultAdapter();
        if(BA == null)
        {
            Toast.makeText(this, "你的手机不支持蓝牙", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (!BA.isEnabled()) {
            // 弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
            // 不做提示，强行打开
            // mBluetoothAdapter.enable();
            BA.startDiscovery();//开始搜索
        }

    }


    @Override
    //resultCode区分哪一个页面的传来的值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 1) {
                dizhi=data.getStringExtra("data");//标识语句
                new ConnectTask().execute(dizhi);//地址
//                etSend.setText(data.getStringExtra("data"));
//                etReceived.setText(dizhi);
//                Toast.makeText(
//                        MainActivity.this, "地址0是："+ dizhi,
//                        Toast.LENGTH_SHORT).show(

            }
            else if (requestCode==2)
            {

            }
            else if(requestCode==3)  //login
            {
                IDnumber =data.getStringExtra("data");
                data_control(IDnumber);
            }
            else if (requestCode==4) //对比
            {

            }
        }
    }

    public void logon_click(View view) {
        try{
            //指令校准功能
            Intent intent = new Intent(MainActivity.this,Msglogin.class);
            startActivityForResult(intent, 3);//界面3
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void qupi_click(View view) {
        try{
            //指令校准功能
            String  msgBuffer = "fa0301fc";
            outStream=btSocket.getOutputStream();
            outStream.write(msgBuffer.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void contrast_click(View view) {
        try{
            //指令校准功能
            flag_contrast = true ;
            Intent intent = new Intent(MainActivity.this,Compare.class);
            startActivityForResult(intent, 4);//界面3
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void set_click(View view) {
        try{
            //指令校准功能
            Intent intent = new Intent(MainActivity.this,Msgset.class);
            startActivityForResult(intent, 5);//界面3
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //连接蓝牙设备的异步任务
    class ConnectTask extends AsyncTask<String,String,String>
    {


        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            BluetoothDevice device = BA.getRemoteDevice(params[0]);

            try {

                btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);


                btSocket.connect();

                Log.e("error", "ON RESUME: BT connection established, data transfer link open.");

            } catch (IOException e) {

                try {
                    btSocket.close();
                    return "Socket 创建失败";

                } catch (IOException e2) {

                    Log .e("error","ON RESUME: Unable to close socket during connection failure", e2);
                    return "Socket 关闭失败";
                }

            }
            //取消搜索
            BA.cancelDiscovery();

            try {
                outStream = btSocket.getOutputStream();

            } catch (IOException e) {
                Log.e("error", "ON RESUME: Output stream creation failed.", e);
                return "Socket 流创建失败";
            }


            return "蓝牙连接正常,Socket 创建成功";
        }

        @Override    //这个方法是在主线程中运行的，所以可以更新界面
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            //连接成功则启动监听
            rThread=new ReceiveThread();

            rThread.start();

            statusLabel.setText(result);

            super.onPostExecute(result);
        }



    }

    //发送数据到蓝牙设备的异步任务
    class SendInfoTask extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            statusLabel.setText(result);

            //将发送框清空
            etSend.setText("");
        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub

            if(btSocket==null)
            {
                return "还没有创建连接";
            }

            if(arg0[0].length()>0)//不是空白串
            {
                //String target=arg0[0];

                byte[] msgBuffer = arg0[0].getBytes();

                try {
                    //  将msgBuffer中的数据写到outStream对象中
                    outStream.write(msgBuffer);

                } catch (IOException e) {
                    Log.e("error", "ON RESUME: Exception during write.", e);
                    return "发送失败";
                }

            }

            return "发送成功";
        }

    }

    //从蓝牙接收信息的线程
    class ReceiveThread extends Thread
    {

        String buffer="";

        @Override
        public void run() {

            while(btSocket!=null )
            {
                //定义一个存储空间buff
                byte[] buff=new byte[1024*1024];
                try {
                    inStream = btSocket.getInputStream();
                    System.out.println("waitting for instream");
                    inStream.read(buff); //读取数据存储在buff数组中
//                    final int len =  inStream.read(buff);
//                    runOnUiThread(new Runnable()//不允许其他线程直接操作组件，用提供的此方法可以
//                    {
//                        public void run() {
//// TODO Auto-generated method stub
//                            if (len > 0) {
//                                etReceived.setText(new String(buff, 0, len));
//                            }
//                        }
//                    });
//                        System.out.println("buff receive :"+buff.length);
                    processBuffer(buff,1024);
                    //System.out.println("receive content:"+ReceiveData);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        private void processBuffer(byte[] buff,int size)
        {
            int length=0;
            for(int i=0;i<size;i++)
            {
                if(buff[i]>'\0')
                {
                    length++;
                }
                else
                {
                    break;
                }
            }
//          System.out.println("receive fragment size:"+length);
            byte[] newbuff=new byte[length];  //newbuff字节数组，用于存放真正接收到的数据

            for(int j=0;j<length;j++)
            {
                newbuff[j]=buff[j];
            }
           ReceiveData=ReceiveData+new String(newbuff);
//            Log.e("Data",ReceiveData);
//          System.out.println("result :"+ReceiveData); //
            //根据数据长度
            if((ReceiveData.indexOf('a')>=0)&&(ReceiveData.indexOf('f')>=0)) {
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);  //发送消息:系统会自动调用handleMessage( )方法来处理消息
            }
//            if(ReceiveData.length()>12) {
//                Message msg = Message.obtain();
//                msg.what = 1;
//                handler.sendMessage(msg);  //发送消息:系统会自动调用handleMessage( )方法来处理消息
//            }
        }

    }

    //更新界面的Handler类
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
                String buf;
            switch(msg.what){
                case 1:
                    etReceived.setText(ReceiveData);//显示处理
//                    numbe++;
//                    buf =String.valueOf(numbe);
//                    etname.setText(buf);
                    data_handle();
                    ReceiveData=ReceiveData.substring(0,0);

                    break;
            }
        }
    }
  int mode =0,buf[];
    private void data_handle()
    {

      int box;
      int lenth = ReceiveData.length();
      int addres = ReceiveData.indexOf('M');
      int addres1 = ReceiveData.indexOf('T');
     String name = ReceiveData.substring(1,addres);
     String time = ReceiveData.substring(addres+1,addres1);
     String DATA = ReceiveData.substring(addres1+1,lenth-3);//去掉f/r/n
      box=Integer.valueOf(name);
     etdata.setText(DATA+"g");
     etSend.setText(time);
     choose(box);
     String save = time+" "+dataget+" "+DATA+"g";//保存数据格式
        if(flag_contrast==true)
        {
            if (mode==0)
            {
                mode++;
                buf[0]=Integer.valueOf(DATA);
                Compare.last_weight.setText(DATA+"g");
            }
            else if (mode==1)
            {
                int temp;
                mode = 0;
                Compare.now_weight.setText(DATA+"g");
                temp = Integer.valueOf(DATA)-buf[0];
                Compare.diff_weight.setText(temp+"g"); //
            }

        }
     appUtil.saveFile(save);//保存数据 格式
    }
    private void choose(int num)
    {
        if(num==1)   {dataget="苹果";etname.setText("苹果");}
        else if(num==2)  {dataget="西瓜";etname.setText("西瓜");}
        else if (num==3)  {dataget="香蕉";etname.setText("香蕉");}
        else if(num==4)  {dataget="橙子";etname.setText("橙子");}
        else if(num==5)  {dataget="瓜子";etname.setText("瓜子");}
        else if(num==6)  {dataget="芒果";etname.setText("芒果");}
        else if(num==7)  {dataget="也是";etname.setText("也是");}
        else if(num==8)  {dataget="你好";etname.setText("你好");}
        else if(num==9)  {dataget="哎收";etname.setText("哎收");}
        else if(num==10)  {dataget="嘻嘻";etname.setText("嘻嘻");}
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        try {
            if(rThread!=null)
            {

                btSocket.close();
                btSocket=null;

                rThread.join();
            }

            this.finish();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
   private  void  data_control(String s)
   {
           try{
               //指令校准功能
               String  msgBuffer = "fa02"+s+"fc";
               outStream=btSocket.getOutputStream();
               outStream.write(msgBuffer.getBytes());
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }

   }
}
