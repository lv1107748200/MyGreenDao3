package com.hr.mygreendao3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;

/**
 * Created by 吕 on 2017/11/15.
 */

public class TabActivity extends FragmentActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager viewPager;

    BottomBarLayout bottomBarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomBarLayout = (BottomBarLayout) findViewById(R.id.bottom_layout);
        bottomBarLayout.setViewPager(viewPager);

        mFragments.add(new TabOneFragment());
        mFragments.add(new TabOneFragment());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
