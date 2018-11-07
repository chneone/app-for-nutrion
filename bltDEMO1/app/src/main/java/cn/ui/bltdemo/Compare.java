package cn.ui.bltdemo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.ui.bltdemo.MainActivity;
/**
 * Created by ucchip on 2018/11/6.
 */

public class Compare extends AppCompatActivity {

    Button fanhui;
    public static EditText last_weight;
    public static EditText  now_weight;
    public static EditText  diff_weight;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
        fanhui=(Button)this.findViewById(R.id.button_return);
        last_weight=(EditText)this.findViewById(R.id.weightlast_edit);
        now_weight=(EditText)this.findViewById(R.id.weightnow_edit);
        diff_weight=(EditText)this.findViewById(R.id.weightdif_edit);

    }
        public void back_click (View view) {
            try{
                Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
                MainActivity.flag_contrast =false;
                intent.putExtra("data","null");
                setResult(0,intent);//主ID code
                finish();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    }


}
