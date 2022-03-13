package main;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

import java.util.ArrayList;

public final class Itineraire {

    /*Classe "statique" : finale, constructeur private, TOUS les membres static*/

    private Itineraire() {} //Empêche instanciation

    /*public static void donneesTest(){
        ArrayList<Station> listeStations = new ArrayList<Station>();

        Station foret = new Station("Forêt");
        Station gare = new Station("Gare");
        Station centreVille = new Station("Centre-Ville");
        Station ecole = new Station("Ecole");
        Station campus = new Station("Campus");
        Station musee = new Station("Musée");
        Station zoneIndustrielle = new Station("Zone Industrielle");

        foret.addTrajet(new Trajet(foret, gare, MoyenTransport.TRAIN, 4));
        foret.addTrajet(new Trajet(foret, centreVille, MoyenTransport.TRAIN, 7));
        foret.addTrajet(new Trajet(foret, musee, MoyenTransport.TRAM, 3));

        listeStations.add(new Station("Forêt"));
        listeStations.add(new Station("Gare"));
        listeStations.add(new Station("Centre-Ville"));
        listeStations.add(new Station("Ecole"));
        listeStations.add(new Station("Campus"));
        listeStations.add(new Station("Musée"));
        listeStations.add(new Station("Zone Industrielle"));
    }*/

}
