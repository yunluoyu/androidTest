package com.yunlu.textdemo;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import ViewUtils.MyViewInjcet;
import ViewUtils.OnClick;
import ViewUtils.ViewUtil;

public class URLActivity extends AppCompatActivity {

//    @MyViewInjcet(R.id.btn_get)
//    Button btnGet;
//    @MyViewInjcet(R.id.btn_post)
//    Button btnPost;
    @MyViewInjcet(R.id.iv_image)
    private ImageView imageView;

    private Bitmap bitMap;

    static class MyHandler extends Handler{

        private WeakReference<URLActivity> a;
        private MyHandler(WeakReference<URLActivity> a){
            this.a = a;
        }

        @Override
        public void handleMessage(Message msg) {
            LogU.d("加载图片");
            a.get().imageView.setImageBitmap(a.get().bitMap);

        }
    }
    private MyHandler handler = new MyHandler(new WeakReference<>(this));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        ViewUtil.inject(this);

    }

    @OnClick(R.id.btn_post)
    public void onPost(View v){
        requestPermissions(new String[]{Manifest.permission.INTERNET},0x987);
    }
    @OnClick(R.id.btn_get)
    public void onGet(View v){
        requestPermissions(new String[]{Manifest.permission.INTERNET},0x789);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 0x789 && permissions[0].equals("android.permission.INTERNET") && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            URLGetTest();
        }
        if(requestCode == 0x987 && permissions[0].equals("android.permission.INTERNET") && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            URLPostTest();
        }

    }

    private void URLPostTest() {
    }

    public void URLGetTest(){

        final String imageUrl = "https://c-ssl.duitang.com/uploads/blog/201403/31/20140331023911_AYQ4i.jpeg";
        // 获取图片的资源 将图片打印ImageView中;
        new Thread(){
            @Override
            public void run() {
                InputStream in = null;
                try {
                    URL url = new URL(imageUrl);
                    in  = url.openStream();
                    bitMap = BitmapFactory.decodeStream(in);
                    handler.sendEmptyMessage(0);
                    in.close();
                    in = url.openStream();
                    //BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream("peng.jpeg"));
                    OutputStream bof = openFileOutput("peng.jpeg", Context.MODE_PRIVATE);

                    byte [] r = new byte[1024];
                    int len;
                    while ((len = in.read(r)) > 0){
                        bof.write(r,0,len);
                    }
                    bof.flush();
                    bof.close();
                    in.close();

                } catch (Exception e) {
                    LogU.d("获取图片出错了");
                    e.printStackTrace();
                }finally {
                    if(in != null)
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }


            }
        }.start();


//        try {
//            URL  url = new URL(urlStr);
//            LogU.d("资源名称："+url.getFile());
//            LogU.d("主机名称："+url.getHost());
//            LogU.d("路径："+url.getPath());
//            LogU.d("端口："+url.getPort());
//            LogU.d("查询字段："+url.getQuery());
//        } catch (MalformedURLException e) {
//            LogU.d("失败");
//            e.printStackTrace();
//        }


    }

}
