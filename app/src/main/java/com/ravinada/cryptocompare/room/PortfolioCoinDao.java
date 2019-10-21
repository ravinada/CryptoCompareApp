package com.ravinada.cryptocompare.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PortfolioCoinDao {
    @Insert
    void insert(PortfolioCoin portfolioCoin);

    @Delete
    void delete(PortfolioCoin portfolioCoin);

    @Query("SELECT * FROM portfolio_coins")
    List<PortfolioCoin> getPortfolioCoins();
}
