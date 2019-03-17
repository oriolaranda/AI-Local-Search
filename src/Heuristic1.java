package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

public class Heuristic1 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {

        Map m = (Map)n;
        ArrayList<Pair> e = m.getEstatConductors();
        int k = 0;
        for (int i = 0; i < e.size(); ++i){
            ArrayList<Integer> a = (ArrayList<Integer>) e.get(i).getSecond();
            int km = m.calculateDistance(i,a);
            if (km > 300) k++;
        }
        ArrayList<Boolean> estaRecollit = m.getEstaRecullit();
        int norecollits = 0;
        for (int i=0; i < estaRecollit.size(); ++i) if(!estaRecollit.get(i)) ++norecollits;
        if (norecollits > 0) k = k + (int)1.5*norecollits;
        return k;
    }
}
