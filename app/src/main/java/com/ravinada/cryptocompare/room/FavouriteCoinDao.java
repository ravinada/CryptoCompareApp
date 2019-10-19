package com.ravinada.cryptocompare.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteCoinDao {
    @Insert
    void insert(FavouriteCoin coin);

    @Delete
    void delete(FavouriteCoin coin);

    @Query("SELECT * FROM favourite_coins ")
    List<FavouriteCoin> getFavouriteCoins();

    @Query("SELECT `check` FROM favourite_coins WHERE tag = :tag ")
    Boolean getCoinExistance(String tag);

    @Query("UPDATE favourite_coins SET name =:newName,imageUrl =:newImageUrl," +
            "currentCoinPrice =:newCurrentCoinPrice,rateChg =:newRateChg,marketCap =:newMarketCap," +
            "totalVolume24h =:newTotalVolume24h,directVolume24h =:newDirectVolume24h," +
            "open24h =:newOpen24h,directVolumeSigned =:newDirectVolumeSigned," +
            "lowHigh =:newLowHigh WHERE tag=:tag")
    void updateData(String tag, String newName, String newImageUrl, String newCurrentCoinPrice,
                    String newRateChg, String newMarketCap, String newTotalVolume24h,
                    String newDirectVolume24h, String newOpen24h, String newDirectVolumeSigned,
                    String newLowHigh);
}
