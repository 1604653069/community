package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.entity.Address;
import com.example.community.entity.ShopCart;
import com.example.community.fragment.MineFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AddressFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private Button addAddressBtn;
    private ListView addressListView;
    private List<Address> addresses = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter();
    public AddressFragment(){}
    private boolean isTrue;
    public AddressFragment(boolean isTrue){
        this.isTrue = isTrue;
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    addresses = JSON.parseArray(msg.obj.toString(),Address.class);
                    addressListView.setDividerHeight(10);
                    addressListView.setAdapter(adapter);
                    Log.i("TAG",addresses.toString());
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_address,container,false);
        initView(view);
        initData();
        initListener();
        return view;
    }


    private void initData() {
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body, Param.GETADDRESSINFO,handler);
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTrue){
                    List<ShopCart.ItemsBean.ProductBean> productBeans = new ArrayList<>();
                    for(int i=0;i<Param.shopCart.getItems().size();i++){
                        if(Param.shopCart.getItems().get(i).isSelected()){
                            productBeans.add(Param.shopCart.getItems().get(i).getProduct());
                        }
                    }
                    getFragmentManager().beginTransaction().replace(R.id.fl,new OrderDetailFragment(productBeans)).commit();
                }else
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment2()).commit();
            }
        });
    }

    private void initView(View view) {
        title = view.findViewById(R.id.title_text);
        title.setText("地址管理");
        back = view.findViewById(R.id.title_back);
        addAddressBtn = view.findViewById(R.id.btn_add_address);
        addressListView = view.findViewById(R.id.address_listview);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return addresses.size();
        }

        @Override
        public Object getItem(int i) {
            return addresses.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler= null;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.address_listview_item,null);
                viewHodler = new ViewHodler();
                viewHodler.name  = view.findViewById(R.id.address_item_name);
                viewHodler.phone = view.findViewById(R.id.address_item_phone);
                viewHodler.address = view.findViewById(R.id.address_item_address);
                viewHodler.select=view.findViewById(R.id.address_item_select);
                viewHodler.label=view.findViewById(R.id.address_item_label);
                viewHodler.edit = view.findViewById(R.id.address_item_edit);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
            Address address = addresses.get(i);
            viewHodler.name.setText(address.getName());
            viewHodler.phone.setText(address.getPhone());
            viewHodler.address.setText(address.getAddress());
            if(address.getStatues()==1){
                viewHodler.select.setImageResource(R.mipmap.icon_hook);
                viewHodler.label.setText("默认地址");
            }else{
                viewHodler.select.setImageResource(R.mipmap.icon_hook_gray);
                viewHodler.label.setText("设为默认");
            }
            ViewHodler finalViewHodler = viewHodler;
            viewHodler.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(addresses.get(i).getStatues()==0){
                       address.setStatues(1);
                       showNormalDialog(i);
                   }
                }
            });
            viewHodler.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment2(address)).commit();
                }
            });
            return view;
        }
    }
    class ViewHodler{
        TextView name;
        TextView phone;
        TextView address;
        ImageView select;
        TextView label;
        LinearLayout edit;
    }
    private void showNormalDialog(int position){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setTitle("默认地址");
        normalDialog.setMessage("是否将这个地址修改为默认地址?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int index =0;index<addresses.size();index++){
                            if(index==position) {
                                addresses.get(position).setStatues(1);
                                Param.address = addresses.get(position);
                            }else{
                                addresses.get(index).setStatues(0);
                            }
                            RequestBody body = new FormBody.Builder()
                                    .add("aid",addresses.get(index).getAid())
                                    .add("name",addresses.get(index).getName())
                                    .add("phone",addresses.get(index).getPhone())
                                    .add("address",addresses.get(index).getAddress())
                                    .add("statues",addresses.get(index).getStatues()+"")
                                    .add("user.uid","3BBE78935EDE4212B17866C5510E8315")
                                    .build();
                            OkHttpUtils.post(body,Param.CHANGEADDRESSINFO,handler,false);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }
}
