package main;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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


    /**
     * Crée des données manuellement pour tester l'algorithme.
     * Obsolète une fois les parseurs fonctionnels.
     */
    public static ArrayList<Station> donneesTest(){
        ArrayList<Station> listeStations = new ArrayList<Station>();

        Station A = new Station("A");
        Station B = new Station("B");
        Station C = new Station("C");
        Station D = new Station("D");
        Station E = new Station("E");

        //A
        Trajet AtoB = new Trajet(A, B, MoyenTransport.TRAIN, 3);
        Trajet AtoC = new Trajet(A, C, MoyenTransport.TRAM, 2);
        Trajet AtoD = new Trajet(A, D, MoyenTransport.TRAIN, 4);

        //B
        Trajet BtoA = new Trajet(B, A, MoyenTransport.TRAIN, 3);
        Trajet BtoC = new Trajet(B, C, MoyenTransport.TRAM, 2);
        Trajet BtoE = new Trajet(B, E, MoyenTransport.TRAIN, 3);

        //C
        Trajet CtoA = new Trajet(C, A, MoyenTransport.TRAM, 2);
        Trajet CtoB = new Trajet(C, B, MoyenTransport.TRAM, 2);
        Trajet CtoE = new Trajet(C, E, MoyenTransport.TRAM, 6);

        //D
        Trajet DtoA = new Trajet(D, A, MoyenTransport.TRAIN, 4);
        Trajet DtoE = new Trajet(D, E, MoyenTransport.TRAIN, 2);

        //E
        Trajet EtoB = new Trajet(E, B, MoyenTransport.TRAIN, 3);
        Trajet EtoC = new Trajet(E, C, MoyenTransport.TRAM, 6);
        Trajet EtoD = new Trajet(E, D, MoyenTransport.TRAIN, 2);

        //population horaires
        //trains
        for(int i = 0; i <= 30; i += 5){ //0 à 30 minutes
            AtoB.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            BtoA.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            BtoE.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            EtoB.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            EtoD.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            DtoE.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            AtoD.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            DtoA.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
        }

        //trams
        for(int i = 0; i <= 30; i += 2){ //0 à 30 minutes
            AtoC.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            CtoA.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            BtoC.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            CtoB.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            EtoC.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
            CtoE.addHoraire(LocalTime.of(8, 0).plusMinutes(i));
        }

        //trajets dans stations
        A.addTrajet(AtoB);
        A.addTrajet(AtoC);
        A.addTrajet(AtoD);

        B.addTrajet(BtoA);
        B.addTrajet(BtoC);
        B.addTrajet(BtoE);

        C.addTrajet(CtoA);
        C.addTrajet(CtoB);
        C.addTrajet(CtoE);

        D.addTrajet(DtoA);
        D.addTrajet(DtoE);

        E.addTrajet(EtoB);
        E.addTrajet(EtoC);
        E.addTrajet(EtoD);

        //liste
        listeStations.add(A);
        listeStations.add(B);
        listeStations.add(C);
        listeStations.add(D);
        listeStations.add(E);

        return listeStations;
    }

}
