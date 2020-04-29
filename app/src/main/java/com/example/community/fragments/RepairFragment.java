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
import android.widget.Button;
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
import com.example.community.entity.Repair;
import com.example.community.fragment.IndexFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class RepairFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    /*返回图片按钮*/
    private ImageView back;
    /*标题信息*/
    private TextView title;
    /*选择上门保修的时间段*/
    private TextView select;
    /*上门保修选择的时间段*/
    private String selectTime="";
    /*显示上传图片的信息*/
    private GridView gridView;
    /*要上传的图片*/
    private List<Images> images = new ArrayList<>();
    /*展示图片设配器*/
    private MyAdapter adapter;
    /*上传按钮*/
    private Button uploadBtn;
    /*上传图片的uri*/
    private  List<Uri> mSelected = new ArrayList<>();
    /*提交问题的文本框*/
    private EditText commitEdit;
    private Repair repair = new Repair();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /*关闭对话框*/
                    Utils.closeDialog();
                    Toast.makeText(getContext(), "报修已提交...", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repair,container,false);
        initView(view);
        initListener();
        initDate();
        return view;
    }

    private void initDate() {
        images.add(new Images(0,null, R.mipmap.ic_camera_selected));
    }
    private void initListener() {
        back.setOnClickListener(this);
        select.setOnClickListener(this);
    }
    private void initView(View view) {
        uploadBtn = view.findViewById(R.id.repair_commit);
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        title.setText("报事报修");
        select = view.findViewById(R.id.tv_select);
        gridView = view.findViewById(R.id.re_gridview);
        commitEdit = view.findViewById(R.id.edit_re_requestion);
        adapter = new MyAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    if(images.size()<=3){
                        Utils.selectImg(getActivity(),3);
                    }else{
                        Toast.makeText(getActivity(), "最多上传3张图片", Toast.LENGTH_SHORT).show();
                    }
                }   
            }
        });
        uploadBtn.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back:
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
                break;
            case R.id.tv_select:
                Utils.showDate(this,getFragmentManager());
                break;
            case R.id.repair_commit:
                if(Param.user==null){
                    Toast.makeText(getContext(), "你还没登陆，不能进行操作", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*需要提交的保修信息*/
                String question = commitEdit.getText().toString();
                if(question.equals("")||question.length()<=0){
                    Toast.makeText(getContext(), "提交的问题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }if(selectTime.equals("")||selectTime.length()<=0){
                Toast.makeText(getContext(), "请勾选上门保修的时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                repair.setQuestion(question);
                repair.setDate(selectTime);
               Utils.showDialog(getContext(),"上传中");
               if(mSelected.size()!=0){
                   /*向sd卡中存放图片*/
                   for (int i=0;i<mSelected.size();i++){
                       Utils.savePhotoToSdCard(Param.UPLOAD_PATH,Utils.createFileName()+".png",Utils.createBitmap(getContext(),mSelected.get(i)));
                   }
                   OkHttpUtils.repairPost(Param.REPAIR_URL,repair,true,handler);
               }else{
                   OkHttpUtils.repairPost(Param.REPAIR_URL,repair,false,handler);
               }
                break;
        }
    }
    /*时间选择器选择回调事件*/
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        selectTime = selectTime+hourOfDay+":"+minute+":"+second;
        select.setText(selectTime);
    }
    /*日期选择器选择回调事件*/
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        selectTime = year+"-"+(monthOfYear+1)+"-"+dayOfMonth+" ";
        Utils.showTime(this,getFragmentManager());
    }
    /*显示图片的设配器*/
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
            return 0;
        }

        @Override
        public View getView(int position, View converView, ViewGroup viewGroup) {
                converView =LayoutInflater.from(getContext()).inflate(R.layout.upload_img,viewGroup,false);
                ImageView imageView = converView.findViewById(R.id.upload_img);
                Images image = images.get(position);
                if(image.getType()==0){
                    imageView.setImageResource(image.getId());
                }else{
                    imageView.setImageURI(image.getUri());
                }
                return converView;
        }
    }

}
