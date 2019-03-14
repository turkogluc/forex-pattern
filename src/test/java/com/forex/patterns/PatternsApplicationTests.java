package com.forex.patterns;


import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Currency;
import com.forex.patterns.model.Interval;
import com.forex.patterns.model.Pattern;
import com.forex.patterns.pattern.Bat;
import com.forex.patterns.util.RestConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternsApplicationTests {


    @Test
    public void bcdPointsTest() throws IOException {

        scanBatOnPair(Currency.EUR,Currency.NZD,3);


    }

    public void scanBatOnPair(String from, String to,int interval) throws IOException {
        // 3 bar neighbour
        List <Bar> exchangeRateData = getExchangeRateData(from,to,Interval._1MIN);
        Bat.ScanBat(exchangeRateData, interval);
                
        exchangeRateData = getExchangeRateData(from,to,Interval._5MIN);
        Bat.ScanBat(exchangeRateData, interval);

        exchangeRateData = getExchangeRateData(from,to,Interval._15MIN);
        Bat.ScanBat(exchangeRateData, interval);

        exchangeRateData = getExchangeRateData(from,to,Interval._30MIN);
        Bat.ScanBat(exchangeRateData, interval);

        exchangeRateData = getExchangeRateData(from,to,Interval._60MIN);
        Bat.ScanBat(exchangeRateData, interval);

    }

    @Autowired
    RestConsumer restConsumer;

    public List<Bar> getExchangeRateData(String fromCurrenct,String toCurrency,String interval) {

        List<Bar> exchangeRateData = null;
        exchangeRateData = restConsumer.retrieveExchangeDataByInterval(
                fromCurrenct,
                toCurrency,
                interval
        );

        return exchangeRateData;
    }

    @Test
    public void someListTest(){

        List<Bar> liste = new ArrayList<>();

        Bar t2 = new Bar(10.0, 20.0, 30.0, 40.0, "t2");

        liste.add(new Bar(1.0,2.0,3.0,4.0,"t1"));
        liste.add(new Bar(10.0,20.0,30.0,40.0,"t2"));
        liste.add(new Bar(100.0,200.0,300.0,400.0,"t3"));

        liste.subList(liste.indexOf(t2)+1,liste.size()).forEach(s -> System.out.println(s.toString()));

    }




}
