package src;

import java.util.ArrayList;

import IA.Comparticion.*;
import aima.util.Pair;
import java.util.Random;

public class Map {
    /** Private atributes of the class **/
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecullit = new ArrayList<>();
    private Usuarios nouUsuaris = new Usuarios(100,50, 2);

    /** Constructor **/
    public Map(){
        ompleEstatConductors ();
        ompleEsta
    }


    /** Private methods **/
    private ompleEstatConductors() {
        int n = nouUsuaris.size();
        estaRecullit = new ArrayList<boolean> (n);
        for (int i=0; i < n)
    }



    /** Public methods **/


    public boolean isGoal(){
        return true;
    }
}
