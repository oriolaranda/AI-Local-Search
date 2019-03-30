package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

import static src.Main.m;


/** This heuristic takes also in consideration the distance of the drivers that do pass the restriccions but it counts less.
 * This way we can ensure that the distance is minimized and we still strongly push to the solution world.
 */

public class Heuristic3 implements HeuristicFunction{
    @Override
    public int getHeuristicValue(Object n) {
        Map map = (Map)n;
        ArrayList<Pair> e = map.getEstatConductors();
        int total = 0;


        //WE TRY TO MINIMIZE ALL THE DISTANCES
        int dist;
        for (Pair a : e)
        {
            dist = (Integer) ((Pair) a.getFirst()).getFirst();
            if (dist > 300) total += (dist-300)*35;  //We panalize the exceed of the distance.

            total += Math.min(300,dist)*2;
        }


        //EVERYONE HAS BEEN PICKED UP
        ArrayList<Boolean> b = map.getEstaRecullit();
        for (Boolean r : b)
            if (!r)
                total += 500;  //we wanna make sure everyone has been picked up


        //NO PORTEM MES DE 2 PERSONES
        int vegades_mes_de2 = 0;

        for (int i=0; i < e.size(); ++i)
        {
            ArrayList<Integer> a = map.getPassangers(i);
            int counter = 0;
            HashSet<Integer> aux = new HashSet<>();

            for (Integer c : a)
            {
                if (! aux.contains(c))  //agafem a una persona
                {
                    ++counter;
                    aux.add(c);
                }
                else {  //hem deixat a una persona
                    aux.remove(c);
                }

                if (counter > 2) ++vegades_mes_de2;
            }
        }
        total+= vegades_mes_de2*30;

        return total/10;
    }


}
