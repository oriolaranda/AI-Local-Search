package src;

import IA.Centrals.Representacio;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

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
                    Map aux = (Map)map; //implement the copy
                    aux.setEstaRecullit(i,true);
                    map.addPerson(i,c);
                    retVal.add(aux);
                }
            }
        }

        //REMOVE PERSON
        for (int i=0; i < m; ++i)
        {
            ArrayList<Integer> passanger = map.getPassangers(i);
            HashSet<Integer> p = new HashSet<>();
            p.addAll(passanger);

            Iterator it = p.iterator();
            while (it.hasNext())
            {
               Map aux = (Map)map;
               aux.rmPerson(it.next());
            }



        }




        return retVal;
    }
}
