package src;

import IA.Comparticion.Usuarios;
import src.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        a.rmPerson(2, 0);

        //Map b = new Map(a);
        /*
        System.out.println("Anem a fer un remove de una persona");
        //a.rmPerson(140, 0);
        System.out.println("Ara anem a afegir una persona a un altre");
        a.addPerson(140, 0);
        System.out.println("Ara anem a fer un canvi de cotxe");
        a.swapCar(140, 141, 0, 1);
        System.out.println("Tornem a fer el canvi de cotxe");
        a.swapCar(141, 140, 0, 1);
        */
        MapSuccesors c = new MapSuccesors();
        List d = c.getSuccessors(a);
        System.out.println("Acabat");
    }


    /**
     * Private methods
     **/
    private static void fillDrivers() {
        for (int i = 0; i < n; ++i)
            isConductor.add(nouUsuaris.get(i).isConductor());
    }

}