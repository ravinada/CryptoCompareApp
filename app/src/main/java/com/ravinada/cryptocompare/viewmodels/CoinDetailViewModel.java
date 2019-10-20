package com.ravinada.cryptocompare.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ravinada.cryptocompare.modelclasses.DailyHistoricalData;
import com.ravinada.cryptocompare.repositories.CoinDetailRepository;
import com.ravinada.cryptocompare.room.FavouriteCoin;

import java.util.List;

public class CoinDetailViewModel extends AndroidViewModel {
    private CoinDetailRepository coinDetailRepository;

    public CoinDetailViewModel(@NonNull Application application) {
        super(application);
        coinDetailRepository = CoinDetailRepository.getInstance(application);
    }
    public void insert(FavouriteCoin favouriteCoin) {
        coinDetailRepository.insert(favouriteCoin);
    }

    public void delete(FavouriteCoin favouriteCoin) {
        coinDetailRepository.delete(favouriteCoin);
    }
    public void update(String tag,String newName,String newImageUrl,String newCurrentCoinPrice,
                       String newRateChg,String newMarketCap,String newTotalVolume24h,
                       String newDirectVolume24h,String newOpen24h,String newDirectVolumeSigned,
                       String newLowHigh){
        coinDetailRepository.update(tag,newName,newImageUrl,newCurrentCoinPrice,
                newRateChg,newMarketCap,newTotalVolume24h,
                newDirectVolume24h,newOpen24h, newDirectVolumeSigned,
                newLowHigh);
    }

    public Boolean existance (String tag){
        return coinDetailRepository.getCoinExistance(tag);
    }

    public List<FavouriteCoin> getFavouriteCoinLiveData() {
        return coinDetailRepository.getFavouriteCoins();
    }
    public List<DailyHistoricalData> getMonthlyData(Context context,String fsym,String tsym,int limit){
        return coinDetailRepository.getMonthlyData(context,fsym,tsym,limit);
    }

}
