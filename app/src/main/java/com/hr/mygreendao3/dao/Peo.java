package com.hr.mygreendao3.dao;

import com.hr.mygreendao3.Dog;
import com.hr.mygreendao3.User;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by 吕 on 2018/1/16.
 */
@Entity
public class Peo {
    @Id(autoincrement = true)
    private long id;

    @ToMany(referencedJoinProperty = "id")
    List<Dog> dogs;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1813078063)
    private transient PeoDao myDao;

    @Generated(hash = 1072808675)
    public Peo(long id) {
        this.id = id;
    }

    @Generated(hash = 727780193)
    public Peo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1699545070)
    public List<Dog> getDogs() {
        if (dogs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DogDao targetDao = daoSession.getDogDao();
            List<Dog> dogsNew = targetDao._queryPeo_Dogs(id);
            synchronized (this) {
                if (dogs == null) {
                    dogs = dogsNew;
                }
            }
        }
        return dogs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1473265060)
    public synchronized void resetDogs() {
        dogs = null;
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
    @Generated(hash = 906087790)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPeoDao() : null;
    }
}
