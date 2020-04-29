package com.example.community.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.community.R;
import com.example.community.entity.Pay;
import com.example.community.entity.PayItem;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class WaterAdapter extends BaseAdapter {
    List<Pay.PaysBean> paysBeans;
    private LayoutInflater inflater;
    private Context context;
    private List<PayItem> payItems;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
          switch (msg.what){
              case 1:
                  Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                  Log.i("TAG",msg.obj.toString());
                  break;
          }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(context, "缴费成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public WaterAdapter(){}

    public WaterAdapter(List<Pay.PaysBean> paysBeans, List<PayItem> payItems, Context context) {
        this.paysBeans = paysBeans;
        this.payItems = payItems;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return paysBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return paysBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            view = inflater.inflate(R.layout.water_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.water_item_img);
            viewHolder.name = view.findViewById(R.id.water_item_name);
            viewHolder.date = view.findViewById(R.id.water_item_month);
            viewHolder.money = view.findViewById(R.id.water_item_money);
            viewHolder.paybtn = view.findViewById(R.id.water_item_btn);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Pay.PaysBean paysBean = paysBeans.get(i);
        /*水费图标*/
        if(paysBean.getType()==0){
            viewHolder.img.setImageResource(R.mipmap.icon_water);
        }
        /*电费图标*/
        if(paysBean.getType()==1){
            viewHolder.img.setImageResource(R.mipmap.icon_elec);
        }
        /*物业费图标*/
        if(paysBean.getType()==2){
            viewHolder.img.setImageResource(R.mipmap.icon_prop);
        }
        viewHolder.name.setText("户主:"+ Param.user.getName());
        viewHolder.date.setText("时间:"+paysBean.getStartDate()+"——"+paysBean.getEndDate());
        viewHolder.money.setText(paysBeans.get(i).getMoney()+"");
        if(payItems.get(i).getPayItem().getState()==0){
            viewHolder.paybtn.setText("缴费");
        }else if(payItems.get(i).getPayItem().getState()==1){
            viewHolder.paybtn.setText("已缴费");
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.paybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(payItems.get(i).getPayItem().getState()==0){
                        if(Param.money>paysBean.getMoney()){
                            RequestBody body2 = new FormBody.Builder()
                                    .add("iid", payItems.get(i).getPayItem().getIid())
                                    .add("user.uid",Param.user.getUid())
                                    .add("money",payItems.get(i).getPayItem().getMoney()+"")
                                    .add("pay.pid",paysBean.getPid())
                                    .add("state",1+"")
                                    .build();
                            OkHttpUtils.post(body2,Param.UPDATEPAYITEM_URL,handler);
                            RequestBody body = new FormBody.Builder()
                                    .add("user.uid", Param.user.getUid())
                                    .add("money",-paysBean.getMoney()+"")
                                    .build();
                            OkHttpUtils.post(body,Param.USERCHONGZHI,handler);
                            finalViewHolder.paybtn.setText("已缴费");
                        }else{
                            Toast.makeText(context, "余额不足", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        finalViewHolder.paybtn.setText("已缴费");
                        Toast.makeText(context, "已缴费，无需重复缴费", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView name;
        TextView date;
        TextView money;
        Button paybtn;
    }
}
