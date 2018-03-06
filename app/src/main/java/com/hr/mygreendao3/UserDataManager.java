package com.hr.mygreendao3;

import android.os.Handler;
import android.os.HandlerThread;

import com.hr.mygreendao3.dao.DBManager;
import com.hr.mygreendao3.dao.DaoSession;
import com.hr.mygreendao3.dao.DogDao;
import com.hr.mygreendao3.dao.PeoDao;
import com.hr.mygreendao3.dao.UserDao;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 吕 on 2017/11/9.
 */

public class UserDataManager {
   private static UserDataManager instance = null;

    private DogDao dogDao;
    private PeoDao peoDao;
    private UserDao userDao;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;

    public static UserDataManager getInstance(){
        if(instance==null){
            synchronized (UserDataManager.class) {
                if (instance == null) {
                    instance = new UserDataManager();
                }
            }
        }
        return instance;
    }

    public void init(){
        dogDao = DBManager.getInstance().getDaoSession().getDogDao();
        userDao = DBManager.getInstance().getDaoSession().getUserDao();
        peoDao = DBManager.getInstance().getDaoSession().getPeoDao();
        mWorkThread = new HandlerThread("UserDataManager");
        mWorkThread.start();
        mWorkHandler = new Handler(mWorkThread.getLooper());
    }
    public DaoSession getDaoSession() {
        return DBManager.getInstance().getDaoSession();
    }


    public void insertOrReplaceDog(final Dog dog){
        if(null != mWorkHandler){
            mWorkHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(null != dogDao){
                        System.out.println("insertOrReplace--->");
                        dogDao.insertOrReplace(dog);
                    }
                }
            });
        }
    }

    public void insertDog(final Dog dog){
        if(null != mWorkHandler){
            mWorkHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(null != dogDao){
                        System.out.println("insertOrReplace--->");
                        dogDao.insert(dog);
                    }
                }
            });
        }
    }

    public List<Dog> getDogs() {
        if (dogDao != null) {
            System.out.println("get--->");
            return dogDao.loadAll();
        } else {
            return null;
        }
    }

    /**
     * 使用Rx 进行插入或者替换 返回替换数据
     * @param dog
     */
    public void insertOrReplaceDogRx(Dog dog,DBCallBack<Dog> dbCallBack){
        toSubscribe(dogDao.rx().insertOrReplace(dog),new DBSubscribe<Dog>(dbCallBack));
    }
    public void insert(Dog dog,DBCallBack<Dog> dbCallBack){
        toSubscribe(dogDao.rx().insert(dog),new DBSubscribe<Dog>(dbCallBack));
    }

    /**
     * @param user
     * @param dbCallBack
     */
    public void insertOrReplaceUserRx(User user,DBCallBack<User> dbCallBack){
        toSubscribe(userDao.rx().insertOrReplace(user),new DBSubscribe<User>(dbCallBack));
    }

    /**
     *跟新list
     * @param dog
     * @param dbCallBack
     */
    public void insertOrReplaceListDogRx(List<Dog> dog,DBCallBack<List<Dog>> dbCallBack){
        toSubscribe(dogDao.rx().insertOrReplaceInTx(dog),new DBSubscribe<List<Dog>>(dbCallBack));
    }

    /**
     * 根据id查询
     * @param id
     * @param dogDBCallBack
     */
    public void getDogDao(long id,DBCallBack<Dog> dogDBCallBack) {
        toSubscribe(dogDao.rx().load(id),new DBSubscribe(dogDBCallBack));
    }

    /**
     * @param dogDBCallBack
     */
    public void getDogDaoList(DBCallBack<List<Dog>> dogDBCallBack) {
        toSubscribe(dogDao.rx().loadAll(),new DBSubscribe(dogDBCallBack));
    }

    /**
     * @param dogDBCallBack
     */
    public void getUserDaoList(DBCallBack<List<User>> dogDBCallBack) {
        toSubscribe(userDao.rx().loadAll(),new DBSubscribe(dogDBCallBack));
    }

    public void delete(long key,DBCallBack dbCallBack){
        toSubscribe(dogDao.rx().deleteByKey(key),new DBSubscribe(dbCallBack));
    }
    public void deleteAll(DBCallBack dbCallBack){
        toSubscribe(dogDao.rx().deleteAll(),new DBSubscribe(dbCallBack));
    }

    //添加线程管理并订阅
    @SuppressWarnings("unchecked")
    public void toSubscribe(Observable o, Subscriber s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void close(){
        DBManager.getInstance().uninit();
        DBManager.setInstance(null);
        if(null != dogDao){
            dogDao = null;
        }
        if(null != mWorkThread){
            mWorkThread.quit();
            mWorkThread = null;
            mWorkHandler = null;
        }
    }

}
