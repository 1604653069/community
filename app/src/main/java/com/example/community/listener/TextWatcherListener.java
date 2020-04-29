package com.example.community.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.community.R;

public class TextWatcherListener implements TextWatcher {
    private Context context;
    private String content;
    private EditText name;
    private EditText phone;
    private EditText address;
    private EditText address2;
    private Button btn;
    public TextWatcherListener(Context context,EditText name,EditText phone,EditText address,EditText address2,Button btn){
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.address2 = address2;
        this.btn = btn;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
       this.content =  charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(this.name.getText().toString().length()>0&&this.phone.getText().toString().length()>0&&this.address.getText().toString().length()>0&&this.address2.getText().toString().length()>0){
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.btn);
        }else{
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.btn_bg_enable_press);
        }
    }
}
