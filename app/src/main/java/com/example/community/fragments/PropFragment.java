package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.adapter.WaterAdapter;
import com.example.community.entity.Pay;
import com.example.community.entity.PayItem;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PropFragment extends Fragment {
    private ListView listView;
    private WaterAdapter adapter;
    private List<Pay.PaysBean> paysBeans;
    public PropFragment(){}
    public PropFragment(List<Pay.PaysBean> paysBeans){
        this.paysBeans = paysBeans;
    }
    private List<PayItem> payItems = new ArrayList<>();
    private int count=0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    count++;
                    Log.i("TAG","用户的订单项:"+msg.obj.toString());
                    PayItem payItem = JSON.parseObject(msg.obj.toString(), PayItem.class);
                    payItems.add(payItem);
                    if(count==paysBeans.size()){
                        adapter = new WaterAdapter(paysBeans,payItems,getContext());
                        listView.setAdapter(adapter);
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prop_fragment,container,false);
        initView(view);
        initDate();
        return view;
    }

    private void initDate() {
        /*服务器登录请求地址*/
        for(int i=0;i<paysBeans.size();i++){
            RequestBody body = new FormBody.Builder()
                    .add("user.uid", Param.user.getUid())
                    .add("pay.pid",paysBeans.get(i).getPid())
                    .add("state",0+"")
                    .build();
            OkHttpUtils.post(body, Param.GETPAYITEM_URL,handler);
        }

    }
    private void initView(View view) {
        listView = view.findViewById(R.id.prop_listview);
    }

}
