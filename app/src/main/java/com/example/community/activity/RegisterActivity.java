package com.example.community.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private ImageView back;
    private EditText username;
    private EditText password;
    private EditText secPaassword;
    private Button retistBtn;
    private ImageView showBtn;
    private EditText tel;
    private boolean flag;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG",msg.obj.toString());
                    JSONObject jsonObject = JSONObject.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }else{
                        String message = jsonObject.getString("message");
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        noTitle();
        initView();
        initListener();
    }

    private void initListener() {
        showBtn.setOnClickListener(this);
        retistBtn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initView() {
        title = this.findViewById(R.id.title_text);
        back = this.findViewById(R.id.title_back);
        username = this.findViewById(R.id.regist_username);
        password = this.findViewById(R.id.regist_password);
        retistBtn = this.findViewById(R.id.btn_regist);
        showBtn = this.findViewById(R.id.show_pwd);
        secPaassword = this.findViewById(R.id.regist_sec_password);
        tel = this.findViewById(R.id.regist_tel);
        title.setText("用户注册");
    }

    /*修改标题栏*/
    private void noTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_pwd:
                TransformationMethod method = password.getTransformationMethod();
                if (method == HideReturnsTransformationMethod.getInstance()) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showBtn.setImageResource(R.mipmap.eye);
                } else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showBtn.setImageResource(R.mipmap.eye2);
                }
                // 保证切换后光标位于文本末尾
                Spannable spanText = password.getText();
                if (spanText != null) {
                    Selection.setSelection(spanText, spanText.length());
                }
                break;
            case R.id.btn_regist:
                if(tel.getText().toString().length()<=0||username.getText().toString().length()<=0||password.getText().toString().length()<=0||secPaassword.getText().toString().length()<=0){
                    Toast.makeText(this, "信息输入不全", Toast.LENGTH_SHORT).show();
                }else if(!password.getText().toString().equals(secPaassword.getText().toString())){
                    Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                }else if(!Utils.isPhone(tel.getText().toString())){
                    Toast.makeText(this, "你的手机号码格式有误", Toast.LENGTH_SHORT).show();
                } else{
                    /*服务器登录请求地址*/
                    RequestBody body = new FormBody.Builder()
                            /*提交用户名*/
                            .add("username", username.getText().toString())
                            /*提交登陆密码*/
                            .add("password", password.getText().toString())
                            .add("tel",tel.getText().toString())
                            .add("state",0+"")
                            .add("type",0+"")
                            .build();
                    OkHttpUtils.post(body, Param.USERREGIST_URL,handler);
                }
                break;
            case R.id.title_back:
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                break;
        }
    }
}
