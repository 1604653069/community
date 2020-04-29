package com.example.community.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.community.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BigImgActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<String> imgUrl = new ArrayList<>();
    private int position =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img);
        noTitle();
        initView();
    }
    /*沉浸模式*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    private void initData() {
        Intent intent = getIntent();
        imgUrl = intent.getStringArrayListExtra("imgUrl");
        position = intent.getIntExtra("position",0);
        Log.i("TAG","传输的图片大小为"+imgUrl.size());
    }

    private void initView() {
        initData();
        viewPager = this.findViewById(R.id.big_img_viewpager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgUrl.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.big_img,null);
                PhotoView photoView = view.findViewById(R.id.photoview);
                Picasso.with(getApplicationContext()).load(imgUrl.get(position)).into(photoView);
                photoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        viewPager.setCurrentItem(position);
    }
}
