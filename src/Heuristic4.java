package src;

import aima.search.framework.HeuristicFunction;
import aima.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

import static src.Main.m;

/**
 * This heuristic tries to minimize both distance and number of drivers. We will start from heuristic 3 and add the
 * ponderation of the number of possible drivers that are passangers.
 * <p>
 * We shall ponderate here more the distance than the drivers
 */

//WORKS WELL WITH HILL CLIMBING

public class Heuristic4 implements HeuristicFunction {
    @Override
    public int getHeuristicValue(Object n) {
        Map map = (Map) n;
        ArrayList<Pair> e = map.getEstatConductors();
        int total = 0;


        //WE TRY TO MINIMIZE ALL THE DISTANCES
        //We panalize for the distance since we wanna minimize it, but we penalize it much more if we exceed it.
        int dist;
        for (Pair a : e) {
            dist = (Integer) ((Pair) a.getFirst()).getFirst();
            if (dist > 300) total += (dist - 300) * 400;  //We panalize the exceed of the distance.

            total += Math.min(300, dist);
        }

        //WE PANALIZE THE NON PICKED UP PASSANGERS
        ArrayList<Boolean> b = map.getEstaRecullit();
        for (Boolean r : b)
            if (!r)
                total += 850;  //we wanna make sure everyone has been picked up


        //WE TRY TO MINIMIZE THE NUMBER OF DRIVERS
        int p = m - e.size(); //number of drivers that are passangers
        total -= 1400 * p;


        //GUARANTEE WE DO NOT CARRY MORE THAN 2 PEOPLE AT THE TIME
        int vegades_mes_de2 = 0;

        for (int i = 0; i < e.size(); ++i) {
            ArrayList<Integer> a = map.getPassangers(i);
            int counter = 0;
            HashSet<Integer> aux = new HashSet<>();

            for (Integer c : a) {
                if (!aux.contains(c))  //agafem a una persona
                {
                    ++counter;
                    aux.add(c);
                } else {  //hem deixat a una persona
                    aux.remove(c);
                }

                if (counter > 2) ++vegades_mes_de2;
            }
        }
        total += vegades_mes_de2 * 400;


        int counter = 0;
        //WE WANNA PUSH PEOPLE NOT TO HAVE ANY PASSANGER
        for (int i = 0; i < map.getEstatConductors().size(); ++i) {
            ArrayList<Integer> a = map.getPassangers(i);
            if (a.size() == 0) {
                ++counter;
            }
        }
        total -= counter * 500;

        return (total / 10);
    }

}
