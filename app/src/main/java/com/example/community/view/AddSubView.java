package com.example.community.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.community.R;

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private ImageView sub;
    private ImageView add;
    private TextView count;
    private int currentValue=1;
    private int maxValue=10;
    private int minValue=1;
    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.add_sub_view,this);
        sub = findViewById(R.id.img_sub);
        add = findViewById(R.id.img_add);
        count = findViewById(R.id.tv_count);
        int value = getCurrentValue();
        setCurrentValue(value);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_sub:
                subCount();
                break;
            case R.id.img_add:
                addCount();
                break;
        }
    }

    private void addCount() {
        if(currentValue<maxValue){
            currentValue++;
           this.count.setText(currentValue+"");
           if(onCountChangeListener!=null){
               onCountChangeListener.onCountChangeLisnter(currentValue);
           }
        }
    }

    private void subCount() {
        if(currentValue>minValue){
            currentValue--;
            this.count.setText(currentValue+"");
            if(onCountChangeListener!=null){
                onCountChangeListener.onCountChangeLisnter(currentValue);
            }
        }
    }

    public int getCurrentValue() {
        String valueStr = this.count.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            currentValue = Integer.parseInt(valueStr);
        }
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        this.count.setText(this.currentValue+"");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    public interface OnCountChangeListener{
        public void onCountChangeLisnter(int value);
    }
    private OnCountChangeListener onCountChangeListener;
    public void setOnCountChangeListener(OnCountChangeListener onCountChangeListener) {
        this.onCountChangeListener = onCountChangeListener;
    }
}
