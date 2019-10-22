package com.ravinada.cryptocompare;


import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.ravinada.cryptocompare.utils.SharedPreferenceUtil;

public class CryptoCompareApp extends MultiDexApplication {

    private static CryptoCompareApp mInstance;
    public static synchronized CryptoCompareApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SharedPreferenceUtil.init(this);
        SharedPreferenceUtil.save();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
