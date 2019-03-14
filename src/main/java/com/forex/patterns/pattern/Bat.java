package com.forex.patterns.pattern;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Pattern;
import com.forex.patterns.util.Extremum;
import com.forex.patterns.util.Fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bat {

    /*
        retrace for B-C-D could be between 168.1 and 268.1
     */
    public static final double BCDpercentageLowerLimit = 168.1;
    public static final double BCDpercentageUpperLimit = 268.1;


    /**
     * Scan Bat Pattern : Find X-A-B-C-D points which has proper Fibonacci ratio relations among each other
     *
     * Backward search is conducted.
     * Iterating over price chart ( from most current to past ), find any point possibly D,
     * and iterating over extreme points, find any point possibly C,
     * then look for proper B - X - A points respectively with proper ratio limitations.
     *
     * @param inputChart price chart
     * @param numOfNeighbours to find extreme points
     */

    public static Pattern ScanBat(List<Bar> inputChart, int numOfNeighbours){

        Pattern shapedPattern = new Pattern();

        List<Bar> extremePoints = Extremum.findExtremePoints(inputChart,numOfNeighbours);

        inputChart.forEach(pointD -> { // for each bar (possibly point D) on the chart

            extremePoints.forEach(pointC ->{ // choose each and every extreme points as possible point C

                ScanPointB( pointD,
                            pointC,
                            extremePoints.subList(
                                    extremePoints.indexOf(pointC)+1,
                                    extremePoints.size() )
                            )
                        .forEach( pointB -> {  // for each pointB that is found


                            ScanPointA (pointD,
                                        pointC,
                                        pointB,
                                        extremePoints.subList (
                                                extremePoints.indexOf(pointB)+1,
                                                extremePoints.size() )
                                        );



                        });

            });

        });

        for(Bar currentBar : inputChart){

            // PointD <- current bar (any point possibly D)

            for(Bar extremePoint : extremePoints){

                // PointC <- extremePoint  (any point possibly C)

                // find point B
                List<Bar> pointB = ScanPointB(currentBar,
                                        extremePoint,
                                        extremePoints.subList(extremePoints.indexOf(extremePoint)+1, extremePoints.size())
                );

                Bar pointA = null;

                pointB.forEach( b -> {

                    //cemal

                });

                if (pointB.size() > 0){



                    shapedPattern.setPointD(currentBar);
                    shapedPattern.setPointC(extremePoint);
                    shapedPattern.setPointB(pointB.get(0));

                    return shapedPattern;

//                    pointA = ScanPointA(currentBar,
//                                        extremePoint,
//                                        pointB,
//                                        extremePoints.subList(extremePoints.indexOf(pointB)+1, extremePoints.size())
//                    );
//
//                    if (pointA != null){



                    }

            }

        }

        return null;
    }


    /**
     * Find pointB, given PointC and pointD
     *
     *
     * @param BarC
     * @param BarD
     * @param extremePoints
     * @return
     */

    public static List<Bar> ScanPointB(Bar BarD, Bar BarC, List<Bar> extremePoints){

        List<Bar> resultSet = new ArrayList <>();

        double priceBLowerLimit;
        double priceBUpperLimit;

        if (BarC.getIsExtreme().equals("MIN")){

            // if BarC is an local MIN extreme
            // then BarB and BarD must be local MAX

            priceBLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getLow(), BarD.getHigh(), BCDpercentageLowerLimit);
            priceBUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getLow(), BarD.getHigh(), BCDpercentageUpperLimit);


            List <Bar> collect = extremePoints.stream().filter(bar -> {
                return bar.getHigh() > priceBLowerLimit && bar.getHigh() < priceBUpperLimit;
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){

                System.out.println("Point B found");
                resultSet.addAll(collect);
            }

        }else if (BarC.getIsExtreme().equals("MAX")){

            // if BarC is an local MAX extreme
            // then BarB and BarD must be local MIN

            priceBLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getHigh(), BarD.getLow(), BCDpercentageLowerLimit);
            priceBUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getHigh(), BarD.getLow(), BCDpercentageUpperLimit);

            List <Bar> collect = extremePoints.stream().filter(bar -> {
                return bar.getLow() > priceBLowerLimit && bar.getLow() < priceBUpperLimit;
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){
                System.out.println("Point B found");
                resultSet.addAll(collect);
            }



        }

        return resultSet;
    }

    public static List<Bar> ScanPointA(Bar PointD, Bar PointC, Bar PointB, List<Bar> extremePoints){

        return null;
    }

}
