package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.adapter.ShopAdapter;
import com.example.community.entity.Shop;
import com.example.community.fragment.BaseFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.List;


public class TestFragment extends BaseFragment {
    private ViewPager viewPager;
    private List<Shop> shops;
    private ShopAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG","数据请求成功(handler)");
                    shops = JSON.parseArray(msg.obj.toString(),Shop.class);
                    adapter = new ShopAdapter(mContext,shops);
                    viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return shops.size();
                        }

                        @Override
                        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                            return view==object;
                        }
                        @NonNull
                        @Override
                        public Object instantiateItem(@NonNull ViewGroup container, int position) {
                            GridView gridView;
                            View view = LayoutInflater.from(mContext).inflate(R.layout.shop_item,container,false);
                            gridView = view.findViewById(R.id.gv_shop);
                            gridView.setAdapter(adapter);
                            container.addView(view);
                            return view;
                        }
                        @Override
                        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                            container.removeView((View) object);
                        }
                    });
                    break;
            }
        }
    };
    @Override
    public View initView() {
        Log.i("TAG","initView");
        View view = LayoutInflater.from(mContext).inflate(R.layout.test_fragment,null);
        findViews(view);
        return view;
    }
    public void findViews(View view){
        viewPager = view.findViewById(R.id.test_viewpage);
    }
    @Override
    public void initData() {
        Log.i("TAG","initData");
        OkHttpUtils.get(Param.GETALLSHOPS,handler);
    }
}
