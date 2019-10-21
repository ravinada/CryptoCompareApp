package com.ravinada.cryptocompare.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "portfolio_coins")
public class PortfolioCoin {
    @PrimaryKey(autoGenerate = true)
    private int coinId;
    private String currencyTag;
    private float amount;
    private float buyPrice;
    private String buyTag;
    private String date;
    private String description;
    private int uid;

    public PortfolioCoin(String currencyTag, float amount, float buyPrice, String buyTag, String date, String description,int uid) {
        this.currencyTag = currencyTag;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.buyTag = buyTag;
        this.date = date;
        this.description = description;
        this.uid = uid;
    }

    public float getAmount() {
        return amount;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public String getBuyTag() {
        return buyTag;
    }

    public String getDate() {
        return date;
    }

    public int getCoinId() {
        return coinId;
    }

    public String getCurrencyTag() {
        return currencyTag;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getDescription() {
        return description;
    }
}
