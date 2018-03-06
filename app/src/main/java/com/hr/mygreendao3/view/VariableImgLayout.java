package com.hr.mygreendao3.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hr.mygreendao3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕 on 2017/11/23.
 */

public class VariableImgLayout extends ViewGroup {

    private Context context;
    private VarImgOnClick varImgOnClick;
    private BaseModel baseModel;
    private int widht;

    public void setVarImgOnClick(VarImgOnClick varImgOnClick) {
        this.varImgOnClick = varImgOnClick;
    }
    public VariableImgLayout(Context context) {
        this(context,null);
    }

    public VariableImgLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VariableImgLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int rw = MeasureSpec.getSize(widthMeasureSpec);

        System.out.println("rw--->"+rw);

        if(getChildCount() == 0 || null == baseModel){
            return;
        }

        widht = (rw- ((baseModel.getLineNum(getChildCount())-1)*baseModel.getBetween()))/baseModel.getLineNum(getChildCount());

        int amountHeight = widht*baseModel.getHNum(getChildCount()) + (baseModel.getHNum(getChildCount())-1)*baseModel.getBetween();

        setMeasuredDimension(rw, amountHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(getChildCount() == 0 || null == baseModel){
            return;
        }
        for(int i = 0;i<getChildCount();i++){
            int[] position = findPosition(i);

            int left = (widht + baseModel.getBetween()) * position[1];
            int top = (widht + baseModel.getBetween()) * position[0];
            int right = left + widht;
            int bottom = top + widht;

            getChildAt(i).layout(left,top,right,bottom);
        }
    }

    public void setParms(final int num , final int point,BaseModel baseModel){
        this.baseModel = baseModel;

        if(getChildCount() != num){
            if(getChildCount() > num){
                removeViews(num,getChildCount() - num);
            }else if(getChildCount() < num){
                if(getChildCount() <= 0){
                    for(int i =0; i<num; i++){
                        ImageView imageView = new ImageView(context);
                        addView(imageView,i,generateDefaultLayoutParams());
                        if(getChildCount() == baseModel.getBagNum()){
                            break;
                        }
                    }
                }else {
                    for(int i=getChildCount(); i<num; i++){
                        ImageView imageView = new ImageView(context);
                        addView(imageView,i,generateDefaultLayoutParams());
                        if(getChildCount() == baseModel.getBagNum()){
                            break;
                        }
                    }
                }
            }
        }
        for(int i=0; i<getChildCount(); i++){
            final int key = i;
            ImageView imageView = (ImageView) getChildAt(i);
            imageView.setBackgroundResource(R.drawable.add);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null != varImgOnClick){
                        varImgOnClick.onClick(key,"message : num = "+num+" key = " + key +" point = " + point);
                    }
                }
            });
        }
        invalidate();
    }

    //确定位置
    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < baseModel.getHNum(getChildCount()); i++) {
            for (int j = 0; j < baseModel.getLineNum(getChildCount()); j++) {
                if ((i * baseModel.getLineNum(getChildCount()) + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    public interface VarImgOnClick{
        void onClick(int point,String url);
    }

}
