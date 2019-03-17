package src;

import IA.Comparticion.Usuarios;
import src.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

private static void MapHillClimbing1(Map m) {

        try {
        Problem problem =  new Problem(m,new MapSuccesors(), new MapGoal());
        Search search =  new DepthLimitedSearch(limit);
        SearchAgent agent = new SearchAgent(problem,search);

        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
        e.printStackTrace();
        }
}

public class Main {

    public static ArrayList<Boolean> isConductor = new ArrayList<>(); //This must be global
    public static Usuarios nouUsuaris;    //this must be global
    public static int n = 100;
    public static int m = 50;
    public static int seed = 2;


    public static void main(String[] args) {
        nouUsuaris = new Usuarios(n, m, seed);
        fillDrivers();
        Map a = new Map();
    }





    /**
     * Private methods
     **/
    private static void fillDrivers() {
        for (int i = 0; i < n; ++i)
            isConductor.add(nouUsuaris.get(i).isConductor());
    }

}