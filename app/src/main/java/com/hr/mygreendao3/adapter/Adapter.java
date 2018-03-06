package com.hr.mygreendao3.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hr.mygreendao3.Tab0Fragment;

import java.util.List;

/**
 * Created by 吕 on 2017/11/28.
 */

public class Adapter extends FragmentPagerAdapter {

    private List<String> titleStrings;

    public Adapter(FragmentManager fm, List<String> titleStrings) {
        super(fm);
        this.titleStrings = titleStrings;
    }

    @Override
    public Fragment getItem(int position) {
        Tab0Fragment tab0Fragment = new Tab0Fragment();
        tab0Fragment.setType(titleStrings.get(position));
        return tab0Fragment;
    }

    @Override
    public int getCount() {
        if(null != titleStrings){
            return titleStrings.size();
        }
        return 0;
    }
}
