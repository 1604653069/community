package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;
import com.example.community.entity.Images;
import com.example.community.entity.MySS;
import com.example.community.fragment.ServiceFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.zhihu.matisse.Matisse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SSFragment extends Fragment {
    /*发送内容的编辑*/
    private EditText content;
    /*发送按钮*/
    private TextView send;
    /*显示图片*/
    private GridView gridView;
    /*说说的图片设配器*/
    private MyAdapter adapter;
    /*存放图片的路径*/
    private List<Images> images = new ArrayList<>();
    /*发表说说图片的URI*/
    private  List<Uri> mSelected = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(getContext(), "说说发表成功", Toast.LENGTH_SHORT).show();
                    /*跳转到说说的界面*/
                    getFragmentManager().beginTransaction().replace(R.id.fl,new ServiceFragment()).commit();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ss,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }
    private void initListener() {
        /*发送按钮事件*/
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*发送说说*/
                sendSS();
            }
        });
        /*选择图片事件*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Utils.selectImg(getActivity(),3);
                        break;
                }
            }
        });
    }

    private void initData() {
        /*添加一个图片的选择按钮*/
        images.add(new Images(0,null, R.mipmap.icon_addpic_focused));
    }
    /*对控件的初始化*/
    private void initView(View view) {
        content = view.findViewById(R.id.ss_edit);
        send = view.findViewById(R.id.ss_send);
        gridView = view.findViewById(R.id.ss_gridview);
        adapter = new MyAdapter();
        gridView.setAdapter(adapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Param.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //图片路径 同样视频地址也是这个 根据requestCode
            mSelected = Matisse.obtainResult(data);
            for (int i=0;i<mSelected.size();i++){
                Images image = new Images();
                image.setType(1);
                image.setUri(mSelected.get(i));
                images.add(image);
            }
            adapter.notifyDataSetChanged();
        }
    }
    /*图片的设配器*/
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int i) {
            return images.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.upload_img,viewGroup,false);
            ImageView imageView = view.findViewById(R.id.upload_img);
            Images img = images.get(i);
            if(img.getType()==0){
                imageView.setImageResource(img.getId());
            }else{
                imageView.setImageURI(img.getUri());
            }
            return view;
        }
    }
    /*发表说说*/
    public void sendSS(){
        if(mSelected!=null && mSelected.size()>0){
            for(int i=0;i<mSelected.size();i++){
                Utils.savePhotoToSdCard(Param.UPLOAD_PATH,Utils.createFileName()+".png",Utils.createBitmap(getContext(),mSelected.get(i)));
            }
            OkHttpUtils.ssPost(Param.SENDSS_URL,new MySS(Param.user.getName(),content.getText().toString(),mSelected,getCurrentTime()),true,handler);
        }
        else
            OkHttpUtils.ssPost(Param.SENDSS_URL,new MySS(Param.user.getName(),content.getText().toString(),mSelected,getCurrentTime()),false,handler);
    }
    /*获取当前时间*/
    public String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        return currentTime;
    }
}
