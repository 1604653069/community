package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.entity.SS;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class MySS extends Fragment {
    private ImageView back;
    private TextView title;
    private ListView listView;
    private List<SS> allSS;
    private MyAdapter adapter;
    private ImgAdapter imgAdapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    JSONObject jsonObject = JSONObject.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(getContext(), "获取说说成功", Toast.LENGTH_SHORT).show();
                        String ss = jsonObject.getString("ss");
                        allSS = JSONObject.parseArray(ss, SS.class);
                        adapter = new MyAdapter();
                        listView.setAdapter(adapter);
                    }else{
                        Toast.makeText(getContext(), "获取说说失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.my_ss_layout,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body, Param.GETUSERALLSS_URL,handler);
    }

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        listView = view.findViewById(R.id.my_ss_listview);
        title.setText("我的说说");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return allSS.size();
        }

        @Override
        public Object getItem(int i) {
            return allSS.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ss_item,viewGroup,false);
            ImageView touxiang = view.findViewById(R.id.ss_item_touxiang);
            TextView name = view.findViewById(R.id.ss_item_name);
            TextView content = view.findViewById(R.id.ss_item_content);
            GridView gridView = view.findViewById(R.id.ss_item_gridview);
            SS ss =allSS.get(i);
            name.setText(ss.getUser().getName());
            content.setText(ss.getSsContent());
            /*如果说说里有带图片*/
            if(ss.getSsImgs()!=null&&ss.getSsImgs().size()>0){
                imgAdapter = new ImgAdapter(ss.getSsImgs());
                gridView.setAdapter(imgAdapter);
            }
            return view;
        }
    }
    class ImgAdapter extends BaseAdapter{
        /*发表说说的图片Uri地址*/
        private List<SS.SsImgsBean> uris;
        public ImgAdapter(List<SS.SsImgsBean> uris){
            this.uris = uris;
        }
        @Override
        public int getCount() {
            return uris.size();
        }

        @Override
        public Object getItem(int i) {
            return uris.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ss_img_item,viewGroup,false);
            ImageView imageView = view.findViewById(R.id.upload_img);
            Log.i("TAG","说说的图片地址为:"+uris.get(i).getPath());
            Picasso.with(getContext())
                    .load(uris.get(i).getPath())
                    .into(imageView);
            return view;
        }
    }
}
