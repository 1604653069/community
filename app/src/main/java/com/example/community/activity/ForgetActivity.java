package com.example.community.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ForgetActivity extends AppCompatActivity {
    private TextView title;
    private ImageView back;
    private Button nextBtn;
    private EditText tel;
    private Intent intent;
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
                        startActivity(intent);
                        finish();
                    }else{
                        String message = jsonObject.getString("message");
                        Toast.makeText(ForgetActivity.this,message , Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
        initListener();
        noTitle();
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
                finish();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tel.getText().toString();
                if(Utils.isPhone(s)){
                    intent = new Intent(ForgetActivity.this, ForgetActivity2.class);
                    intent.putExtra("tel",s);
                    RequestBody body = new FormBody.Builder()
                            .add("tel", tel.getText().toString())
                            .build();
                    OkHttpUtils.post(body, Param.GETUSERBYTEL_URL,handler);
                }else{
                    Toast.makeText(ForgetActivity.this, "手机号码格式有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title = this.findViewById(R.id.title_text);
        back = this.findViewById(R.id.title_back);
        nextBtn = this.findViewById(R.id.btn_next);
        tel = this.findViewById(R.id.regist_tel);
        title.setText("找回密码");
    }

    /*修改标题栏*/
    private void noTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}

