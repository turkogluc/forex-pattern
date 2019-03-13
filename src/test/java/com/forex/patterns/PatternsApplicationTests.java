package com.forex.patterns;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Currency;
import com.forex.patterns.model.ExchangeRateData;
import com.forex.patterns.model.Interval;
import com.forex.patterns.util.RestConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.forex.patterns.util.Extremum.findLocalMinimums;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternsApplicationTests {

    @Autowired
    RestConsumer restConsumer;

	@Test
	public void contextLoads() throws IOException {

        List<Bar> exchangeRateData = restConsumer.retrieveExchangeData(
                Currency.EUR,
                Currency.USD,
                Interval._60MIN
        );

        List<Bar> localMinimums = findLocalMinimums(exchangeRateData, 5);

        System.out.println("size="+exchangeRateData.size());


        System.out.println("Local mins");

        for(Bar bar: localMinimums){
            System.out.println(bar.toString());
        }

        System.out.println("size="+localMinimums.size());



    }

}
