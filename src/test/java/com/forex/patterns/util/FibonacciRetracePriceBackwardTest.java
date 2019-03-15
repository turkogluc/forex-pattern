package com.forex.patterns.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciRetracePriceBackwardTest {

    @Test
    public void realPriceUpTrend_CalcRetracePriceBackwardTest() {

        double pointX = 2.820;
        double A = 2.880;


        double B = 2.8658;
        double Percent = 23.6;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);


        B = 2.8571;
        Percent = 38.2;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.8342;
        Percent = 76.4;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.82;
        Percent = 100;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.7971;
        Percent = 138.2;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

    }

    @Test
    public void realPriceDownTrend_CalcRetracePriceBackwardTest(){

        double pointX = 2.820;
        double A = 2.760;


        double B = 2.8429 ;
        double Percent = 138.2;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.82;
        Percent = 100;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.8058;
        Percent = 76.4;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);

        B = 2.7829;
        Percent = 38.2;
        System.out.println(Fibonacci.calcFiboRetracePriceBackward(A,B,Percent));
        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(A,B,Percent),0.01);


    }

    @Test public void simple_CalcRetracePriceBackwardTest(){


        double pointX = 100;

        double pointA = 200;
        double pointB = 70;
        double percentage = 130;

        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(pointA,pointB,percentage),0.01);

    }


    @Test public void simple_CalcRetracePriceBackwardTest2(){


        double pointX = 100;

        double pointA = 200;
        double pointB = 170;
        double percentage = 30;

        assertEquals(pointX,Fibonacci.calcFiboRetracePriceBackward(pointA,pointB,percentage),0.01);

    }
}