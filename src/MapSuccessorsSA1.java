package src;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static src.Main.isConductor;
import static src.Main.n;

/** This is equivalent to MapSuccessors but for Simulated Annealing **/
/** We need to make sure we only choose one value **/


public class MapSuccessorsSA1 {
        public List getSuccessors(Object state)
        {
            ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
            Map map = (Map)state;

            //We don't wanna generate all possibilities but one using random function
            Random myRandom=new Random();
            int option = myRandom.nextInt(4);
            int c,p1,p2;
            Map aux;

            switch (option) {

                case(0):
                    //ADD PERSON
                    ArrayList<Boolean> estaRecullit = map.getEstaRecullit();
                    int person = myRandom.nextInt(estaRecullit.size());


                    if (!estaRecullit.get(person) && !isConductor.get(person))
                    {
                        c = myRandom.nextInt(map.getEstatConductors().size());
                            aux = new Map(map); // copy of map
                            aux.setEstaRecullit(person,true);
                            aux.addPerson(person,c);
                            retVal.add(new Successor(new String("Afegim una persona al cotxe"+c), aux));
                    }
                    else {  //with a certain probability we accept this

                    }

                break;


                case(1):
                    //REMOVE PERSON
                    c = myRandom.nextInt(map.getEstatConductors().size());
                    int k = myRandom.nextInt(map.getPassangers(c).size());  //i get a random passanger

                    aux = new Map(map);
                    aux.rmPerson(k,c);
                    retVal.add(new Successor(new String("Borrem una persona del cotxe"+c), aux));

                break;


                case(2):
                    //SWAP CAR
                    int c1 = myRandom.nextInt(map.getEstatConductors().size());
                    p1 = myRandom.nextInt(map.getPassangers(c1).size());

                    int c2 = myRandom.nextInt(map.getEstatConductors().size());
                    p2 = myRandom.nextInt(map.getPassangers(c2).size());

                    aux = new Map(map);
                    aux.swapCar(p1,p2,c1,c2);
                    retVal.add(new Successor(new String("Fem swap de les persones "+p1+" "+p2+" dels cotxes "+c1 + " i "+c2), aux));

                break;

                default:
                    //FALTARA EL SWAP ORDER
                    c = myRandom.nextInt(map.getEstatConductors().size());

                    p1 = myRandom.nextInt(map.getPassangers(c).size()*2);
                    p2 = myRandom.nextInt(map.getPassangers(c).size()*2);

                    aux = new Map(map); // copy of map

                    if (aux.swapOrder(p1,p2,c))   //we enter only if the operation is not between the same person
                    {
                        retVal.add(new Successor(new String("Fem swap order del cotxe "+c+ " dels passatgers "+p1+" i "+p2), aux));
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
