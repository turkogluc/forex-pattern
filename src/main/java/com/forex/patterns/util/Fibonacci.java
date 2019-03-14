package com.forex.patterns.util;

public class Fibonacci {

    /**
     * Given a zigzag, calculate the retrace percentage
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
     *                  point B should be between X and A for a valid retrace
     * @return
     */
    public static double calcRetracePercentage(double pointX,double pointA, double pointB){

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

}
