package cn.ui.bltdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ucchip on 2018/11/6.
 */
public class Msgset extends AppCompatActivity {

        // 模拟listview中加载的数据
        private List<String> contentList;
        private ListView mListView;


       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.megset);
//           init();

//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(savedata.this,android.R.layout.simple_list_item_1,data);
//             Viewdata.setAdapter(adapter);

    }
//    private void init(){
//        mListView = (ListView) findViewById(R.id.listview);
//        contentList = new ArrayList<String>();
//        Toast.makeText(getApplicationContext(),"Showing list label",
//                Toast.LENGTH_SHORT).show();
//
//        mListView.setAdapter(new ContentAdapter(this,  contentList, this));
//        mListView.setOnItemClickListener(this);
//    }
//    @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    /**
//     * 响应ListView中item的点击事件
//     */
//    @Override
//    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
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
//                address.this, contentList.get((Integer) v.getTag()),
//                Toast.LENGTH_SHORT).show();
//        String contextr=contentList.get((Integer) v.getTag()).toString();
//        int lenght=contextr.length();
//        int num=contextr.indexOf(":");
//        address=contextr.substring(num-2);
//        Toast.makeText(
//                address.this, "地址是："+address,
//                Toast.LENGTH_SHORT).show();
//        try {
//            Intent intent = new Intent();
////        　　　　　　startActivity(intent);
////        　　　　    intent.putExtra("data", content);
////　　　　　       　setResult(2,data);
//            intent.putExtra("data",address);
//            setResult(0,intent);//主ID code
//            finish();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}