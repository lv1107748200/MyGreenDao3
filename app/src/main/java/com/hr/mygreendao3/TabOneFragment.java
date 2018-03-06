package com.hr.mygreendao3;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.hr.magicindicator.MagicIndicator;
import com.hr.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.hr.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.hr.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.hr.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import com.hr.mygreendao3.adapter.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 吕 on 2017/11/15.
 */

public class TabOneFragment extends Fragment implements MagicIndicator.CallBackControl {

    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源","123","456"
            ,"789","8520","78946511","54564654564654"
            ,"5645646546546"
    };
    private List<String> mDataList = new ArrayList<String>(Arrays.asList(mTitles));
    private ViewPager viewPager;
    private Adapter adapter;
    private MagicIndicator mMagicIndicator;
    private View add;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_one,null);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mMagicIndicator = (MagicIndicator)view.findViewById(R.id.magic_indicator1);
        add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resData();
            }
        });

        init();
        return view;
    }

    private void init(){
        adapter = new Adapter(getChildFragmentManager(),mDataList);
        viewPager.setAdapter(adapter);
        mMagicIndicator.setCallBackControl(this);
        mMagicIndicator.bind(this.getContext(),viewPager);
        mMagicIndicator.refreshData(mDataList);
    }


    private void resData(){
        mDataList .add(""+(int)(Math.random()*12000));

        mMagicIndicator.refreshData(mDataList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public IPagerTitleView getTitleView(Context context,final int index) {
        ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
        clipPagerTitleView.setText(mDataList.get(index));
        clipPagerTitleView.setTextColor(Color.BLACK);
        clipPagerTitleView.setClipColor(Color.parseColor("#d43d3d"));
        clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index,false);
            }
        });
        return clipPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
        linePagerIndicator.setColors(Color.parseColor("#d43d3d"));
        return linePagerIndicator;
    }
}
