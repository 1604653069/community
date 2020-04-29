package com.example.community.fragments;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.community.R;
import com.example.community.entity.Address;
import com.example.community.fragment.MineFragment;
import com.example.community.listener.TextWatcherListener;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;

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

public class AddressFragment2 extends Fragment {
    private Button btnSave;
    private EditText editAddress;
    private EditText editName;
    private EditText editPhone;
    private EditText editAddress_xx;
    private TextView title;
    private ImageView back;
    private TableLayout tableLayout;
    private ViewPager viewPager;
    private CheckBox checkBox;
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
    private Address userAddress = new Address();
    private Address address;
    private boolean update;
    private boolean isSelected;
    public AddressFragment2(Address address){
               this.address = address;
               update = true;
    }
    public AddressFragment2(){}
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
              super.handleMessage(msg);
              switch (msg.what){
                  case 1:
                      if(update){
                          Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                      }else{
                          Toast.makeText(getContext(), "地址添加成功", Toast.LENGTH_SHORT).show();
                      }
                      getFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment()).commit();
                      break;
              }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address2,container,false);
        initJsonData();
        initView(view);
        initListener();
        editAddress();
        return view;
    }

    private void editAddress() {
        if(address!=null){
            String str1 = address.getAddress();
            editName.setText(address.getName());
            editPhone.setText(address.getPhone());
            editAddress.setText(str1.substring(0,str1.lastIndexOf('-')));
            editAddress_xx.setText(str1.substring(str1.lastIndexOf('-')+1));
        }

    }

    private void initListener() {
        editName.addTextChangedListener(new TextWatcherListener(getContext(),editName,editPhone,editAddress,editAddress_xx,btnSave));
        editPhone.addTextChangedListener(new TextWatcherListener(getContext(),editName,editPhone,editAddress,editAddress_xx,btnSave));
        editAddress.addTextChangedListener(new TextWatcherListener(getContext(),editName,editPhone,editAddress,editAddress_xx,btnSave));
        editAddress_xx.addTextChangedListener(new TextWatcherListener(getContext(),editName,editPhone,editAddress,editAddress_xx,btnSave));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fl,new MineFragment()).commit();
            }
        });
       editAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
                if(view.getId()== R.id.edit_address&&b){
                    showPicker();
                }
           }
       });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isSelected = b;
            }
        });
       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(!Utils.isPhone(editPhone.getText().toString())){
                   Toast.makeText(getContext(), "输入的手机号码格式不正确", Toast.LENGTH_SHORT).show();
                   return;
               }
               userAddress.setPhone(editPhone.getText().toString());
               userAddress.setUser(Param.user);
               userAddress.setStatues(1);
               FormBody.Builder builder = new FormBody.Builder();
               builder
                       .add("name",editName.getText().toString())
                       .add("address", editAddress.getText().toString()+"-"+editAddress_xx.getText().toString())
                       .add("phone", editPhone.getText().toString())
                       .add("user.uid", Param.user.getUid());
                       if(isSelected){
                           builder.add("statues",1+"");
                       }else{
                           builder.add("statues",0+"");
                       }
                       if(update){
                            RequestBody body = builder.add("aid",address.getAid()).build();
                            OkHttpUtils.post(body,Param.CHANGEADDRESSINFO,handler);
                       }else {
                           OkHttpUtils.post(builder.build(),Param.ADDADDRESSINFO,handler);
                       }

           }
       });
    }

    private void initView(View view) {
        btnSave = view.findViewById(R.id.btn_save);
        title = view.findViewById(R.id.title_text);
        title.setText("添加收货地址");
        back = view.findViewById(R.id.title_back);
        editAddress = view.findViewById(R.id.edit_address);
        editName = view.findViewById(R.id.edit_name);
        editPhone = view.findViewById(R.id.edit_phone);
        editAddress_xx = view.findViewById(R.id.edit_address_xx);
        checkBox = view.findViewById(R.id.img_switch);
    }


    private void showPicker() {
        pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String city = provinceBeanList.get(options1);
                String address; //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1) + "-" + districtList.get(options1).get(options2).get(options3);
                } else {
//                    address = provinceBeanList.get(options1) + " " + cityList.get(options1).get(option2) + " " + districtList.get(options1).get(option2).get(options3);
                    address = provinceBeanList.get(options1) + "-" + cityList.get(options1).get(options2)+"-"+districtList.get(options1).get(options2).get(options3);
                }
                editAddress.setText(address);
                userAddress.setAddress(address);
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

        String JsonData = getJson("province.json");//获取assets目录下的json文件数据
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
