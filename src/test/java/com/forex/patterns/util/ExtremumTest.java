package com.forex.patterns.util;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Currency;
import com.forex.patterns.model.Interval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.forex.patterns.util.Extremum.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ExtremumTest {
    
    Logger logger = LoggerFactory.getLogger(ExtremumTest.class);

    @Autowired
    RestConsumer restConsumer;

    public List<Bar> getExchangeRateData() throws IOException {
        List<Bar> exchangeRateData = restConsumer.retrieveExchangeDataByInterval(
                Currency.EUR,
                Currency.USD,
                Interval._60MIN
        );

        return exchangeRateData;
    }

    @Test
    public void localMaxTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> extrems = findLocalMaximumPoints(exchangeRateData, 5);

        logger.info("Local maxs");

        for(Bar bar: extrems){
            logger.info(bar.toString());
        }

        logger.info("size="+extrems.size());


    }

    @Test
    public void LocalMinTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> localMinimums = findLocalMinimumPoints(exchangeRateData, 5);
        logger.info("Local mins");

        for(Bar bar: localMinimums){
            logger.info(bar.toString());
        }

        logger.info("size="+localMinimums.size());

    }

    @Test
    public void AllExtemsTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> extremePoints = findExtremePoints(exchangeRateData, 5);
        logger.info("Extrem Points");

        for(Bar bar: extremePoints){
            logger.info(bar.toString());
        }

        logger.info("size="+extremePoints.size());


    }

}