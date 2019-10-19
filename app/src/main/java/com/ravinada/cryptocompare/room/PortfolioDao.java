package com.ravinada.cryptocompare.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PortfolioDao {
    @Insert
    void insert (Portfolio portfolio);

    @Delete
    void delete(Portfolio portfolio);

    @Query("SELECT * FROM portfolios")
    LiveData<List<Portfolio>> getPortfolios();

    @Query("UPDATE portfolios SET coins = :newCoins WHERE id=:id")
    void updatePortfolios(int id, ArrayList<PortfolioCoin> newCoins);

}
