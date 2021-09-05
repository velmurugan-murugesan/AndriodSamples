package com.example.velm.domainsearchmvp.data.domain;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.velm.domainsearchmvp.model.DomainName;

import java.util.List;

/**
 * Created by velmmuru on 10/11/2017.
 */
@Dao
public interface DomainDao {

    @Insert
    void insertDomain(DomainName... domainNames);

    @Query("select * from domain")
    List<DomainName> getAllDomains();
}
