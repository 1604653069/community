package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.activity.GoodInfoActivity;
import com.example.community.adapter.ShopAdapter;
import com.example.community.entity.GoodInfo;
import com.example.community.entity.Shop;
import com.example.community.fragment.IndexFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String mTitles[] = {
            "零食", "土特产", "水果", "家居", "家电"};
    private TextView title;
    private ImageView back;
    GridView gridView;
    private ShopAdapter adapter;
    private List<Shop> shops;
    @SuppressLint("HandlerLeak")
    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    shops = JSON.parseArray(msg.obj.toString(),Shop.class);
                    viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return mTitles.length;
                        }
                        @Override
                        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                            return view ==object;
                        }
                        @NonNull
                        @Override
                        public Object instantiateItem(@NonNull ViewGroup container, int position) {
                            View view = LayoutInflater.from(getContext()).inflate(R.layout.shop_item,container,false);
                            gridView = view.findViewById(R.id.gv_shop);
                            List<Shop> sortShop = new ArrayList<>();
                            for (int i=0;i<shops.size();i++){
                                if(shops.get(i).getType()==position){
                                        sortShop.add(shops.get(i));
                                }
                            }
                            adapter = new ShopAdapter(ShopsFragment.this.getContext(),sortShop);
                            gridView.setAdapter(adapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    GoodInfo goodInfo = new GoodInfo();
                                    goodInfo.setIsHot(sortShop.get(i).getIsHot());
                                    goodInfo.setMarketPrice(sortShop.get(i).getMarketPrice());
                                    goodInfo.setPdate(sortShop.get(i).getPdate());
                                    goodInfo.setPdesc(sortShop.get(i).getPdesc());
                                    goodInfo.setPflag(sortShop.get(i).getPflag());
                                    goodInfo.setPid(sortShop.get(i).getPid());
                                    goodInfo.setPname(sortShop.get(i).getPname());
                                    goodInfo.setpImage(sortShop.get(i).getPImage());
                                    goodInfo.setShopPrice(sortShop.get(i).getShopPrice());
                                    goodInfo.setType(sortShop.get(i).getType());
                                    Intent intent = new Intent(getContext(),GoodInfoActivity.class);
                                    intent.putExtra("GoodInfo",goodInfo);
                                    startActivity(intent);
                                }
                            });
                            container.addView(view);
                            return view;
                        }
                        @Override
                        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                            container.removeView((View) object);
                        }
                    });
                    Utils.closeDialog();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
            }
        });
    }

    private void initData() {
        Utils.showDialog(getContext(),"加载中");
        for(int i=0;i<mTitles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }
        OkHttpUtils.get(Param.GETALLSHOPS,handler);
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.shop_tab);
        viewPager = view.findViewById(R.id.shop_viewpage);
        title = view.findViewById(R.id.title_text);
        back = view.findViewById(R.id.title_back);
        title.setText("商城");

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
    }

}
