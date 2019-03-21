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


/** This successor function does not generate the possibility to delete a driver **/
public class MapSuccesors3  implements SuccessorFunction{

    public List getSuccessors(Object state)
    {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map map = (Map)state;


        //ADD PERSON
        ArrayList<Boolean> estaRecullit = map.getEstaRecullit();
        for (int i=0; i < estaRecullit.size(); ++i)
        {
            if (!estaRecullit.get(i))   //we only need to check that he has not been picked up
            {
                for(int c=0; c < map.getEstatConductors().size(); ++c)
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
        for (int c=0; c < map.getEstatConductors().size(); ++c)   //we iterate over all possible drivers
        {
            HashSet<Integer> p = uniquePassengers.get(c);

            for (int k : p)
            {
                Map aux = new Map(map);
                aux.rmPerson(k,c);
                retVal.add(new Successor(new String("Borrem una persona del cotxe"+c), aux));
            }
        }


        //FALTARA EL SWAP ORDER
        for (int c=0; c < map.getEstatConductors().size(); ++c)
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
        return retVal;
    }



    private ArrayList<HashSet<Integer>> passangersFromAllCars (Map currentState)
    {
        Map map = (Map)currentState;
        ArrayList<HashSet<Integer>> a = new ArrayList<>();
        for (int c=0; c < map.getEstatConductors().size(); ++c)
            a.add(currentState.getPassangersNotRepeated(c));

        return a;
    }


}
