package com.forex.patterns.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExchangeRateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String exchangeRate;
    private String timestamp;

    public ExchangeRateData() {
    }

    public ExchangeRateData(String fromCurrencyCode, String toCurrencyCode, String exchangeRate, String timestamp) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.exchangeRate = exchangeRate;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ExchangeRateData{" +
                "id=" + id +
                ", fromCurrencyCode='" + fromCurrencyCode + '\'' +
                ", toCurrencyCode='" + toCurrencyCode + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
