package com.forex.patterns.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.patterns.model.Bar;
import com.forex.patterns.model.ExchangeRateData;
import com.forex.patterns.repository.ExchangeRateDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static com.forex.patterns.model.Interval.GET_JSON_NODE_NAME;

@Component
public class RestConsumer {

    private static final String API_KEY = "4T6YDWXJ7RHY63QK";

    public List<Bar> retrieveExchangeDataByInterval(String from, String to, String interval) {

        Logger logger = LoggerFactory.getLogger(RestConsumer.class);

        List<Bar> bars = new ArrayList<>();

        logger.info(from+to+" "+interval+" is being requested");

        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://www.alphavantage.co/query?function=FX_INTRADAY&" +
                    "from_symbol={FROM}&" +
                    "to_symbol={TO}&" +
                    "interval={INTERVAL}&" +
                    "outputsize=full&" +
                    "apikey={API_KEY}";

            String resultStr = restTemplate.getForObject(url, String.class,from,to,interval, API_KEY);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json;

            json = mapper.readTree(resultStr);

            String nodeTitle = GET_JSON_NODE_NAME(interval); // "Time Series FX (5min)"

            if (json.isNull()){
                return bars;
            }
            if (json.get(nodeTitle).isNull()){
                return bars;
            }
            Iterator<Map.Entry<String, JsonNode>> it = json.get(nodeTitle).fields();

            while ( it.hasNext() ) {
                Map.Entry<String, JsonNode> element = it.next();

                Bar bar = new Bar();

                bar.setTimestamp(element.getKey());
                bar.setOpen(Double.parseDouble(element.getValue().get("1. open").textValue()));
                bar.setHigh(Double.parseDouble(element.getValue().get("2. high").textValue()));
                bar.setLow(Double.parseDouble(element.getValue().get("3. low").textValue()));
                bar.setClose(Double.parseDouble(element.getValue().get("4. close").textValue()));

                bars.add(bar);


            }

        } catch (IOException e) {
            logger.error(e.getMessage());
            /*
                    5 calls per min and 500 calls per day is max limit
                    that's why getting error
             */

        }

        return bars;

    }


}

//  request for tick data
//        ExchangeRateData exchangeRateData = new ExchangeRateData();
//        exchangeRateData.setFromCurrencyCode(json.get("Realtime Currency Exchange Rate").get("1. From_Currency Code")..textValue());
//        exchangeRateData.setToCurrencyCode(json.get("Realtime Currency Exchange Rate").get("3. To_Currency Code").textValue());
//        exchangeRateData.setExchangeRate(json.get("Realtime Currency Exchange Rate").get("5. Exchange Rate").textValue());
//        exchangeRateData.setTimestamp(json.get("Realtime Currency Exchange Rate").get("6. Last Refreshed").textValue());

