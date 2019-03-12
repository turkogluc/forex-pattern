package com.forex.patterns.util;

import com.forex.patterns.model.ExchangeRateData;
import com.forex.patterns.repository.ExchangeRateDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestConsumer {

    private static final String API_KEY = "4T6YDWXJ7RHY63QK";

    private ExchangeRateDataRepository exchangeRateDataRepository;

    @Autowired
    public RestConsumer(ExchangeRateDataRepository exchangeRateDataRepository){
        this.exchangeRateDataRepository = exchangeRateDataRepository;

    }

    public ExchangeRateData retrieveExchangeData(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&" +
                "from_currency=USD&" +
                "to_currency=JPY&" +
                "apikey={api_key}";
        ExchangeRateData result = restTemplate.getForObject(url, ExchangeRateData.class, API_KEY);

        System.out.println(result.toString());

        return result;
    }


}
