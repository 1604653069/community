package com.example.community.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.Placard;

import java.util.ArrayList;
import java.util.List;

public class PlaAdapter  extends BaseAdapter {
    private List<Placard> placards = new ArrayList<>();
    private LayoutInflater inflater;
    public PlaAdapter(Context context, List<Placard> placards){
        this.inflater = LayoutInflater.from(context);
        this.placards = placards;
    }
    @Override
    public int getCount() {
        return this.placards.size();
    }

    @Override
    public Object getItem(int i) {
        return this.placards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        PlaAdapter.ViewHolder viewHolder=null;
        if(converView==null){
            converView = inflater.inflate(R.layout.pla_item,viewGroup,false);
            viewHolder = new PlaAdapter.ViewHolder();
            viewHolder.title = converView.findViewById(R.id.card_title);
            viewHolder.stitle = converView.findViewById(R.id.card_stitle);
            viewHolder.time = converView.findViewById(R.id.card_time);
            viewHolder.type = converView.findViewById(R.id.card_type);
            converView.setTag(viewHolder);
        }else{
            viewHolder = (PlaAdapter.ViewHolder) converView.getTag();
        }
        Placard placard = placards.get(position);
        viewHolder.title.setText(placard.getTitle());
        viewHolder.stitle.setText(placard.getStitle()+"（点击查看详情）");
        viewHolder.time.setText(placard.getDate());
        viewHolder.type.setText(placard.getType());
        return converView;
    }
    class ViewHolder {
        TextView title;
        TextView stitle;
        TextView time;
        TextView type;
    }
}
