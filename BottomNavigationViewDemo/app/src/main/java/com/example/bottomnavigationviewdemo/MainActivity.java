package com.example.bottomnavigationviewdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomView;
    private ViewPager mViewPager;
    private List<Fragment> pagerAdpter;
    private String[] strings = {"消息", "联系人", "动态"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
        navigationViewListener();
        viewPagerListener();
    }

    private void viewPagerListener() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d("Log", "i:" + i + "  v:" + v + "  i1" + i1);
//                mBottomView.setSelectedItemId(mBottomView.getMenu().getItem(i).getItemId());
                mBottomView.getMenu().getItem(i).setChecked(true);

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void navigationViewListener() {
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("heh", ""+R.id.one+" "+R.id.two+" "+R.id.three+" ");
                Log.d("heh", "onNavigationItemSelected: "+menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case R.id.one:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.two:mViewPager.setCurrentItem(1);
                        break;
                    case R.id.three:mViewPager.setCurrentItem(2);
                        break;
                }
                return true;

            }
        });
    }

    private void init() {
        pagerAdpter = new ArrayList<>();
        initFragment();
        mViewPager.setAdapter(new AdapterViewPager(getSupportFragmentManager()));

    }

    private void initFragment() {
        for (int i = 0; i < 3; i++) {
            MyFragment fragment = new MyFragment(strings[i]);
            pagerAdpter.add(fragment);
        }
    }

    private void initView() {
        mBottomView = findViewById(R.id.mBottomView);
        mViewPager = findViewById(R.id.mViewPager);
    }

    class AdapterViewPager extends FragmentPagerAdapter {

        public AdapterViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pagerAdpter.get(i);
        }

        @Override
        public int getCount() {
            return pagerAdpter.size();
        }

    }

}
