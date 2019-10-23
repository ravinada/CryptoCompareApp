package com.ravinada.cryptocompare.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioCoin;
import com.ravinada.cryptocompare.room.PortfolioCoinDao;
import com.ravinada.cryptocompare.room.PortfolioCoinDb;
import com.ravinada.cryptocompare.room.PortfolioDao;
import com.ravinada.cryptocompare.room.PortfolioDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PortfolioRepository {
    private static PortfolioRepository portfolioRepository;
    private PortfolioDao portfolioDao;
    private PortfolioCoinDao portfolioCoinDao;
    private Executor executor;
    private PortfolioRepository(Application application){
        PortfolioDatabase portfolioDatabase = PortfolioDatabase.getInstance(application);
        PortfolioCoinDb portfolioCoinDb = PortfolioCoinDb.getInstance(application);
        portfolioDao = portfolioDatabase.portfolioDao();
        portfolioCoinDao = portfolioCoinDb.portfolioCoinDao();
        executor = Executors.newSingleThreadExecutor();
    }
    public static PortfolioRepository getInstance(Application application){
        if(portfolioRepository == null){
            portfolioRepository = new PortfolioRepository(application);
        }
        return portfolioRepository;
    }
    public void insertPortfolio(Portfolio portfolio){
        executor.execute(()->portfolioDao.insert(portfolio));
    }
    public void deletePortfolio(Portfolio portfolio){
        executor.execute(()->portfolioDao.delete(portfolio));
    }
    public void insertPortfolioCoin(PortfolioCoin portfolioCoin){
        executor.execute(()->portfolioCoinDao.insert(portfolioCoin));
    }
    public void deletePortfolioCoin(PortfolioCoin portfolioCoin){
        executor.execute(()-> portfolioCoinDao.delete(portfolioCoin));
    }
    public List<PortfolioCoin> getPortfolioCoins(){
        return portfolioCoinDao.getPortfolioCoins();
    }
    public LiveData<List<Portfolio>>getPortfolios(){
        return portfolioDao.getPortfolios();
    }
    public String getSelectedName(){
        return portfolioDao.getSelected(true);
    }
    public void setNotSelected(String name){
        executor.execute(() ->portfolioDao.setNotSelected(name,false));
    }
    public void setSelected(String name){
        executor.execute(()->portfolioDao.setSelected(name,true));
    }
    public  int getIdForSelectedName(){
        return portfolioDao.getidForSelectedName(true);
    }

    public LiveData<List<PortfolioCoin>> getPortfolioCoinsForId(int portfolioId) {
        return portfolioCoinDao.getPortfolioCoinsForId(portfolioId);
    }
    public LiveData<String> getCurrencyForSelectedPortfolio(){
        return portfolioDao.getCurrencySelectionForSelectedPortfolio(true);
    }
}
