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

        List <Bar> exchangeRateData = getExchangeRateData();

        System.out.println(Bat.ScanBat(exchangeRateData, 5).toString());


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

    @Autowired
    RestConsumer restConsumer;

    public List<Bar> getExchangeRateData() throws IOException {
        List<Bar> exchangeRateData = restConsumer.retrieveExchangeDataByInterval(
                Currency.GBP,
                Currency.USD,
                Interval._60MIN
        );

        return exchangeRateData;
    }


}
