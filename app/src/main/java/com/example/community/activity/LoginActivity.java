package com.example.community.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.entity.User;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.view.OwlView;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Dialog progressDialog;
    private TextView regist;
    private TextView forget;
    private OwlView mOwlView;
    private EditText email,password;
    private Button login;
    private EditText username;
    private User user =new User();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    /*关闭对话框*/
                    closeDialog();
                    /*转化成json数据转成Javabean*/
                    JSONObject jsonObject = JSONObject.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        User user = JSON.parseObject(jsonObject.getString("user"), User.class);
                        Param.user = user;
                        editor.putString("user",jsonObject.getString("user"));
                        editor.commit();
                        /*跳转到登陆界面*/
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    /*关闭对话框*/
                    closeDialog();
                    /*弹出登陆失败的信息*/
                    Toast.makeText(LoginActivity.this, "登陆失败了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /*控件初始化*/
        initView();
        /*去除标题栏*/
        noTitle();
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    /*密码框聚焦*/
                    mOwlView.open();

                }else{
                    /*密码框无聚焦*/
                    mOwlView.close();
                }
            }
        });
    }

    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2b90cf"));//将状态栏设置成透明色
            getWindow().setNavigationBarColor(Color.TRANSPARENT);//将导航栏设置为透明色
        }

    }
    /*控件的初始化*/
    public void initView(){
        mOwlView= (OwlView) findViewById(R.id.owl_view);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.email);
        regist = findViewById(R.id.tv_regist);
        forget = findViewById(R.id.tv_forget);
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
        forget.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*登录按钮*/
            case R.id.btn_login:
                /*请服务器提交登陆请求*/
                if(username.getText().toString().length()<=0){
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if(password.getText().toString().length()<=0){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    /*登陆请求*/
                    postLogin();
                    /*显示登陆的对话框*/
                    showDialog();
                }
                break;
                /*注册按钮*/
            case R.id.tv_regist:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
                finish();
                break;
        }
    }
    public void  postLogin(){
        /*服务器登录请求地址*/
        RequestBody body = new FormBody.Builder()
                /*提交用户名*/
                .add("username", username.getText().toString())
                /*提交登陆密码*/
                .add("password", password.getText().toString())
                .build();
        OkHttpUtils.post(body, Param.LOGINACTION_URL,handler);
    }
    /*显示登陆对话框*/
    public void showDialog(){
        progressDialog = new Dialog(LoginActivity.this, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("登录中...");
        progressDialog.show();
    }
    /*关闭对话框*/
    public void closeDialog(){
        progressDialog.dismiss();
    }

   /* public void myTest(){
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        String jsonString = JSON.toJSONString(user);
        RequestBody requestBody = (RequestBody) RequestBody.create(Param.JSON, jsonString);
        OkHttpUtils.postJson(requestBody,Param.LOGIN_URL,handler);
    }*/
}
