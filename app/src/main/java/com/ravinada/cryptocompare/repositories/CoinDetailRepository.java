package com.ravinada.cryptocompare.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ravinada.cryptocompare.modelclasses.DailyHistoricalData;
import com.ravinada.cryptocompare.room.FavouriteCoin;
import com.ravinada.cryptocompare.room.FavouriteCoinDao;
import com.ravinada.cryptocompare.room.FavouriteCoinDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CoinDetailRepository {
    private FavouriteCoinDao favouriteCoinDao;
    private Executor executor;
    private static CoinDetailRepository coinDetailRepository;

    private CoinDetailRepository(Application application) {
        FavouriteCoinDatabase database = FavouriteCoinDatabase.getInstance(application);
        favouriteCoinDao = database.favouriteCoinDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public static CoinDetailRepository getInstance(Application application) {
        if (coinDetailRepository == null) {
            coinDetailRepository = new CoinDetailRepository(application);
        }
        return coinDetailRepository;
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
