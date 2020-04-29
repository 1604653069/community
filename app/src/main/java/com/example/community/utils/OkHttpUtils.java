package com.example.community.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.community.entity.MySS;
import com.example.community.entity.Repair;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.community.utils.Param.REPAIR_URL;
import static com.example.community.utils.Param.SENDSS_URL;

/*数据请求类*/
public class OkHttpUtils {
    public static OkHttpClient okHttpClient =  new OkHttpClient();
    /*发送post请求*/
    public static void post(RequestBody body, String url, final Handler handler){
        Call call = getCall(body,url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message  =new Message();
                handler.sendMessage(message);
                Log.i("TAG","数据请求失败:"+e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                if(response.isSuccessful()){
                    message.what =1;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                    Log.i("TAG","数据请求成功");
                }
            }
        });
    }
    public static void post(RequestBody body, String url, final Handler handler,int flag){
        Call call = getCall(body,url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message  =new Message();
                handler.sendMessage(message);
                Log.i("TAG","数据请求失败:"+e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                if(response.isSuccessful()){
                    message.what =flag;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                    Log.i("TAG","数据请求成功");
                }
            }
        });
    }
    public static void post(RequestBody body, String url, final Handler handler,boolean b){
        Call call = getCall(body,url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message  =new Message();
                handler.sendMessage(message);
                Log.i("TAG","数据请求失败:"+e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(b){
                    Message message = new Message();
                    if(response.isSuccessful()){
                        message.what =1;
                        message.obj = response.body().string();
                        handler.sendMessage(message);
                        Log.i("TAG","数据请求成功");
                    }
                }else{

                }

            }
        });
    }
   /* public static void postJson(RequestBody body,String url,final Handler handler){
          getCall(body,url).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message  =new Message();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                if(response.isSuccessful()){
                    message.what =1;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                }
            }
        });
    }*/
    private static Call getCall(RequestBody body,String url){
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call =  okHttpClient.newCall(request);
        return call;
    }
    /*单文件上传测试*/
    public static void fileupload(String url){
        File dir = new File(Param.UPLOAD_PATH);
        if(dir.exists()&&dir.listFiles().length>0){
            File[] files = dir.listFiles();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (int i=0;i<files.length;i++){
                RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), files[i]);
                builder.addFormDataPart("myFile",files[i].getName(),fileBody);
            }
            builder.addFormDataPart("user.uid",Param.user.getUid());
            builder.setType(MultipartBody.FORM);
            MultipartBody multipartBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(multipartBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("upload","文件上传失败");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("upload","文件上传成功");
                    /*文件上传成功后删除临时文件夹下的所有文件*/
                    File[] files = dir.listFiles();
                    for (int i = 0; i <files.length ; i++) {
                        File file = files[i];
                        file.delete();
                    }
                }
            });
        }
    }
    /*多文件上传*/
    public static void uploadFiles(String url){
        File dir = new File(Param.UPLOAD_PATH);
        if(dir.exists()&&dir.listFiles().length>0){
            File[] files = dir.listFiles();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (int i=0;i<files.length;i++){
                RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), files[i]);
                builder.addFormDataPart("myFile",files[i].getName(),fileBody);
            }
            builder.addFormDataPart("user.uid",Param.user.getUid());
            builder.setType(MultipartBody.FORM);
            MultipartBody multipartBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(multipartBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("upload","文件上传失败");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("upload","文件上传成功");
                    /*文件上传成功后删除临时文件夹下的所有文件*/
                    File[] files = dir.listFiles();
                    for (int i = 0; i <files.length ; i++) {
                        File file = files[i];
                        file.delete();
                    }
                }
            });
        }
    }
    public static void repairPost(String url, Repair repair, boolean flag,Handler handler){
        /*判断是否需要上传图片*/
        if(flag){
            /*需要上传图片*/
            File dir = new File(Param.UPLOAD_PATH);
            if(dir.exists()&&dir.listFiles().length>0){
                File[] files = dir.listFiles();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                for (int i=0;i<files.length;i++){
                    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), files[i]);
                    builder.addFormDataPart("myFile",files[i].getName(),fileBody);
                }
                builder.addFormDataPart("question",repair.getQuestion());
                builder.addFormDataPart("datetime",repair.getDate());
                builder.addFormDataPart("state",0+"");
                builder.addFormDataPart("user.uid",Param.user.getUid());
                builder.setType(MultipartBody.FORM);
                MultipartBody multipartBody = builder.build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(multipartBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("upload","文件上传失败");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        message.what=1;
                        handler.sendMessage(message);
                        Log.i("upload","文件上传成功");
                        /*文件上传成功后删除临时文件夹下的所有文件*/
                        File[] files = dir.listFiles();
                        for (int i = 0; i <files.length ; i++) {
                            File file = files[i];
                            file.delete();
                        }
                    }
                });
            }
        }else{
            /*不需要上传图片请求*/
            RequestBody body = new FormBody.Builder()
                    .add("question",repair.getQuestion())
                    .add("datetime",repair.getDate())
                    .add("state",0+"")
                    .add("user.uid",Param.user.getUid())
                    .build();
            post(body,REPAIR_URL,handler);
        }
    }

    public static void get(String url,LinkedHashMap<String,String> params){
        String getUrl =attachHttpGetParams(url,params);
        Request request = new Request.Builder()
                .url(getUrl)
                .addHeader("Authorization","APPCODE b12e8ad7968e453eb7842adfac87cf80")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG","请求新闻数据失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.i("TAG","新闻请求的数据为:"+response.body().string());
                EventBus.getDefault().post(response.body().string());
            }
        });
    }
    public static void get(String url,Handler handler){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG","请求商品失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //EventBus.getDefault().post(response.body().string());
                //Log.i("TAG","商品信息请求成功:"+response.body().string());
                Message message = new Message();
                message.what =1;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
    }
    /*封装get请求参数*/
    private static String attachHttpGetParams(String url, LinkedHashMap<String,String> params){
        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");
        for (int i=0;i<params.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
            Log.i("TAG","stringBuffer:"+stringBuffer.toString());
        }
        return url + stringBuffer.toString();
    }

    public static void ssPost(String url, MySS ss, boolean flag, Handler handler){
        /*判断是否需要上传图片*/
        if(flag){
            /*需要上传图片*/
            File dir = new File(Param.UPLOAD_PATH);
            if(dir.exists()&&dir.listFiles().length>0){
                File[] files = dir.listFiles();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                for (int i=0;i<files.length;i++){
                    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), files[i]);
                    builder.addFormDataPart("myFile",files[i].getName(),fileBody);
                }
                builder.addFormDataPart("ssContent",ss.getContent());
                builder.addFormDataPart("time",ss.getTime());
                builder.addFormDataPart("user.uid",Param.user.getUid());
                builder.setType(MultipartBody.FORM);
                MultipartBody multipartBody = builder.build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(multipartBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("upload","文件上传失败");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        message.what=1;
                        handler.sendMessage(message);
                        Log.i("upload","文件上传成功");
                        /*文件上传成功后删除临时文件夹下的所有文件*/
                        File[] files = dir.listFiles();
                        for (int i = 0; i <files.length ; i++) {
                            File file = files[i];
                            file.delete();
                        }
                    }
                });
            }
        }else{
            /*不需要上传图片请求*/
            RequestBody body = new FormBody.Builder()
                    .add("ssContent",ss.getContent())
                    .add("time",ss.getTime())
                    .add("user.uid",Param.user.getUid())
                    .build();
            post(body,SENDSS_URL,handler);
        }
    }
}
