package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.community.R;
import com.example.community.fragment.MineFragment;
import com.google.android.material.tabs.TabLayout;

//我的红包界面
public class HongBaoFragment extends Fragment {
    private ImageView back;
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"未使用","已使用","已过期"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hongbao,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_hongbao,null);
                ListView listView = view.findViewById(R.id.hongbao_listview);
                listView.setAdapter(new MyAdapter());
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
            }
        });
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

    private void initData() {
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
    }

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        title.setText("我的红包");
        tabLayout = view.findViewById(R.id.hongbao_tab);
        viewPager = view.findViewById(R.id.hongbao_viewpager);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.hongbao_item,null);
                viewHodler = new ViewHodler();
                viewHodler.money = view.findViewById(R.id.hongbao_item_money);
                viewHodler.maxMoney = view.findViewById(R.id.hongbao_item_max);
                viewHodler.time = view.findViewById(R.id.hongbao_item_time);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
            viewHodler.money.setText("￥2元");
            viewHodler.maxMoney.setText("满10元可用");
            viewHodler.time.setText("使用期限:2020.02.02-2020.03.03");
            return view;
        }
    }
    class ViewHodler{
        TextView money;
        TextView maxMoney;
        TextView time;
    }
}
