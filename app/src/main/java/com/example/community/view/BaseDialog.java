package com.example.community.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

public class BaseDialog extends Dialog {
    public View mCreateView;
    public Context mContext;
    public int mScreenWidth;//屏幕宽  
    public int mScreenHeight;//屏幕高  
    public int mDensity;//单位像素  
    public Animation mExitAnim;//退出动画  
    public Animation mEnterAnim;//进入动画  

    public BaseDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        //计算屏幕的宽高像素  
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;
        mDensity = (int) metrics.density;
    }

    @Override
    public void show() {
        super.show();
        enterAnimation();//进入动画  
    }

    /**
     *  
     *      * 进入动画 
     *      
     */
    private void enterAnimation() {
        if (mEnterAnim == null) {
            mEnterAnim = new TranslateAnimation(1, 0, 1, 0, 1, 1, 1, 0);
            mEnterAnim.setDuration(500);
        }
        mCreateView.startAnimation(mEnterAnim);
    }

    /**
     *  
     *      * 退出动画 
     *      
     */
    private void exitAnimation() {
        if (mExitAnim == null) {
            mExitAnim = new TranslateAnimation(1, 0, 1, 0, 1, 0, 1, 1);
            mExitAnim.setDuration(500);
            mExitAnim.setAnimationListener(
                    new AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            dismissDialog(); //动画完成执行关闭  
                        }
                    });
        }
        mCreateView.startAnimation(mExitAnim);
    }

    /**
     *  
     *      * 执行关闭 
     *      
     */


    private void dismissDialog() {
        super.dismiss();
    }

    /**
     *  
     *      * 执行动画 
     *      
     */

    @Override

    public void dismiss() {
        exitAnimation();
    }

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }
}
