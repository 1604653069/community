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

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ForgetActivity2 extends AppCompatActivity {
    private EditText password;
    private EditText password2;
    private Button btn;
    private TextView title;
    private ImageView back;
    private ImageView showBtn;
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
                        Toast.makeText(ForgetActivity2.this, "设置成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgetActivity2.this,LoginActivity.class));
                        finish();
                    }else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(ForgetActivity2.this, message, Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget2);
        noTitle();
        initView();
        initListener();
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals(password2.getText().toString())){
                    Toast.makeText(ForgetActivity2.this, "两次填写密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    String tel = intent.getStringExtra("tel");
                    RequestBody body = new FormBody.Builder()
                            .add("tel",tel)
                            .add("password",password.getText().toString())
                            .build();
                    OkHttpUtils.post(body, Param.FORGET_URL,handler);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetActivity2.this,ForgetActivity.class));
                finish();
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    private void initView() {
        password = this.findViewById(R.id.forget_password);
        password2 = this.findViewById(R.id.forget_password2);
        btn = this.findViewById(R.id.btn_forget);
        title = this.findViewById(R.id.title_text);
        back = this.findViewById(R.id.title_back);
        showBtn = this.findViewById(R.id.show_pwd2);
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
