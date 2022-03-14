package main;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class Itineraire {

    /*Classe "statique" : finale, constructeur private, TOUS les membres static*/

    private Itineraire() {} //Empêche instanciation


    /**
     * Algorithme de Dijkstra adapté au cas d'un réseau de transport avec listes d'horaires.
     * C'est la fonction principale que l'on voudra utiliser dans le programme.
     */
    protected static void trouveChemin(){

        PriorityQueue<Ticket> queue = new PriorityQueue<Ticket>(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) { //o1 - o2
                return o1.temps.compareTo(o2.temps);
            }
        });

    }




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
