package com.forex.patterns.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class FibonacciRetracePercentageTest {
    
    Logger logger = LoggerFactory.getLogger(FibonacciRetracePercentageTest.class);

    /**
     *
     * Tests for CalcFiboRetracePercentage function
     *
     */

    @Test
    public void retrace40_CalcRetracePercentageTest() {

        double X = 0;
        double A = 100;
        double B = 60;

        assertEquals(40,Fibonacci.calcFiboRetracePercentage(X,A,B),0);

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));

    }


    @Test
    public void retrace60_CalcRetracePercentageTest() {

        double X = 0;
        double A = 100;
        double B = 40;

        assertEquals(60,Fibonacci.calcFiboRetracePercentage(X,A,B),0);

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));

    }



    @Test
    public void downwardMove_retrace60_CalcRetracePercentageTest() {

        double X = 100;
        double A = 0;
        double B = 60;

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(60,Fibonacci.calcFiboRetracePercentage(X,A,B), 0);

    }

    @Test
    public void given_B_isFurtherThan_A_CalcRetracePercentageTest() {

        double X = 0;
        double A = 100;
        double B = 150;

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(0,Fibonacci.calcFiboRetracePercentage(X,A,B),0);

    }

    @Test
    public void fullRetrace_CalcRetracePercentageTest(){

        double X = 100;
        double A = 200;
        double B = 100;

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(100,Fibonacci.calcFiboRetracePercentage(X,A,B),0);

    }

    @Test
    public void given_B_isSmallerThan_X_CalcRetracePercentageTest() {

        double X = 100;
        double A = 200;
        double B = 50;

        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(150,Fibonacci.calcFiboRetracePercentage(X,A,B),0);

    }


    @Test
    public void realPriceUpTrend_CalcRetracePercentageTest() {

        double X = 2.820;
        double A = 2.880;

        double B = 2.90; // continuous up move, no retrace
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(0,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.88;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(0,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.8658;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(23.6,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.8571;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(38.2,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.85;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(50,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.8429;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(61.8,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.8342;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(76.4,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.82;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(100,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.7971;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(138.2,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

    }

    @Test
    public void realPriceDownTrend_CalcRetracePercentageTest(){

        double X = 2.820;
        double A = 2.760;

        double B = 2.8429;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(138.2,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.82;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(100,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.8058;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(76.4,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.7829;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(38.2,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.76;
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(0,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

        B = 2.75; // no trace, but kept going down
        System.out.println(Fibonacci.calcFiboRetracePercentage(X,A,B));
        assertEquals(0,Fibonacci.calcFiboRetracePercentage(X,A,B),0.1);

    }


}