package com.ravinada.cryptocompare.modelclasses;

public class UserPortfolioCoins {
    private String coinTag;
    private String coinUrl;
    private String coinFullName;
    private float valueHoldingsPortfolioCurrency;
    private float getValueHoldingsPurchaseCurrency;
    private float plPortfolioCurrency;
    private float plPurchaseCurrency;
    float currentCoinValue;
    private int coinId;
    private int pl;
    private boolean expanded;

    public UserPortfolioCoins(String coinTag, String coinUrl, String coinFullName, float valueHoldingsPortfolioCurrency, float getValueHoldingsPurchaseCurrency, float plPortfolioCurrency, float plPurchaseCurrency, int coinId, int pl, float currentCoinValue, boolean expanded) {
        this.coinTag = coinTag;
        this.coinUrl = coinUrl;
        this.coinFullName = coinFullName;
        this.currentCoinValue=
        this.valueHoldingsPortfolioCurrency = valueHoldingsPortfolioCurrency;
        this.getValueHoldingsPurchaseCurrency = getValueHoldingsPurchaseCurrency;
        this.plPortfolioCurrency = plPortfolioCurrency;
        this.plPurchaseCurrency = plPurchaseCurrency;
        this.coinId = coinId;
        this.pl =pl;
        this.expanded = expanded;
    }

    public String getCoinTag() {
        return coinTag;
    }

    public String getCoinUrl() {
        return coinUrl;
    }

    public String getCoinFullName() {
        return coinFullName;
    }

    public float getValueHoldingsPortfolioCurrency() {
        return valueHoldingsPortfolioCurrency;
    }

    public float getGetValueHoldingsPurchaseCurrency() {
        return getValueHoldingsPurchaseCurrency;
    }

    public float getPlPortfolioCurrency() {
        return plPortfolioCurrency;
    }

    public float getPlPurchaseCurrency() {
        return plPurchaseCurrency;
    }

    public int getCoinId() {
        return coinId;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getPl() {
        return pl;
    }

    public float getCurrentCoinValue() {
        return currentCoinValue;
    }
}
