package src;

import java.util.ArrayList;

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
        System.out.println("b");
        fillDrivers ();
        System.out.println("c");
        inicializeEstatConductors();
        System.out.println("d");
        initializeEstaRecollit();
    }


    /** Private methods **/
    private void fillDrivers() {
        for (int i=0; i < n; ++i)
            this.isConductor.add(nouUsuaris.get(i).isConductor());
        System.out.println(this.isConductor.size());
    }


    private void initializeEstaRecollit() {
        for (int i = 0; i < n; ++i)
            estaRecullit.add(Boolean.FALSE);
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
        return false;
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






    /** Operator Swap Order of p and q in the same car **/
    public boolean swapOrder(int c){

        if (isCarFull(c)) {

        }
        return false;
    }

    /** Operator Swap Car between p and q **/
    public boolean swapCar(int p, int q) {
        return false;
    }

    /** Operator Add Person p in car c **/
    public void addPerson(int p, int c){

       // if (!isCarFull(c)){
        //    estatConductors.get(c).getSecond().add(p);
       // }

    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {

    }


    public boolean isGoal(){
        return true;
    }
}
