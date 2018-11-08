package cn.ui.bltdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ucchip on 2018/11/6.
 */
public class Msglogin extends AppCompatActivity {

    Button queding,quxiao;
    EditText bianhao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meglogin);
        bianhao=(EditText)this.findViewById(R.id.bianhao_edit);
        queding=(Button)this.findViewById(R.id.button_save);
        quxiao=(Button)this.findViewById(R.id.button_loss);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(savedata.this,android.R.layout.simple_list_item_1,data);
//             Viewdata.setAdapter(adapter);
    }

    public void loss_click(View view) {
        try{
            Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
            intent.putExtra("data","null");
            setResult(0,intent);//主ID code
            finish();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void savedata_click(View view) {
        try{
            Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
            String  temp;
          int a = Integer.parseInt(  bianhao.getText().toString());
            if (a<10)
            {
                temp="0"+bianhao.getText().toString();
            }
            else temp=bianhao.getText().toString();
            intent.putExtra("data",temp);
            setResult(0,intent);//主ID code
            finish();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}