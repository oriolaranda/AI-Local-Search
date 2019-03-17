package src;

import IA.Centrals.Representacio;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

import static src.Main.*;

public class MapSuccesors  implements SuccessorFunction{

    public MapSuccesors() {

    }

    public List getSuccessors(Object state)
    {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map map = (Map)state;


        //ADD PERSON
        ArrayList<Boolean> estaRecullit = map.getEstaRecullit();
        for (int i=0; i < estaRecullit.size(); ++i)
        {
            if (!estaRecullit.get(i) && !isConductor.get(i))
            {
                for(int c=0; c < m; ++c)
                {
                    Map aux = new Map(map); // copy of map
                    aux.setEstaRecullit(i,true);
                    aux.addPerson(i,c);
                    retVal.add(aux);
                }
            }
        }


        ArrayList<HashSet<Integer>> uniquePassengers = passangersFromAllCars(map);


        //REMOVE PERSON
        for (int i=0; i < m; ++i)   //we iterate over all the passangers
        {
            HashSet<Integer> p = uniquePassengers.get(i);

            for (int k : p)
            {
               Map aux = new Map(map);
               aux.rmPerson(k,i);
               retVal.add(aux);
            }
        }


        //SWAP CAR
        boolean hanFetSwap[][] = new boolean[n][n];

        for (int i=0; i < m; ++i)
        {
            HashSet<Integer> p1 = uniquePassengers.get(i);
            for(int j=i+1; j < m; ++j)
            {
                HashSet<Integer> p2 = uniquePassengers.get(j);
                for (int k : p1)    //person k of the first car
                {
                    for (int l: p2) //person l of the second car
                    {
                        //if the swap of these two people has not yet been done
                        if (!hanFetSwap[i][j] && !hanFetSwap[i][j])
                        {
                            hanFetSwap[i][j] = true;
                            hanFetSwap[j][j] = true;
                            Map aux = new Map(map);
                            aux.swapCar(k, l, i, j);
                            retVal.add(aux);
                        }
                    }
                }
            }
        }



        //FALTARA EL SWAP ORDER
        for (int c=0; c < m; ++c)
        {
            HashSet<Integer> p1 = uniquePassengers.get(i);
            boolean SwapOrderDone[][] = new boolean[p1.size()][p1.size()]; //to avoid doing unnecessary swaps

            for (int j=0; j < p1.size(); ++j)
            {
                for (int k=j+1; k < p1.size(); ++k)
                {
                    Map aux = new Map(map); // copy of map
                    if (aux.swapOrder(j,k,c))   //we enter only if the operation is not between the same person
                    {
                        retVal.add(aux);
                    }
                    retVal.add(aux);
                }
            }
        }

        return retVal;
    }



    private ArrayList<HashSet<Integer>> passangersFromAllCars (Map currentState)
    {
        ArrayList<HashSet<Integer>> a = new ArrayList<>();
        for (int c=0; c < m; ++c)
            a.add(currentState.getPassangersNotRepeated(c));

        return a;
    }


}
