package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.community.R;
import com.example.community.entity.Auth;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AuthFragment extends Fragment {
    private TextView title;
    private ImageView back;
    private EditText edit_village;
    private EditText edit_tel;
    private EditText edit_name;
    private EditText edit_house;
    private Button btnCommint;
    private OptionsPickerView pvOptions;
    //  省份
    private ArrayList<String> provinceBeanList = new ArrayList<>();
    //  城市
    private ArrayList<String> cities;
    private ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    private ArrayList<String> district;
    private ArrayList<List<String>> districts;
    private ArrayList<List<List<String>>> districtList = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Log.i("TAG","更新成功");
                    break;
                case 2:
                    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(msg.obj.toString());
                    Boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Auth auth = JSON.parseObject(msg.obj.toString(), Auth.class);
                        edit_tel.setText(auth.getAuth().getTel());
                        edit_tel.setEnabled(false);
                    }else{
                        Toast.makeText(getContext(), "获取认证信息失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    Log.i("TAG","用户信息修改成功");
                    getFragmentManager().beginTransaction().replace(R.id.fl,new AuthFragment2()).commit();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_layout,container,false);
        initView(view);
        initData();
        initListner();
        return view;
    }

    private void initListner() {
        edit_house.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(view.getId()== R.id.auth_house&&b){
                    showPicker();
                }
            }
        });
        btnCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody body = new FormBody.Builder()
                        .add("user.uid", Param.user.getUid())
                        .add("uid",Param.user.getUid())
                        .add("village","武夷小区")
                        .add("tel",edit_tel.getText().toString())
                        .add("name",edit_name.getText().toString())
                        .add("address",edit_house.getText().toString())
                        .add("state",1+"")
                        .add("username",Param.user.getUsername())
                        .add("password",Param.user.getPassword())
                        .build();
                OkHttpUtils.post(body,Param.UPDATEAUTH_URL,handler);
                OkHttpUtils.post(body,Param.USERUPDATE_URL,handler,3);
            }
        });
    }

    private void initData() {
        /*服务器登录请求地址*/
        RequestBody body = new FormBody.Builder()
                .add("uid", Param.user.getUid())
                .build();
        OkHttpUtils.post(body, Param.GETUSERAUTH_URL,handler,2);
        initJsonData();
    }

    private void initView(View view) {
        edit_village = view.findViewById(R.id.auth_village);
        edit_tel = view.findViewById(R.id.auth_tel);
        edit_name = view.findViewById(R.id.auth_name);
        edit_house = view.findViewById(R.id.auth_house);
        btnCommint = view.findViewById(R.id.auth_btn);
        title = view.findViewById(R.id.title_text);
        back = view.findViewById(R.id.title_back);
        title.setText("业主验证");
    }
    private void showPicker() {
        pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                edit_house.setText(provinceBeanList.get(options1)+cityList.get(options1).get(options2)+districtList.get(options1).get(options2).get(options3));
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {

            }
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.GRAY)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
        pvOptions.setPicker(provinceBeanList, cityList, districtList);//添加数据源
        pvOptions.show();
    }
    private void initJsonData() {
        //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */

        String JsonData = getJson("test.json");//获取assets目录下的json文件数据
        parseJson(JsonData);
    }

    /**
     * 从asset目录下读取fileName文件内容
     *
     * @param fileName 待读取asset下的文件名
     * @return 得到省市县的String
     */
    private String getJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getActivity().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    /**
     * 解析json填充集合
     *
     * @param str 待解析的json，获取省市县
     */
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
//                provinceBeanList.add(new ProvinceBean(provinceName));
                provinceBeanList.add(provinceName);
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();
                //   声明存放城市的集合
                districts = new ArrayList<>();
                //声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();
                    // 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
