package com.example.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.Shop;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private  Context context;
    private  List<Shop> shops;
    private LayoutInflater inflater;
    public ShopAdapter(Context context, List<Shop> shops){
        this.context = context;
        this.shops = shops;
        this.inflater = LayoutInflater.from(context);
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder=null;
        if(view==null){
            view = inflater.inflate(R.layout.item_hot_grid_view,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.iv_hot);
            viewHolder.decs = view.findViewById(R.id.tv_name);
            viewHolder.price = view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Shop shop = shops.get(i);
        Picasso.with(context).load(shop.getPImage()).into(viewHolder.img);
        viewHolder.decs.setText(shop.getPdesc());
        viewHolder.price.setText("￥"+shop.getShopPrice()+"");
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView decs;
        TextView price;
    }
}
