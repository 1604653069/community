package com.example.community.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.community.R;
import com.example.community.activity.GoodInfoActivity;
import com.example.community.entity.GoodInfo;
import com.example.community.entity.StartUpShop;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SecKillAdapter extends RecyclerView.Adapter<SecKillAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<StartUpShop.HotBean> datas;
    public SecKillAdapter(Context context,List<StartUpShop.HotBean> datas){
        this.context  = context;
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_seckill,null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StartUpShop.HotBean shop = datas.get(position);
         holder.tv_origin_price.setText(shop.getShopPrice()+"");
         holder.tv_cover_price.setText(shop.getMarketPrice()+"");
        Picasso.with(context).load(shop.getPImage()).into(holder.iv_figure);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
         ImageView iv_figure;
         TextView tv_cover_price;
         TextView tv_origin_price;
        public ViewHolder(@NonNull View view) {
            super(view);
            iv_figure = view.findViewById(R.id.iv_figure);
            tv_cover_price = view.findViewById(R.id.tv_cover_price);
            tv_origin_price = view.findViewById(R.id.tv_origin_price);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startGoodInfoActivity(getLayoutPosition());
                }
            });
        }
    }
    /*跳转到商品详情界面*/
    public void startGoodInfoActivity(int position){
        Intent intent = new Intent(this.context, GoodInfoActivity.class);
            GoodInfo goodInfo = new GoodInfo();
            goodInfo.setIsHot(datas.get(position).getIsHot());
            goodInfo.setMarketPrice(datas.get(position).getMarketPrice());
            goodInfo.setPdate(datas.get(position).getPdate());
            goodInfo.setPdesc(datas.get(position).getPdesc());
            goodInfo.setPflag(datas.get(position).getPflag());
            goodInfo.setPid(datas.get(position).getPid());
            goodInfo.setPname(datas.get(position).getPname());
            goodInfo.setpImage(datas.get(position).getPImage());
            goodInfo.setShopPrice(datas.get(position).getShopPrice());
            goodInfo.setType(datas.get(position).getType());
            intent.putExtra("GoodInfo",goodInfo);
        this.context.startActivity(intent);
    }
}
