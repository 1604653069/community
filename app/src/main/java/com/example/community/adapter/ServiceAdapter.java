package com.example.community.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.Icon;

import java.util.ArrayList;
import java.util.List;

/*服务选择设配器*/
public class ServiceAdapter extends BaseAdapter {
    private List<Icon> icons = new ArrayList<>();
    private LayoutInflater inflater;
    public ServiceAdapter(Context context, List<Icon> icons){
       inflater = LayoutInflater.from(context);
        this.icons = icons;
    }
    @Override
    public int getCount() {
        return icons.size();
    }

    @Override
    public Object getItem(int i) {
        return icons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(converView==null){
            converView = inflater.inflate(R.layout.gird_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.img = converView.findViewById(R.id.item_img);
            viewHolder.name = converView.findViewById(R.id.item_name);
            converView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) converView.getTag();
        }
        Icon icon = icons.get(position);
        viewHolder.img.setImageResource(icon.getId());
        viewHolder.name.setText(icon.getName());
        Log.i("TAG","数组的大小为:"+this.icons.size());
        return converView;
    }
    class ViewHolder {
        ImageView img;
        TextView name;
    }
}
