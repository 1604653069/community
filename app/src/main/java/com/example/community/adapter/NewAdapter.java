package com.example.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.community.R;
import com.example.community.entity.News;
import com.squareup.picasso.Picasso;

public class NewAdapter extends BaseAdapter {
    private News news ;
    private Context context;
    private LayoutInflater inflater;
    public NewAdapter(Context context,News news){
        inflater = LayoutInflater.from(context);
        this.news = news;
        this.context = context;
    }
    @Override
    public int getCount() {
        return news.getResult().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return news.getResult().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewAdapter.ViewHolder viewHolder=null;
        if(view==null){
            view = inflater.inflate(R.layout.new_item,viewGroup,false);
            viewHolder = new NewAdapter.ViewHolder();
            viewHolder.title = view.findViewById(R.id.new_item_title);
            viewHolder.imageView1 = view.findViewById(R.id.new_img1);
            viewHolder.imageView2 = view.findViewById(R.id.new_img2);
            viewHolder.imageView3 = view.findViewById(R.id.new_img3);
            view.setTag(viewHolder);
        }else{
            viewHolder = (NewAdapter.ViewHolder) view.getTag();
        }
        viewHolder.title.setText(news.getResult().getData().get(i).getTitle());
        Picasso.with(context).load(news.getResult().getData().get(i).getThumbnail_pic_s()).into(viewHolder.imageView1);
        Picasso.with(context).load(news.getResult().getData().get(i).getThumbnail_pic_s02()).into(viewHolder.imageView2);
        Picasso.with(context).load(news.getResult().getData().get(i).getThumbnail_pic_s03()).into(viewHolder.imageView3);
        return view;
    }
    class ViewHolder {
        TextView title;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
    }
}
