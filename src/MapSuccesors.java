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
                    retVal.add(new Successor(new String("Afegim una persona al cotxe"+c), aux));
                }
            }
        }



        ArrayList<HashSet<Integer>> uniquePassengers = passangersFromAllCars(map);


        //REMOVE PERSON
        for (int c=0; c < m; ++c)   //we iterate over all the drivers
        {
            HashSet<Integer> p = uniquePassengers.get(c);

            for (int k : p)
            {
               Map aux = new Map(map);
               aux.rmPerson(k,c);
               retVal.add(new Successor(new String("Borrem una persona del cotxe"+c), aux));
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
                            retVal.add(new Successor(new String("Fem swap de les persones "+k+" "+l+" dels cotxes "+i + " i "+j), aux));
                        }
                    }
                }
            }
        }


        //FALTARA EL SWAP ORDER
        for (int c=0; c < m; ++c)
        {
            HashSet<Integer> p1 = uniquePassengers.get(c);
            for (int j=0; j < p1.size()*2; ++j)
            {
                for (int k=j+1; k < p1.size()*2; ++k)
                {
                    Map aux = new Map(map); // copy of map
                    if (aux.swapOrder(j,k,c))   //we enter only if the operation is not between the same person
                    {
                        retVal.add(new Successor(new String("Fem swap order del cotxe "+c+ " dels passatgers "+j+" i "+k), aux));
                    }
                }
            }
        }
        System.out.println(retVal.size());
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
