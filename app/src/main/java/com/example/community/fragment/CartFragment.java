package com.example.community.fragment;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.adapter.ShopCartAdapter;
import com.example.community.entity.Address;
import com.example.community.entity.ShopCart;
import com.example.community.fragments.AddressFragment;
import com.example.community.fragments.OrderDetailFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class CartFragment extends Fragment implements View.OnClickListener {
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShopCartAdapter adapter;
    private TextView title;
    private ImageView back;
    private List<ShopCart.ItemsBean.ProductBean> productBeans = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    ShopCart shopCart = JSON.parseObject(msg.obj.toString(),ShopCart.class);
                    adapter = new ShopCartAdapter(getContext(),shopCart,checkboxAll,tvShopcartTotal);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        List<Address> addresses = JSON.parseArray(msg.obj.toString(), Address.class);
                        for (Address address:addresses){
                            if(address.getStatues()==1){
                                Param.address = address;
                            }
                        }
                    }catch (Exception e){
                    }
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_zhi_n, container, false);
        findViews(view);
        initListener();
        return view;
    }

    private void initListener() {
        btnCheckOut.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void findViews(View view) {
        title = view.findViewById(R.id.title_text);
        title.setText("购物车");
        back = view.findViewById(R.id.title_back);
        back.setVisibility(View.INVISIBLE);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);
        ll_empty_shopcart = (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);
        tvEmptyCartTobuy.setClickable(true);
        initData();
    }
    /*从服务器端获取数据*/
    public void initData(){
       // OkHttpUtils.get(Param.GETCARTINFO, handler);
        if(Param.user!=null){
            RequestBody body = new FormBody.Builder()
                    /*提交用户名*/
                    .add("user.uid", Param.user.getUid())
                    .build();
            OkHttpUtils.post(body,Param.GETCARTINFO,handler);
            OkHttpUtils.post(body,Param.GETADDRESSINFO,handler2);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_check_out:
                if(Param.user==null){
                    Toast.makeText(getContext(), "你还没登陆，无法使用", Toast.LENGTH_SHORT).show();
                    return;
                }
               if(Param.address==null){
                    showNormalDialog();
                }else if(Param.shopCart==null){
                    Toast.makeText(getContext(), "你还没有选择要购买的商品", Toast.LENGTH_SHORT).show();
                }else{
                for(int i=0;i<Param.shopCart.getItems().size();i++){
                    if(Param.shopCart.getItems().get(i).isSelected()){
                        productBeans.add(Param.shopCart.getItems().get(i).getProduct());
                    }
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl,new OrderDetailFragment(productBeans)).commit();
            }
                
                break;
            case R.id.title_back:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
                break;
        }
    }
    private void showNormalDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());
        normalDialog.setTitle("提示");
        normalDialog.setMessage("您还没有默认的收货地址，是否现在添加?");
        normalDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment()).commit();
                    }
                });
        normalDialog.setNegativeButton("不了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }
}
