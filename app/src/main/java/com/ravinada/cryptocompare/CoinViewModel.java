package com.ravinada.cryptocompare;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ravinada.cryptocompare.Repository;
import com.ravinada.cryptocompare.room.FavouriteCoin;

import java.util.List;

public class CoinViewModel extends AndroidViewModel {
    private Repository repository;

    public CoinViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }
    public void insert(FavouriteCoin favouriteCoin) {
        repository.insert(favouriteCoin);
    }

    public void delete(FavouriteCoin favouriteCoin) {
        repository.delete(favouriteCoin);
    }
    public void update(String tag,String newName,String newImageUrl,String newCurrentCoinPrice,
                       String newRateChg,String newMarketCap,String newTotalVolume24h,
                       String newDirectVolume24h,String newOpen24h,String newDirectVolumeSigned,
                       String newLowHigh){
        repository.update(tag,newName,newImageUrl,newCurrentCoinPrice,
                newRateChg,newMarketCap,newTotalVolume24h,
                newDirectVolume24h,newOpen24h, newDirectVolumeSigned,
                newLowHigh);
    }

    public Boolean existance (String tag){
        return repository.getCoinExistance(tag);
    }

    public List<FavouriteCoin> getFavouriteCoinLiveData() {
        return repository.getFavouriteCoins();
    }

}
