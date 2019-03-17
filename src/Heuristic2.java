package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

public class Heuristic2 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {

        Map m = (Map)n;
        ArrayList<Pair> e = m.getEstatConductors();
        int sumatotal = 0;
        int k = 0;
        for (int i = 0; i < e.size(); ++i){ //Iterate over every driver
            int km = m.getDistance(i);  //we get the distance of this driver
            if (km > 300) k += 50;
            sumatotal += km;
        }
        ArrayList<Boolean> estaRecollit = m.getEstaRecullit();
        int norecollits = 0;
        for (Boolean r : estaRecollit)
            if(!r)
                ++norecollits;

        k += 50*norecollits;
        return k;
    }
}
