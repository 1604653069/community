package com.example.community.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.community.R;
import com.example.community.adapter.ServiceAdapter;
import com.example.community.entity.House;
import com.example.community.entity.Icon;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class HouseInfoActivity extends BaseActivity implements OnBannerListener {
    private Banner banner;
    private House house;
    private ImageView back;
    private TextView rent;
    private TextView detail;
    private TextView date;
    private TextView houseType;
    private TextView houseArea;
    private GridView gridView;
    private ServiceAdapter adapter;
    private List<Icon> icons = new ArrayList<>();
    private int max=5;
    private int min=0;
    private ImageView favorite;
    private boolean isFavorite;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(msg.obj.toString().contains(house.getHid())){
                        isFavorite = true;
                        favorite.setImageResource(R.mipmap.good_collected);
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
        noTitle();
        initView();
        initData();
        initListener();
    }

    private void initData() {
        RequestBody body = new FormBody.Builder()
                .add("user.uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body,Param.GETALLFAVORITEINFO_URL,handler);
    }

    private void initListener() {
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFavorite){
                    isFavorite = true;
                    favorite.setImageResource(R.mipmap.good_collected);
                    RequestBody body = new FormBody.Builder()
                            /*提交用户名*/
                            .add("type", 1+"")
                            /*提交登陆密码*/
                            .add("house.hid", house.getHid())
                            .add("user.uid", Param.user.getUid())
                            .build();
                    OkHttpUtils.post(body,Param.ADDFAVORITEPRODUCT_URL,handler);
                    Toast.makeText(HouseInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }else{
                    isFavorite = false;
                    favorite.setImageResource(R.mipmap.good_uncollected);
                    RequestBody body = new FormBody.Builder()
                            /*提交用户名*/
                            .add("type", 1+"")
                            /*提交登陆密码*/
                            .add("house.hid",house.getHid())
                            .add("user.uid",Param.user.getUid())
                            .build();
                    OkHttpUtils.post(body,Param.REMOVEFAVORITE_URL,handler);
                }
            }
        });
    }

    /*沉浸模式*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
    public void getHouseInfo(){
        Intent intent = getIntent();
        house = (House) intent.getSerializableExtra("houseInfo");
        icons.add(new Icon(R.mipmap.rent_room_service_wifi,"WIFI"));
        icons.add(new Icon(R.mipmap.rent_room_service_bed,"床"));
        icons.add(new Icon(R.mipmap.rent_room_service_chester,"衣柜"));
        icons.add(new Icon(R.mipmap.rent_room_service_sofa,"沙发"));
        icons.add(new Icon(R.mipmap.rent_room_service_desk,"座椅"));
        icons.add(new Icon(R.mipmap.rent_room_service_tv,"电视机"));
        icons.add(new Icon(R.mipmap.rent_room_service_air_condition,"空调"));
        icons.add(new Icon(R.mipmap.rent_room_service_washer,"洗衣机"));
        icons.add(new Icon(R.mipmap.rent_room_service_freezer,"冰箱"));
        icons.add(new Icon(R.mipmap.rent_room_service_heater,"暖气"));
        icons.add(new Icon(R.mipmap.rent_room_service_water_heater,"热水器"));
        icons.add(new Icon(R.mipmap.rent_room_service_kitchen_ventilator,"燃气灶"));
        icons.add(new Icon(R.mipmap.rent_room_service_smoke,"抽烟机"));
        icons.add(new Icon(R.mipmap.rent_room_service_microwave_oven,"微波炉"));
        icons.add(new Icon(R.mipmap.rent_room_service_electromagnetic_oven,"电磁炉"));
        icons.add(new Icon(R.mipmap.rent_room_service_water_heater,"卫生间"));
        icons.add(new Icon(R.mipmap.rent_service_independent_balcony,"阳台"));
        int num = min + (int)(Math.random() * (max-min+1));
        for(int i=0;i<num;i++){
            int ma = 15;
            int mn = 0;
            int position = mn + (int)(Math.random() * (ma-mn+1));
            icons.get(position).setId(R.mipmap.ic_none);
        }
        adapter = new ServiceAdapter(getApplicationContext(),icons);
    }
    private void initView() {
        getHouseInfo();
        this.banner = this.findViewById(R.id.house_banner);
        this.back = this.findViewById(R.id.house_back);
        this.rent = this.findViewById(R.id.house_rent);
        this.detail = this.findViewById(R.id.house_detail);
        this.date = this.findViewById(R.id.house_date);
        this.gridView = this.findViewById(R.id.house_sb);
        this.houseType = this.findViewById(R.id.house_type);
        this.houseArea = this.findViewById(R.id.house_area);
        this.gridView.setAdapter(adapter);
        this.rent.setText(house.getRent()+"元/月");
        this.detail.setText(house.getDetailed());
        this.houseArea.setText(house.getArea()+"㎡");
        if(house.getType()==0){
            this.houseType.setText("单间");
        }else{
            this.houseType.setText("套房");
        }
        this.favorite = this.findViewById(R.id.favorite_house);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy");
        String format = simpleDateFormat.format(new Date(house.getDate()));
        this.date.setText(format+"更新");
        /*图片加载器*/
        banner.setImageLoader(new MyImageLoader());
        /*设置图片的路径*/
        banner.setImages(house.getHouseImgs());
        /*播放的动画效果*/
        banner.setBannerAnimation(Transformer.DepthPage);
        /*轮播的样式*/
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        /*手动播放*/
        banner.isAutoPlay(false);
        banner.setOnBannerListener(this);
        /*开始轮播*/
        banner.start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "你点击的是"+position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),BigImgActivity.class);
        ArrayList<String> imgUrl = new ArrayList<>();
        for(int i=0;i<house.getHouseImgs().size();i++){
            imgUrl.add(house.getHouseImgs().get(i).getPath());
        }
        intent.putStringArrayListExtra("imgUrl",imgUrl);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /*进行图片的加载*/
            House.HouseImgsBean imgsBean  = (House.HouseImgsBean) path;
           Glide.with(context.getApplicationContext())
                    .load(imgsBean.getPath())
                    .into(imageView);
        }
    }
}
