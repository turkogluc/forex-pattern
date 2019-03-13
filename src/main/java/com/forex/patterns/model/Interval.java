package com.forex.patterns.model;

public class Interval {
    public static final String _5MIN = "5min";
    public static final String _15MIN = "15min";
    public static final String _30MIN = "30min";
    public static final String _60MIN = "60min";

    public static String GET_JSON_NODE_NAME(String interval){
        return "Time Series FX (" + interval + ")" ;
    }
}
