package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

import static src.Main.m;

/** This heuristic tries to minimize the distance as well as the number of drivers **/
public class Heuristic3 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {
        Map map = (Map)n;
        ArrayList<Pair> e = map.getEstatConductors();
        int k = 0;

        //CHECK IF SOME PASSANGER HAS PASSED THE MAXIMUM DISTANCE
        for (int i = 0; i < e.size(); ++i){ //Iterate over every driver
            int km = map.getDistance(i);  //we get the distance of this driver
            if (km > 300)
                k += (km-300);
        }

        //CHECK IF THERE ARE PASSANGERS NOT PICKED UP
        ArrayList<Boolean> estaRecollit = map.getEstaRecullit();
        int norecollits = 0;
        for (Boolean r : estaRecollit)
            if(!r)
                ++norecollits;

        k += 50*norecollits;


        //WE BENEFIT THE MINIMUM NUMBER OF DRIVERS
        int diff = m-e.size();  //number of drivers who are passangers now
        k -= diff*50;

        return k;
    }
}
