package com.forex.patterns.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonRootName(value = "Realtime Currency Exchange Rate")
public class ExchangeRateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("1. From_Currency Code")
    private String fromCurrencyCode;

    @JsonProperty("3. To_Currency Code")
    private String toCurrencyCode;

    @JsonProperty("5. Exchange Rate")
    private String exchangeRate;

    @JsonProperty("6. Last Refreshed")
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
