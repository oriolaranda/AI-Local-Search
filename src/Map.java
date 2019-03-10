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
    }


    /** Private methods **/
    private void fillDrivers() {
        for (int i=0; i < n; ++i)
            isConductor.set(i,nouUsuaris.get(i).isConductor());
    }


    private void inicializeEstatConductors() {
        for (int i=0; i < n; ++i) {
            ArrayList<Integer> a = new ArrayList<>();
            Pair c = new Pair(0, a);
            estatConductors.set(i,c);
        }
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


    public int getKilometers(int indexDriver)
    {
        return (int) estatConductors.get(indexDriver).getFirst();
    }


    public ArrayList getPassangers (int indexDriver)
    {
        return (ArrayList<Integer>) estatConductors.get(indexDriver).getSecond();
    }




    /** Operator Swap Order of p and q in the same car **/
    public void swapOrder(int p, int q){

    }

    /** Operator Swap Car between p and q **/
    public void swapCar(int p, int q) {

    }

    /** Operator Add Person p in car c **/
    public void addPerson(int p, int c){

    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {

    }


    public boolean isGoal(){
        return true;
    }
}
