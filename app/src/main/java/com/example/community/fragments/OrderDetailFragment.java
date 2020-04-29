package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.ShopCart;
import com.example.community.entity.Wallet;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class OrderDetailFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private ListView listView;
    private List<ShopCart.ItemsBean.ProductBean> productBeans;
    private TextView total;
    private TextView price;
    private TextView price2;
    private TextView number;
    private int money;
    private Button commit;
    private TextView name;
    private TextView tel;
    private TextView myAddress;
    private LinearLayout changeAddress;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    break;
            }
        }
    };
    public OrderDetailFragment(List<ShopCart.ItemsBean.ProductBean> productBeans){
        this.productBeans = productBeans;
    }
    public OrderDetailFragment(){}
    private Wallet wallet;
    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    wallet = JSON.parseObject(msg.obj.toString(),Wallet.class);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body,Param.GETUSERWALLET_URL,handler2);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.title_text);
        back = view.findViewById(R.id.title_back);
        name = view.findViewById(R.id.name);
        name.setText(Param.address.getName());
        tel = view.findViewById(R.id.tel);
        tel.setText(Param.address.getPhone());
        myAddress = view.findViewById(R.id.address);
        myAddress.setText(Param.address.getAddress());
        changeAddress = view.findViewById(R.id.change_address);
        listView = view.findViewById(R.id.fragment_order_detail_listview);
        total = view.findViewById(R.id.shop_order_detail_total);
        price = view.findViewById(R.id.shop_order_detail_price);
        price2 = view.findViewById(R.id.shop_order_detail_price2);
        number = view.findViewById(R.id.shop_order_detail_number);
        commit = view.findViewById(R.id.btn_order_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("user.uid",Param.user.getUid())
                        .add("total",money+"")
                        .add("address.aid",Param.address.getAid());
                for(int i=0;i<productBeans.size();i++){
                    builder.add("products["+i+"].pid",productBeans.get(i).getPid());
                }
                if(wallet.getMoney()>money){
                    builder.add("state",1+"");
                    FormBody build = builder.build();
                    OkHttpUtils.post(build, Param.COMMITORDER_URL,handler);
                    Toast.makeText(getContext(), "购买成功", Toast.LENGTH_SHORT).show();
                    RequestBody body = new FormBody.Builder()
                            .add("user.uid", Param.user.getUid())
                            .add("money",-money+"")
                            .build();
                    OkHttpUtils.post(body,Param.USERCHONGZHI,handler);
                }else {
                    builder.add("state",0+"");
                    FormBody build = builder.build();
                    OkHttpUtils.post(build, Param.COMMITORDER_URL,handler);
                    Toast.makeText(getContext(), "购买失败,您的余额不足", Toast.LENGTH_SHORT).show();
                }
                getFragmentManager().beginTransaction().replace(R.id.fl,new ShopingOrderFragment()).commit();
            }
        });
        for(int i=0;i<productBeans.size();i++){
            money+=productBeans.get(i).getShopPrice();
        }
        total.setText("￥"+money+".00");
        price.setText("￥"+money+".00");
        price2.setText("￥"+money+".00");
        number.setText("共"+productBeans.size()+"件");
        listView.setAdapter(new MyAdapter());
        title.setText("订单详情");
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment(true)).commit();
            }
        });
    }
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return productBeans.size();
        }

        @Override
        public Object getItem(int i) {
            return productBeans.get(i);
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.shop_order_listview_item,null);
                viewHolder.img = view.findViewById(R.id.shop_order_detail_img);
                viewHolder.title = view.findViewById(R.id.shop_order_detail_title);
                viewHolder.count = view.findViewById(R.id.shop_order_detail_count);
                viewHolder.price = view.findViewById(R.id.shop_order_detail_price);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            Picasso.with(getContext()).load(productBeans.get(i).getPImage()).into(viewHolder.img);
            viewHolder.title.setText(productBeans.get(i).getPname());
            viewHolder.count.setText("x1");
            viewHolder.price.setText("￥"+productBeans.get(i).getShopPrice());
            return view;
        }
    }
    class ViewHolder{
        ImageView img;
        TextView title;
        TextView count;
        TextView price;
    }

}
