package com.example.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.House;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HouseAdatper extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<House> houses;
    public HouseAdatper(Context context,List<House> houses){
        this.context = context;
        this.houses = houses;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return houses.size();
    }

    @Override
    public Object getItem(int i) {
        return houses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler viewHodler= null;
        if(view==null){
            view = inflater.inflate(R.layout.item_house,null);
            viewHodler = new ViewHodler();
            viewHodler.img  = view.findViewById(R.id.item_house_img);
            viewHodler.img.setScaleType(ImageView.ScaleType.FIT_XY);
            viewHodler.title = view.findViewById(R.id.item_house_title);
            viewHodler.address=view.findViewById(R.id.item_house_address);
            viewHodler.type=view.findViewById(R.id.item_house_type);
            viewHodler.rent=view.findViewById(R.id.item_house_rent);
            view.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) view.getTag();
        }
        House house = houses.get(i);
        Picasso.with(context).load(house.getHouseImgs().get(0).getPath()).into(viewHodler.img);
        viewHodler.title.setText(houses.get(i).getTitle());
        viewHodler.address.setText(houses.get(i).getAddress());
        viewHodler.rent.setText(houses.get(i).getRent()+"元/月");
        return view;
    }
    class ViewHodler{
        ImageView img;
        TextView title;
        TextView address;
        LinearLayout type;
        TextView rent;
    }
}
