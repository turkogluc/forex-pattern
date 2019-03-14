package com.forex.patterns.model;

public class Pattern {
    private Bar PointX;
    private Bar PointA;
    private Bar PointB;
    private Bar PointC;
    private Bar PointD;

    public Pattern() {
    }

    public Pattern(Bar pointX, Bar pointA, Bar pointB, Bar pointC, Bar pointD) {
        PointX = pointX;
        PointA = pointA;
        PointB = pointB;
        PointC = pointC;
        PointD = pointD;
    }

    public Bar getPointX() {
        return PointX;
    }

    public void setPointX(Bar pointX) {
        PointX = pointX;
    }

    public Bar getPointA() {
        return PointA;
    }

    public void setPointA(Bar pointA) {
        PointA = pointA;
    }

    public Bar getPointB() {
        return PointB;
    }

    public void setPointB(Bar pointB) {
        PointB = pointB;
    }

    public Bar getPointC() {
        return PointC;
    }

    public void setPointC(Bar pointC) {
        PointC = pointC;
    }

    public Bar getPointD() {
        return PointD;
    }

    public void setPointD(Bar pointD) {
        PointD = pointD;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "PointX=" + PointX +
                ", PointA=" + PointA +
                ", PointB=" + PointB +
                ", PointC=" + PointC +
                ", PointD=" + PointD +
                '}';
    }
}
