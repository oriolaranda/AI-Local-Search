package src;

import java.util.ArrayList;

import IA.Comparticion.*;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecullit = new ArrayList<>();
    private Usuarios nouUsuaris = new Usuarios(100,50, 2);

    /** Constructor **/
    public Map(){
        ompleEstatConductors ();
    }


    /** Private methods **/
    private void ompleEstatConductors() {
        int n = nouUsuaris.size();
        estaRecullit = new ArrayList<> (n);
        for (int i=0; i < n; ++i)
        {
            estaRecullit.set(i,nouUsuaris.get(i).isConductor());
        }

    }



    /** Public methods **/




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

    /** Useless function **/
    public boolean isGoal(){
        return true;
    }
}
