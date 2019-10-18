package com.ravinada.cryptocompare;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ravinada.cryptocompare.room.FavouriteCoin;
import com.ravinada.cryptocompare.room.FavouriteCoinDao;
import com.ravinada.cryptocompare.room.FavouriteCoinDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    public FavouriteCoinDao favouriteCoinDao;
    private Executor executor;
    private static Repository repository;

    private Repository(Application application) {
        FavouriteCoinDatabase database = FavouriteCoinDatabase.getInstance(application);
        favouriteCoinDao = database.favouriteCoinDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public static Repository getInstance(Application application) {
        if (repository == null) {
            repository = new Repository(application);
        }
        return repository;
    }
    public void insert(FavouriteCoin favouriteCoin) {
        executor.execute(()-> favouriteCoinDao.insert(favouriteCoin));
    }

    public void delete(FavouriteCoin favouriteCoin) {
        executor.execute(()-> favouriteCoinDao.delete(favouriteCoin));
    }
    public void update(String tag,String newName,String newImageUrl,String newCurrentCoinPrice,
                       String newRateChg,String newMarketCap,String newTotalVolume24h,
                       String newDirectVolume24h,String newOpen24h,String newDirectVolumeSigned,
                       String newLowHigh){
        executor.execute(()->favouriteCoinDao.updateData(tag,newName,newImageUrl,newCurrentCoinPrice,
                newRateChg,newMarketCap,newTotalVolume24h,
                 newDirectVolume24h,newOpen24h, newDirectVolumeSigned,
                 newLowHigh));
    }
    public Boolean getCoinExistance(String tag){
        return favouriteCoinDao.getCoinExistance(tag);
    }

    public LiveData<List<FavouriteCoin>> getFavouriteCoins() {
        return favouriteCoinDao.getFavouriteCoins();
    }
}
