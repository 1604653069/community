package com.example.community.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.community.R;

/**
 * sgf
 * QQ分享底部弹出动画
 */
public class ShareDialog extends BaseDialog{
    //界面显示的数据
    private String[]  mTexts={"QQ好友","QQ空间"};
    private int[]	  mImgs={R.mipmap.qq, R.mipmap.qqkongjian};//图片数据
    private OnClickListener mListener;

    public ShareDialog(Context context) {
        super(context);
        mCreateView=initView();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Dialog没有标题。需在setContentView之前设置，在之后设置会报错
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置Dialog背景透明效果，必须设置一个背景，否则会有系统的Dialog样式：外部白框
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(mCreateView);//添加视图布局
        setLayout();
    }

    private View initView() {
        int num=mTexts.length;
        int number=3;//一行显示的数量 //最外面的布局
        LinearLayout group=new LinearLayout(mContext);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        group.setLayoutParams(params);
        group.setBackgroundColor(Color.BLACK);
        group.setOrientation(LinearLayout.VERTICAL);//设置垂直方向
        //标题
        TextView title=new TextView(mContext);
        params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                50*mDensity);
        params.bottomMargin=1;
        title.setLayoutParams(params);
        title.setText("分享");
        title.setTextColor(Color.BLACK);
        title.setTextSize(16);
        title.setGravity(Gravity.CENTER);//字居中
        title.setBackgroundColor(Color.WHITE);
        group.addView(title);
        //设置内容
        for(int i=0,j=0;i<num;){
            j++;
            LinearLayout ll=new LinearLayout(mContext);
            params=new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,1.0f);//设置权重
            ll.setLayoutParams(params);
            ll.setBackgroundColor(Color.WHITE);
            ll.setOrientation(LinearLayout.HORIZONTAL);//设置水平方向
            group.addView(ll);
            for(;i<j*number && i<num;i++){//一行三个
                //设置分享图文控件
                TextView item=new TextView(mContext);
                params=new LinearLayout.LayoutParams(
                        mScreenWidth/number,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                params.topMargin=10*mDensity;
                params.bottomMargin=10*mDensity;
                item.setLayoutParams(params);
                //item.setBounds(50, 50);//设置图片的宽高
                Drawable top = mContext.getResources().getDrawable(mImgs[i]);
                item.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);//给DrawableTop设置图片
                item.setText(mTexts[i]);//设置文字
                item.setGravity(Gravity.CENTER);//字居中
                MyOnClickListener listener=new MyOnClickListener(i);
                item.setOnClickListener(listener);//设置点击监听器
                ll.addView(item);
            }
        }
        //底部
        TextView cancel=new TextView(mContext);
        params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                50*mDensity);
        params.topMargin=1;
        cancel.setLayoutParams(params);
        cancel.setText("取消");
        cancel.setTextColor(Color.BLACK);
        cancel.setTextSize(16);
        cancel.setGravity(Gravity.CENTER);//字居中
        cancel.setBackgroundColor(Color.WHITE);
        cancel.setOnClickListener(new View.OnClickListener() {//设置取消
            public void onClick(View arg0) {
                ShareDialog.this.cancel();
            }
        });
        group.addView(cancel);
        return group;
    }
    public class MyOnClickListener implements View.OnClickListener{
        private int mPosition;
        public MyOnClickListener(int position){
            mPosition=position;
        }
        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext, mTexts[mPosition], Toast.LENGTH_SHORT).show();
            ShareDialog.this.cancel();
            if(mListener!=null){
                mListener.OnClick(v,mPosition);//调用自定义接口, TODO（分享在调用界面实现分享功能）
            }
        }
    }
    public interface OnClickListener {
        void OnClick(View v, int position);
    }
    public void setOnClickListener(OnClickListener listener){
        mListener=listener;
    }
    private void setLayout(){
        WindowManager.LayoutParams params=this.getWindow().getAttributes();
        params.width=mScreenWidth;
        params.gravity=Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;;//水平居中、底部
        this.getWindow().setAttributes(params);
    }

}
