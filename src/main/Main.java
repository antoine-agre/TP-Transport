package main;

import donnees.Station;

import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Station> reseau = Itineraire.donneesTest();
        System.out.println(reseau);
        Itineraire.trouveChemin(reseau, reseau.get(0), reseau.get(4), LocalTime.of(8,2));

    }
}
