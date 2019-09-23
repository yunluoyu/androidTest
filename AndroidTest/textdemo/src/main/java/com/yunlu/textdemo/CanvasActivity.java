package com.yunlu.textdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import view.GuideView;

public class CanvasActivity extends AppCompatActivity {

    private String[]text = new String[]{"AVCSD","FDfFDFDF","WHFJDKHFK","HDFJDHUFJD",
            "HEOSHFKK","HENDBFD","HFBFHDFJ"};

    private String[]cars = new String[]{
            "奥迪",
            "阿尔法罗米欧","宝马","奔腾","本田","比亚迪","标致","别克","宾利","布嘉迪","丰田","福特",
            "宝骏","长安","长城","川汽野马","大众"," 道奇","东风","帝豪","东风风神","法拉利","菲亚特",
            "Jeep吉普","悍马","捷豹","吉利全球鹰","凯迪拉克","克莱斯勒","柯尼赛格","兰博基尼","劳斯莱斯",
            "雷克萨斯","雷雷诺","英菲尼迪","玛莎拉蒂","帕加尼","林肯","迈巴赫","英伦","宾利","布嘉迪",
            "阿尔法罗米欧","宝马","凯迪拉克","克莱斯勒","柯尼赛格","奔腾","本田","比亚迪","标致","别克",
            "宝骏","长安","长城","川汽野马","大众"," 道奇","东风","帝豪","东风风神","法拉利","菲亚特",
            "Jeep吉普","悍马","捷豹","吉利全球鹰","凯迪拉克","克莱斯勒","柯尼赛格","兰博基尼","劳斯莱斯",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        GuideView guideView = findViewById(R.id.guideview);

        for(int i = 0; i < cars.length; i++){
            TextView tv = new TextView(CanvasActivity.this);
            tv.setText(cars[i]);
            tv.setTextSize(20);
            tv.setPadding((int)getResources().getDimension(R.dimen.dp10),15,(int)getResources().getDimension(R.dimen.dp10),15);
            tv.setBackgroundColor(Color.GREEN);
            tv.setTextColor(Color.WHITE);
            guideView.addView(tv);
        }
        guideView.setOnGuideClickListener(new GuideView.OnGuideClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(CanvasActivity.this,tv.getText() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
