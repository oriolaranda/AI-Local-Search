package src;

import java.util.ArrayList;
import java.lang.Math;

import IA.Comparticion.*;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/
    private int n = 100;
    private int m = 50;
    private int seed = 2;


    // ArrayList<Pair(Integer, ArrayList<Integer>)
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecullit = new ArrayList<>();
    private ArrayList<Boolean> isConductor = new ArrayList<>();
    private Usuarios nouUsuaris = new Usuarios(n,m, seed);

    /** Constructor **/

    public Map(){
        fillDrivers ();
        inicializeEstatConductors();
        initializeEstaRecollit();
    }


    /** Private methods **/
    private void fillDrivers() {
        for (int i=0; i < n; ++i)
            this.isConductor.add(nouUsuaris.get(i).isConductor());
    }


    private void initializeEstaRecollit() {
        for (int i = 0; i < n; ++i)
            estaRecullit.add(Boolean.FALSE);
    }


    private void inicializeEstatConductors() {
        for (int i=0; i < n; ++i) {
            if (isConductor.get(i)) //if it enters, then we have a new driver
            {
                Pair a = new Pair(0,i); //Pair of the distance and then the index of the driver
                ArrayList<Pair> b = new ArrayList<>();  //order of the people that the car has brought
                Pair c = new Pair(a, b);
                estatConductors.add(c);
            }
        }
    }


    private boolean isCarFull(int c){
        return numberPassengers(c) == 2;
    }

    private boolean isCarEmpty(int c){
        return numberPassengers(c) == 0;
    }

    /** Public methods **/


    /** Getters of the functions **/
    public boolean estaRecullit(int indexPassanger)
    {
        return estaRecullit.get(indexPassanger);
    }

    public boolean isConductor(int indexPerson)
    {
        return isConductor.get(indexPerson);
    }


    public int getDistance(int indexDriver)
    {
        Pair a = (Pair)estatConductors.get(indexDriver).getFirst(); //pair con la distancia i el numero de personas
        return (int)a.getFirst();
    }


    public int getIndexDriver(int index)
    {
        Pair a = (Pair)estatConductors.get(index).getFirst(); //pair con la distancia i el numero de personas
        return (int)a.getSecond();
    }

    public ArrayList<Integer> getPassangers (int indexDriver)
    {
        return (ArrayList<Integer>) estatConductors.get(indexDriver).getSecond();
    }


    /** Print the information **/
    public void printRecullits()
    {
        for(int i=0; i < n;++i)
            System.out.println(estaRecullit.get(i));
    }

    public void printIsConductor()
    {
        for(int i=0; i < n;++i)
            System.out.println(isConductor.get(i));
    }


    /**Function to find distance from a given passanger assignation **/
    public int calculateDistance(int j, ArrayList<Integer> passangers)
    {
        int indexPerson = getIndexDriver(j);
        ArrayList<Boolean> recullits = new ArrayList<>(n);

        for (int i=0; i < n; ++i)
            recullits.add(false);

        Usuario u = nouUsuaris.get(indexPerson);
        int current_x = u.getCoordOrigenX();
        int current_y = u.getCoordOrigenY();

        int distance = 0;
        for (int i=0; i < passangers.size();++i)
        {
            int indexPassanger = passangers.get(i);
            Usuario passanger = nouUsuaris.get(indexPassanger);

            int goTo_x;
            int goTo_y;
            if (!recullits.get(indexPassanger)) //if it enters, means we have to go to the origin coordinates
            {
                recullits.set(indexPassanger, true);  //we negate so if not picked-up yet, it is n
                goTo_x = passanger.getCoordOrigenX();
                goTo_y = passanger.getCoordOrigenY();
            }
            else {
                goTo_x = passanger.getCoordDestinoX();
                goTo_y = passanger.getCoordDestinoY();
            }

            distance += Math.abs(current_x-goTo_x)+ Math.abs(current_y-goTo_y);
            current_x = goTo_x;
            current_y = goTo_y;
        }

        distance += Math.abs(current_x-u.getCoordDestinoX())+ Math.abs(current_y-u.getCoordDestinoY());

        return distance;
    }



    /**Operators **/

    /** Operator Swap Order of p1 and p2 in the same car c**/
    public void swapOrder(int c){

        if (numberPassengers(c) > 1) {
            int p1 = getPassangers(c).get(0);
            int p2 = getPassangers(c).get(1);
            getPassangers(c).set(0,p2);
            getPassangers(c).set(1,p1);
        }
    }

    /** Operator Swap Car between p1 and p2 **/
    public void swapCar(int p1, int p2, int c1, int c2) {

        int q1 = getPassangers(c1).indexOf(p1); //position of p1 in c1
        int q2 = getPassangers(c2).indexOf(p2); //position of p2 in c2
        getPassangers(c1).set(q1,p2);
        getPassangers(c2).set(q2,p1);
        q1 = getPassangers(c1).indexOf(p1);
        q2 = getPassangers(c2).indexOf(p2);
        getPassangers(c1).set(q1,p2);
        getPassangers(c2).set(q2,p1);
    }

    /** Operator Add Person p in car c **/
    public void addPerson(int p, int c){
        if (!isCarFull(c)){
            Object o = estatConductors.get(c).getSecond();
            ArrayList<Integer> i = (ArrayList<Integer>) o;
            i.add(p);
            i.add(p);
            Pair m = new Pair(estatConductors.get(c).getFirst(),i);
            estatConductors.set(c,m);
        }


    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {

    }


    public boolean isGoal(){
        return true;
    }
}
