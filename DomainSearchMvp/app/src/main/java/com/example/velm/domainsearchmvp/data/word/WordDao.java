package com.example.velm.domainsearchmvp.data.word;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.velm.domainsearchmvp.model.Words;

import java.util.List;

/**
 * Created by velmmuru on 10/11/2017.
 */

@Dao
public interface WordDao {

    @Query("select * from words")
    List<Words> getAllWords();

    @Insert
    void insertAll(Words... words);
}
