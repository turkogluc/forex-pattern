package com.forex.patterns.util;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Currency;
import com.forex.patterns.model.Interval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestConsumerTest {

    @Autowired
    RestConsumer restConsumer;

    /**
     *
     * NOTE: Sometimes API call gets error, may be network issues or api server problem
     *
     * @throws IOException
     */

    @Test
    public void getExchangeRateDataTest() {
        List<Bar> exchangeRateData = null;
        try {
            exchangeRateData = restConsumer.retrieveExchangeDataByInterval(
                    Currency.EUR,
                    Currency.USD,
                    Interval._60MIN
            );

            System.out.println("size="+exchangeRateData.size());

            exchangeRateData.forEach(bar -> System.out.println(bar.toString()));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sometimes API call gets error, may be network issues or api server problem");
        }



    }

}