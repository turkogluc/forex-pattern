package com.forex.patterns.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciRetracePriceTest {

    /**
     *
     * Tests for CalcFiboRetracePercentage function
     *
     */

    @Test
    public void realPriceUpTrend_CalcRetracePriceTest() {

        double X = 2.820;
        double A = 2.880;

        double Percent = 0;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.88,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 23.6;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8658,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 38.2;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8571,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 50;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.85,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 61.8;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8429,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 76.4;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8342,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 100;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.82,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 138.2;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.7971,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

    }

    @Test
    public void realPriceDownTrend_CalcRetracePriceTest(){

        double X = 2.820;
        double A = 2.760;

        double Percent = 138.2;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8429,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 100;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.82,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 76.4;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.8058,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 38.2;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.7829,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

        Percent = 2.76;
        System.out.println(Fibonacci.calcFiboRetracePrice(X,A,Percent));
        assertEquals(2.76,Fibonacci.calcFiboRetracePrice(X,A,Percent),0.01);

    }

    /*
        NOTE:
        assertEqual function parameter Delta is determining error range
     */


}