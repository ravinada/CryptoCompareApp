package com.ravinada.cryptocompare.room;

import android.os.Parcel;
import android.os.Parcelable;

public class PortfolioCoin implements Parcelable {
    private String currencytag;
    private float amount;
    private float buyPrice;
    private String buyTag;
    private String date;
    private String description;

    public PortfolioCoin(String currencytag, float amount, float buyPrice, String buyTag, String date, String description) {
        this.currencytag = currencytag;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.buyTag = buyTag;
        this.date = date;
        this.description = description;
    }

    protected PortfolioCoin(Parcel in) {
        currencytag = in.readString();
        amount = in.readFloat();
        buyPrice = in.readFloat();
        buyTag = in.readString();
        date = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currencytag);
        dest.writeFloat(amount);
        dest.writeFloat(buyPrice);
        dest.writeString(buyTag);
        dest.writeString(date);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PortfolioCoin> CREATOR = new Creator<PortfolioCoin>() {
        @Override
        public PortfolioCoin createFromParcel(Parcel in) {
            return new PortfolioCoin(in);
        }

        @Override
        public PortfolioCoin[] newArray(int size) {
            return new PortfolioCoin[size];
        }
    };

    public String getCurrencytag() {
        return currencytag;
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

    public String getDescription() {
        return description;
    }
}
