package com.hr.mygreendao3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 吕 on 2017/11/16.
 */

public class BottomBarLayout extends LinearLayout {

    private ViewPager viewPager;

    public BottomBarLayout(Context context) {
        this(context,null);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        LinearLayout linearLayout = (LinearLayout) View.inflate(context,R.layout.bottom_bar,this);

    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setViewPagerSelect(){
        setSelect();

    }

    private void setSelect(){

    }
}
