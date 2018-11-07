package cn.ui.bltdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class savedata extends AppCompatActivity {
    private Button turn;
    private ListView Viewdata;
    private TextView textView1;
    dataapter appUtil;
    String data_all;
    private List<String> data;
//    private String[] data=new String[50];
    int location[] =new int[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datashow);
        data = new ArrayList<String>();
        Viewdata=(ListView)findViewById(R.id.listview) ;
        textView1=(TextView) findViewById(R.id.textView) ;
        turn = (Button) findViewById(R.id.button);
        appUtil  = (dataapter) savedata.this.getApplication();
         data_all=  appUtil.readFile();//
        textView1.setText( data_all);
        result_data();
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(savedata.this,android.R.layout.simple_list_item_1,data);
//             Viewdata.setAdapter(adapter);

    }

    public void  return_click(View v) {
        try {
            Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
//            intent.putExtra("data", 0);
//            setResult(0, intent);//主ID code
//            finish();
            intent.putExtra("data","");
            setResult(0,intent);//主ID code
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
   删除记录
     */
    public void delete_click(View v)
    {
        try{
            appUtil.removeFile();
            textView1.setText(appUtil.readFile());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    private void result_data()
    {
        int strlen =data_all.length();
        int s1;
        location[0]=data_all.indexOf('s');
        strlen = (strlen-location[0])/11;
        if(location[0]>=0) {//大于才执行
            for (int i=0;i<strlen;i++)
            {
               data.add( data_all.substring(location[0]+i*12+1,location[0]+(i+1)*12+1));
            }
       //     Viewdata.setAdapter(new ContentAdapter(this,  data, this));
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(savedata.this,android.R.layout.simple_list_item_1,data);
           Viewdata.setAdapter(adapter);
        }
//        if(location[0]>=0) {//大于才执行
//            s1=charget();//g的个数
//            location[0]=location[0]-1;//补差回来
//            for (int i=0;i<4;i++)
//            {
//                data[i]= data_all.substring(location[i]+1,location[i+1]);
//            }
//            textView1.setText("dizi"+ data[0]+ data[1]+ data[2]+ data[3]);
//        }

    }
     private int charget()
     {
         int temp[] = new int[50];
         int temp_loc[] = new int[50];
         temp_loc[0] =data_all.indexOf('g');//第1个
         temp[0]=0;
         if(temp_loc[0]>=0) {
             for (int i = 1; i <= 10; i++) {
                 temp_loc[i]= data_all.indexOf('g', temp[i - 1]);
                 temp[i] = temp_loc[i];
                    location[i]= location[i]+temp_loc[i];
                 if (temp[i]< 0)
                     return i;
             }
         }
         else return 0;
    return 10;
     }

}