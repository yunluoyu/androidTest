package com.yunlu.androidtest;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.gxz.PagerSlidingTabStrip;

import java.util.ArrayList;

import fragment.AppFragment;
import fragment.GameFragment;
import fragment.HomeFragment;
import fragment.HotFragment;
import fragment.ItemFragment;
import fragment.RecommendFragment;
import fragment.SubjectFragment;
import receiver.RemoteReceiver;

public class MainActivity extends AppCompatActivity {

    //整理布局  c+a+l
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mBarDrawerToggle;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        setMyActionBar();
        initViewPager();

        RemoteReceiver re = new RemoteReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.peng.remote");
        String per = "com.peng.remote.share";
        registerReceiver(re,intentFilter,per,null);

    }

    /**
     * 初始化ViewPager
     * */
    private void initViewPager() {
        ArrayList<Fragment> list  = new ArrayList<Fragment>();
        list.add(new HomeFragment());
        list.add(new AppFragment());
        list.add(new GameFragment());
        list.add(new SubjectFragment());
        list.add(new RecommendFragment());
        list.add(new ItemFragment());
        list.add(new HotFragment());


        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),list));
        mTabs.setViewPager(mViewPager);

        // 设置Tab是自动填充满屏幕的
        mTabs.setShouldExpand(true);
        // 设置Tab底部线的高度,传入的是dp
        mTabs.setUnderlineHeight(1);
        // 设置Tab 指示器Indicator的高度,传入的是dp
        mTabs.setIndicatorHeight(3);
        // 设置Tab Indicator的颜色
         mTabs.setIndicatorColor(getResources().getColor(R.color.colorPrimary,null));
        // 设置选中Tab文字的颜色
        mTabs.setSelectedTextColor(getResources().getColor(R.color.colorPrimary,null));

    }



    /*
    * 初始化控件
    * */
    private void initView() {
        mDrawerLayout = findViewById(R.id.main_drawerlayout);
        mViewPager = findViewById(R.id.main_vp_viewpaer);
        mTabs = findViewById(R.id.main_psts_tabs);

    }

    private void setMyActionBar() {
        ActionBar abar  = getSupportActionBar();
        abar.setTitle("陨落市场");

        // 显示箭头按钮
        abar.setHomeButtonEnabled(true);
        abar.setDisplayShowHomeEnabled(true);
        abar.setDisplayHomeAsUpEnabled(true);
        //new一个ActionBarDrawerToggle对象，将箭头设置为汉堡包
        mBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,0,0);
        mBarDrawerToggle.syncState();

        // 添加监听器
        mDrawerLayout.addDrawerListener(mBarDrawerToggle);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                mBarDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;
        ArrayList<Fragment> list;
        String []tabNames = getApplicationContext().getResources().getStringArray(R.array.tab_names);

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }

}



