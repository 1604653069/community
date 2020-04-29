package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;
import com.example.community.fragment.MineFragment;


/**
 * 充值界面
 */
public class RechargeFragment extends Fragment {
    private TextView  title;
    private ImageView back;
    private Button btnChongzhi;
    private TextView myMoney;
    private int money;
    private TextView history;
    public RechargeFragment(int money){
        this.money = money;
    }
    public RechargeFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charge,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        myMoney = view.findViewById(R.id.tv_mymoney);
        myMoney.setText("￥"+money+".00元");
        title.setText("余额");
        history = view.findViewById(R.id.tv_history);
        btnChongzhi =view.findViewById(R.id.btn_chongzhi);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new HistoryFragment()).commit();
            }
        });
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new WalletFragment(money)).commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }
}
