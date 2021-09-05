package com.example.velm.ds;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class DomainSearch implements Parcelable {

    private String name;

    private String searchType;

    private int letterCount;

    private List<String> domains;

    public DomainSearch(String name, String searchType, int letterCount, List<String> domains) {
        this.name = name;
        this.searchType = searchType;
        this.letterCount = letterCount;
        this.domains = domains;
    }

    public DomainSearch(Parcel parcel) {

        this.name = parcel.readString();
        this.searchType = parcel.readString();
        this.letterCount = parcel.readInt();
        this.domains = parcel.createStringArrayList();


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(int letterCount) {
        this.letterCount = letterCount;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    @Override
    public String toString() {
        return "DomainSearch{" +
                "name='" + name + '\'' +
                ", searchType='" + searchType + '\'' +
                ", letterCount=" + letterCount +
                ", domains=" + domains +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(searchType);
        parcel.writeInt(letterCount);
        parcel.writeStringList(domains);

    }

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public DomainSearch createFromParcel(Parcel parcel) {
            return new DomainSearch(parcel);
        }

        @Override
        public DomainSearch[] newArray(int i) {
            return new DomainSearch[i];
        }
    };
}
