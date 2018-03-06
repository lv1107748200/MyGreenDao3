package com.hr.mygreendao3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕 on 2017/11/15.
 */

public class Tab0Fragment extends BaseFragment implements BackTopScrollListener.HideScrollListener{

    private TextView textView;

    private String type;

    private ImageButton imageButton;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    public void setType(String type){
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_0,null);
        textView = (TextView) view.findViewById(R.id.tv);
        if(null != type){
            textView.setText(type);
        }
        imageButton = (ImageButton) view.findViewById(R.id.image_btn);
        recyclerView = (RecyclerView) view.findViewById(R.id.rl);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(getContext());
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addOnScrollListener(new BackTopScrollListener(this));


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

        return view;
    }

    @Override
    public void loadData() {
        super.loadData();
        System.out.println("loadData--->"+type);

        List<Dog> dogs = new ArrayList<>();
        for(int i=0; i<40; i++){
            Dog dog = new Dog();
            dog.setName("dfdf");
            dog.setId((long) i);
            dogs.add(dog);
        }
        mainAdapter.addData(dogs);
    }

    @Override
    public void isShow(boolean isShow) {

        if(isShow){
            imageButton.setVisibility(View.VISIBLE);
        }else {
            imageButton.setVisibility(View.GONE);
        }

    }

}
