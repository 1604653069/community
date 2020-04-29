package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.History;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class HistoryFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private ListView listView;
    private List<History> histories;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    histories = JSON.parseArray(msg.obj.toString(),History.class);
                    listView.setAdapter(new MyAdapter());
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        RequestBody body = new FormBody.Builder()
                /*提交登陆密码*/
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body,Param.GETALLUSERHISTORY_URL,handler);
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.balance_listview);
        title = view.findViewById(R.id.title_text);
        title.setText("充值记录");
        back = view.findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return histories.size();
        }

        @Override
        public Object getItem(int i) {
            return histories.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.balance_item,null);
                viewHolder.yue = view.findViewById(R.id.balance_item_yue);
                viewHolder.time = view.findViewById(R.id.balance_item_time);
                viewHolder.money = view.findViewById(R.id.balance_item_money);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.yue.setText("余额:"+histories.get(i).getMoney());
            viewHolder.time.setText(histories.get(i).getTime()+"");
            viewHolder.money.setText("+"+histories.get(i).getBalance());
            return view;
        }
    }
    class ViewHolder{
        TextView yue;
        TextView time;
        TextView money;
    }
}
