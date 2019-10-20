package com.ravinada.cryptocompare.repositories;

import android.app.Application;

import com.ravinada.cryptocompare.room.Portfolio;
import com.ravinada.cryptocompare.room.PortfolioDao;
import com.ravinada.cryptocompare.room.PortfolioDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PortfolioRepository {
    private static PortfolioRepository portfolioRepository;
    private PortfolioDao portfolioDao;
    private Executor executor;
    private PortfolioRepository(Application application){
        PortfolioDatabase portfolioDatabase = PortfolioDatabase.getInstance(application);
        portfolioDao = portfolioDatabase.portfolioDao();
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
}
