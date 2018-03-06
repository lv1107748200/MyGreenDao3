package com.hr.mygreendao3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hr.mygreendao3.view.VariableImgLayout;

/**
 * Created by 吕 on 2017/11/14.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private VariableImgLayout variableImgLayout;
    public BaseViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.i_tv);
        variableImgLayout = (VariableImgLayout) itemView.findViewById(R.id.varlayout);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public VariableImgLayout getVariableImgLayout() {
        return variableImgLayout;
    }
}
