package com.forex.patterns.pattern;

import com.forex.patterns.model.Bar;
import com.forex.patterns.model.Pattern;
import com.forex.patterns.util.Extremum;
import com.forex.patterns.util.Fibonacci;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bat {

    static Logger logger = LoggerFactory.getLogger(Bat.class);

    /*
        retrace for B-C-D could be between 168.1 and 268.1
     */
    public static final double BCDpercentageLowerLimit = 168.1;
    public static final double BCDpercentageUpperLimit = 268.1;

    public static final double ABCpercentageLowerLimit = 38.2;
    public static final double ABCpercentageUpperLimit = 88.6;

    public static final double XABpercentageLowerLimit = 38.2;
    public static final double XABpercentageUpperLimit = 50.0;

    public static final double XADpercentageLowerLimit = 88.0;
    public static final double XADpercentageUpperLimit = 89.0;


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

        //List<Bar> chart = inputChart.subList(0, inputChart.indexOf(extremePoints.get(1)));

        inputChart.forEach(pointD -> { // for each bar (possibly point D) on the chart

            extremePoints.forEach(pointC -> { // choose each and every extreme points as possible point C

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
                                        )
                                    .forEach(pointA ->{

                                        ScanPointX( pointD,
                                                    pointC,
                                                    pointB,
                                                    pointA,
                                                    extremePoints.subList(
                                                            extremePoints.indexOf(pointA)+1,
                                                            extremePoints.size()
                                                    )
                                        ).forEach(pointX ->{

                                            shapedPattern.setPointX(pointX);
                                            shapedPattern.setPointA(pointA);
                                            shapedPattern.setPointB(pointB);
                                            shapedPattern.setPointC(pointC);
                                            shapedPattern.setPointD(pointD);

                                            logger.info("pointX:" + pointX);
                                            logger.info("pointA:" + pointA);
                                            logger.info("pointB:" + pointB);
                                            logger.info("pointC:" + pointC);
                                            logger.info("pointD:" + pointD);

                                        });

                                    });
                        });

            });

        });

        return shapedPattern;
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
                return bar.getHigh() > priceBLowerLimit && bar.getHigh() < priceBUpperLimit && bar.getIsExtreme().equals("MAX");
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){

                logger.info("Point B found");
                resultSet.addAll(collect);
            }

        }else if (BarC.getIsExtreme().equals("MAX")){

            // if BarC is an local MAX extreme
            // then BarB and BarD must be local MIN

            priceBLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getHigh(), BarD.getLow(), BCDpercentageLowerLimit);
            priceBUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarC.getHigh(), BarD.getLow(), BCDpercentageUpperLimit);

            List <Bar> collect = extremePoints.stream().filter(bar -> {
                return bar.getLow() > priceBLowerLimit && bar.getLow() < priceBUpperLimit && bar.getIsExtreme().equals("MIN");
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){
                logger.info("Point B found");
                resultSet.addAll(collect);
            }



        }

        return resultSet;
    }

    /**
     *
     * @param BarD
     * @param BarC
     * @param BarB
     * @param extremePoints
     * @return
     */
    public static List<Bar> ScanPointA(Bar BarD, Bar BarC, Bar BarB, List<Bar> extremePoints){

        List<Bar> resultSet = new ArrayList <>();

        double priceALowerLimit;
        double priceAUpperLimit;

        if (BarB.getIsExtreme().equals("MIN")){

            // if BarB is an local MIN extreme
            // then BarB and BarD must be local MAX

            priceALowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarB.getLow(), BarC.getHigh(), ABCpercentageLowerLimit);
            priceAUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarB.getLow(), BarC.getHigh(), ABCpercentageUpperLimit);


            List <Bar> collect = extremePoints.stream().filter(bar -> {
                return bar.getHigh() > priceALowerLimit && bar.getHigh() < priceAUpperLimit && bar.getIsExtreme().equals("MAX");
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){

                logger.info("Point A found");
                resultSet.addAll(collect);
            }

        }else if (BarB.getIsExtreme().equals("MAX")){

            // if BarB is an local MAX extreme
            // then BarB and BarD must be local MIN

            priceALowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarB.getHigh(), BarC.getLow(), ABCpercentageLowerLimit);
            priceAUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarB.getHigh(), BarC.getLow(), ABCpercentageUpperLimit);

            List <Bar> collect = extremePoints.stream().filter(bar -> {
                return bar.getLow() > priceALowerLimit && bar.getLow() < priceAUpperLimit && bar.getIsExtreme().equals("MIN");
            }).collect(Collectors.toList());

            if (collect.size() > 0 ){
                logger.info("Point A found");
                resultSet.addAll(collect);
            }

        }

        return resultSet;
    }

    /**
     *
     * @param BarD
     * @param BarC
     * @param BarB
     * @param extremePoints
     * @return
     */
    public static List<Bar> ScanPointX(Bar BarD, Bar BarC, Bar BarB, Bar BarA, List<Bar> extremePoints){

        List<Bar> resultSet = new ArrayList <>();

        double priceXABLowerLimit;  // for first constraint: X-A-B should have retrace %38-%50
        double priceXABUpperLimit;

        double priceXADLowerLimit;  // for second constraint: X-A-D should have retrace %87-%88
        double priceXADUpperLimit;

        double priceXLowerLimit;    // merged constraint: first + second
        double priceXUpperLimit;

        if (BarA.getIsExtreme().equals("MIN")){

            // if BarA is an local MIN extreme
            // then BarB and BarD must be local MAX

            priceXABLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getLow(), BarB.getHigh(), XABpercentageLowerLimit);
            priceXABUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getLow(), BarB.getHigh(), XABpercentageUpperLimit);

            priceXADLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getLow(), BarD.getHigh(), XADpercentageLowerLimit);
            priceXADUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getLow(),BarD.getHigh(), XADpercentageUpperLimit);

            // merging 2 constraints
            priceXLowerLimit = Math.max(priceXABLowerLimit, priceXADLowerLimit);   // highest between lower limits will be general low limit
            priceXUpperLimit = Math.min(priceXABUpperLimit, priceXADUpperLimit);   // lowest between  highest limits will be general high limit

            List <Bar> collect = extremePoints.stream().filter(bar -> {

                return bar.getHigh() > priceXLowerLimit && bar.getHigh() < priceXUpperLimit && bar.getIsExtreme().equals("MAX");

            }).collect(Collectors.toList());

            if (collect.size() > 0 ){

                logger.info("Point X found");
                resultSet.addAll(collect);
            }

        }else if (BarA.getIsExtreme().equals("MAX")){

            // if BarA is an local MAX extreme
            // then BarB and BarD must be local MIN

            priceXABLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getHigh(), BarB.getLow(), XABpercentageLowerLimit);
            priceXABUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getHigh(), BarB.getLow(), XABpercentageUpperLimit);

            priceXADLowerLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getHigh(), BarD.getLow(), XADpercentageLowerLimit);
            priceXADUpperLimit = Fibonacci.calcFiboRetracePriceBackward(BarA.getHigh(), BarD.getLow(), XADpercentageUpperLimit);

            priceXLowerLimit = Math.max(priceXABLowerLimit, priceXADLowerLimit);
            priceXUpperLimit = Math.min(priceXABUpperLimit, priceXADUpperLimit);

            List <Bar> collect = extremePoints.stream().filter(bar -> {

                return bar.getLow() > priceXLowerLimit && bar.getLow() < priceXUpperLimit && bar.getIsExtreme().equals("MIN");

            }).collect(Collectors.toList());

            if (collect.size() > 0 ){

                logger.info("Point X found");
                resultSet.addAll(collect);
            }

        }

        return resultSet;
    }

}
