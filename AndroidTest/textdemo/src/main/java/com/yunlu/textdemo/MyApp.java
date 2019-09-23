package com.yunlu.textdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yunlu on 2019/9/4.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        System.out.printf("xutils 初始化");
    }
}
