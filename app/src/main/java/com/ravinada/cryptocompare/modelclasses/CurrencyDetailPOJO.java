package com.ravinada.cryptocompare.modelclasses;

public class CurrencyDetailPOJO {
    private String currentCoinPrice,rateChg,marketCap,totalVolume24h,directVolume24h,open24h,
            directVolumeSigned,low,high;

    public CurrencyDetailPOJO(String currentCoinPrice, String rateChg, String marketCap,
                              String totalVolume24h, String directVolume24h, String open24h,
                              String directVolumeSigned, String low, String high) {
        this.currentCoinPrice = currentCoinPrice;
        this.rateChg = rateChg;
        this.marketCap = marketCap;
        this.totalVolume24h = totalVolume24h;
        this.directVolume24h = directVolume24h;
        this.open24h = open24h;
        this.directVolumeSigned = directVolumeSigned;
        this.low = low;
        this.high = high;
    }

    public String getCurrentCoinPrice() {
        return currentCoinPrice;
    }

    public String getRateChg() {
        return rateChg;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getTotalVolume24h() {
        return totalVolume24h;
    }

    public String getDirectVolume24h() {
        return directVolume24h;
    }

    public String getOpen24h() {
        return open24h;
    }

    public String getDirectVolumeSigned() {
        return directVolumeSigned;
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }
}
