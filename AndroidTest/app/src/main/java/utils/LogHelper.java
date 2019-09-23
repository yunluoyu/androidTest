package utils;

import android.util.Log;

/**
 * Created by yunlu on 2019/9/4.
 */

public class LogHelper {

    private final static boolean isDebug = true;
    private final static String TAG = "AAA";

    public static void d(String ret){
        if(isDebug)
            Log.d(TAG,ret);
    }
    public static void i(String ret){
        if(isDebug)
            Log.i(TAG,ret);
    }
    public static void e(String ret){
        if(isDebug)
            Log.e(TAG,ret);
    }

}
