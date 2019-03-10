package src;

import java.util.ArrayList;
import aima.util.Pair;

public class Map {
    /** Private atributes of the class **/
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecollit = new ArrayList<>();

    /** CONSTRUCTOR **/
    public Map(){
        //inicialitzem els usuaris
    }

    /** PRIVATE METHODS **/
    private boolean isCarEmpty(int c){
        return false;
    }


    /** PUBLIC METHODS **/

    /** Operator Swap Order of p and q in the same car **/
    public boolean swapOrder(int p, int q){

        return false;
    }

    /** Operator Swap Car between p and q **/
    public boolean swapCar(int p, int q) {
        return false;
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
