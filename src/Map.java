package src;

import java.util.ArrayList;

import IA.Comparticion.*;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/

    private Integer n = 100;
    private Integer m = 50;
    private Integer seed = 2;

    // ArrayList<Pair(Integer, ArrayList<Integer>)
    private ArrayList<Pair> estatConductors = new ArrayList<>(m);
    private ArrayList<Boolean> estaRecullit = new ArrayList<>(n);
    private ArrayList<Boolean> isConductor = new ArrayList<>(n);
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
            isConductor.set(i,nouUsuaris.get(i).isConductor());
    }


    private void initializeEstaRecollit() {
        for (int i = 0; i < n; ++i) estaRecullit.set(i, Boolean.FALSE);
    }


    private void inicializeEstatConductors() {
        for (int i=0; i < n; ++i) {
            Pair a = new Pair(0,0); //Pair of the distance and the number of passanger currently in the car
            ArrayList<Pair> b = new ArrayList<>();  //order of the people that the car has brought
            Pair c = new Pair(a, b);
            estatConductors.set(i,c);
        }
    }


    private boolean isCarFull(int c){
        return numberPassengers(c) == 2;
    }

    private boolean isCarEmpty(int c){
        return numberPassengers(c) == 0;
    }

    /** Public methods **/
    public boolean estaRecullit(int index)
    {
        return estaRecullit.get(index);
    }

    public boolean isConductor(int index)
    {
        return isConductor.get(index);
    }


    public int getDistance(int indexDriver)
    {
        return (Integer)estatConductors.get(indexDriver).getFirst(); //pair con la distancia i el numero de personas

    }

    public ArrayList<Integer> getPassangers (int indexDriver)
    {
        return (ArrayList<Integer>) estatConductors.get(indexDriver).getSecond();
    }



    /** Funcion per imprimir per pantall **/
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
            i.add(c);
            Pair p = new Pair(estatConductors.get(c).getFirst(),i);
            estatConductors.set(c,p);
        }


    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {

    }


    public boolean isGoal(){
        return true;
    }
}
