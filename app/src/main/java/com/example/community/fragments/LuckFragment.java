package com.example.community.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.community.R;
import com.example.community.fragment.MineFragment;
import com.example.community.view.LotteryView;

public class LuckFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private LotteryView lotteryView;
    private int score;
    public LuckFragment(int score){
        this.score = score;
    }
    public LuckFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.luck_layout,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        title = view.findViewById(R.id.title_text);
        title.setText("幸运转转转");
        back = view.findViewById(R.id.title_back);
        lotteryView = view.findViewById(R.id.lotteryview);
        lotteryView.setCurrentScore(score);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
    }
}
