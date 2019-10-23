package com.ravinada.cryptocompare.room;

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
    private String imageUrl;
    private String fullName;

    public PortfolioCoin(String currencyTag, float amount, float buyPrice, String buyTag, String date, String description, int uid, String imageUrl, String fullName) {
        this.currencyTag = currencyTag;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.buyTag = buyTag;
        this.date = date;
        this.description = description;
        this.uid = uid;
        this.imageUrl =imageUrl;
        this.fullName = fullName;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCurrencyTag() {
        return currencyTag;
    }

    public void setCurrencyTag(String currencyTag) {
        this.currencyTag = currencyTag;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyTag() {
        return buyTag;
    }

    public void setBuyTag(String buyTag) {
        this.buyTag = buyTag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
