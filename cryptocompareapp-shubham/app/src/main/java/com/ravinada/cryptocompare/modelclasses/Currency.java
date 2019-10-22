package com.ravinada.cryptocompare.modelclasses;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency  {

    String fullName;
    String code;
    String currentRate;
    String openDay;
    String imageURL;


    public Currency() {
    }

    public Currency(String fullName, String code, String currentRate,String openDay, String imageURL) {
        this.fullName = fullName;
        this.code = code;
        this.currentRate = currentRate;
        this.openDay = openDay;
        this.imageURL = imageURL;
    }

    public Currency(String fullName, String name, String price, String lowday, String highday, String openday, String image, String algorithm) {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(String currentRate) {
        this.currentRate = currentRate;
    }

    public String getOpenDay() {
        return openDay;
    }

    public void setOpenDay(String openDay) {
        this.openDay = openDay;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}