package com.forex.patterns.util;

import com.forex.patterns.model.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricalDataReader {

    // Open - High - Low - Close

    public static String COMMA_DELIMITER = ",";

    static Logger logger = LoggerFactory.getLogger(HistoricalDataReader.class);

    public static List<Bar> retrieveHistoricalDataFromFile(String filename){

        List<Bar> resultSet = new ArrayList <>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(COMMA_DELIMITER);

                String timestamp = values[0] + " " + values[1];

                Bar bar = new Bar( Double.valueOf(values[2]),
                                   Double.valueOf(values[5]),
                                   Double.valueOf(values[4]),
                                   Double.valueOf(values[3]),
                                   timestamp);

                resultSet.add(bar);

                //logger.info(String.join(",",values));


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultSet
                .stream()
                .sorted(Comparator.comparing(Bar::getTimestamp)
                        .reversed())
                .collect(Collectors.toList());
    }

}
