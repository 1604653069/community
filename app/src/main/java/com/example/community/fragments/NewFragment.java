package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.adapter.NewAdapter;
import com.example.community.entity.News;
import com.example.community.fragment.IndexFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Utils;
import com.google.android.material.tabs.TabLayout;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashMap;

public class NewFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;
    private TabLayout tableLayout;
    private String mTitles[] = {
            "头条", "社会", "国内", "国际", "体育",
            "科技", "财经", "时尚"};
    private String newType[] = {"toutiao","shehui","guonei","guoji","tiyu","keji","caijing","shishang"};
    private NewAdapter adapter;
    private News news;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.new_fragment,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), mTitles[tab.getPosition()], Toast.LENGTH_SHORT).show();
                getNews(newType[tab.getPosition()]);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("url",news.getResult().getData().get(i-1).getUrl());
                ShowNewFragment showNewFragment = new ShowNewFragment();
                showNewFragment.setArguments(bundle);
                Toast.makeText(getActivity(),news.getResult().getData().get(i-1).getTitle() , Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.fl,showNewFragment).commit();
            }
        });
    }

    private void initData() {
        for(int i=0;i<mTitles.length;i++){
            tableLayout.addTab(tableLayout.newTab().setText(mTitles[i]));
        }
        getNews("yule");
    }
    private void initView(View view) {
        EventBus.getDefault().register(this);
        tableLayout = view.findViewById(R.id.mytab);
        title = view.findViewById(R.id.title_text);
        title.setText("新闻");
        back = view.findViewById(R.id.title_back);
        pullToRefreshListView = view.findViewById(R.id.pull_news);
        listView = pullToRefreshListView.getRefreshableView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newMessage(String newMessage) {
        try {
            news =JSON.parseObject(newMessage, News.class);
            adapter = new NewAdapter(getContext(),news);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Utils.closeDialog();
        }catch (Exception e){
        }
    }
    /*获取新闻的链接*/
    public void getNews(String type){
        String url = "http://toutiao-ali.juheapi.com/toutiao/index";
        LinkedHashMap<String,String> params = new LinkedHashMap<>();
        params.put("type",type);
        OkHttpUtils.get(url,params);
        Utils.showDialog(getContext(),"资源加载中");
    }

}
