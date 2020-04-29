package com.example.community.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.community.R;
import com.example.community.entity.GoodInfo;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.squareup.picasso.Picasso;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class GoodInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private LinearLayout ll_root;
    private Button btn_more;
    private GoodInfo goodInfo;
    private boolean isFavorite;
    private WebView webView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    //Toast.makeText(GoodInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    if(msg.obj.toString().contains(goodInfo.getPid())){
                        Drawable drawable = getResources().getDrawable(R.mipmap.good_collected);
                        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                        tvGoodInfoCollection.setCompoundDrawables(null, drawable,null , null);
                        isFavorite = true;
                        tvGoodInfoCollection.setText("已收藏");
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info);
        noTitle();
        /*初始化控件*/
        findViews();
        getMyIntent();
        isFavoriteGood();
    }

    private void isFavoriteGood() {
        if(Param.user!=null){
            RequestBody body = new FormBody.Builder()
                    .add("user.uid", Param.user.getUid())
                    .build();
            OkHttpUtils.post(body,Param.GETALLFAVORITEINFO_URL,handler);
        }
    }

    /*获取意图中的商品信息*/
    private void getMyIntent() {
        Intent intent = getIntent();
        goodInfo = (GoodInfo) intent.getSerializableExtra("GoodInfo");
        Picasso.with(this).load(goodInfo.getpImage()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodInfo.getPname());
        tvGoodInfoDesc.setText(goodInfo.getPdesc());
        tvGoodInfoPrice.setText("￥"+goodInfo.getShopPrice());
        Log.i("TAG","意图中的商品名称为:"+goodInfo.getPname());
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
    }

    private void findViews() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);

        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);

        btn_more = (Button) findViewById(R.id.btn_more);
        webView = findViewById(R.id.wb_good_info_more);
        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvMoreShare.setOnClickListener(this);
        tvMoreSearch.setOnClickListener(this);
        tvMoreHome.setOnClickListener(this);

        btn_more.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            /*顶部返回按钮*/
            finish();
        } else if (v == ibGoodInfoMore) {
            /*显示更多信息*/
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
        } else if (v == btn_more) {
            ll_root.setVisibility(View.GONE);
        } else if(v==tvGoodInfoCallcenter){
            Toast.makeText(this, "你点击的是客服", Toast.LENGTH_SHORT).show();
        }else if(v==tvGoodInfoCollection){
            if(Param.user==null){
                Toast.makeText(this, "你还没登陆，不能进行操作", Toast.LENGTH_SHORT).show();
                return ;
            }
            if(!isFavorite){
                Drawable drawable = getResources().getDrawable(R.mipmap.good_collected);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                tvGoodInfoCollection.setCompoundDrawables(null, drawable,null , null);
                isFavorite = true;
                RequestBody body = new FormBody.Builder()
                        /*提交用户名*/
                        .add("type", 0+"")
                        /*提交登陆密码*/
                        .add("product.pid", goodInfo.getPid())
                        .add("user.uid",Param.user.getUid())
                        .build();
                        OkHttpUtils.post(body,Param.ADDFAVORITEPRODUCT_URL,handler);
                tvGoodInfoCollection.setText("已收藏");
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
            }else{
                isFavorite = false;
                Drawable drawable = getResources().getDrawable(R.mipmap.good_uncollected);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                tvGoodInfoCollection.setCompoundDrawables(null, drawable,null , null);
                RequestBody body = new FormBody.Builder()
                        /*提交用户名*/
                        .add("type", 0+"")
                        /*提交登陆密码*/
                        .add("product.pid", goodInfo.getPid())
                        .add("user.uid",Param.user.getUid())
                        .build();
                OkHttpUtils.post(body,Param.REMOVEFAVORITE_URL,handler);
                tvGoodInfoCollection.setText("收藏");
            }
        }else if(v==tvGoodInfoCart){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("fragmentName","cart");
            startActivity(intent);
        }else if(v==btnGoodInfoAddcart){
            if(Param.user==null){
                Toast.makeText(this, "你还没登陆，不能进行登陆", Toast.LENGTH_SHORT).show();
                return;
            }
            if(Param.user==null){
                RequestBody body = new FormBody.Builder()
                        .add("quantity", 1+"")
                        .add("product.pid",goodInfo.getPid())
                        .add("user.uid",Param.user.getUid())
                        .build();
                OkHttpUtils.post(body, Param.ADDCARTITEM,handler);
                Toast.makeText(this, "加购成功！", Toast.LENGTH_SHORT).show();
            }else{
                RequestBody body = new FormBody.Builder()
                        .add("quantity", 1+"")
                        .add("product.pid",goodInfo.getPid())
                        .add("user.uid",Param.user.getUid())
                        .build();
                OkHttpUtils.post(body, Param.ADDCARTITEM,handler);
                Toast.makeText(this, "加购成功！", Toast.LENGTH_SHORT).show();
            }
        }
}
    /*修改标题栏*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
