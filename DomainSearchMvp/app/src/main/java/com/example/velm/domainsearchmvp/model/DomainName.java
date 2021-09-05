package com.example.velm.domainsearchmvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by velmmuru on 10/11/2017.
 */

@Entity(tableName = "domain")
public class DomainName {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "domain_id")
    private int domainId;

    public DomainName(String name, int domainId) {
        this.name = name;
        this.domainId = domainId;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DomainName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
