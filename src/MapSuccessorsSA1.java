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

            switch (option) {

                case(0):
                    //ADD PERSON
                    ArrayList<Boolean> estaRecullit = map.getEstaRecullit();
                    int person = myRandom.nextInt(estaRecullit.size());


                    if (!estaRecullit.get(person) && !isConductor.get(person))
                    {
                        int c = myRandom.nextInt(map.getEstatConductors().size());
                            Map aux = new Map(map); // copy of map
                            aux.setEstaRecullit(person,true);
                            aux.addPerson(person,c);
                            retVal.add(new Successor(new String("Afegim una persona al cotxe"+c), aux));
                    }
                    else {  //with a certain probability we accept this

                    }

                break;


                case(1):
                    //REMOVE PERSON
                    int c = myRandom.nextInt(map.getEstatConductors().size());
                    int k = myRandom.nextInt(map.getPassangers(c).size());  //i get a random passanger

                    Map aux = new Map(map);
                    aux.rmPerson(k,c);
                    retVal.add(new Successor(new String("Borrem una persona del cotxe"+c), aux));

                break;


                case(2):
                    //SWAP CAR
                    int c1 = myRandom.nextInt(map.getEstatConductors().size());
                    int p1 = myRandom.nextInt(map.getPassangers(c1).size());

                    int c2 = myRandom.nextInt(map.getEstatConductors().size());
                    int p2 = myRandom.nextInt(map.getPassangers(c2).size());

                    Map aux2 = new Map(map);
                    aux2.swapCar(p1,p2,c1,c2);
                    retVal.add(new Successor(new String("Fem swap de les persones "+p1+" "+p2+" dels cotxes "+c1 + " i "+c2), aux2));

                                }
                            }
                        }
                    }
                break;

                default:
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

}
