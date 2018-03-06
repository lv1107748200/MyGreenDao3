package com.hr.mygreendao3;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 吕 on 2017/11/16.
 */

public class BackTopScrollListener extends RecyclerView.OnScrollListener {

    private static final int THRESHOLD = 20;
    private int distance = 0;
    private boolean visible = true;//是否可见

    private HideScrollListener hideScrollListener;

    public BackTopScrollListener(HideScrollListener hideScrollListener) {
        this.hideScrollListener = hideScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (distance > THRESHOLD && visible) {
            //隐藏动画
            visible = false;
            hideScrollListener.isShow(false);
            distance = 0;
        } else if (distance < -20 && !visible) {
            //显示动画
            visible = true;
            hideScrollListener.isShow(true);
            distance = 0;
        }

        if (visible && dy > 0 || (!visible && dy < 0)) {
            distance += dy;
        }

    }

    public interface HideScrollListener{
        void isShow(boolean isShow);
    }
}
