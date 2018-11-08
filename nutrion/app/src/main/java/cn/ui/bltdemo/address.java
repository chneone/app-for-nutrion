package cn.ui.bltdemo;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.ui.bltdemo.ContentAdapter.Callback;
public class address extends Activity implements AdapterView.OnItemClickListener,
        Callback {

    // 模拟listview中加载的数据
    private List<String> contentList;
    private ListView mListView;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addres);
        init();
    }

    private void init() {
        BA = BluetoothAdapter.getDefaultAdapter();
        mListView = (ListView) findViewById(R.id.listview);
        //获取设备列表
        pairedDevices =BA.getBondedDevices();
        contentList = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices)
            contentList.add(bt.getName()+" "+bt.getAddress() );
        Toast.makeText(getApplicationContext(),"Showing Paired Devices",
                Toast.LENGTH_SHORT).show();
//            final ArrayAdapter adapter = new ArrayAdapter
//                    (this,android.R.layout.simple_list_item_1, list);
//            mListView.setAdapter(adapter);
        mListView.setAdapter(new ContentAdapter(this,  contentList, this));
        mListView.setOnItemClickListener(this);
//        contentList = new ArrayList<String>();
//        for (int i = 0; i < CONTENTS.length; i++) {
//            contentList.add(CONTENTS[i]);
//        }
//        //
//        mListView.setAdapter(new ContentAdapter(this, contentList, this));
//        mListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 响应ListView中item的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
        Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position,
                Toast.LENGTH_SHORT).show();

    }
    String address;
    /**
     * 接口方法，响应ListView按钮点击事件
     */
    @Override
    public void click(View v) {
        Toast.makeText(
                address.this, contentList.get((Integer) v.getTag()),
                Toast.LENGTH_SHORT).show();
        String contextr=contentList.get((Integer) v.getTag()).toString();
        int lenght=contextr.length();
        int num=contextr.indexOf(":");
        address=contextr.substring(num-2);
        Toast.makeText(
                address.this, "地址是："+address,
                Toast.LENGTH_SHORT).show();
        try {
            Intent intent = new Intent();
//        　　　　　　startActivity(intent);
//        　　　　    intent.putExtra("data", content);
//　　　　　       　setResult(2,data);
            intent.putExtra("data",address);
            setResult(0,intent);//主ID code
            finish();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
