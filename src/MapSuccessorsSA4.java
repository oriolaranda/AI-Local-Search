package src;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import aima.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static src.Main.potConduir;
import static src.Main.n;

/** This is equivalent to MapSuccessors2 but for Simulated Annealing but we can delete any driver that has no passangers, not only after deletion**/
/** We need to make sure we only choose one value **/


public class MapSuccessorsSA4 implements SuccessorFunction{
    public List getSuccessors(Object state)
    {
        ArrayList retVal= new ArrayList();  //we must add all posibilities from a current state to this list
        Map map = (Map)state;

        //We don't wanna generate all possibilities but one using random function
        Random myRandom=new Random();
        boolean found = false;

        while (!found) {

            int option = myRandom.nextInt(6);
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


                case (2):
                    //SWAP CAR
                    int c1 = myRandom.nextInt(map.getEstatConductors().size()); //we get two drivers
                    int c2 = myRandom.nextInt(map.getEstatConductors().size());

                    if (c1 != c2 && map.getPassangers(c1).size() > 0 && map.getPassangers(c2).size() > 0)   //Al cars have at least one passanger
                    {
                        int i1 = myRandom.nextInt(map.getPassangers(c1).size());
                        int i2 = myRandom.nextInt(map.getPassangers(c2).size());

                        p1 = map.getPassangers(c1).get(i1); //we need to pass the actual passanger, not the index
                        p2 = map.getPassangers(c2).get(i2);

                        aux = new Map(map);
                        aux.swapCar(p1, p2, c1, c2);
                        retVal.add(new Successor(new String("Fem swap de les persones " + p1 + " " + p2 + " dels cotxes " + c1 + " i " + c2), aux));
                        found = true;
                    }
                    break;


                case(3):
                    //WE DELETE ONE DRIVER
                    ArrayList<Integer> indexEmptyCars = new ArrayList<>();
                    ArrayList<Pair> a = map.getEstatConductors();


                    for (int i=0; i < a.size(); ++i)    //we iterate over all cars
                    {
                        Pair e = a.get(i);
                        if (((ArrayList<Integer>)e.getSecond()).size() == 0)
                        {
                            indexEmptyCars.add(i);
                        }
                    }

                    if (indexEmptyCars.size() > 0) {
                        c = myRandom.nextInt(indexEmptyCars.size());   //select one driver to delete
                        aux = new Map(map);
                        if (aux.removeDriver(c)) {
                            int indexConductor = (Integer) ((Pair) map.getEstatConductors().get(c).getFirst()).getSecond();
                            retVal.add(new Successor(new String("Hem eliminat el cotxe " + c + " i el conductor " + indexConductor + " ara es passetger"), aux));
                            found = true;
                        }
                    }

                    break;



                case(4):
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
                    break;

                case(5):
                    //SWAP DRIVERS
                    int i = myRandom.nextInt(map.getEstatConductors().size());
                    int j = myRandom.nextInt(map.getEstatConductors().size());
                    if (i != j)
                    {
                        found = true;
                        aux = new Map(map); // copy of map
                        aux.changeDrivers(i,j);
                        retVal.add(new Successor(new String("Canviem els passatgers dels conductors "+i+" i el "+j), aux));
                    }
                    break;


                default:    //SEND PERSON FROM ONE CAR TO THE OTHER
                    //driver inicial
                    i = myRandom.nextInt(map.getEstatConductors().size());
                    ArrayList<Integer> p = map.getPassangers(i);
                    if (p.size() > 0) {
                        //agafem un dels seus passatgers
                        int k = myRandom.nextInt(p.size());
                        Integer b = p.get(k);

                        //diver final
                        j = myRandom.nextInt(map.getEstatConductors().size());

                        if (i != j) {
                            found = true;
                            aux = new Map(map); // copy of map
                            aux.rmPerson(b, i);
                            aux.addPerson(b, j);
                            retVal.add(new Successor(new String("Canviem la persona " + b + " del cotxe " + i + " al cotxe " + j), aux));
                        }
                    }

            }

        }

        return retVal;
    }

}