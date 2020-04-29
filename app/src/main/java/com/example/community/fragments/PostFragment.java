package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.adapter.PlaAdapter;
import com.example.community.entity.Placard;
import com.example.community.fragment.IndexFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PostFragment extends Fragment {
    private PullToRefreshListView refreshListView;
    private ListView listView;
    private PlaAdapter adapter;
    private List<Placard> placards = new ArrayList<>();
    private  RequestBody body;
    private ImageView back;
    private TextView title;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    List<Placard> datas = JSON.parseArray(msg.obj.toString(), Placard.class);
                    placards = new ArrayList<>();
                    placards.addAll(datas);
                    adapter = new PlaAdapter(getContext(),placards);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    refreshListView.onRefreshComplete();
                    Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_post,null);
        initView(view);
        initDate();
        initLisnter();
        //setTitle("公告通知");
        return view;
    }

    private void initView(View view) {
        refreshListView = view.findViewById(R.id.id_lv_test);
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        listView = refreshListView.getRefreshableView();
        listView.setDividerHeight(0);
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.listview_item);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.2f);
        listView.setLayoutAnimation(lac);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("title",placards.get(i-1).getTitle());
                bundle.putString("date",placards.get(i-1).getDate());
                bundle.putString("content",placards.get(i-1).getContent());
                bundle.putString("type",placards.get(i-1).getType());
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setArguments(bundle);
                setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fl,messageFragment).commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
            }
        });
        title.setText("公告通知");
    }
    public void initLisnter(){
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                OkHttpUtils.post(body, Param.PLA_URL,handler);
            }
        });
    }
    public void initDate(){
         body = new FormBody.Builder().build();
         OkHttpUtils.post(body, Param.PLA_URL,handler);
    }

}
