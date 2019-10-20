package com.ravinada.cryptocompare.modelclasses;

public class MinuteHistoricalData {
    long time;
    double high;
    double low;
    double close;

    public MinuteHistoricalData(long time, double high, double low, double close) {
        this.time = time;
        this.high = high;
        this.low = low;
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
