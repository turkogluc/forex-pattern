package com.forex.patterns.util;

import com.forex.patterns.model.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Static functions related with extreme point analyze
 */
public class Extremum {
    
    static Logger logger = LoggerFactory.getLogger(Extremum.class);

    /**
     * Find Local Minimums in a list of price bars
     * A relative minimum point is a point where the function changes direction from decreasing to increasing
     *
     * @param inputBars : price list
     * @param numOfNeighbours : int n, to check critical points that should be greater || smaller than ;
     *                  following n points and
     *                  preceding n points
     *
     * @return outputBars -> list of localMin points
     */
    public static List<Bar> findLocalMinimumPoints(List<Bar> inputBars, int numOfNeighbours){


        ArrayList<Bar> outputBars = new ArrayList<>();

        /*
          Initial constraint check : if the graph is wide enough to calculate
          To be able to test at least it should be enough as wide as (Range + 1 + Range)
         */

        if(inputBars.size() < numOfNeighbours*2+1){
            logger.debug("input bars are not enough wide to calculate");
            return null;
        }

        /*
            FIRST and last points are UNKNOWN for extremum search on a chart
            In a mathematical function they are always considered as critical point
            However here we add it as local min iff it is smaller than following bars in numOfNeighbours
         */


        // checking if the first element could be considered as local min
        boolean firstBarIsLocalMin = true;
        Bar firstBar = inputBars.get(0);

        for(int i=1; i<=numOfNeighbours ; i++){
            if(firstBar.getLow() > inputBars.get(i).getLow()){
                firstBarIsLocalMin = false;
                break;
            }
        }

        if(firstBarIsLocalMin)
            outputBars.add(firstBar);


        /*
         Searching for critical points in the graph
         A point is local min iff it is;
            smaller than following n element AND
            smaller than preceding n elements
         We should check if the point of interest is smallest in the given numOfNeighbours
          */


        for(int i=numOfNeighbours ; i < inputBars.size()-numOfNeighbours ; i++){

            Bar currentBar = inputBars.get(i);

            boolean isMin = true;

            for(int j=1; j<= numOfNeighbours ; j++){
                if (currentBar.getLow() > inputBars.get(i-j).getLow() ||
                        currentBar.getLow() > inputBars.get(i+j).getLow() ){

                    isMin = false;
                    break;
                }
            }

            if(isMin){
                outputBars.add(currentBar);
            }

        }


        /*
            First and LAST points are UNKNOWN for extremum search on a chart
            In a mathematical function they are always considered as critical point
            However here we add it as local min iff it is smaller than following bars in numOfNeighbours
         */

        // checking if the last element could be considered as local min
        boolean lastBarIsMin = true;
        Bar lastBar = inputBars.get(inputBars.size() - 1);
        for (int i=1; i<=numOfNeighbours ; i++){

            if(lastBar.getLow() > inputBars.get(inputBars.size()-1-i).getLow()){
                lastBarIsMin = false;
                break;
            }
        }

        if (lastBarIsMin)
            outputBars.add(lastBar);

        return outputBars;
    }


    /**
     * Find Local Maximums in a list of price bars
     * A relative maximum point is a point where the function changes direction from increasing to decreasing
     *
     * @param inputBars : price list
     * @param numOfNeighbours : int n, to check critical points that should be greater || smaller than ;
     *                  following n points and
     *                  preceding n points
     *
     * @return outputBars -> list of localMax points
     */
    public static List<Bar> findLocalMaximumPoints(List<Bar> inputBars, int numOfNeighbours){


        ArrayList<Bar> outputBars = new ArrayList<>();

        /*
          Initial constraint check : if the graph is wide enough to calculate
          To be able to test at least it should be enough as wide as (Range + 1 + Range)
         */

        if(inputBars.size() < numOfNeighbours*2+1){
            logger.debug("input bars are not enough wide to calculate <- local max points");
            return null;
        }

        /*
            FIRST and last points are UNKNOWN for extremum search on a chart
            In a mathematical function they are always considered as critical point
            However here we add it as local max iff it is greater than following bars in numOfNeighbours
         */


        // checking if the first element could be considered as local max
        boolean firstBarIsLocalMax = true;
        Bar firstBar = inputBars.get(0);

        for(int i=1; i<=numOfNeighbours ; i++){
            if(firstBar.getHigh() < inputBars.get(i).getHigh()){
                firstBarIsLocalMax = false;
                break;
            }
        }

        if(firstBarIsLocalMax)
            outputBars.add(firstBar);


        /*
         Searching for critical points in the graph
         A point is local max iff it is;
            greater than following n element AND
            greater than preceding n elements
         We should check if the point of interest is greatest in the given numOfNeighbours
          */


        for(int i=numOfNeighbours ; i < inputBars.size()-numOfNeighbours ; i++){

            Bar currentBar = inputBars.get(i);

            boolean isMax = true;

            for(int j=1; j<= numOfNeighbours ; j++){
                if (currentBar.getHigh() < inputBars.get(i-j).getHigh() ||
                        currentBar.getHigh() < inputBars.get(i+j).getHigh() ){

                    isMax = false;
                    break;
                }
            }

            if(isMax){
                outputBars.add(currentBar);
            }

        }


        /*
            First and LAST points are UNKNOWN for extremum search on a chart
            In a mathematical function they are always considered as critical point
            However here we add it as local max iff it is greater than following bars in numOfNeighbours
         */

        // checking if the last element could be considered as local max
        boolean lastBarIsMax = true;
        Bar lastBar = inputBars.get(inputBars.size() - 1);
        for (int i=1; i<=numOfNeighbours ; i++){

            if(lastBar.getHigh() < inputBars.get(inputBars.size()-1-i).getHigh()){
                lastBarIsMax = false;
                break;
            }
        }

        if (lastBarIsMax)
            outputBars.add(lastBar);

        return outputBars;
    }

    /**
     * Find All Extreme Points including local minimum and maximum
     *
     * @param inputBars : price list
     * @param numOfNeighbours : int n, to check critical points that should be greater || smaller than ;
     *                  following n points and
     *                  preceding n points
     * @return allExtrems
     */
    public static List<Bar> findExtremePoints(List<Bar> inputBars, int numOfNeighbours){

        List<Bar> localMinimumPoints = findLocalMinimumPoints(inputBars, numOfNeighbours);
        List<Bar> localMaximumPoints = findLocalMaximumPoints(inputBars, numOfNeighbours);


        localMaximumPoints.forEach(bar -> bar.setIsExtreme("MAX"));
        localMinimumPoints.forEach(bar -> bar.setIsExtreme("MIN"));


        List<Bar> allExtrems = new ArrayList<>();
        allExtrems.addAll(localMinimumPoints);
        allExtrems.addAll(localMaximumPoints);


        // Sort by timestamp and return list starting from most current to past
        return allExtrems
                .stream()
                .sorted(Comparator.comparing(Bar::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

}