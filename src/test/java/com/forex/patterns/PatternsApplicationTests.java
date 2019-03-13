package com.forex.patterns;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Currency;
import com.forex.patterns.model.Interval;
import com.forex.patterns.util.RestConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.forex.patterns.util.Extremum.findExtremePoints;
import static com.forex.patterns.util.Extremum.findLocalMaximumPoints;
import static com.forex.patterns.util.Extremum.findLocalMinimumPoints;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternsApplicationTests {

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
    public void getExchangeRateDataTest() throws IOException {
        List<Bar> exchangeRateData = restConsumer.retrieveExchangeDataByInterval(
                Currency.EUR,
                Currency.USD,
                Interval._60MIN
        );

        System.out.println("size="+exchangeRateData.size());

    }

	@Test
	public void localMaxTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> extrems = findLocalMaximumPoints(exchangeRateData, 5);

        System.out.println("Local maxs");

        for(Bar bar: extrems){
            System.out.println(bar.toString());
        }

        System.out.println("size="+extrems.size());


    }

    @Test
    public void LocalMinTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> localMinimums = findLocalMinimumPoints(exchangeRateData, 5);
        System.out.println("Local mins");

        for(Bar bar: localMinimums){
            System.out.println(bar.toString());
        }

        System.out.println("size="+localMinimums.size());

    }

    @Test
    public void AllExtemsTest() throws IOException {

        List<Bar> exchangeRateData = getExchangeRateData();

        List<Bar> extremePoints = findExtremePoints(exchangeRateData, 5);
        System.out.println("Extrem Points");

        for(Bar bar: extremePoints){
            System.out.println(bar.toString());
        }

        System.out.println("size="+extremePoints.size());


    }

}
