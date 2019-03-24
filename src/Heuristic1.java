package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

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



        //Anem a garantir que cap conductor porta mes de 2 persones
        int vegades_mes_de2 = 0;

        for (int i=0; i < e.size(); ++i)
        {
            ArrayList<Integer> a = m.getPassangers(i);
            int counter = 0;
            HashSet<Integer> aux = new HashSet<>();

            for (Integer b : a)
            {
                if (! aux.contains(b))  //agafem a una persona
                {
                    ++counter;
                    aux.add(b);
                }
                else {  //hem deixat a una persona
                    aux.remove(b);
                }

                if (counter > 2) ++vegades_mes_de2;
            }
        }
        k+= vegades_mes_de2;


        k += norecollits;
        return k;
    }
}
