package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.Auth;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AuthFragment2 extends Fragment {
    private TextView title;
    private ImageView back;
    private Button btn;
    private TextView auth_address;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG",msg.obj.toString());
                    Auth auth = JSON.parseObject(msg.obj.toString(), Auth.class);
                    if(auth.getAuth().getState()==2){
                        Toast.makeText(getContext(), "认证成功", Toast.LENGTH_SHORT).show();
                        auth_address.setText(auth.getAuth().getAddress());
                        btn.setText("认证成功");
                        btn.setEnabled(false);
                    }else if(auth.getAuth().getState()==1){
                        auth_address.setText(auth.getAuth().getAddress());
                        btn.setText("等待认证中...");
                        btn.setEnabled(false);

                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth2_fragment,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body,Param.GETMYAUTH_URL,handler);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.title_text);
        back = view.findViewById(R.id.title_back);
        auth_address = view.findViewById(R.id.auth_address);
        title.setText("我的小区");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
        btn = view.findViewById(R.id.btn_sure);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new AuthFragment()).commit();
            }
        });
    }
}
