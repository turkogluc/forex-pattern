package com.forex.patterns.util;

import com.forex.patterns.model.Bar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HistoricalDataReaderTest {

    @Test
    public void retrieveHistoricalDataFromFileTest() {

        String filename = "/Users/cemalturkoglu/Downloads/eurusd.csv";

        List <Bar> bars = HistoricalDataReader.retrieveHistoricalDataFromFile(filename);

        bars.forEach(b -> System.out.println(b.toString()));

    }
}