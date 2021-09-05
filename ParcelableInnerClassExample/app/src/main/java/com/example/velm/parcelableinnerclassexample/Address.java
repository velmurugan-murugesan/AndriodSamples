package com.example.velm.parcelableinnerclassexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by velmmuru on 9/21/2017.
 */

public class Address implements Parcelable {

    private int doorNo;

    private String streetName;

    private String city;

    public Address(int doorNo, String streetName, String city) {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
    }

    public Address(Parcel parcel) {

        doorNo = parcel.readInt();
        streetName = parcel.readString();
        city = parcel.readString();

    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(doorNo);
        parcel.writeString(streetName);
        parcel.writeString(city);
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Address createFromParcel(Parcel parcel) {
            return new Address(parcel);
        }

        @Override
        public Address[] newArray(int i) {
            return new Address[i];
        }
    };

    @Override
    public String toString() {
        return "Address{" +
                "doorNo=" + doorNo +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
