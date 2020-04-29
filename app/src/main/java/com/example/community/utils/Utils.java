package com.example.community.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.example.community.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*工具类*/
public class Utils {
    public static Dialog progressDialog;
    /**
     * 将图片保存到指定的文件夹
     * @param path 保存图片的指定路径
     * @param filename 保存图片的名称
     * @param bitmap 保存的图片
     */
    public static void savePhotoToSdCard(String path, String filename, Bitmap bitmap){
        /*创建文件夹*/
        File dir= new File(path);
        /*判断文件夹是否存在*/
        if(!dir.exists()){
            /*不存在则创建*/
            dir.mkdirs();
            Log.i("TAG","文件夹创建成功");
        }else{
            Log.i("TAG","文件夹已创建，无需创建");
        }
        /*保存的图片文件*/
        File photoFile = new File(path+"/"+filename);
        /*创建文件输出流*/
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(photoFile);
            if(bitmap!=null){
                /*将图片写入到文件中*/
                if(bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream)){
                    fileOutputStream.flush();
                }
            }
        }catch (IOException e){
            /*删除文件夹*/
            photoFile.delete();
            e.printStackTrace();
        }finally {
            try {
                /*关闭输出流*/
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*图片选择器*/
    public static void selectImg(Activity activity,int max){
        Matisse
                .from(activity)
                //选择视频和图片
                .choose(MimeType.ofAll())
                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                .showSingleMediaType(true)
                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                .capture(true)
                .captureStrategy(new CaptureStrategy(true,"PhotoPicker"))
                //有序选择图片 123456...
                .countable(true)
                //最大选择数量为9
                .maxSelectable(max)
                //选择方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                //界面中缩略图的质量
                .thumbnailScale(0.8f)
                //黑色主题
                .theme(R.style.Matisse_Dracula)
                //Picasso加载方式
                .imageEngine(new PicassoEngine())
                //请求码
                .forResult(Param.REQUEST_CODE_CHOOSE);
    }

    /**
     * 创建一个bitmap图像
     * @param context 上下文
     * @param uri 图片的uri
     * @return 返回一个bitmap图片
     */
    public static Bitmap createBitmap(Context context, Uri uri){
        ContentResolver resolver = context.getContentResolver();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 日期选择器
     * @param listener 传入一个实现了DatePickerDialog.OnDateSetListener的实现类
     * @param fragmentManager 传入一个实现了DatePickerDialog.OnDateSetListener的实现类的Fragment
     */
    public static void showDate(DatePickerDialog.OnDateSetListener listener, FragmentManager fragmentManager){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                listener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(Color.parseColor("#00796b"));
        dpd.show(fragmentManager, "选择日期");
    }

    /**
     * 事件选择器
     * @param listener 传入一个实现了TimePickerDialog.OnTimeSetListener的实现类
     * @param fragmentManager 传入一个实现了TimePickerDialog.OnTimeSetListener的实现类的Fragment
     */
    public static void showTime(TimePickerDialog.OnTimeSetListener listener, FragmentManager fragmentManager){
        Calendar now = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                listener,
                now.get(Calendar.HOUR),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                true
        );
        timePickerDialog.setVersion(TimePickerDialog.Version.VERSION_2);
        timePickerDialog.setAccentColor(Color.parseColor("#00796b"));
        timePickerDialog.show(fragmentManager,"时间选择器");
    }
    public static String createFileName(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
    /*显示登陆对话框*/
    public static void showDialog(Context context,String content){
        progressDialog = new Dialog(context, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText(content+"");
        Log.i("Dialog","显示对话框");
        progressDialog.show();
    }
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
            }
            return isMatch;
        }
    }
    /*关闭对话框*/
    public static void closeDialog(){
        progressDialog.dismiss();
    }
}
