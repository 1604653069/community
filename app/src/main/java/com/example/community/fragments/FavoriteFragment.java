package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.activity.GoodInfoActivity;
import com.example.community.activity.HouseInfoActivity;
import com.example.community.entity.Favorite;
import com.example.community.entity.GoodInfo;
import com.example.community.entity.House;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class FavoriteFragment extends Fragment {
    private ImageView back;
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"商品","房屋"};
    private Favorite favorite;
    private List<Favorite.FavoritesBean.HouseBean> houseBeans = new ArrayList<>();
    private List<Favorite.FavoritesBean.ProductBean> productBeans = new ArrayList<>();
    private  MyListViewAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG",msg.obj.toString());
                    favorite = JSON.parseObject(msg.obj.toString(),Favorite.class);
                    if(favorite.getFavorites()!=null&&favorite.getFavorites().size()>0){
                        for(int i=0;i<favorite.getFavorites().size();i++){
                            if(favorite.getFavorites().get(i).getType()==0){
                                productBeans.add(favorite.getFavorites().get(i).getProduct());
                            }else{
                                houseBeans.add(favorite.getFavorites().get(i).getHouse());
                            }
                        }
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
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_listview,null);
                                ListView listView = view.findViewById(R.id.favorite_item_listview);
                                adapter=null;
                                if(position==0){
                                    adapter = new MyListViewAdapter(productBeans,houseBeans,0);
                                }else if(position==1){
                                    adapter = new MyListViewAdapter(productBeans,houseBeans,1);
                                }
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        if(position==0){
                                            GoodInfo goodInfo = new GoodInfo();
                                            goodInfo.setIsHot(productBeans.get(i).getIsHot());
                                            goodInfo.setMarketPrice(productBeans.get(i).getMarketPrice());
                                            goodInfo.setPdate(productBeans.get(i).getPdate());
                                            goodInfo.setPdesc(productBeans.get(i).getPdesc());
                                            goodInfo.setPflag(productBeans.get(i).getPflag());
                                            goodInfo.setPid(productBeans.get(i).getPid());
                                            goodInfo.setPname(productBeans.get(i).getPname());
                                            goodInfo.setpImage(productBeans.get(i).getPImage());
                                            goodInfo.setShopPrice(productBeans.get(i).getShopPrice());
                                            goodInfo.setType(productBeans.get(i).getType());
                                            Intent intent = new Intent(getContext(), GoodInfoActivity.class);
                                            intent.putExtra("GoodInfo",goodInfo);
                                            startActivity(intent);
                                        }else{
                                            Intent intent = new Intent(getContext(), HouseInfoActivity.class);
                                            House house = new House();
                                            house.setRent(houseBeans.get(i).getRent());
                                            house.setAddress(houseBeans.get(i).getAddress());
                                            house.setTitle(houseBeans.get(i).getTitle());
                                            house.setArea(houseBeans.get(i).getArea());
                                            house.setDate(houseBeans.get(i).getDate());
                                            house.setDetailed(houseBeans.get(i).getDetailed());
                                            house.setHid(houseBeans.get(i).getHid());
                                            house.setHouseImgs(houseBeans.get(i).getHouseImgs());
                                            intent.putExtra("houseInfo",house);
                                            startActivity(intent);
                                        }
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
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
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
        OkHttpUtils.post(body,Param.GETALLFAVORITEINFO_URL,handler);
    }

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        title.setText("我的收藏");
        tabLayout = view.findViewById(R.id.favorite_tab);
        viewPager = view.findViewById(R.id.favorite_viewpager);
    }
    class MyListViewAdapter extends BaseAdapter{
        private List<Favorite.FavoritesBean.HouseBean> houseBeans;
        private List<Favorite.FavoritesBean.ProductBean> productBeans;
        private int type;
        public MyListViewAdapter(List<Favorite.FavoritesBean.ProductBean> productBeans,List<Favorite.FavoritesBean.HouseBean> houseBeans,int type){
            this.productBeans = productBeans;
            this.houseBeans = houseBeans;
            this.type = type;
        }
        @Override
        public int getCount() {
            if(type==0){
                return productBeans.size();
            }else{
                return houseBeans.size();
            }
        }

        @Override
        public Object getItem(int i) {
            if(type==0){
              return productBeans.get(i);
            }else{
                return houseBeans.get(i);
            }
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_listview_item,null);
                viewHodler = new ViewHodler();
                viewHodler.img = view.findViewById(R.id.favorite_item_img);
                viewHodler.title = view.findViewById(R.id.favorite_item_title);
                viewHodler.number = view.findViewById(R.id.favorite_item_number);
                viewHodler.price = view.findViewById(R.id.favorite_item_price);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
            if(type==0){
                //如果是商品信息
                Picasso.with(getContext()).load(productBeans.get(i).getPImage()).into(viewHodler.img);
                viewHodler.title.setText(productBeans.get(i).getPdesc());
                viewHodler.number.setText("1人收藏");
                viewHodler.price.setText("￥"+productBeans.get(i).getMarketPrice());
            }else if(type==1){
                //如果是房屋信息
                Picasso.with(getContext()).load(houseBeans.get(i).getHouseImgs().get(0).getPath()).into(viewHodler.img);
                viewHodler.title.setText(houseBeans.get(i).getTitle());
                viewHodler.number.setText("1人收藏");
                viewHodler.price.setText("￥"+houseBeans.get(i).getRent());
            }
            return view;
        }
    }
    class ViewHodler{
        ImageView img;
        TextView title;
        TextView number;
        TextView price;
    }
}
