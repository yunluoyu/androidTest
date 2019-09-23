package broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yunlu on 2019/9/20.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("peng");

        Intent in = new Intent();
        in.setAction("com.peng.yunluo.callcanvas");
        in.addCategory("android.intent.category.DEFAULT");
        context.startActivity(in);

    }
}
