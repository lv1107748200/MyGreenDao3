package com.hr.mygreendao3;

import rx.Subscriber;

/**
 * Created by 吕 on 2017/11/10.
 */

public class DBSubscribe<T> extends Subscriber<T> {

    private DBCallBack<T> dbCallBack;

    public DBSubscribe(DBCallBack<T> dbCallBack) {
        this.dbCallBack = dbCallBack;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(null != dbCallBack){
            dbCallBack.onDBError(e);
        }
    }

    @Override
    public void onNext(T t) {
        if(null != dbCallBack){
            dbCallBack.onDBSuccess(t);
        }
    }
}
