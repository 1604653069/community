package com.example.community.fragments;

import android.os.Bundle;
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

import com.example.community.R;
import com.example.community.entity.OrderIcon;
import com.example.community.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private ImageView back;
    private TextView title;
    private ListView listView;
    private List<OrderIcon> datas = new ArrayList<>();
    private MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        initData();
        initView(view);
        initListener();
        return view;
    }

    private void initData() {
        datas.add(new OrderIcon(R.mipmap.ic_legou_order,"乐购订单",0,0));
        datas.add(new OrderIcon(R.mipmap.ic_service_order,"服务订单",0,0));
        adapter = new MyAdapter();
    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            getFragmentManager().beginTransaction().replace(R.id.fl,new ShopingOrderFragment()).commit();
                            break;
                        case 1:
                            getFragmentManager().beginTransaction().replace(R.id.fl,new ServiceOrderFragment()).commit();
                            break;
                    }
            }
        });
    }

    private void initView(View view) {
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        title.setText("订单中心");
        listView = view.findViewById(R.id.order_listview);
        listView.setAdapter(adapter);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler= null;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.order_item,null);
                viewHodler = new ViewHodler();
                viewHodler.img  = view.findViewById(R.id.order_item_img);
                viewHodler.name = view.findViewById(R.id.order_item_name);
                viewHodler.numberFk = view.findViewById(R.id.order_item_number_fukuang);
                viewHodler.numberSH = view.findViewById(R.id.order_item_number_shop);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
            OrderIcon orderIcon = datas.get(i);
            viewHodler.img.setImageResource(orderIcon.getImgId());
            viewHodler.name.setText(orderIcon.getName());
            viewHodler.numberFk.setText(orderIcon.getNumberFk()+"");
            viewHodler.numberSH.setText(orderIcon.getNumberSk()+"");
            return view;
        }
        class ViewHodler {
            ImageView img;
            TextView name;
            TextView numberFk;
            TextView numberSH;
        }
    }
}
