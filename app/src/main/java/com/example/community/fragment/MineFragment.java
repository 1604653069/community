package com.example.community.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.example.community.R;
import com.example.community.activity.LoginActivity;
import com.example.community.entity.MineEntry;
import com.example.community.entity.User;
import com.example.community.entity.UserHead;
import com.example.community.entity.Wallet;
import com.example.community.fragments.AddressFragment;
import com.example.community.fragments.AuthFragment2;
import com.example.community.fragments.FavoriteFragment;
import com.example.community.fragments.MySS;
import com.example.community.fragments.RechargeFragment;
import com.example.community.fragments.RepairOrderFragment;
import com.example.community.fragments.ShopingOrderFragment;
import com.example.community.utils.OkHttpUtils;
import com.example.community.utils.Param;
import com.example.community.utils.Utils;
import com.example.community.view.ShareDialog;
import com.squareup.picasso.Picasso;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhihu.matisse.Matisse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends Fragment implements View.OnClickListener {
    private LinearLayout ll_yue;
    private LinearLayout ll_jifen;
    private LinearLayout ll_quan;
    private ListView listView;
    private CircleImageView circleImageView;
    private List<MineEntry> datas = new ArrayList<>();
    private MyAdapter adapter;
    private TextView money;
    private Wallet wallet;
    private TextView tv_name;
    private ImageView mine_touxiang;
    private Dialog bgSetDialog;
    private TextView tv_jifen;
    private  List<Uri> mSelected = new ArrayList<>();
    private int score;
    private Tencent tencent;
    private BaseUiListener baseUiListener = new BaseUiListener();
    private Button button;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                        wallet = JSON.parseObject(msg.obj.toString(),Wallet.class);
                        money.setText("￥"+wallet.getMoney()+".00");
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    try {
                        UserHead userHead = JSON.parseObject(msg.obj.toString(), UserHead.class);
                        Picasso.with(getContext()).load(userHead.getPath()).into(mine_touxiang);
                    }catch (Exception e){
                        Picasso.with(getContext()).load(R.mipmap.house).into(mine_touxiang);
                    }
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler3 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(msg.obj.toString());
                        score = jsonObject.optInt("score");
                        Log.i("TAG","用户当前的积分为:"+msg.obj.toString());
                      /*  tv_jifen.setText(""+score);*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.fragment_mine,null);
       initData();
       initView(view);
       initListner();
       return view;
    }
    private void initData() {
        datas.add(new MineEntry(R.mipmap.icon_order,"我的订单"));
        datas.add(new MineEntry(R.mipmap.icon_save_thin,"我的收藏"));
        datas.add(new MineEntry(R.mipmap.icon_my_estate,"我的小区"));
        datas.add(new MineEntry(R.mipmap.address_mark,"地址管理"));
        datas.add(new MineEntry(R.mipmap.shuoshuo,"我的说说"));
        datas.add(new MineEntry(R.mipmap.baoxiu,"报修订单"));
        datas.add(new MineEntry(R.mipmap.share,"分享"));
        adapter = new MyAdapter();
        if(Param.user!=null){
            RequestBody body = new FormBody.Builder()
                    .add("user.uid", Param.user.getUid())
                    .build();
            OkHttpUtils.post(body,Param.GETUSERWALLET_URL,handler);
            OkHttpUtils.post(body,Param.GETUSERHEAD_URL,handler2);
            OkHttpUtils.post(body,Param.GETUSERSCORE_URL,handler3);
        }
    }

    private void initListner() {
        button.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        ll_yue.setOnClickListener(this);
        /*ll_jifen.setOnClickListener(this);*/
        mine_touxiang.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(Param.user==null){
                    Toast.makeText(getActivity(), "你还没有登录，无法使用", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (i){
                    case 0:
                        //我的红包
                       // getFragmentManager().beginTransaction().replace(R.id.fl,new HongBaoFragment()).commit();
                        //我的订单
                        getFragmentManager().beginTransaction().replace(R.id.fl,new ShopingOrderFragment()).commit();
                        break;
                    case 1:
                        //我的收藏
                        getFragmentManager().beginTransaction().replace(R.id.fl,new FavoriteFragment()).commit();
                        break;
                    case 2:
                        //小区认证
                        getFragmentManager().beginTransaction().replace(R.id.fl,new AuthFragment2()).commit();
                        break;
                    case 3:
                        //地址管理
                        getFragmentManager().beginTransaction().replace(R.id.fl,new AddressFragment()).commit();
                        break;
                    case 4:
                        //我的说说
                        getFragmentManager().beginTransaction().replace(R.id.fl,new MySS()).commit();
                        break;
                    case 5:
                        //报修订单
                        getFragmentManager().beginTransaction().replace(R.id.fl,new RepairOrderFragment()).commit();
                        break;
                    case 6:
                       // isSupport();
                        //onClickShare();
                        showShareDialog();
                        break;
                }
            }
        });
    }

    private void initView(View view) {
        money = view.findViewById(R.id.tv_money);
        circleImageView = view.findViewById(R.id.mine_touxiang);
        tv_name = view.findViewById(R.id.tv_name);
        ll_yue = view.findViewById(R.id.ll_yue);
    /*    ll_jifen = com.example.community.view.findViewById(R.id.ll_jifen);*/
        listView = view.findViewById(R.id.mine_list);
        mine_touxiang = view.findViewById(R.id.mine_touxiang);
        listView.setAdapter(adapter);
        if(Param.user!=null)
        tv_name.setText("昵称:"+Param.user.getName());
        else
            tv_name.setText("游客");
     /*   tv_jifen = com.example.community.view.findViewById(R.id.tv_jifen);*/
        tencent = Tencent.createInstance("101856511", getContext());
        button = view.findViewById(R.id.mine_btn);
        if(Param.user==null){
            button.setText("登陆");
        }else{
            button.setText("注销");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mine_touxiang:
                showDialog();
                break;
            case R.id.ll_yue:
                if(Param.user!=null)
                    getFragmentManager().beginTransaction().replace(R.id.fl,new RechargeFragment(wallet.getMoney())).commit();
                 else
                    Toast.makeText(getContext(), "未登录，无法使用", Toast.LENGTH_SHORT).show();
                break;
         /*   case R.id.ll_jifen:
                getFragmentManager().beginTransaction().replace(R.id.fl,new LuckFragment(score)).commit();
                break;*/
            case R.id.open_from_camera:

                break;
            case R.id.open_album:
                Utils.selectImg(getActivity(),1);
                break;
            case R.id.cancel:
                bgSetDialog.dismiss();
                break;
            case R.id.mine_btn:
                if(Param.user!=null){
                    SharedPreferences userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = userInfo.edit();
                    edit.putString("user",null);
                    edit.commit();
                }
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler= null;
            if(view==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_mine_listview,null);
                viewHodler = new ViewHodler();
                viewHodler.icon  = view.findViewById(R.id.mine_img);
                viewHodler.name = view.findViewById(R.id.mine_name);
                view.setTag(viewHodler);
            }else{
                viewHodler = (ViewHodler) view.getTag();
            }
            MineEntry mineEntry= datas.get(i);
            viewHodler.icon.setImageResource(mineEntry.getImgId());
            viewHodler.name.setText(mineEntry.getName());
            return view;
        }
    }
    class ViewHodler {
        ImageView icon;
        TextView name;
    }
    public void showDialog(){
            bgSetDialog = new Dialog(getContext(), R.style.BottomDialogStyle);
            //填充对话框的布局
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pop, null);
            //初始化控件
            TextView fromCamera = (TextView) view.findViewById(R.id.open_from_camera);
            TextView fromSystem = (TextView) view.findViewById(R.id.open_album);
            TextView canle = (TextView) view.findViewById(R.id.cancel);
            fromCamera.setOnClickListener(this);
            fromSystem.setOnClickListener(this);
            canle.setOnClickListener(this);
            //将布局设置给Dialog
            bgSetDialog.setContentView(view);
            //获取当前Activity所在的窗体
            Window dialogWindow = bgSetDialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity( Gravity.BOTTOM);
            dialogWindow.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
            dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager wm1 = getActivity().getWindowManager();
            int width1 = wm1.getDefaultDisplay().getWidth();
            //lp.width = (int) (getScreenWidth()*0.95);
            lp.width = width1;
            lp.y = 20; //设置Dialog距离底部的距离
            dialogWindow.setAttributes(lp); //将属性设置给窗体
            bgSetDialog.show();//显示对话框
        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Param.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //图片路径 同样视频地址也是这个 根据requestCode
            mSelected = Matisse.obtainResult(data);
            mine_touxiang.setImageURI(mSelected.get(0));
            //向临时存储文件夹中保存要上传的头像
            Utils.savePhotoToSdCard(Param.UPLOAD_PATH,Utils.createFileName()+".png",Utils.createBitmap(getContext(),mSelected.get(0)));
            OkHttpUtils.uploadFiles(Param.HEADIMG_URL);
            bgSetDialog.dismiss();
            Toast.makeText(getContext(), "头像上传成功", Toast.LENGTH_SHORT).show();
        }
        Tencent.onActivityResultData(requestCode,resultCode,data,baseUiListener);
        if(requestCode== Constants.REQUEST_API ){
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, baseUiListener);
            }
        }
    }
    class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            Log.i("TAG","分享成功");
        }

        @Override
        public void onError(UiError uiError) {
            Log.i("TAG","分享失败");
        }

        @Override
        public void onCancel() {
            Log.i("TAG","用户取消了");
        }
    }
    private void onClickShare() {
         Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, "音乐链接");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        tencent.shareToQQ(getActivity(), params,baseUiListener);
    }
    /**
     * 分享到QQ空间
     * @param
     */
    public void qqQzoneShare() {
        int QzoneType = QzoneShare.SHARE_TO_QZONE_TYPE_NO_TYPE;
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneType);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "夷山社区");//分享标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "让小区更智能，让服务更贴心");//分享的内容摘要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "https://blog.csdn.net/k571039838k");//分享的链接
        //分享的图片, 以ArrayList<String>的类型传入，以便支持多张图片（注：图片最多支持9张图片，多余的图片会被丢弃）
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://avatar.csdn.net/6/9/D/1_k571039838k.jpg?1537239037");//添加一个图片地址
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);//分享的图片URL
        tencent.shareToQzone(getActivity(), params, new BaseUiListener());
    }
    public void showShareDialog(){
        //分享
        ShareDialog shareDialog = new ShareDialog(getContext());
        shareDialog.show();
        shareDialog.setOnClickListener(new ShareDialog.OnClickListener() {
            @Override
            public void OnClick(View v, int position) {
                if(position == 0){
                    onClickShare();
                }else  if(position == 1){
                    qqQzoneShare();
                }
            }
        });
    }
}
