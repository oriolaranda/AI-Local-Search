package src;

import IA.Centrals.Representacio;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

import static src.Main.isConductor;
import static src.Main.m;
import static src.Main.nouUsuaris;

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
                    map.addPerson(i,c);
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
                        Map aux = new Map(map);
                        map.swapCar(k, l, i, j);
                        retVal.add(aux);
                    }
                }
            }
        }

        //FALTARA EL SWAP ORDER
        for (int i=0; i < m; ++i)
        {
            HashSet<Integer> p1 = uniquePassengers.get(i);
            Iterator it1 = p1.iterator();
            Iterator it2 = p1.iterator();

                while (it1.hasNext())
                {
                    while (it2.hasNext())
                    {
                        Map aux = (Map)map;
                        map.swapOrder((int)it1.next(), (int)it2.next(), i);
                        retVal.add(aux);
                    }
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
