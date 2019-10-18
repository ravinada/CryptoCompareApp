package com.ravinada.cryptocompare.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteCoin.class}, version = 1)
public abstract class FavouriteCoinDatabase extends RoomDatabase {
    private static FavouriteCoinDatabase instance;

    public abstract FavouriteCoinDao favouriteCoinDao();

    public static synchronized FavouriteCoinDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FavouriteCoinDatabase.class, "Favourite_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
