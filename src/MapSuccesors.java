package src;

import IA.Centrals.Representacio;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import com.sun.tools.corba.se.idl.InterfaceGen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;

import static src.Main.*;


/** This successor function does not generate the possibility to delete a driver **/
public class MapSuccesors  implements SuccessorFunction{

    public List getSuccessors(Object state)
    {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map map = (Map)state;

        //SWAP DRIVERS
        for(int i=0; i < map.getEstatConductors().size(); ++i)
        {
            for (int j=i+1; j < map.getEstatConductors().size();++j)
            {
                Map aux = new Map(map); // copy of map
                aux.changeDrivers(i,j);
                retVal.add(new Successor(new String("Canviem els passatgers dels conductors "+i+" i el "+j), aux));
            }
        }

        //SEND TO OTHER CAR (REMOVE+ADD)
        for(int i=0; i < map.getEstatConductors().size();++i)
        {
            HashSet<Integer> p = map.getPassangersNotRepeated(i);
            for (Integer a: p)
            {
                for (int j=0; j < map.getEstatConductors().size();++j)
                {
                    Map aux = new Map(map); // copy of map
                    aux.rmPerson(a,i);
                    aux.addPerson(a,j);
                    retVal.add(new Successor(new String("Canviem la persona "+a+ " del cotxe "+i+" al cotxe "+j), aux));
                }
            }
        }


        //ADD PERSON
        ArrayList<Boolean> estaRecullit = map.getEstaRecullit();
        for (int i=0; i < estaRecullit.size(); ++i)
        {
            if (!estaRecullit.get(i))   //we only need to check that he has not been picked up
            {
                //when we remove a car, do the index keep working????
                for(int c=0; c < map.getEstatConductors().size(); ++c)
                {
                    Map aux = new Map(map); // copy of map
                    aux.setEstaRecullit(i,true);
                    aux.addPerson(i,c);
                    retVal.add(new Successor(new String("Afegim una persona al cotxe "+c), aux));
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


        //SWAP CAR
        boolean hanFetSwap[][] = new boolean[n][n];

        for (int i=0; i < map.getEstatConductors().size(); ++i)
        {
            HashSet<Integer> p1 = uniquePassengers.get(i);
            for(int j=i+1; j < map.getEstatConductors().size(); ++j)
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
                        retVal.add(new Successor(new String("Fem swap order del cotxe "+c+ " dels passatgers a les posicions "+j+" i "+k), aux));
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
