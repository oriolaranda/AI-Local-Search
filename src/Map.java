package src;

import java.util.ArrayList;

import IA.Comparticion.*;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/

    private Integer n = 100;
    private Integer m = 50;
    private Integer seed = 2;
    private ArrayList<Pair> estatConductors = new ArrayList<>(n);
    private ArrayList<Boolean> estaRecullit = new ArrayList<>(n);
    private ArrayList<Boolean> isConductor = new ArrayList<>(n);
    private Usuarios nouUsuaris = new Usuarios(n,m, seed);

    /** Constructor **/
    public Map(){
        fillDrivers ();
    }


    /** Private methods **/
    private void fillDrivers() {
        for (int i=0; i < n; ++i)
            isConductor.set(i,nouUsuaris.get(i).isConductor());
    }
    private void initializeEstaRecollit() {
        for (int i = 0; i < n; ++i) estaRecullit.set(i, Boolean.FALSE);
    }

    private boolean isCarFull(int c){
        return false;
    }

    /** Public methods **/


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

        if (){

        }

    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {

    }


    public boolean isGoal(){
        return true;
    }
}
