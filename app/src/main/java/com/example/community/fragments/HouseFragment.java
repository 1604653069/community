package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.community.R;
import com.example.community.activity.HouseInfoActivity;
import com.example.community.adapter.ConstellationAdapter;
import com.example.community.adapter.GirdDropDownAdapter;
import com.example.community.adapter.HouseAdatper;
import com.example.community.adapter.ListDropDownAdapter;
import com.example.community.entity.House;
import com.example.community.fragment.IndexFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.example.community.view.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class HouseFragment extends Fragment {
    private ListView listView;
    private ImageView back;
    private TextView title;
    private DropDownMenu dropDownMenu;
    private String headers[] = {"价格", "房型"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private int constellationPosition = 0;
    private String citys[] = {"不限", "500以下", "800以下", "1000以下"};
    private int[] prices = {0,500,800,1000};
    private String ages[] = {"不限", "单间", "套间"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private HouseAdatper adatper;
    private List<House> houses;
    private int index=0;
    private int index2=0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(getContext(), "房屋信息请求成功", Toast.LENGTH_SHORT).show();
                    houses = JSON.parseArray(msg.obj.toString(),House.class);
                    adatper = new HouseAdatper(getContext(),houses);
                    listView.setAdapter(adatper);
                    Utils.closeDialog();
                    break;
                case 2:
                    JSONObject jsonObject = JSONObject.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        houses = JSON.parseArray(jsonObject.getString("houses"),House.class);
                        adatper = new HouseAdatper(getContext(),houses);
                        listView.setAdapter(adatper);
                    }else{
                        Toast.makeText(getContext(), "服务器繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                    Utils.closeDialog();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house,container,false);
        initView(view);
        initData();
        dropDownMenu();
        initListener();
        return view;
    }

    private void initData() {
        Utils.showDialog(getContext(),"资源加载中");
        OkHttpUtils.get(Param.GETHOUSEINFO,handler);
    }
    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new IndexFragment()).commit();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),houses.get(i).getTitle() , Toast.LENGTH_SHORT).show();
                startHouseInfoActivity(houses.get(i));
            }
        });
    }

    public void initView(View view){
        listView = view.findViewById(R.id.house_listview);
        back = view.findViewById(R.id.title_back);
        title = view.findViewById(R.id.title_text);
        dropDownMenu = view.findViewById(R.id.drop_down_menu);
        title.setText("房屋出租");


    }

    public void dropDownMenu(){
        //init city menu
        final ListView cityView = new ListView(getContext());
        cityAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(getContext());
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(getContext());
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = constellationView.findViewById( R.id.constellation);
        constellationAdapter = new ConstellationAdapter(getContext(), Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = constellationView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                dropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
       //   popupViews.add(sexView);
      //  popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                Toast.makeText(getActivity(), "你选择的是:"+citys[position], Toast.LENGTH_SHORT).show();
                dropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                dropDownMenu.closeMenu();
                index = position;
                if(position!=0){
                    if(index2!=0){
                        RequestBody body = new FormBody.Builder()
                                .add("type2", (index2-1)+"")
                                .add("price", prices[position]+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }else{
                        RequestBody body = new FormBody.Builder()
                                .add("price", prices[position]+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }
                }else{
                    if(index2!=0){
                        RequestBody body = new FormBody.Builder()
                                .add("type2", (index2-1)+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }else{
                        OkHttpUtils.get(Param.GETHOUSEINFO,handler);
                    }

                }
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index2 = position;
                ageAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                dropDownMenu.closeMenu();
                if(position!=0){
                    if(index!=0){
                        RequestBody body = new FormBody.Builder()
                                .add("type2", (position-1)+"")
                                .add("price", prices[index]+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }else{
                        RequestBody body = new FormBody.Builder()
                                .add("type2", (position-1)+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }
                }else{
                    if(index!=0){
                        RequestBody body = new FormBody.Builder()
                                .add("price", prices[index]+"")
                                .build();
                        OkHttpUtils.post(body,Param.GETHOUSEBYSORT_URL,handler,2);
                    }else{
                        OkHttpUtils.get(Param.GETHOUSEINFO,handler);
                    }
                }
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                dropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context com.example.community.view
        TextView contentView = new TextView(getContext());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropdownview
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }
    public void startHouseInfoActivity(House house){
        Intent intent = new Intent(getContext(), HouseInfoActivity.class);
        intent.putExtra("houseInfo",house);
        startActivity(intent);
    }
}
