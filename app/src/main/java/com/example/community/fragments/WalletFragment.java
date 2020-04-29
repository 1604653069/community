package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.ApliyUtils;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.PayResult;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class WalletFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private GridView gridView;
    private TextView grechange_tv;
    private TextView username;
    private int[] money = {10,20,30,50,100,200,300,500};
    private boolean[] isChecked = new boolean[9];
    private MyAdapter adapter  = new MyAdapter();
    private Button btnChongzhi;
    private int position;
    private int balance;
    private static final int SDK_PAY_FLAG = 1;
    public WalletFragment(int balance){
        this.balance = balance;
    }
    public WalletFragment(){};
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                       // showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        RequestBody body = new FormBody.Builder()
                                .add("user.uid", Param.user.getUid())
                                .add("money",money[position]+"")
                                .build();
                        OkHttpUtils.post(body,Param.USERCHONGZHI,handler2);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                       // showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG","充值成功");
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet,container,false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApliyUtils.payV2(getActivity(),money[position],handler);
            }
        });
    }

    private void initView(View view) {
        gridView = view.findViewById(R.id.wallet_gridview);
        gridView.setAdapter(adapter);
        grechange_tv = view.findViewById(R.id.rechange_tv);
        username = view.findViewById(R.id.tv_username);
        username.setText(Param.user.getUid());
        btnChongzhi = view.findViewById(R.id.btn_chongzhi);
        title = view.findViewById(R.id.title_text);
        title.setText("金额充值");
        back = view.findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return money.length;
        }

        @Override
        public Object getItem(int i) {
            return money[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view ==null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.wallet_item,null);
                viewHolder.textView = view.findViewById(R.id.wallet_item_btn);
                view.setTag(viewHolder);
            }else{
                 viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.textView.setText(money[i]+"元");
            viewHolder.textView.setChecked(isChecked[i]);
            if(viewHolder.textView.isChecked()){
                viewHolder.textView.setBackgroundResource(R.drawable.radiobutton_checked);
                viewHolder.textView.setTextColor(Color.WHITE);
            }else{
                viewHolder.textView.setBackgroundResource(R.drawable.radiobutton_unchecked);
                viewHolder.textView.setTextColor(Color.parseColor("#34ba63"));
            }
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int index=0;index<isChecked.length;index++){
                        if(index==i){
                            isChecked[i] = true;
                            grechange_tv.setText(money[i]+"");
                            position = i;
                        }else {
                            isChecked[index] = false;
                        }
                    }
                    int count=0;
                    for(int i=0;i<isChecked.length;i++){
                        if(isChecked[i]==false)
                            count++;
                    }
                    if(count==isChecked.length){
                        btnChongzhi.setBackgroundResource(R.drawable.btn_bg_enable_press);
                        btnChongzhi.setEnabled(false);
                    }else{
                        btnChongzhi.setBackgroundResource(R.drawable.btn);
                        btnChongzhi.setEnabled(true);
                    }
                    MyAdapter.this.notifyDataSetChanged();
                }
            });
            return view;
        }
    }
    class ViewHolder{
        CheckBox textView;
    }
}
