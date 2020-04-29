package com.example.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.StartUpShop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecommandAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<StartUpShop.CommandBean> shops = new ArrayList<>();
    public RecommandAdapter(){}
    public RecommandAdapter(Context context,List<StartUpShop.CommandBean> shops){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shops = shops;
    }
    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Object getItem(int i) {
        return shops.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            view = inflater.inflate(R.layout.item_recommend_grid_view,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.iv_recommend);
            viewHolder.shpoName = view.findViewById(R.id.tv_name);
            viewHolder.price = view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        StartUpShop.CommandBean shop = shops.get(i);
        Picasso.with(context).load(shop.getPImage()).into(viewHolder.img);
        viewHolder.shpoName.setText(shop.getPname());
        viewHolder.price.setText(shop.getShopPrice()+"");
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView shpoName;
        TextView price;
    }
}
