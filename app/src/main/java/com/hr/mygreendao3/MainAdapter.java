package com.hr.mygreendao3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hr.mygreendao3.view.BaseModel;
import com.hr.mygreendao3.view.StyleManger;
import com.hr.mygreendao3.view.VariableImgLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕 on 2017/11/14.
 */

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> implements VariableImgLayout.VarImgOnClick  {

    private List list;
    private Context context;
    private BaseModel baseModel;

    public MainAdapter(Context context) {
        this.context = context;
        list = new ArrayList();
        baseModel = StyleManger.getBaseModel(StyleManger.FOUR);
    }

    public void addData(List data){
        if(null != data){
            list.clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_r,null,false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        Object object = list.get(position);
        if(object instanceof  Dog){
            holder.getTextView().setText( ((Dog) object).getId()+((Dog) object).getName());
        }
        int num = (int) (Math.random()*12);
        if(num > 0){
            holder.getVariableImgLayout().setVisibility(View.GONE);
            holder.getVariableImgLayout().setParms(num,position,baseModel);
            holder.getVariableImgLayout().setVarImgOnClick(this);
        }else {
            holder.getVariableImgLayout().setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if(null != list){
            return list.size();
        }
        return 0;
    }
    @Override
    public void onClick(int point, String url) {
        System.out.println("url--->"+url);
    }
}
