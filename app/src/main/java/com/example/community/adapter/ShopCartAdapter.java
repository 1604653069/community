package com.example.community.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.community.R;
import com.example.community.entity.ShopCart;
import com.example.community.utils.Param;
import com.example.community.view.AddSubView;
import com.squareup.picasso.Picasso;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.MyViewHolder>  {
    private Context context;
    private ShopCart datas;
    private CheckBox checkBox;
    private TextView total;
    private boolean flag;
    public ShopCartAdapter(Context context, ShopCart datas,CheckBox checkBox,TextView total){
        this.context = context;
        this.datas = datas;
        this.checkBox = checkBox;
        this.total = total;
        showTotalPrice();
        setCheboxState();
    }

    /*显示总价格*/
    private void showTotalPrice() {
        this.total.setText(""+getTotalPrice());
    }


    /*获取选中的总价格*/
    private double getTotalPrice(){
        double price = 0.0;
        if(datas.getItems().size()>0&&datas.getItems()!=null){
            for (int i=0;i<datas.getItems().size();i++){
                if(datas.getItems().get(i).isSelected()){
                    price+=datas.getItems().get(i).getQuantity()*datas.getItems().get(i).getProduct().getShopPrice();
                }
            }
        }
        return price;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(this.context, R.layout.item_shop_cart,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShopCart.ItemsBean itemsBean = this.datas.getItems().get(position);
        /*是否是选中状态*/
        holder.checkBox.setChecked(itemsBean.isSelected());
        Picasso.with(this.context).load(itemsBean.getProduct().getPImage()).into(holder.showImg);
        holder.shopDes.setText(itemsBean.getProduct().getPdesc());
        holder.price.setText("￥"+itemsBean.getProduct().getShopPrice());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                itemsBean.setSelected(b);
                total.setText(getTotalPrice()+"");
                Param.shopCart = datas;
                isSelectAll();
            }
        });
        holder.addSubView.setOnCountChangeListener(new AddSubView.OnCountChangeListener() {
            @Override
            public void onCountChangeLisnter(int value) {
                datas.getItems().get(position).setQuantity(value);
                total.setText(getTotalPrice()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.getItems().size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private ImageView showImg;
        private TextView shopDes;
        private TextView price;
        private AddSubView addSubView;
        public MyViewHolder(@NonNull View view) {
            super(view);
            this.checkBox = view.findViewById(R.id.cb_gov);
            this.showImg = view.findViewById(R.id.iv_gov);
            this.shopDes = view.findViewById(R.id.tv_desc_gov);
            this.price = view.findViewById(R.id.tv_price_gov);
            this.addSubView = view.findViewById(R.id.numberAddSubView);
        }
    }
    /*判断是否是全选中*/
    public void isSelectAll(){
          int count =0;
          for (int i=0;i<datas.getItems().size();i++){
              if(datas.getItems().get(i).isSelected()){
                  count++;
              }else{
                  checkBox.setChecked(false);
              }
          }
          if(count==datas.getItems().size())
              checkBox.setChecked(true);
    }
    public void setCheboxState(){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = checkBox.isChecked();
                checkAll_none(checked);
            }
        });
    }
    private void checkAll_none(boolean isCheck){
        for(int i=0;i<datas.getItems().size();i++){
            datas.getItems().get(i).setSelected(isCheck);
        }
        notifyDataSetChanged();
    }
}
