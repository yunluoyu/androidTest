package com.yunlu.textdemo;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import bean.User;
import service.MyService;

public class MainActivity extends AppCompatActivity {

    private NotificationManager am;
    private ObjectOutputStream os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AAA","onCreate");
        if(savedInstanceState != null){
            Toast.makeText(MainActivity.this,savedInstanceState.getString("peng"),Toast.LENGTH_SHORT).show();
        }


        am = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("123","测试channel",NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("测试Channel的描述信息");
        // 设置呼吸灯颜色
        channel.enableLights(true);
        channel.setLightColor(Color.BLUE);
        //设置震动
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{0,50,10,150});
        am.createNotificationChannel(channel);
    }



    public void requestHttp(View view) {
        //Toast.makeText(MainActivity.this,"点击",0).show();
        Log.e("AAA","开启请求");
        new Thread(){
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams("https://sinacloud.net/appstore/common.json");

                x.http().request(HttpMethod.GET, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                       Log.e("AAA","获取成功"+result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("AAA","发生错误");
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {

                    }
                    @Override
                    public void onFinished() {

                    }
                });

            }
        }.start();


    }

    public void send(View view) {
        Intent intent = new Intent(MainActivity.this,OtherActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

//        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle("通知");
//        messagingStyle.setConversationTitle("一条新通知");
//        Notification.MessagingStyle.Message msg = new Notification.MessagingStyle.Message("恭喜你，工资加薪",System.currentTimeMillis(),"发送人");
//        messagingStyle.addMessage(msg);


        Notification notification = new Notification.Builder(this,"123")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent)
                .setContentTitle("我是")
                .setContentText("大家好，----")
                .build();

        am.notify(0x456,notification);


    }

    public void cancel(View view) {
        am.cancelAll();
    }

    public void createDialog(View view) {

        AlertDialog diglog = new AlertDialog.Builder(this)
                .setTitle("对话框")
                .setIcon(R.drawable.icon)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"中立",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setSingleChoiceItems(new String[]{"选项1", "选项2", "选项3", "选项4"}, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,which+" ",Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        diglog.show();




    }

    public void createPopupWindow(View view) {

        Button btn = findViewById(R.id.btn_pop);

//        View v = new View(this);
//        view.setBackgroundColor(Color.BLUE);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,40);
//
//        v.setLayoutParams(params);
//        view.setAlpha(0.8f);



        TextView tv = new TextView(this);
        tv.setText("HELLO");
        tv.setBackgroundColor(Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(45);
        tv.setAlpha(0.5f);
        tv.setTextColor(Color.BLACK);
        Display dis = getWindowManager().getDefaultDisplay();
        DisplayMetrics disM = new DisplayMetrics();
        dis.getMetrics(disM);

        PopupWindow popupWindow = new PopupWindow(disM.widthPixels,disM.heightPixels/2);

        popupWindow.setContentView(tv);
        popupWindow.setOutsideTouchable(true);

        View v = (View) btn.getParent();
        popupWindow.showAtLocation(v,Gravity.CENTER,0,0);


    }

    public void createDatePickerDialog(View view) {

        Calendar calendar = Calendar.getInstance();

//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//            }
//        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this);

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(view.getContext(),year+"."+month+"."+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        });
        datePickerDialog.show();
    }

    public void createTimePickerDialog(View view) {

        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(MainActivity.this,hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();

            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
        timePickerDialog.show();
        calendar.get(Calendar.SECOND);
    }

    public void getConfiguration(View view) {

        // 获取设备配置信息
        Configuration con = getResources().getConfiguration();
        String ori = "竖向屏幕";
        //Configuration.ORIENTATION_PORTRAIT：竖向屏幕
        //Configuration.ORIENTATION_LANDSCAPE：横向屏幕

        if(con.orientation == Configuration.ORIENTATION_LANDSCAPE)
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(con.orientation == Configuration.ORIENTATION_PORTRAIT)
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void getPermission(View view) {

        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},0x123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0x123){
            //PackageManager.PERMISSION_DENIED    denied  拒绝
            //PackageManager.PERMISSION_GRANTED   granted 接受
            for(String str : permissions){
                Log.d("AAA",str);
            }
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Log.d("AAA","同意");
                if(permissions[0].equals("android.permission.READ_CONTACTS")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_PICK);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivity(intent);
                }
                if(permissions[0].equals("android.permission.WRITE_EXTERNAL_STORAGE")){
                        wirteUserToSdcard();
                }
            }
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Log.d("AAA","不同意");
            }
        }
    }

    private void wirteUserToSdcard() {
        String path = Environment.getExternalStorageDirectory().getPath()+"";
        Log.d("AAA",path);
        try {

            File file = new File(path,"com.peng/t.txt");

           

            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(new User("李四",22));
            os.close();
        }
        catch (IOException e){
            Log.d("AAA","error");
            e.printStackTrace();

        }


    }

    public void toActivity(View view) {

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_EDIT);
//        intent.setData(Uri.parse("content://com.android.contacts/contacts/1"));
//        startActivity(intent);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:15362134672"));
        startActivity(intent);

    }

    public void getAvailableProgress(View view) {

        int time = Runtime.getRuntime().availableProcessors();
        Toast.makeText(MainActivity.this,time+"核",Toast.LENGTH_SHORT).show();
    }

    public void goToCanvasActivity(View view) {

        Intent intent = new Intent(this,CanvasActivity.class);
        startActivity(intent);
    }

    private Intent service;
    MyService.MyIBinder binder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           binder = (MyService.MyIBinder) service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void startSevice(View view) {
        service = new Intent(this, MyService.class);
        //startService(service);
        bindService(service, connection,Service.BIND_AUTO_CREATE);
    }

    public void stopSevice(View view) {
        unbindService(connection);
    }

    public void getCount(View view) {
        Toast.makeText(MainActivity.this,binder.getCount()+"",Toast.LENGTH_SHORT).show();
    }

    public void gotoSecondAcivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);

        startActivity(intent);
    }

    public void gotoFragment(View view) {
        Intent intent = new Intent(this,ThridActivity.class);
        startActivity(intent);
    }

    public void sendBroadcastWithPerm(View view) {


//          <action android:name="com.peng.yunluo.callcanvas" />
//                <category android:name="android.intent.category.DEFAULT"/>

//        String per = "com.peng.yunluo.action.broadcast";
//        Intent intent = new Intent();
//        //intent.putExtra("peng","hello world");
//        intent.setAction("com.peng.remote");
//        sendBroadcast(intent,"com.peng.remote.share");

        Intent intent = new Intent();
        intent.setAction("com.peng.yunluo.app");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("peng","大家好");
        Log.d("AAA","onSaveInstance");
        super.onSaveInstanceState(outState);
    }


    public void serializableTest(View view) {


        User user = new User("张三",19);

        String path = getFilesDir().getPath();
        //Log.d("AAA",path);

//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(path)));
//            User user1 = (User)in.readObject();
//            Log.d("AAA","读取到的"+user1.getName());
//            in.close();
            File file = new File(path,"t.txt");

            Log.d("AAA",file.getAbsolutePath());

            try {
//                    os = new ObjectOutputStream(new FileOutputStream(file));
//                    os.writeObject(user);
//                    os.close();
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                        User user1 = (User)in.readObject();
                        Log.d("AAA","读取到的"+user1.getName());
                        in.close();


            } catch (Exception e) {
                Log.d("AAA","出错了");
                e.printStackTrace();

            }
    }

    public void wirte2Sdcard(View view) {

        // 请求权限
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x123);


    }

    public void gotoURL(View view) {
        Intent intent = new Intent(this,URLActivity.class);
        startActivity(intent);
    }
}
