package com.example.community.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.community.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class WecomeActivity extends AppCompatActivity {
    private List<Integer> imagePath = new ArrayList<>();
    private ViewPager viewPager;
    private  SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wecome);
        noTitle();
        initView();
        initData();
    }

    private void initData() {
         sharedPreferences = getSharedPreferences("remeber",Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
        boolean first = sharedPreferences.getBoolean("first", false);
        if(first){
            startActivity(new Intent(WecomeActivity.this,MainActivity.class));
            finish();
        }else{

        }
    }

    /*沉浸模式*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    private void initView() {
        /*初始化图片控件*/
        imagePath.add(R.mipmap.bg_guide20);
        imagePath.add(R.mipmap.bg_guide21);
        imagePath.add(R.mipmap.bg_guide22);
        imagePath.add(R.mipmap.bg_guide23);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(imagePath.get(position));
                container.addView(imageView);
                if(position==imagePath.size()-1){
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            editor.putBoolean("first",true);
                            editor.commit();
                            startActivity(new Intent(WecomeActivity.this,MainActivity.class));
                        }
                    });
                }
                return imageView;
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((ImageView)object);
            }

            @Override
            public int getCount() {
                return imagePath.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
        });
    }
}
