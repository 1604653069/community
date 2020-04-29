package com.example.community.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.community.R;
import com.example.community.fragment.CartFragment;
import com.example.community.fragment.IndexFragment;
import com.example.community.fragment.MineFragment;
import com.example.community.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_CHOOSE =1 ;
    /*底部菜单栏*/
    private FragmentManager fragmentManager;

    private LinearLayout ll_index;
    private LinearLayout ll_service;
    private LinearLayout ll_zhineng;
    private LinearLayout ll_mine;

    private ImageView indexImg;
    private ImageView serviceImg;
    private ImageView zhiNImg;
    private ImageView mineImg;

    private TextView indexT;
    private TextView serviceT;
    private TextView zhiNT;
    private TextView mineT;

    private List<ImageView> imageViews = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private List<Integer> imageViews2 = new ArrayList<>();
    private List<Integer> imageView3 = new ArrayList<>();
    private int WRITE_COARSE_LOCATION_REQUEST_CODE=100;
    private LocationManager lm;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    aMapLocation.getCity();//城市信息
                    aMapLocation.getDistrict();//城区信息
                    aMapLocation.getStreet();//街道信息
                    aMapLocation.getStreetNum();//街道门牌号信息
                    aMapLocation.getCityCode();//城市编码
                    aMapLocation.getAdCode();//地区编码
                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    aMapLocation.getFloor();//获取当前室内定位的楼层
                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    Log.i("TAG",aMapLocation.getAddress());
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        noTitle();
        initView();
        initDate();
        initListener();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getIntentInfo();
    }

    private void getIntentInfo() {
        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragmentName");
        if (!TextUtils.isEmpty(fragmentName)&&fragmentName.equals("cart")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl,new CartFragment()).commit();
        }
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        /*底部点击按钮*/
        ll_index = (LinearLayout) this.findViewById(R.id.ll_index);
        ll_service = (LinearLayout) this.findViewById(R.id.ll_service);
        ll_zhineng = (LinearLayout) this.findViewById(R.id.ll_zhineng);
        ll_mine = (LinearLayout) this.findViewById(R.id.ll_mine);

        indexImg = (ImageView) this.findViewById(R.id.bottom_index_img);
        indexT = (TextView) this.findViewById(R.id.bottom_index_text);

        serviceImg = (ImageView) this.findViewById(R.id.bottom_service_img);
        serviceT = (TextView) this.findViewById(R.id.bottom_service_text);

        zhiNImg = (ImageView) this.findViewById(R.id.bottom_zhin_img);
        zhiNT = (TextView) this.findViewById(R.id.bottom_zhin_text);

        mineImg = (ImageView) this.findViewById(R.id.bottom_mine_img);
        mineT = (TextView) this.findViewById(R.id.bottom_mine_text);
        showGPSContacts();
    }
    /**
     * 检测GPS、位置权限是否开启
     */
    public void showGPSContacts() {
        lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            WRITE_COARSE_LOCATION_REQUEST_CODE);
                } else {
                    location();//getLocation为定位方法
                }
            } else {
                location();//getLocation为定位方法
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show();
        }
    }

    public void location(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null != mLocationClient){
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    private void initDate(){
        /*将图片添加到集合中*/
        imageViews.add(indexImg);
        imageViews.add(serviceImg);
        imageViews.add(zhiNImg);
        imageViews.add(mineImg);

        textViews.add(indexT);
        textViews.add(serviceT);
        textViews.add(zhiNT);
        textViews.add(mineT);

        imageViews2.add(R.mipmap.tab_index2);
        imageViews2.add(R.mipmap.tab_talk2);
        imageViews2.add(R.mipmap.tab_cart2);
        imageViews2.add(R.mipmap.tab_mine2);

        imageView3.add(R.mipmap.tab_index);
        imageView3.add(R.mipmap.tab_talk);
        imageView3.add(R.mipmap.tab_cart);
        imageView3.add(R.mipmap.tab_mine);

        change(0);
        fragmentManager.beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
        //fragmentManager.beginTransaction().replace(R.id.fl,new MyIndexFragment()).commit();

    }
    private void initListener(){
        ll_index.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        ll_zhineng.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
    }
    /*修改标题栏*/
    private void noTitle() {
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_index:
                change(0);
                fragmentManager.beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
                break;
            case R.id.ll_service:
                change(1);
                fragmentManager.beginTransaction().replace(R.id.fl,new ServiceFragment()).commit();
                break;
            case R.id.ll_zhineng:
                change(2);
                fragmentManager.beginTransaction().replace(R.id.fl,new CartFragment()).commit();
                break;
            case R.id.ll_mine:
                change(3);
                fragmentManager.beginTransaction().replace(R.id.fl,new MineFragment(),"MyTag").commit();
                break;
        }
    }
    public void change(int position){
        for (int i=0;i<imageViews.size();i++){
            if(i==position){
                        imageViews.get(position).setImageResource(imageViews2.get(position));
                        textViews.get(position).setTextColor(Color.GREEN);
            }else{
                        imageViews.get(i).setImageResource(imageView3.get(i));
                         textViews.get(i).setTextColor(Color.GRAY);
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            Fragment fragment =getSupportFragmentManager().findFragmentByTag("MyTag");
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==WRITE_COARSE_LOCATION_REQUEST_CODE){
            location();
        }else{
            Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
        }
    }
}
