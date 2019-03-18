package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.Math;
import java.util.HashSet;
import java.util.Random;


import IA.Comparticion.*;
import aima.util.Pair;
import src.Main;

import static src.Main.n;
import static src.Main.m;
import static src.Main.nouUsuaris;
import static src.Main.isConductor;


public class Map {
    /** Private atributes of the class **/

    // ArrayList<Pair(Pair(Integer,Integer), ArrayList<Integer>)
    private ArrayList<Pair> estatConductors = new ArrayList<>();
    private ArrayList<Boolean> estaRecullit = new ArrayList<>();


    /** Constructor **/
    public Map(){
        inicializeEstatConductors();
        initializeEstaRecollit();
        assignacioRandom();
    }


    /** Copy constructor **/
    public Map(Map map) {
        copyOfEstatConductors((ArrayList<Pair>) map.getEstatConductors());
        this.estaRecullit = (ArrayList<Boolean>) map.getEstaRecullit().clone(); //aquest clone funciona
    }



    /** Auxiliar functions **/

    //Function that clones the estatConductors variable
    private void copyOfEstatConductors (ArrayList<Pair> e)
    {
        this.estatConductors = new ArrayList<>();
        for (int i=0; i < e.size(); ++i)
        {
            ArrayList<Integer> a = (ArrayList)e.get(i).getSecond();
            ArrayList<Integer> b = (ArrayList<Integer>) a.clone();  //clone the passangers array
            this.estatConductors.add(new Pair(e.get(i).getFirst(),b));
        }

    }


    private void initializeEstaRecollit()
    {
        estaRecullit.addAll(isConductor);   //we inicialize all the drivers to true since they have already been picked-up
    }


    private void inicializeEstatConductors()
    {
        for (int i=0; i < n; ++i) {
            if (isConductor.get(i)) //if it enters, then we have a new driver
            {
                Pair a = new Pair(0,i); //Pair of the distance and then the index of the driver
                ArrayList<Pair> b = new ArrayList<>();  //order of the people that the car has brought
                Pair c = new Pair(a, b);
                estatConductors.add(c);
            }

        }
    }



    private boolean isCarEmpty(int c){
        ArrayList<Integer> a = (ArrayList<Integer>) estatConductors.get(c).getSecond();
        return a.size()==0;
    }



    /** Public methods **/


    /** Getters of the functions **/

    public int getDistance(int indexDriver)
    {
        Pair a = (Pair)estatConductors.get(indexDriver).getFirst(); //pair con la distancia i el numero de personas
        return (int)a.getFirst();
    }


    public int getIndexDriver(int index)
    {
        Pair a = (Pair)estatConductors.get(index).getFirst(); //pair con la distancia i el numero de personas
        return (int)a.getSecond();
    }


    public void setEstaRecullit(int index, boolean newState)
    {
        estaRecullit.set(index,newState);
    }


    //Retruns a set with all the passangers that are taken by a driver
    public HashSet<Integer> getPassangersNotRepeated (int indexDriver)
    {
        return new HashSet<>(getPassangers(indexDriver));
    }



    public ArrayList<Integer> getPassangers (int indexDriver)
    {
        return (ArrayList<Integer>) estatConductors.get(indexDriver).getSecond();
    }

    public ArrayList<Boolean> getEstaRecullit() {
        return estaRecullit;
    }



    public ArrayList<Pair> getEstatConductors() {
        return estatConductors;
    }


    /** Print the information **/
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


    /** Setters **/
    private void setDistance(int indexDriver, int newDistance)
    {
        ArrayList<Integer> a = (ArrayList<Integer>) estatConductors.get(indexDriver).getSecond();
        int id = (Integer)((Pair) estatConductors.get(indexDriver).getFirst()).getSecond();
        estatConductors.set(indexDriver,new Pair(new Pair(newDistance,id),a));
    }


    /** Changes the info of one car **/
    private void changeInfoCar(int j, int newDistance, ArrayList<Integer> a)
    {
        int id = (Integer)((Pair) estatConductors.get(j).getFirst()).getSecond();
        estatConductors.set(j,new Pair(new Pair(newDistance,id),a));
    }



    /**Function to find distance from a given passanger assignation **/
    /** The calculus will be made in hundred meters units, so there is a maximum of 300 per passenger **/
    public int calculateDistance(int indexCar, ArrayList<Integer> passangers)
    {
        int indexPerson = getIndexDriver(indexCar);
        ArrayList<Boolean> recullits = new ArrayList<>(n);

        for (int i=0; i < n; ++i)
            recullits.add(false);

        Usuario u = nouUsuaris.get(indexPerson);
        int current_x = u.getCoordOrigenX();
        int current_y = u.getCoordOrigenY();


        int distance = 0;
        for (int i=0; i < passangers.size();++i)
        {
            int indexPassanger = passangers.get(i);
            Usuario passanger = nouUsuaris.get(indexPassanger);

            int goTo_x;
            int goTo_y;
            if (!recullits.get(indexPassanger)) //if it enters, means we have to go to the origin coordinates
            {
                recullits.set(indexPassanger, true);  //we negate so if not picked-up yet, it is n
                goTo_x = passanger.getCoordOrigenX();
                goTo_y = passanger.getCoordOrigenY();
            }
            else {
                goTo_x = passanger.getCoordDestinoX();
                goTo_y = passanger.getCoordDestinoY();
            }

            distance += Math.abs(current_x-goTo_x)+ Math.abs(current_y-goTo_y);
            current_x = goTo_x;
            current_y = goTo_y;
        }

        distance += Math.abs(current_x-u.getCoordDestinoX())+ Math.abs(current_y-u.getCoordDestinoY());

        return distance;
    }



    /**Operators **/


    /** Operator Swap Order of p1 and p2 in the same car c**/
    public boolean swapOrder(int i, int j, int c){

        ArrayList a = (ArrayList)estatConductors.get(c).getSecond();
        int a1 = (Integer)a.get(i);
        int b1 = (Integer)a.get(j);
        if(a1 == b1) return false;
        a.set(j,a1);
        a.set(i,b1);
        int k = calculateDistance(c,a);
        Pair p1 = new Pair(k,c);
        Pair p = new Pair(p1,a);
        estatConductors.set(c,p);
        return true;

    }



    /** Operator Swap Car between p1 in c1 and p2 in c2 **/
    public void swapCar(int p1, int p2, int c1, int c2) {
        int q1 = getPassangers(c1).indexOf(p1); //position of p1 in c1
        int q2 = getPassangers(c2).indexOf(p2); //position of p2 in c2
        if(q1 >= 0 || q2 >= 0) {
            getPassangers(c1).set(q1, p2);
            getPassangers(c2).set(q2, p1);
            q1 = getPassangers(c1).indexOf(p1);
            q2 = getPassangers(c2).indexOf(p2);
            getPassangers(c1).set(q1, p2);
            getPassangers(c2).set(q2, p1);
        }
        int newDist1 = calculateDistance(c1,getPassangers(c1));
        int newDist2 = calculateDistance(c2,getPassangers(c2));
        setDistance(c1,newDist1);
        setDistance(c2,newDist2);
    }

    /** Operator Add Person p in car c **/
    public void addPerson(int p, int c){
        ArrayList<Integer> i = (ArrayList<Integer>) estatConductors.get(c).getSecond();
        i.add(p);
        i.add(p);
        int km = calculateDistance(c,i);

        Pair aux = (Pair)estatConductors.get(c).getFirst();
        Integer id = (Integer)aux.getSecond();
        Pair def = new Pair(new Pair(km, id),i);
        estatConductors.set(c, def);

    }

    /** Operator Remove Person p of car c **/
    public void rmPerson(int p, int c) {
        ArrayList<Integer> i = (ArrayList<Integer>) estatConductors.get(c).getSecond();

        for (int j=i.size()-1; j>=0;--j){
            if(i.get(j) == p) i.remove(j);
        }

        estaRecullit.set(p,false);
        int km = calculateDistance(c,i);
        Pair aux = (Pair)estatConductors.get(c).getFirst();
        Integer id = (Integer)aux.getSecond();
        Pair def = new Pair(new Pair(km, id),i);
        estatConductors.set(c, def);
    }



    public boolean removeDriver(int c)
    {
        if (isCarEmpty(c))
        {
            estaRecullit.set(c, false);
            estatConductors.remove(c);
            return true;
        }
        return false;
    }




    /** CHECK IF FINAL STATE **/

    public boolean isGoal(){
        return true;
    }



    /** FUNCTIONS TO OBTAIN AN INITIAL SOLUTION **/

    /** This first function assigns n/m persons per driver **/
    public void assignacioBasica(){
        int j = 0;  //index of the estatConductors vector. It indicates what car we are going to locate the people to.

        for(int i=0; i<n; ++i){ /** recorrem les N persones **/
            if (j == estatConductors.size()) j = 0;

            if(!isConductor.get(i)){ /** si la persona i es un passetger **/
                estaRecullit.set(i,true);

                ArrayList<Integer> a = (ArrayList<Integer>)estatConductors.get(j).getSecond() ;
                a.add(i);   //one for the pick-up
                a.add(i);   //one for the arrival
                //substituir aixo per setDistance???
                int km = calculateDistance(j,a);
                changeInfoCar(j, km ,a);
                ++j;
            }
        }
    }


    /** This second function assigns the passangers to every driver in a random order **/

    public void assignacioRandom() {
        Random r  = new Random();

        for(int i=0; i<n; ++i){ /** recorrem les N persones **/
            if(!isConductor.get(i)){ /** si la persona i es un passetger **/
                estaRecullit.set(i,true);
                int j = r.nextInt(m);   //we generate a random index of a driver

                ArrayList<Integer> a = (ArrayList<Integer>)estatConductors.get(j).getSecond() ;
                a.add(i);   //one for the pick-up
                a.add(i);   //one for the arrival
                int km = calculateDistance(j,a);
                changeInfoCar(j, km,a);
            }
        }
    }


    /** This thir function **/

    public void assignacioConductorsSols () {
        for (int i=0; i < m;++i) {   //iterate over all drivers to assign their distance
            int km = calculateDistance(i, new ArrayList<Integer>());
            setDistance(i, km);
        }
    }





}
