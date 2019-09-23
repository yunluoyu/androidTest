package view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yunlu.androidtest.R;

/**
 * Created by yunlu on 2019/8/30.
 */

public class StateLayout extends FrameLayout {

    private View loadingView;
    private View errorView;
    private View successView;

    public StateLayout(@NonNull Context context) {
        this(context,null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        loadingView = View.inflate(context, R.layout.page_loading,null);
        errorView = View.inflate(context,R.layout.page_error,null);

        Button reloadBtn = errorView.findViewById(R.id.btn_reload);
        reloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReloadListener != null)
                    onReloadListener.onReload();
            }
        });

        addView(loadingView);
        addView(errorView);
        hideAll();
    }
    public void bindSucessView(View view){
        successView = view;
        if(successView != null) {
            successView.setVisibility(View.GONE);
            addView(successView);
        }
    }

    public void hideAll(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        if(successView != null)
            successView.setVisibility(View.GONE);
    }
    public void showErrorView(){
        hideAll();
        errorView.setVisibility(View.VISIBLE);
    }

    public void showLoadingView(){
        hideAll();
        loadingView.setVisibility(View.VISIBLE);
    }

    public void showSuccessView(){
        hideAll();
        if(successView !=null)
            successView.setVisibility(View.VISIBLE);
    }

    private OnReloadListener onReloadListener;
    public void setOnReloadListener(OnReloadListener onReloadListener){
            this.onReloadListener = onReloadListener;
    }


    // 定义回调接口
    public interface OnReloadListener{
        void onReload();
    }


}
