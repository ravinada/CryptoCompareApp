package com.ravinada.cryptocompare.modelclasses;

public class DailyHistoricalData {
    private long time;
    private double high;
    private double low;
    private double close;

    public void setTime(long time) {
        this.time = time;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getTime() {
        return time;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }
}
