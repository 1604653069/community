package com.example.community.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.community.R;
import com.example.community.activity.BusinessActiviey;
import com.example.community.activity.GoodInfoActivity;
import com.example.community.adapter.RecommandAdapter;
import com.example.community.adapter.SecKillAdapter;
import com.example.community.adapter.ServiceAdapter;
import com.example.community.entity.Auth;
import com.example.community.entity.GoodInfo;
import com.example.community.entity.Icon;
import com.example.community.entity.StartUpShop;
import com.example.community.entity.User;
import com.example.community.fragments.HouseFragment;
import com.example.community.fragments.NewFragment;
import com.example.community.fragments.PostFragment;
import com.example.community.fragments.RepairFragment;
import com.example.community.fragments.ShopsFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class IndexFragment extends Fragment {
    /*图片轮播控件*/
    private Banner banner;
    /*社服服务*/
    private GridView gridView;
    /*图片的地址*/
    private List<Integer> imagePath = new ArrayList<>();
    /*每日推送的图片*/
    private List<Integer> tsImgPath = new ArrayList<>();
    /*显示图片的标题*/
    private List<String> titles = new ArrayList<>();
    private List<Icon> icons = new ArrayList<>();
    /*社区服务的设配器*/
    private ServiceAdapter myAdapter;
    /*每日推荐的viewpage*/
    private ViewPager viewPager;
    /*每日推荐设配器*/
    private MyViewPageAdapter adapter;
    /*每日推荐的图片的地址*/
    private List<Integer> imgPath2 = new ArrayList<>();
    /*秒杀控件*/
    private RecyclerView recyclerView;
    /*秒杀设配器*/
    private SecKillAdapter secKillAdapter;
    /*开始界面的商品信息*/
    private StartUpShop startUpShop;
    /*上下文*/
    private Context context;
    /*推荐商品信息*/
    private GridView recommand;
    /*更多商品信息*/
    private TextView moreShops;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("TAG","请求商品的信息为:"+msg.obj.toString());
                     startUpShop = JSON.parseObject(msg.obj.toString(), StartUpShop.class);
                   // shops = JSON.parseArray(msg.obj.toString(),MyShop.class);
                    secKillAdapter = new SecKillAdapter(context,startUpShop.getHot());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                    recyclerView.setAdapter(secKillAdapter);
                  //  secKillAdapter.notifyDataSetChanged();
                    RecommandAdapter adapter2 = new RecommandAdapter(context,startUpShop.getCommand());
                    recommand.setAdapter(adapter2);
                   // adapter2.notifyDataSetChanged();
                    Utils.closeDialog();
                    break;
                case 2:
                    Auth auth = JSON.parseObject(msg.obj.toString(), Auth.class);
                    Param.auth = auth;
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        View view = inflater.inflate(R.layout.test_index, null);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        /*更多商品信息*/
        moreShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new ShopsFragment()).commit();
            }
        });
        /*推荐商品子选项点击事件*/
        recommand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                StartUpShop.CommandBean commandBean = startUpShop.getCommand().get(position);
                GoodInfo goodInfo = new GoodInfo();
                goodInfo.setIsHot(commandBean.getIsHot());
                goodInfo.setMarketPrice(commandBean.getMarketPrice());
                goodInfo.setPdate(commandBean.getPdate());
                goodInfo.setPdesc(commandBean.getPdesc());
                goodInfo.setPflag(commandBean.getPflag());
                goodInfo.setPid(commandBean.getPid());
                goodInfo.setPname(commandBean.getPname());
                goodInfo.setpImage(commandBean.getPImage());
                goodInfo.setShopPrice(commandBean.getShopPrice());
                goodInfo.setType(commandBean.getType());
                startGoodInfoActivity(goodInfo);
            }
        });
    }

    private void initDate(){
        SharedPreferences userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userInfoString = userInfo.getString("user", null);
        if(!TextUtils.isEmpty(userInfoString)){
            User user = JSON.parseObject(userInfoString, User.class);
            Param.user = user;
        }
        /*初始化图片控件*/
        imagePath.add(R.mipmap.index_slideer1);
        imagePath.add(R.mipmap.index_slider2);
        imagePath.add(R.mipmap.index_slider3);
        imagePath.add(R.mipmap.index_slider4);
        /*初始化标题*/
        titles.add("咱小区");
        titles.add("物业在线");
        titles.add("服务保证");
        titles.add("智能社区");

        /*服务数据控件初始化*/
        icons.add(new Icon(R.mipmap.service3,"公告通知"));
        icons.add(new Icon(R.mipmap.serivce1,"报事维修"));
        icons.add(new Icon(R.mipmap.icon_news,"新闻"));
        icons.add(new Icon(R.mipmap.service2,"费用查缴"));
        icons.add(new Icon(R.mipmap.service6,"商城服务"));
        icons.add(new Icon(R.mipmap.service7,"租房"));
        /*每日推荐*/
        imgPath2.add(R.mipmap.icon_hot1);
        imgPath2.add(R.mipmap.icon_hot2);
        /*向服务器发送商品请求*/
        Utils.showDialog(getContext(),"资源加载中");
        RequestBody body = new FormBody.Builder()
                .build();
        OkHttpUtils.post(body, Param.GETSTARTUPSHOPS,handler);
        /*服务器登录请求地址*/
        if(Param.user!=null){
            RequestBody body2 = new FormBody.Builder()
                    /*提交用户名*/
                    .add("uid", Param.user.getUid())
                    .build();
            OkHttpUtils.post(body2, Param.GETUSERAUTH_URL,handler,2);
        }
    }
    private void initView(View view) {
        /*数据的初始化*/
        initDate();
        gridView = view.findViewById(R.id.gridview);
        myAdapter = new ServiceAdapter(getActivity(),icons);
        viewPager = view.findViewById(R.id.viewpage);
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new MyViewPageAdapter(getContext(),imgPath2);
        recommand = view.findViewById(R.id.gv_recommend);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true,new AlphaPageTransformer(new ScaleInTransformer()));
        viewPager.setAdapter(adapter);
        gridView.setAdapter(myAdapter);
        moreShops = view.findViewById(R.id.tv_more_recommend);
        /*初始化控件*/
        banner = view.findViewById(R.id.banner);
        /*图片轮播时间的间隔*/
        banner.setDelayTime(2000);
        /*图片加载器*/
        banner.setImageLoader(new MyImageLoader());
        /*设置图片的标题*/
        banner.setBannerTitles(titles);
        /*设置图片的路径*/
        banner.setImages(imagePath);
        /*播放的动画效果*/
        banner.setBannerAnimation(Transformer.DepthPage);
        /*轮播的样式*/
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        /*开始轮播*/
        banner.start();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(Param.user==null){
                    Toast.makeText(getActivity(), "你还没登陆，无法使用", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Param.auth.getAuth().getState()!=2){
                    Toast.makeText(getActivity(), "你还未认证，无法使用", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (position){
                    case 0:
                        /*公告通知*/
                            getFragmentManager().beginTransaction().replace(R.id.fl,new PostFragment()).commit();
                        break;
                    case 1:
                        /*维修*/
                        getFragmentManager().beginTransaction().replace(R.id.fl,new RepairFragment(),"MyTag").commit();
                        break;
                    case 2:
                        /*新闻*/
                        getFragmentManager().beginTransaction().replace(R.id.fl,new NewFragment()).commit();
                        break;
                    case 3:
                        /*生活缴费*/
                        startActivity(new Intent(context, BusinessActiviey.class));
                        //getFragmentManager().beginTransaction().replace(R.id.fl,new PaymentFragment()).commit();
                        break;
                    case 4:
                        /*商城*/
                        getFragmentManager().beginTransaction().replace(R.id.fl,new ShopsFragment()).commit();
                        break;
                    case 5:
                        /*房屋消息*/
                        getFragmentManager().beginTransaction().replace(R.id.fl,new HouseFragment()).commit();
                        break;
                }
            }
        });
    }
     class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /*进行图片的加载*/
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
        EventBus.getDefault().unregister(this);
    }

    class MyViewPageAdapter extends PagerAdapter{
        private List<Integer> MyImgs;
        private Context context;
        public MyViewPageAdapter(Context context,List<Integer> imgPath){
            this.context = context;
            this.MyImgs = imgPath;
        }
        @Override
        public int getCount() {
            return this.MyImgs.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(this.MyImgs.get(position)).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            container.addView(imageView);
           return  imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
             container.removeView((View) object);
        }
    }
    /*跳转到商品信息界面*/
    public void startGoodInfoActivity(GoodInfo goodInfo){
        Intent intent = new Intent(this.context, GoodInfoActivity.class);
        intent.putExtra("GoodInfo",goodInfo);
        startActivity(intent);
    }
}
