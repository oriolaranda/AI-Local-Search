package src;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static src.Main.potConduir;
import static src.Main.n;

/** This is equivalent to MapSuccessors3 but for Simulated Annealing. We do not use the operator of swapCar **/
/** We need to make sure we only choose one value **/


public class MapSuccessorsSA3 implements SuccessorFunction{
    public List getSuccessors(Object state)
    {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map map = (Map)state;

        //We don't wanna generate all possibilities but one using random function
        Random myRandom=new Random();
        boolean found = false;

        while (!found) {

            int option = myRandom.nextInt(3);
            int c, p1, p2;
            Map aux;

            switch (option) {

                case (0):
                    //ADD PERSON
                    ArrayList<Boolean> estaRecullit = map.getEstaRecullit();

                    //we get the people who has not yet been picked up
                    ArrayList<Integer> notPickedUp = new ArrayList<>(); //we save the index of the passangers not picked up
                    for (int i=0; i < estaRecullit.size(); ++i) //iterate over all people
                    {
                        if (!estaRecullit.get(i))
                            notPickedUp.add(i);
                    }

                    if (notPickedUp.size() > 0) {
                        int index = myRandom.nextInt(notPickedUp.size());  //we choose a passanger to pick up
                        int person = notPickedUp.get(index);

                        c = myRandom.nextInt(map.getEstatConductors().size());  //we choose a driver to add it to.
                        aux = new Map(map); // copy of map
                        aux.setEstaRecullit(person, true);
                        aux.addPerson(person, c);
                        retVal.add(new Successor(new String("Afegim la persona "+person+" al cotxe" + c), aux));
                        found = true;
                    }
                    break;



                case (1):
                    //REMOVE PERSON
                    c = myRandom.nextInt(map.getEstatConductors().size());

                    if (map.getPassangers(c).size() > 0) {
                        int k = myRandom.nextInt(map.getPassangers(c).size());  //i get a random passanger index
                        k = map.getPassangers(c).get(k);    //we get the passanger

                        aux = new Map(map);
                        aux.rmPerson(k, c);
                        retVal.add(new Successor(new String("Borrem la persona "+k+" del cotxe" + c), aux));
                        found = true;
                    }
                    break;



                default:
                    //SWAP ORDER
                    c = myRandom.nextInt(map.getEstatConductors().size());

                    if (map.getPassangers(c).size() > 0)    //has at least one passanger
                    {
                        p1 = myRandom.nextInt(map.getPassangers(c).size()); //in this case we simply pass the index
                        p2 = myRandom.nextInt(map.getPassangers(c).size());

                        aux = new Map(map); // copy of map

                        if (aux.swapOrder(p1, p2, c))   //we enter only if the operation is not between the same person
                        {
                            retVal.add(new Successor(new String("Fem swap order del cotxe " + c + " dels passatgers " + p1 + " i " + p2), aux));
                            found = true;
                        }
                    }



            }

        }

        return retVal;
    }

}
