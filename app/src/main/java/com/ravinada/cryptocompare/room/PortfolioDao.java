package com.ravinada.cryptocompare.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("Select name From portfolios Where selected=:selection")
    String getSelected(Boolean selection);

    @Query("UPDATE portfolios SET selected =:notselected WHERE name=:selectedName")
    void setNotSelected(String selectedName,Boolean notselected);

    @Query("UPDATE portfolios SET selected =:selected WHERE name=:choiceName")
    void setSelected(String choiceName,Boolean selected);

    @Query("SELECT id FROM portfolios WHERE selected=:selected")
    int getidForSelectedName(Boolean selected);

    @Query("SELECT name FROM portfolios")
    List<String> getNames();

    @Query("SELECT currency FROM portfolios WHERE selected=:selected")
    LiveData<String> getCurrencySelectionForSelectedPortfolio(Boolean selected);
}
