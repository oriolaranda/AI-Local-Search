package src;

import java.util.ArrayList;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecollit = new ArrayList<>();

    public Map(){
        //inicialitzem els usuaris
    }

    public boolean isGoal(){
        return true;
    }
}
