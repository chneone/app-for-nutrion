package cn.ui.bltdemo;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

class ContentAdapter extends BaseAdapter implements OnClickListener {

    private static final String TAG = "ContentAdapter";
    private List<String> mContentList;
    private LayoutInflater mInflater;
    private Callback mCallback;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     * @author Ivan Xu
     * 2014-11-26
     */
    public interface Callback {
        public void click(View v);
    }

    public ContentAdapter(Context context, List<String> contentList,
                          Callback callback) {
        mContentList = contentList;
        mInflater = LayoutInflater.from(context);
        mCallback = callback;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem");
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView");
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.addres, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textView1);
            holder.button = (Button) convertView.findViewById(R.id.button1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mContentList.get(position));


        holder.button.setOnClickListener(this);
        holder.button.setTag(position);
        return convertView;
    }

    public class ViewHolder {
        public TextView textView;
        public Button button;
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}