package cn.ui.bltdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ucchip on 2018/11/6.
 */
public class Msgset extends AppCompatActivity {
    String databuf="";
     EditText d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;
    dataapter appUtil;

        // 模拟listview中加载的数据
        private List<String> contentList;
        private ListView mListView;
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.megset);
        appUtil  = (dataapter) Msgset.this.getApplication();
        init();
    }
    private void init(){
        d1=(EditText)this.findViewById(R.id.edit_1);
        d2=(EditText)this.findViewById(R.id.edit_2);
        d3=(EditText)this.findViewById(R.id.edit_3);
        d4=(EditText)this.findViewById(R.id.edit_4);
        d5=(EditText)this.findViewById(R.id.edit_5);
        d6=(EditText)this.findViewById(R.id.edit_6);
        d7=(EditText)this.findViewById(R.id.edit_7);
        d8=(EditText)this.findViewById(R.id.edit_8);
        d9=(EditText)this.findViewById(R.id.edit_9);
        d10=(EditText)this.findViewById(R.id.edit_10);
        d1.setText( MainActivity.devcie_name[0]);
        d2.setText( MainActivity.devcie_name[1]);
        d3.setText( MainActivity.devcie_name[2]);
        d4.setText( MainActivity.devcie_name[3]);
        d5.setText( MainActivity.devcie_name[4]);
        d6.setText( MainActivity.devcie_name[5]);
        d7.setText( MainActivity.devcie_name[6]);
        d8.setText( MainActivity.devcie_name[7]);
        d9.setText( MainActivity.devcie_name[8]);
        d10.setText( MainActivity.devcie_name[9]);
    }


    public void fanhui_click(View view) {
        try {
            Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
            intent.putExtra("data","null");
            setResult(0,intent);//主ID code
            finish();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void baocun_click(View view) {
        try {
            MainActivity.devcie_name[9]=d10.getText().toString();
            MainActivity.devcie_name[8]=d9.getText().toString();
            MainActivity.devcie_name[7]=d8.getText().toString();
            MainActivity.devcie_name[6]=d7.getText().toString();
            MainActivity.devcie_name[5]=d6.getText().toString();
            MainActivity.devcie_name[4]=d5.getText().toString();
            MainActivity.devcie_name[3]=d4.getText().toString();
            MainActivity.devcie_name[2]=d3.getText().toString();
            MainActivity.devcie_name[1]=d2.getText().toString();
            MainActivity.devcie_name[0]=d1.getText().toString();
            databuf="!"+d1.getText().toString()+"@"+d2.getText().toString()+"#"+d3.getText().toString()+"$"+d4.getText().toString()+"%"+d5.getText().toString()+"^"+d6.getText().toString()+"&"+d7.getText().toString()+"*"+d8.getText().toString()+"("+d9.getText().toString()+")"+d10.getText().toString();
            appUtil.savedevice(databuf);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    /**
//     * 响应ListView中item的点击事件
//     */
//    @Override
//    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
//
//        //主要就通过底下的方法进行的
//        View view1 =  mListView .getChildAt(position);
//        ContentAdapter.ViewHolder viewHolder= (ContentAdapter.ViewHolder) view1.getTag();
//        viewHolder.editText.getText();
//      //  viewHolder.textView.setTextColor(Color.rgb(220,20,60));
//        Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position,
//                Toast.LENGTH_SHORT).show();
//
//    }
//    String address;
//    /**
//     * 接口方法，响应ListView按钮点击事件
//     */
//    @Override
//    public void click(View v) {
//        Toast.makeText(
//                Msgset.this, contentList.get((Integer) v.getTag()),
//                Toast.LENGTH_SHORT).show();
//        String contextr=contentList.get((Integer) v.getTag()).toString();
//        int lenght=contextr.length();
//        Toast.makeText(
//                Msgset.this, "地址是：",
//                Toast.LENGTH_SHORT).show();
//    }



}