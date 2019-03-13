package com.forex.patterns.model;

public class Bar {

    private Double open;
    private Double close;
    private Double low;
    private Double high;
    private String timestamp;

    public Bar() {
    }

    public Bar(Double open, Double close, Double low, Double high, String timestamp) {
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.timestamp = timestamp;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}