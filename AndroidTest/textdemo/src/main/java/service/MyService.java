package service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yunlu on 2019/9/10.
 */

public class MyService extends Service {
    private int count;
    private boolean quit = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d("AAA","onStart");

        new Thread(){
            @Override
            public void run() {
                while(!quit){
                    count ++;
                }
            }
        }.start();
        return new MyIBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("AAA","onDestory");
        this.quit = true;
    }

    public class MyIBinder extends Binder{
        public  int getCount(){
            return count;
        }
    }

}
