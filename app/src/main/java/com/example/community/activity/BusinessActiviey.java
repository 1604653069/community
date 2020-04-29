package com.example.community.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.Pay;
import com.example.community.entity.Wallet;
import com.example.community.fragments.ElecFragment;
import com.example.community.fragments.PropFragment;
import com.example.community.fragments.WaterFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class BusinessActiviey extends BaseActivity {
    private ImageView back;
    private TextView title;
    private String[] titles ={"水费","电费","物业费"};
    private TabLayout tabLayout;
    private List<Pay.PaysBean> paysBeans1;
    private List<Pay.PaysBean> paysBeans2;
    private List<Pay.PaysBean> paysBeans3;
    private Wallet wallet;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG","数据请求成功"+msg.obj.toString());
                    Pay pay = JSON.parseObject(msg.obj.toString(), Pay.class);
                     paysBeans1 = new ArrayList<>();
                     paysBeans2= new ArrayList<>();
                     paysBeans3 = new ArrayList<>();
                    if(pay.isSuccess()){
                        for(int i=0;i<pay.getPays().size();i++){
                            if(pay.getPays().get(i).getType()==0){
                                paysBeans1.add(pay.getPays().get(i));
                            }else if(pay.getPays().get(i).getType()==1){
                                paysBeans2.add(pay.getPays().get(i));
                            }else if(pay.getPays().get(i).getType()==2){
                                paysBeans3.add(pay.getPays().get(i));
                            }
                        }
                        /*默认选中水费界面*/
                        getSupportFragmentManager().beginTransaction().replace(R.id.pay_test,new WaterFragment(paysBeans1)).commit();
                    }else{
                        /*默认选中水费界面*/
                        getSupportFragmentManager().beginTransaction().replace(R.id.pay_test,new WaterFragment(paysBeans1)).commit();
                    }
                    Toast.makeText(getApplicationContext(), "数据请求成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    wallet = JSON.parseObject(msg.obj.toString(),Wallet.class);
                    Param.money = wallet.getMoney();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        noTitle();
        initView();
        initData();
        initListener();
    }

    private void initData() {
        /*服务器登录请求地址*/
        RequestBody body = new FormBody.Builder()
                .build();
        OkHttpUtils.post(body, Param.GETALLPAYS_URL,handler);
        RequestBody body2 = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body2,Param.GETUSERWALLET_URL,handler,2);
    }

    private void initView() {
        tabLayout = findViewById(R.id.pay_tab);
        back  = this.findViewById(R.id.title_back);
        title = this.findViewById(R.id.title_text);
        title.setText("缴费订单");
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
    }
    /*控件绑定事件*/
    private void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.pay_test,new WaterFragment(paysBeans1)).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.pay_test,new ElecFragment(paysBeans2)).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.pay_test,new PropFragment(paysBeans3)).commit();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    /*沉浸模式*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
