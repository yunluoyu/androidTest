package com.yunlu.textdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ViewUtils.MyViewInjcet;
import ViewUtils.OnClick;
import ViewUtils.ViewUtil;

public class SecondActivity extends AppCompatActivity {

    @MyViewInjcet(R.id.tv1)
    private TextView tv1;

    @MyViewInjcet(R.id.tv2)
    private TextView tv2;
    private Handler subHandler;
    private Looper subLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ViewUtil.inject(this);
        Log.d("AAA",tv1.getText()+"---"+tv2.getText());

        new Thread(){
            @Override
            public void run() {
                /*
                * 1.创建MessageQueue对象
                * 2.将sThreadLocal和该子线程绑定
                * */
                Looper.prepare();
                subHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        String str = (String) msg.obj;
                       Toast.makeText(SecondActivity.this,"我是子线程:收到主线程发送过来的消息="+str,Toast.LENGTH_SHORT).show();
                    }
                };
                /*
                *  loop() 死循环 从MessageQueue中拿到消息对象，
                *  消息对象 的target属性为Handler对象，调用Handler.distpathMessage();
                *  dispathMessage();会回调Callback.handlerMessage();方法
                * */
                subLooper = Looper.myLooper();
                Looper.loop();
            }
        }.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subLooper.quit();
    }

    @OnClick(R.id.btn)
    public void ClickMe(View v){

        Message msg = new Message();
        msg.obj = "大家好， 我是来自主线程";
        subHandler.sendMessage(msg);
        //subHandler.obtainMessage(0,"我来自主线程").sendToTarget();

        //Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
    }


}
