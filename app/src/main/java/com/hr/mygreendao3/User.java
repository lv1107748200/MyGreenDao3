package com.hr.mygreendao3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.hr.mygreendao3.dao.DaoSession;
import com.hr.mygreendao3.dao.DogDao;
import org.greenrobot.greendao.annotation.NotNull;
import com.hr.mygreendao3.dao.UserDao;

/**
 * Created by 吕 on 2017/11/10.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long id;
    private String name;

    private long dogid;

    @ToOne(joinProperty = "dogid")
    private Dog dog;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 265955384)
    private transient Long dog__resolvedKey;

    @Generated(hash = 988662503)
    public User(long id, String name, long dogid) {
        this.id = id;
        this.name = name;
        this.dogid = dogid;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDogid() {
        return this.dogid;
    }

    public void setDogid(long dogid) {
        this.dogid = dogid;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2112756352)
    public Dog getDog() {
        long __key = this.dogid;
        if (dog__resolvedKey == null || !dog__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DogDao targetDao = daoSession.getDogDao();
            Dog dogNew = targetDao.load(__key);
            synchronized (this) {
                dog = dogNew;
                dog__resolvedKey = __key;
            }
        }
        return dog;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1535468400)
    public void setDog(@NotNull Dog dog) {
        if (dog == null) {
            throw new DaoException(
                    "To-one property 'dogid' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.dog = dog;
            dogid = dog.getId();
            dog__resolvedKey = dogid;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
