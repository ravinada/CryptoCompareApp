package com.ravinada.cryptocompare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyType {
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("abr")
    @Expose
    private String abr;
    @SerializedName("name")
    @Expose
    private String name;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
