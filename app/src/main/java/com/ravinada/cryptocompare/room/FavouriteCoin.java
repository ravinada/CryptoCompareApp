package com.ravinada.cryptocompare.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "favourite_coins")
public class FavouriteCoin implements Parcelable {
    @NonNull
    @PrimaryKey
    private String tag;
    private String name,imageUrl,currentCoinPrice,rateChg,marketCap,totalVolume24h,directVolume24h,
            open24h,directVolumeSigned,lowHigh;
    private Boolean check = false;

    public FavouriteCoin(String tag, String name, String imageUrl, String currentCoinPrice,
                         String rateChg, String marketCap, String totalVolume24h,
                         String directVolume24h, String open24h, String directVolumeSigned,
                         String lowHigh, Boolean check) {
        this.tag = tag;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currentCoinPrice = currentCoinPrice;
        this.rateChg = rateChg;
        this.marketCap = marketCap;
        this.totalVolume24h = totalVolume24h;
        this.directVolume24h = directVolume24h;
        this.open24h = open24h;
        this.directVolumeSigned = directVolumeSigned;
        this.lowHigh = lowHigh;
        this.check = check;
    }

    private FavouriteCoin(Parcel in) {
        tag = Objects.requireNonNull(in.readString());
        name = in.readString();
        imageUrl = in.readString();
        currentCoinPrice = in.readString();
        rateChg = in.readString();
        marketCap = in.readString();
        totalVolume24h = in.readString();
        directVolume24h = in.readString();
        open24h = in.readString();
        directVolumeSigned = in.readString();
        lowHigh = in.readString();
    }

    public static final Creator<FavouriteCoin> CREATOR = new Creator<FavouriteCoin>() {
        @Override
        public FavouriteCoin createFromParcel(Parcel in) {
            return new FavouriteCoin(in);
        }

        @Override
        public FavouriteCoin[] newArray(int size) {
            return new FavouriteCoin[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tag);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeString(currentCoinPrice);
        parcel.writeString(rateChg);
        parcel.writeString(marketCap);
        parcel.writeString(totalVolume24h);
        parcel.writeString(directVolume24h);
        parcel.writeString(open24h);
        parcel.writeString(directVolumeSigned);
        parcel.writeString(lowHigh);
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCurrentCoinPrice() {
        return currentCoinPrice;
    }

    public String getRateChg() {
        return rateChg;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getTotalVolume24h() {
        return totalVolume24h;
    }

    public String getDirectVolume24h() {
        return directVolume24h;
    }

    public String getOpen24h() {
        return open24h;
    }

    public String getDirectVolumeSigned() {
        return directVolumeSigned;
    }

    public String getLowHigh() {
        return lowHigh;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCurrentCoinPrice(String currentCoinPrice) {
        this.currentCoinPrice = currentCoinPrice;
    }

    public void setRateChg(String rateChg) {
        this.rateChg = rateChg;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public void setTotalVolume24h(String totalVolume24h) {
        this.totalVolume24h = totalVolume24h;
    }

    public void setDirectVolume24h(String directVolume24h) {
        this.directVolume24h = directVolume24h;
    }

    public void setOpen24h(String open24h) {
        this.open24h = open24h;
    }

    public void setDirectVolumeSigned(String directVolumeSigned) {
        this.directVolumeSigned = directVolumeSigned;
    }

    public void setLowHigh(String lowHigh) {
        this.lowHigh = lowHigh;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
