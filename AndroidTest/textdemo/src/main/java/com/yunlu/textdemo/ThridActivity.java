package com.yunlu.textdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import ViewUtils.MyViewInjcet;
import ViewUtils.ViewUtil;
import fragment.FragmentA;
import fragment.FragmentB;
import fragment.FragmentC;

public class ThridActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{


    @MyViewInjcet(R.id.rg_group)
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private FragmentA fa;
    private FragmentB fb;
    private FragmentC fc;

    private Fragment mCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);

        ViewUtil.inject(this);


        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        fa = new FragmentA();
        fb = new FragmentB();
        fc = new FragmentC();


        ft.add(R.id.fl_fragment, fa,"FragmentA");
        ft.add(R.id.fl_fragment, fb,"FragmentB");
        ft.add(R.id.fl_fragment, fc,"FragmentC");
        ft.hide(fa).hide(fb).hide(fc).commit();

        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.rb_1);

    }

//    private void showFragment(Fragment fragment){
//        if (mCurrent != fragment){//  判断传入的fragment是不是当前的currentFragmentgit
//            //FragmentTransaction transaction = fm.beginTransaction();
//            ft = fm.beginTransaction();
//            ft.hide(mCurrent);//  不是则隐藏
//            mCurrent = fragment;  //  然后将传入的fragment赋值给currentFragment
//            if (!fragment.isAdded()){ //  判断传入的fragment是否已经被add()过
//                ft.add(R.id.fl_fragment,fragment).show(fragment).commit();
//            }else{
//                ft.show(fragment).commit();
//            }
//        }
//    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d("AAA","进来了");
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(fb).hide(fc).hide(fa);
        switch (checkedId){
            case R.id.rb_1:
                //showFragment(fa);
                ft.show(fa);
                Toast.makeText(ThridActivity.this, "单击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_2:
                ft.show(fb);
                //showFragment(fb);
                Toast.makeText(ThridActivity.this, "双击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb_3:
                ft.show(fc);
                //showFragment(fc);
                Toast.makeText(ThridActivity.this, "点赞", Toast.LENGTH_SHORT).show();
                break;
        }
        ft.commit();

        //ft.commit();
    }
}
