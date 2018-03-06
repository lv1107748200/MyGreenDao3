package com.hr.magicindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hr.magicindicator.abs.IPagerNavigator;
import com.hr.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.hr.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.hr.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.hr.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.hr.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.hr.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 整个框架的入口，核心
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class MagicIndicator extends FrameLayout {
    private IPagerNavigator mNavigator;
    private CommonNavigator mCommonNavigator;
    private CallBackControl callBackControl;
    private List lists;

    public MagicIndicator(Context context) {
        super(context);
    }

    public MagicIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mNavigator != null) {
            mNavigator.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    public void onPageSelected(int position) {
        if (mNavigator != null) {
            mNavigator.onPageSelected(position);
        }
    }

    public void onPageScrollStateChanged(int state) {
        if (mNavigator != null) {
            mNavigator.onPageScrollStateChanged(state);
        }
    }

    public IPagerNavigator getNavigator() {
        return mNavigator;
    }

    private void setNavigator(IPagerNavigator navigator) {
        if (mNavigator == navigator) {
            return;
        }
        if (mNavigator != null) {
            mNavigator.onDetachFromMagicIndicator();
        }
        mNavigator = navigator;
        removeAllViews();
        if (mNavigator instanceof View) {
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView((View) mNavigator, lp);
            mNavigator.onAttachToMagicIndicator();
        }
    }

    public void setCallBackControl(CallBackControl callBackControl) {
        this.callBackControl = callBackControl;
    }

    public void refreshData(List list){
        if(null == lists){

        }else {
            lists.clear();
            lists.addAll(list);
            mCommonNavigator.notifyDataSetChanged();
        }
    }

    public void bind(Context context, final ViewPager viewPager){

        this.lists = new ArrayList();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                MagicIndicator.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                MagicIndicator.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                MagicIndicator.this.onPageScrollStateChanged(state);
            }
        });

        mCommonNavigator = new CommonNavigator(context);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                if(null != lists)
                return lists.size();
                return 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                if(null != callBackControl){
                    return callBackControl.getTitleView(context,index);
                }

                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText((String) lists.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
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
                if(null != callBackControl){
                    return callBackControl.getIndicator(context);
                }
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.parseColor("#d43d3d"));
                return linePagerIndicator;
            }
        });
       setNavigator(mCommonNavigator);
    }

    public interface CallBackControl{
       IPagerTitleView getTitleView(Context context,int index);
       IPagerIndicator getIndicator(Context context);
    }
}
