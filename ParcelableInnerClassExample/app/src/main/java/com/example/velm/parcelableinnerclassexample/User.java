package com.example.velm.parcelableinnerclassexample;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by velmmuru on 9/21/2017.
 */

public class User implements Parcelable{

    private String name;

    private int age;

    private Address address;

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User(Parcel parcel) {
        name = parcel.readString();
        age = parcel.readInt();
        address = parcel.readParcelable(Address.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeParcelable(address,i);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
