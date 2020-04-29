package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.Order;
import com.example.community.entity.Wallet;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShopingOrderFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"全部订单","待付款","待发货","待收货","已完成"};
    private List<Order> orders;
    private Wallet wallet;
    private int position;
    private ListView listView;
    private ShopOrderListViewAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        orders = JSON.parseArray(msg.obj.toString(),Order.class);
                        viewPager.setAdapter(new PagerAdapter() {
                            @Override
                            public int getCount() {
                                return titles.length;
                            }

                            @Override
                            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                                return view==object;
                            }

                            @NonNull
                            @Override
                            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.shop_order_listview,null);
                                 listView = view.findViewById(R.id.shop_order_listview2);
                                if(position!=0){
                                    List<Order> myOrders = new ArrayList<>();
                                    for(int i=0;i<orders.size();i++){
                                        if(orders.get(i).getState()==(position-1)){
                                            myOrders.add(orders.get(i));
                                        }
                                    }
                                    adapter = new ShopOrderListViewAdapter(myOrders);
                                    listView.setAdapter(adapter);
                                    container.addView(view);
                                }else{
                                    listView.setAdapter(new ShopOrderListViewAdapter(orders));
                                    container.addView(view);
                                }
                                return view;
                            }

                            @Override
                            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                                container.removeView((View) object);
                            }
                        });
                    }catch (Exception e){
                    }
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
           switch (msg.what){
               case 1:
                   wallet = JSON.parseObject(msg.obj.toString(), Wallet.class);
                   break;
               case 2:
                   break;
               case 3:
                   Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                   getFragmentManager().beginTransaction().replace(R.id.fl,new ShopingOrderFragment()).commit();
                   break;
               case 4:
                   Toast.makeText(getContext(), "确认收货成功", Toast.LENGTH_SHORT).show();
                   getFragmentManager().beginTransaction().replace(R.id.fl,new ShopingOrderFragment()).commit();
                   break;
           }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_order,container,false);
        initView(view);
        initDatas();
        initListener();
        return view;
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }

    private void initDatas() {
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body,Param.GETALLORDER_URL,handler);
        RequestBody body2 = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body2,Param.GETUSERWALLET_URL,handler2);
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.shop_order_tab);
        viewPager = view.findViewById(R.id.shop_order_viewpager);
        title = view.findViewById(R.id.title_text);
        title.setText("乐购订单");
        back = view.findViewById(R.id.title_back);
    }
    class ShopOrderListViewAdapter extends BaseAdapter{
        private List<Order> orders;
        public ShopOrderListViewAdapter(List<Order> orders){
            this.orders = orders;
        }
        @Override
        public int getCount() {
            return orders.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.shop_order_item,null);
                viewHolder.orderId = view.findViewById(R.id.shop_order_id);
                viewHolder.orderState = view.findViewById(R.id.shop_order_state);
                viewHolder.orderListView = view.findViewById(R.id.shop_order_listview);
                viewHolder.leftTime = view.findViewById(R.id.shop_order_lefttime);
                viewHolder.count = view.findViewById(R.id.shop_order_count);
                viewHolder.price = view.findViewById(R.id.shop_order_detail_price);
                viewHolder.commit = view.findViewById(R.id.shop_order_tv);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.orderId.setText("订单号:"+orders.get(i).getOid().toUpperCase());
            if(orders.get(i).getState()==1){
                viewHolder.orderState.setText("未发货");
                viewHolder.leftTime.setText("");
                viewHolder.commit.setVisibility(View.GONE);
                viewHolder.leftTime.setVisibility(View.GONE);
            }else if(orders.get(i).getState()==0)
            {
                viewHolder.orderState.setText("未支付");
                viewHolder.leftTime.setText("距离交易结束：23:59:59");
            }else if(orders.get(i).getState()==2){
                viewHolder.orderState.setText("待收货");
                viewHolder.leftTime.setText("");
                viewHolder.commit.setVisibility(View.VISIBLE);
                viewHolder.commit.setText("确认收货");
                viewHolder.leftTime.setVisibility(View.GONE);
            }else if(orders.get(i).getState()==3){
                viewHolder.orderState.setText("已完成");
                viewHolder.leftTime.setText("");
                viewHolder.commit.setVisibility(View.GONE);
                viewHolder.leftTime.setVisibility(View.GONE);
            }

            viewHolder.orderListView.setDividerHeight(0);
            viewHolder.count.setText("共"+orders.get(i).getOrderItems().size()+"件");
            viewHolder.price.setText("￥"+orders.get(i).getTotal());
            viewHolder.orderListView.setAdapter(new MyListViewAdapter(orders.get(i).getOrderItems()));
            viewHolder.commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(orders.get(i).getState()==0){
                        if(wallet.getMoney()>orders.get(i).getTotal()){
                            position = i;
                            RequestBody body2 = new FormBody.Builder()
                                    .add("oid",orders.get(i).getOid())
                                    .build();
                            OkHttpUtils.post(body2,Param.UPDATEORDER_URL,handler2,3);
                            RequestBody body = new FormBody.Builder()
                                    .add("user.uid", Param.user.getUid())
                                    .add("money",-(orders.get(i).getTotal())+"")
                                    .build();
                            OkHttpUtils.post(body,Param.USERCHONGZHI,handler2,2);
                        }else{
                            Toast.makeText(getContext(), "支付失败，余额不足", Toast.LENGTH_SHORT).show();
                        }
                    }else if(orders.get(i).getState()==2) {
                        RequestBody body2 = new FormBody.Builder()
                                .add("oid",orders.get(i).getOid())
                                .build();
                        OkHttpUtils.post(body2,Param.UPDATEORDER_URL,handler2,4);
                    }
                    
                }
            });
            return view;
        }
    }
    class ViewHolder{
        TextView orderId;
        TextView orderState;
        ListView orderListView;
        TextView leftTime;
        TextView count;
        TextView price;
        TextView commit;
    }
    class MyListViewAdapter extends BaseAdapter{
        List<Order.OrderItemsBean > orderItemsBeans;
        public MyListViewAdapter(List<Order.OrderItemsBean >orderItemsBeans){
            this.orderItemsBeans = orderItemsBeans;
        }
        @Override
        public int getCount() {
            return orderItemsBeans.size();
        }

        @Override
        public Object getItem(int i) {
            return orderItemsBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MyViewHolder viewHolder;
            if(view==null){
                viewHolder = new MyViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.shop_order_listview_item,null);
                viewHolder.shopImg = view.findViewById(R.id.shop_order_detail_img);
                viewHolder.title = view.findViewById(R.id.shop_order_detail_title);
                viewHolder.count = view.findViewById(R.id.shop_order_detail_count);
                viewHolder.price = view.findViewById(R.id.shop_order_detail_price);
                view.setTag(viewHolder);
            }else{
                viewHolder = (MyViewHolder) view.getTag();
            }
            Picasso.with(getContext()).load(orderItemsBeans.get(i).getProduct().getPImage()).into(viewHolder.shopImg);
            viewHolder.title.setText(orderItemsBeans.get(i).getProduct().getPdesc());
            viewHolder.count.setText("x1");
            viewHolder.price.setText("￥"+orderItemsBeans.get(i).getProduct().getShopPrice());
            return view;
        }
    }
    class MyViewHolder{
        ImageView shopImg;
        TextView title;
        TextView count;
        TextView price;
    }
}
