package com.hr.mygreendao3;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hr.mygreendao3.view.VariableImgLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends FragmentActivity implements VariableImgLayout.VarImgOnClick {

    private TextView tv;
    private RecyclerView rv;
    private MainAdapter mainAdapter;
    private RefreshLayout mRefreshLayout;
    private VariableImgLayout variableImgLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        rv = (RecyclerView) findViewById(R.id.rv);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        variableImgLayout = (VariableImgLayout) findViewById(R.id.varlayout);

      //  variableImgLayout.setParms();



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(this);
        rv.setAdapter(mainAdapter);

        setmRefreshLayout();
        UserDataManager.getInstance().init();
    }



    public void onlick(View view){

        switch (view.getId()){
            case R.id.check:
                getData();

                break;
            case R.id.insert:
                UserDataManager.getInstance().insert(getDog(), new DBCallBack<Dog>() {
                    @Override
                    public void onDBError(Throwable e) {
                        System.out.println("onDBError--->"+e.getMessage());
                    }
                    @Override
                    public void onDBSuccess(Dog dog) {
                        System.out.println("onDBSuccess--->"+dog.getId());
                    }
                });
                break;
            case R.id.check_id:
                UserDataManager.getInstance().getDogDao(0, new DBCallBack<Dog>() {
                    @Override
                    public void onDBError(Throwable e) {
                        System.out.println("onDBError--->"+e.getMessage());
                    }

                    @Override
                    public void onDBSuccess(Dog dog) {
                        System.out.println("onDBSuccess--->"+"name = "+dog.getName()+"id = "+dog.getId());
                    }
                });
                break;
            case R.id.del:

                UserDataManager.getInstance().deleteAll(new DBCallBack() {
                    @Override
                    public void onDBError(Throwable e) {
                        System.out.println("onDBError--->"+e.getMessage());
                    }

                    @Override
                    public void onDBSuccess(Object o) {
                        System.out.println("onDBSuccess--->");
                    }
                });

                break;
            case R.id.wai:

                User user = new User();
                user.setId(0);
                user.setDogid(403);
                user.setName("小明");
                UserDataManager.getInstance().insertOrReplaceUserRx(user, new DBCallBack<User>() {
                    @Override
                    public void onDBError(Throwable e) {
                        System.out.println("onDBError--->"+e.getMessage());
                    }

                    @Override
                    public void onDBSuccess(final User user) {
                        System.out.println("onDBSuccess--->");
                        UserDataManager.getInstance().getUserDaoList(new DBCallBack<List<User>>() {
                            @Override
                            public void onDBError(Throwable e) {
                                System.out.println("onDBError--->"+e.getMessage());
                            }

                            @Override
                            public void onDBSuccess(List<User> users) {
                                System.out.println("onDBSuccess--->");
                            }
                        });
                    }
                });
                break;
        }

    }

    private void getData(){
        UserDataManager.getInstance().getDogDaoList(new DBCallBack<List<Dog>>() {
            @Override
            public void onDBError(Throwable e) {
                System.out.println("onDBError--->"+e.getMessage());
            }

            @Override
            public void onDBSuccess(List<Dog> dogs) {
                System.out.println("onDBSuccess--->");
                if(null != dogs){
                    mRefreshLayout.finishRefresh();
                    mainAdapter.addData(dogs);
                }
            }
        });
    }
    private void setmRefreshLayout(){
         ClassicsHeader mClassicsHeader;
        int deta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader = (ClassicsHeader)mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis()-deta));
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
         Drawable mDrawableProgress;
        mDrawableProgress = mClassicsHeader.getProgressView().getDrawable();
        if (mDrawableProgress instanceof LayerDrawable) {
            mDrawableProgress = ((LayerDrawable) mDrawableProgress).getDrawable(0);
        }
        mClassicsHeader.setEnableLastTime(false);//显示时间

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mRefreshLayout.finishLoadmore();
            }
        });
    }

    private Dog getDog(){
        Dog dog = new Dog();
        //dog.setId(getNumber());
        dog.setName("二二狗");
        return dog;
    }

        int  getNumber(){
            return (int) (Math.random()*10000);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserDataManager.getInstance().close();
    }

    @Override
    public void onClick(int point, String url) {
        System.out.println("url--->"+url);
    }
}
