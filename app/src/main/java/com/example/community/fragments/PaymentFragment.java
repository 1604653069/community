package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;
import com.example.community.fragment.IndexFragment;
import com.google.android.material.tabs.TabLayout;


public class PaymentFragment extends Fragment {
    private ImageView back;
    private TextView title;
    private TabLayout tabLayout;
    private String[] titles ={"水费","电费","物业费"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pay_fragment,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }
    /*控件绑定事件*/
    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.pay_test,new WaterFragment()).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.pay_test,new ElecFragment()).commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().replace(R.id.pay_test,new PropFragment()).commit();
                        break;
                }
           }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        /*默认选中水费界面*/
        getFragmentManager().beginTransaction().replace(R.id.pay_test,new WaterFragment()).commit();
    }
    /*数据初始化*/
    private void initData() {
        title.setText("生活缴费");
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
    }
    /*控件初始化*/
    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        tabLayout = view.findViewById(R.id.pay_tab);
    }

}
