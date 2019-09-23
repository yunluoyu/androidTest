package utils;

import android.widget.ImageView;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yunlu on 2019/9/4.
 * 单例模式
 */

public class HttpHelper {

    private HttpManager am = x.http();
    private static HttpHelper helper = new HttpHelper();
    private static String res;
    private OnRequestListener lis;
    private HttpHelper(){
    }
    public static HttpHelper create(){
        return helper;
    }

    public String doPost(String url){
        am.post(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                res = result;
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
        return res;
    }

    public void doGet(String url){
        am.get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(lis != null)
                    lis.onSucceed(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                    if(lis != null)
                        lis.onFailed(ex.getMessage());
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }
    public void displayImageView(ImageView v, String url){
        x.image().bind(v , url);
    }




    public interface OnRequestListener{
          void onSucceed(String result);
          void onFailed(String cause);
    }

    public void setOnRequestListener(OnRequestListener lis){
        this.lis = lis;
    }
}
