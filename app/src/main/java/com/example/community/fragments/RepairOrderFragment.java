package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.entity.MyRepair;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RepairOrderFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private ListView listView;
    private MyAdapter adapter;
    private List<MyRepair> myRepairs;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    JSONObject jsonObject = JSONObject.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(getContext(), "数据获取成功", Toast.LENGTH_SHORT).show();
                        myRepairs = JSON.parseArray(jsonObject.getString("repairs"), MyRepair.class);
                        adapter = new MyAdapter();
                        listView.setAdapter(adapter);
                    }else{
                        Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repair_order_layout,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        /*服务器登录请求地址*/
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body, Param.GETUSERALLREPAIR_URL,handler);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.title_text);
        back = view.findViewById(R.id.title_back);
        listView =view.findViewById(R.id.repair_order_listview);
        title.setText("报修订单");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
        listView.setDividerHeight(0);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myRepairs.size();
        }

        @Override
        public Object getItem(int i) {
            return myRepairs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler = null;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.repair_item,null);
                viewHodler = new ViewHodler();
                viewHodler.photo = view.findViewById(R.id.repair_item_img);
                viewHodler.title = view.findViewById(R.id.repair_item_title);
                viewHodler.time = view.findViewById(R.id.repair_item_time);
                viewHodler.type_imt = view.findViewById(R.id.repair_item_img_type);
                viewHodler.content = view.findViewById(R.id.repair_item_content);
                viewHodler.repair_item_type = view.findViewById(R.id.repair_item_type);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
           if(myRepairs.get(i).getRepairPhotos().size()>0){
               Log.i("TAG","上传的报修有图片,地址为:"+myRepairs.get(i).getRepairPhotos().get(0).getPath());
               Picasso.with(getContext()).load(myRepairs.get(i).getRepairPhotos().get(0).getPath()).into(viewHodler.photo);
           }
           viewHodler.time.setText(myRepairs.get(i).getDatetime());
           if(myRepairs.get(i).getState()==0){
               Picasso.with(getContext()).load(R.mipmap.wait).into(viewHodler.type_imt);
               viewHodler.repair_item_type.setText("等待中");
               viewHodler.repair_item_type.setTextColor(Color.parseColor("#f4ea2a"));
           }else if(myRepairs.get(i).getState()==1){
               viewHodler.repair_item_type.setText("派遣中");
               Picasso.with(getContext()).load(R.mipmap.run).into(viewHodler.type_imt);
               viewHodler.repair_item_type.setTextColor(Color.parseColor("#1296db"));
           }else if(myRepairs.get(i).getState()==2){
               Picasso.with(getContext()).load(R.mipmap.done).into(viewHodler.type_imt);
               viewHodler.repair_item_type.setText("已完成");
               viewHodler.repair_item_type.setTextColor(Color.parseColor("#34ba63"));
           }
            viewHodler.content.setText(myRepairs.get(i).getQuestion());
            return view;
        }
    }
    class ViewHodler{
        ImageView photo;
        TextView title;
        TextView time;
        ImageView type_imt;
        TextView content;
        TextView repair_item_type;
    }
}
