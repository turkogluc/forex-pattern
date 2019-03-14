package com.forex.patterns.util;

public class Fibonacci {

    /**
     * Given X,A,B points in a zigzag,
     * Calculate the retrace percentage
     *
     *         [A]              |
     *        /   \             |   Retrace range [AB]
     *       /    \             |
     *      /    [B]
     *     /
     *  [X]
     *
     * @param pointX    starting point
     * @param pointA    extremum min or max
     * @param pointB    retrace after point A
     * @return  percentage
     */
    public static double calcFiboRetracePercentage(double pointX, double pointA, double pointB){

        /*
            if they are all increasing or decreasing order
            then there is no retrace but an continuous up or down move
         */
        if( (pointX > pointA && pointA > pointB) || (pointX < pointA && pointA < pointB) ) {
            System.out.println("there is no retrace");
            return 0;
        }

        double diffXA = Math.abs( pointX - pointA );
        double diffXB = Math.abs( pointX - pointB );
        double diffAB = Math.abs( pointA - pointB );

        // Zero division check
        if(diffXA == 0){
            System.out.println("zero division mistake");
            return 0;
        }

        // for [XA] distance retrace [AB]
        // for 100 --------- retrace [?]

        return (100* diffAB) / diffXA;
    }

    /**
     * Given X,A points and percentage,
     * Calculate point B
     *
     * @param pointX        starting point
     * @param pointA        extremum min or max
     * @param percentage    retrace percentage
     * @return  pointB according to given X,A points and percentage
     */
    public static double calcFiboRetracePrice(double pointX, double pointA, double percentage){

        double diffXA = Math.abs( pointX - pointA );
        double newPriceDiff = (diffXA * percentage) / 100;
        double pointB = 0 ;

        if ( pointX < pointA ){

            pointB = pointA - newPriceDiff;

        }else{

            pointB = pointA + newPriceDiff;

        }

        return pointB;
    }

    /**
     * Given A,B points and percentage,
     * Calculate starting point X
     *
     * @param pointA        extreme min or max
     * @param pointB        ending point
     * @param percentage    retrace percentage
     * @return  pointX according to given A,B points and percentage
     */
    public static double calcFiboRetracePriceBackward(double pointA, double pointB, double percentage){

        if (percentage == 0){

        }

        double diffAB = Math.abs( pointA - pointB);

        //  Expected distance calculator
        //        100  ---- percentage
        //        ?    ---- [AB]


        double expectedDistance = ( 100 * diffAB ) / percentage ;

        if (pointA > pointB){

            return pointA - expectedDistance;

        }else{

            return pointA + expectedDistance;

        }

    }

}
