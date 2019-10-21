package com.ravinada.cryptocompare.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ravinada.cryptocompare.repositories.PortfolioRepository;
import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;

import java.util.List;

public class PortfolioViewModel extends AndroidViewModel {
    private PortfolioRepository portfolioRepository;
    public PortfolioViewModel(@NonNull Application application) {
        super(application);
        portfolioRepository = PortfolioRepository.getInstance(application);
    }
    public void insertPortfolio(Portfolio portfolio){
        portfolioRepository.insertPortfolio(portfolio);
    }
    public void insertPortfolioCoin(PortfolioCoin portfolioCoin){
        portfolioRepository.insertPortfolioCoin(portfolioCoin);
    }
    public List<PortfolioCoin> getPortfolioCoins (){
     return portfolioRepository.getPortfolioCoins();
    }
    public List<Portfolio> getPortfolios(){
        return portfolioRepository.getPortfolios();
    }
    public String getSelectedPortfolio(){
        return portfolioRepository.getSelectedName();
    }
    public void setUnselected(String name){
        portfolioRepository.setNotSelected(name);
    }
    public void setSelected(String name){
        portfolioRepository.setSelected(name);
    }
}
