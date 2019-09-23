package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yunlu on 2019/9/10.
 */

public class AidlService extends Service{


    private CatBinder catBinder;
    private Timer timer;
    private String []colors = new String[]{"红色","黄色","蓝色"};
    private double []weights = new double[]{2.3,1.5,1.58};
    private String color;
    private double weight;
    class CatBinder extends ICat.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catBinder = new CatBinder();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int rand  = (int) (Math.random()*3);
                color = colors[rand];
                weight = weights[rand];
            }
        },0,800);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return catBinder;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }
}
