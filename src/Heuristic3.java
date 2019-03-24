package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        //We panalize for the distance since we wanna minimize it, but we penalize it much more if we exceed it.
        int dist;
        for (Pair a : e)
        {
            dist = (Integer) ((Pair) a.getFirst()).getFirst();
            if (dist > 300) total += (dist-300)*500;  //We panalize the exceed of the distance.

            total += min(300,dist)*2;
        }

        ArrayList<Boolean> b = map.getEstaRecullit();
        for (Boolean r : b)
            if (!r)
                total += 500;  //we wanna make sure everyone has been picked up

        return total/10;
    }


    private int min (int a, int b)
    {
        if (a < b) return a;
        return b;
    }
}
