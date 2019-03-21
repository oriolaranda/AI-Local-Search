package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

/** This heuristic tries only to go to a solution where all passangers make it on time and minimizes the distance
    We punctuate the number of people that do not pass the restrictions **/

public class Heuristic1 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {
        Map m = (Map)n;
        ArrayList<Pair> e = m.getEstatConductors();
        int k = 0;
        for (int i = 0; i < e.size(); ++i){ //Iterate over every driver
            int km = m.getDistance(i);  //we get the distance of this driver
            if (km > 300)
                k ++;
        }

        ArrayList<Boolean> estaRecollit = m.getEstaRecullit();
        int norecollits = 0;
        for (Boolean r : estaRecollit)
            if(!r)
                ++norecollits;

        k += norecollits;
        return k;
    }
}
