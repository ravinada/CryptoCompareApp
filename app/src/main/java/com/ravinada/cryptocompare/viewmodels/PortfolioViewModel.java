package com.ravinada.cryptocompare.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ravinada.cryptocompare.repositories.PortfolioRepository;
import com.ravinada.cryptocompare.room.Portfolio;

public class PortfolioViewModel extends AndroidViewModel {
    private PortfolioRepository portfolioRepository;
    public PortfolioViewModel(@NonNull Application application) {
        super(application);
        portfolioRepository = PortfolioRepository.getInstance(application);
    }
    public void insertPortfolio(Portfolio portfolio){
        portfolioRepository.insertPortfolio(portfolio);
    }
}
