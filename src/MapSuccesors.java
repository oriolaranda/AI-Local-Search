package src;

import IA.Centrals.Representacio;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.List;

public class MapSuccesors  implements SuccessorFunction{

    public MapSuccesors() {

    }

    public List getSuccessors(Object state) {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map m = (Map)state;

        //ADD PERSON
        ArrayList<Boolean> estaRecullit = m.getEstaRecullit();
        for (int i=0; i < estaRecullit.size(); ++i) {
           // if (!estaRecullit.get(i) && nouUsuaris.)


        }



        return retVal;
    }
}
