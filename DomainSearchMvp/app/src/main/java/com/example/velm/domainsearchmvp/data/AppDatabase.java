package com.example.velm.domainsearchmvp.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.velm.domainsearchmvp.data.domain.DomainDao;
import com.example.velm.domainsearchmvp.data.word.WordDao;
import com.example.velm.domainsearchmvp.model.DomainName;
import com.example.velm.domainsearchmvp.model.Words;

/**
 * Created by velmmuru on 10/11/2017.
 */
@Database(entities = {Words.class, DomainName.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract WordDao wordDao();

    public abstract DomainDao domainDao();

    public static AppDatabase getAppDatabase(Context context){

        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"wordmanager").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    public void destroyInstance(){
        appDatabase = null;
    }
}
