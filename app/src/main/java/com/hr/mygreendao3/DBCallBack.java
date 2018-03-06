package com.hr.mygreendao3;

/**
 * Created by 吕 on 2017/11/10.
 */

public interface DBCallBack<T> {

    void onDBError(Throwable e);

    void onDBSuccess(T t);
}
