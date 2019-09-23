package utils;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by yunlu on 2019/8/30.
 */

public class MyApp extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        //初始化xutils
        x.Ext.init(this);
    }
}
