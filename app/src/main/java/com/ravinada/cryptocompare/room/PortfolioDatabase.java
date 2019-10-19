package com.ravinada.cryptocompare.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Portfolio.class},version = 1)
public abstract class PortfolioDatabase extends RoomDatabase {
    private static PortfolioDatabase instance;

    public abstract PortfolioDao portfolioDao();

    public static synchronized PortfolioDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),PortfolioDatabase.class,"Portfolio_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
