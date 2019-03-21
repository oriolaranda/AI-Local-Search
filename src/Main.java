package src;

import IA.Comparticion.Usuarios;
import aima.search.framework.*;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.util.Pair;
import src.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import aima.util.Pair;



public class Main {
    public static ArrayList<Boolean> potConduir = new ArrayList<>(); //This must be global
    public static Usuarios nouUsuaris;    //this must be global
    public static int n = 200;
    public static int m = 100;
    public static int seed = 2;


    public static void main(String[] args) {
        //GENERAR CIUTAT
        nouUsuaris = new Usuarios(n, m, seed);
        fillDrivers();

        //GENERAR ESTAT SOLUCIO
        Map m = new Map();
        m.tipusAssignacio(1);   //triem el tipus de solucio inicial que volem


        //GENERAR SOLUCIO FINAL
        triaAlgorisme(m,0,1,3);
    }




    public static void triaAlgorisme(Map m, int algorisme, int funcioSuccesors, int heuristica){
        switch(algorisme){
            case 0:
                MapHillClimbing1(m,funcioSuccesors,heuristica);

            default: MapSimulatedAnnealing1(m,funcioSuccesors,heuristica);
        }
    }


    private static void MapHillClimbing1(Map m,int funcioSuccessors, int heuristica) {
        try {
            Problem problem;
            SuccessorFunction successor;
            HeuristicFunction heuristic;

            switch (heuristica) {
                case 0:
                    heuristic = new Heuristic1();
                    break;
                case 1:
                    heuristic = new Heuristic2();
                    break;

                case 2:
                    heuristic = new Heuristic3();
                    break;

                case 3:
                    heuristic = new Heuristic4();
                    break;

                default:
                    heuristic = new Heuristic5();
                    break;
            }

            switch(funcioSuccessors){
                case 0:
                    successor = new MapSuccesors();
                    break;
                case 1:
                    successor = new MapSuccesors2();
                    break;

                default:
                    successor = new MapSuccesors3();
                    break;
            }
            problem = new Problem(m, successor, new MapGoal(), heuristic);
            Search search = new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem, search);

            System.out.println();
            System.out.println(actionsToString(agent.getActions()));
            System.out.println(instrumentationToString(agent.getInstrumentation()));
            System.out.println(solutionToString((Map) search.getGoalState()));
            System.out.println(checkSolution((Map) search.getGoalState()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void MapSimulatedAnnealing1(Map m, int funcioSuccessors, int heuristica) {
        try {
            Problem problem;
            SuccessorFunction successor;
            HeuristicFunction heuristic;

            switch (heuristica) {
                case 0:
                    heuristic = new Heuristic1();
                    break;
                case 1:
                    heuristic = new Heuristic2();
                    break;

                case 2:
                    heuristic = new Heuristic3();
                    break;

                case 3:
                    heuristic = new Heuristic4();
                    break;

                default:
                    heuristic = new Heuristic5();
                    break;
            }

            switch (funcioSuccessors) {
                case 0:
                    successor = new MapSuccesors();
                    break;
                case 1:
                    successor = new MapSuccesors2();
                    break;

                default:
                    successor = new MapSuccesors3();
                    break;
            }

            problem = new Problem(m, successor, new MapGoal(), heuristic);
            Search search = new SimulatedAnnealingSearch();
            SearchAgent agent = new SearchAgent(problem, search);

            System.out.println();
            System.out.println(actionsToString(agent.getActions()));
            System.out.println(instrumentationToString(agent.getInstrumentation()));
            System.out.println(solutionToString((Map) search.getGoalState()));
            System.out.println(checkSolution((Map) search.getGoalState()));
        }
            catch(Exception e){
                e.printStackTrace();
            }
    }



    /** Auxiliary functions to return the solutions in the correct format **/

    //Useful to print in the UI the solution
    private static String solutionToString(Map a)
    {
        String result = "";
        ArrayList<Pair> b = a.getEstatConductors();
        int total_dist = 0;
        for (Pair c : b) {
            Pair d = (Pair) c.getFirst();
            total_dist += (Integer) d.getFirst();
            result += "El conductor " + d.getSecond() + " fa " + ((Integer)d.getFirst()/10.0) +" km \n";

            ArrayList<Integer> e = (ArrayList<Integer>) c.getSecond();
            if (e.size() > 0) {
                result += "El seu ordre de recollida/arribada de passatgers es ";

                for (Integer p : e)
                    result += p + " ";
                result += "\n\n";
            }
            else result += "Aquest conductor no porta cap passatger \n\n";
        }

        result += "El total de la distance es "+(total_dist/10.0)+" km\n";
        result += "El nombre total de conductors es "+a.getEstatConductors().size()+" sobre els "+m+" possibles";


        return result;
    }


    private static String instrumentationToString(Properties properties) {
        String result = "";
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            result += key + " : " + property+"\n";
        }
        return result;
    }


    private static String actionsToString(List actions) {
        String result = "";
        result += "Hem realitzat "+actions.size()+" accions\n";
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            result += action +"\n";
        }
        return result;
    }


    /**
     * Private methods
     **/
    private static void fillDrivers() {
        for (int i = 0; i < n; ++i)
            potConduir.add(nouUsuaris.get(i).isConductor());
    }


    private static boolean checkSolution(Map a) {
        ArrayList<Pair> b = a.getEstatConductors();
        for (Pair c : b)    //iterate over all drivers
        {
            Pair d = (Pair) c.getFirst();
            if ((Integer) d.getFirst() > 300) {
                System.out.println("Fallen les distancies");
                return false;
            }
        }

        ArrayList<Boolean> c = a.getEstaRecullit();
        for (int i=0; i < c.size(); ++i) {
            Boolean r = c.get(i);
            if (!r) {
                System.out.println("Falla el esta recullit per la persona " + i + " com a minim.");
                return false;
            }
        }

        return true;
    }
}