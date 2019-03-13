package com.forex.patterns.util;

import com.forex.patterns.model.Bar;

import java.util.ArrayList;
import java.util.List;

public class Extremum {

    public static List<Bar> findLocalMinimums(List<Bar> inputBars, int range){

        ArrayList<Bar> outputBars = new ArrayList<>();

        // Constraint check
        // if the graph is wide enough to calculate
        if(inputBars.size() < range*2+1){
            System.out.println("input bars are not enough long to calculate");
            return null;
        }

        // check the first element
        // filter if it is bigger than preceding
        boolean firstBarIsMin = true;
        Bar firstBar = inputBars.get(0);

        for(int i=1; i<=range ; i++){

            if(firstBar.getLow() > inputBars.get(i).getLow()){
                firstBarIsMin = false;
                break;
            }

        }

        if(firstBarIsMin)
            outputBars.add(firstBar);


        // check the ones in the middle


        for(int i=range ; i < inputBars.size()-range ; i++){

            Bar currentBar = inputBars.get(i);

            boolean isMin = true;

            for(int j=1; j<= range ; j++){
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

        // check the last element
        boolean lastBarIsMin = true;
        Bar lastBar = inputBars.get(inputBars.size() - 1);
        for (int i=1; i<=range ; i++){

            if(lastBar.getLow() > inputBars.get(inputBars.size()-1-i).getLow()){
                lastBarIsMin = false;
                break;
            }
        }

        if (lastBarIsMin)
            outputBars.add(lastBar);

        return outputBars;
    }

}


//// 12 -> 5 + 1 + 1 + 5
//range = 5
//0-4 // 5  // 6 // 7-11