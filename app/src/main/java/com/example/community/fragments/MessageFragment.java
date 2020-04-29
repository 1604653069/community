package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.community.R;

public class MessageFragment extends Fragment {
    private TextView date;
    private TextView title;
    private TextView content;
    private ImageView back;
    private TextView  title_contet;
    private ImageView img;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showmessage,container,false);
        initView(view);
        initDate();
        return view;
    }

    private void initDate() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String date = bundle.getString("date");
        String content = bundle.getString("content");
        this.title.setText(title);
        this.date.setText("发布时间:"+date);
        this.content.setText(content);
        if(title.contains("消杀"))
            img.setImageResource(R.mipmap.shachong);
        else if(title.contains("停电"))
            img.setImageResource(R.mipmap.tingdian);
        else if(title.contains("停水"))
            img.setImageResource(R.mipmap.tingshui);

    }

    private void initView(View view) {
        title = view.findViewById(R.id.message_title);
        date = view.findViewById(R.id.message_date);
        content = view.findViewById(R.id.message_content);
        back = view.findViewById(R.id.title_back);
        img = view.findViewById(R.id.message_img);
        title_contet = view.findViewById(R.id.title_text);
        title_contet.setText("公告详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new PostFragment()).commit();
            }
        });
    }

}
