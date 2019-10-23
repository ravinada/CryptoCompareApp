package com.ravinada.cryptocompare.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PortfolioCoin.class},version = 2 ,exportSchema = false)
public abstract class PortfolioCoinDb extends RoomDatabase {
    private static PortfolioCoinDb instance;
    public abstract PortfolioCoinDao portfolioCoinDao();

    public static synchronized PortfolioCoinDb getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),PortfolioCoinDb.class,"Portfolio_coins")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
