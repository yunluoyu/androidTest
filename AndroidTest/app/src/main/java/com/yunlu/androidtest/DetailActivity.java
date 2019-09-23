package com.yunlu.androidtest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    private Button downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        if(bar != null)
            bar.hide();

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT >= 23){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {

        downloadBtn = findViewById(R.id.detail_btn_download);
        initDownLoadBtn();

    }


    private void initDownLoadBtn() {

        Random random = new Random();
        int r = random.nextInt(200)+20;
        int g = random.nextInt(200)+20;
        int b = random.nextInt(200)+20;
        GradientDrawable normal =  getGradientDrawable(r,g,b);
        GradientDrawable pressed =  getGradientDrawable(r,g+30,b+30);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed},normal);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pressed);
        downloadBtn.setBackground(stateListDrawable);

    }

    public GradientDrawable getGradientDrawable(int r, int g, int b){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.rgb(r,g,b));
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(dp2px(DetailActivity.this,12));
        drawable.setSize(dp2px(DetailActivity.this,250),dp2px(DetailActivity.this,55));
        return drawable;
    }

    public int dp2px(Context context,int value)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,context.getResources().getDisplayMetrics() );
    }
}
