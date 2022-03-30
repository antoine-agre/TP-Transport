package main;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * Classe utilitaire fournissant les méthodes pour chercher un trajet pour l'utilisateur.
 */
public final class Itineraire {

    /*Classe "statique" : finale, constructeur private, TOUS les membres static*/

    private Itineraire() {} //Empêche instanciation


    /**
     * Algorithme de Dijkstra adapté au cas d'un réseau de transport avec listes d'horaires.
     * C'est la fonction principale que l'on voudra utiliser dans le programme.
     * Affiche une visualisation du trajet trouvé.
     * @param listeStations réseau dans lequel effectuer la recherche.
     * @param depart la station de départ du trajet.
     * @param arrivee la station d'arrivée du trajet.
     * @param heureDepart l'heure à partir de laquelle l'usager souhaite partir.
     */
    protected static void trouveChemin(ArrayList<Station> listeStations, Station depart, Station arrivee, LocalTime heureDepart){

        ArrayList<Ticket> queue = new ArrayList<Ticket>(); //Tickets en attente de traitement
        ArrayList<Ticket> stash = new ArrayList<Ticket>(); //Tickets visités

        listeStations.remove(depart);
        for(Station s : listeStations){
            queue.add(new Ticket(s, Integer.MAX_VALUE));
        }
        queue.add(new Ticket(depart, 0, heureDepart));
        sortTickets(queue);

        while(queue.stream().anyMatch((t) -> {return t.getStation().equals(arrivee);})){

            //traitement voisins de station de tête
            Ticket tete = queue.remove(0);
            stash.add(tete);

            if(tete.getStation().equals(arrivee)){
                stash.addAll(queue);
                System.out.println(Ticket.chemin(tete, stash));
                return;
            }

            //voisin en cours de traitement
            for (Ticket voisin : queue) {
                if(tete.getStation().getTrajet(voisin.getStation()) == null){continue;}

                //System.out.println("Tete : " + tete.getStation().getNom());
                //System.out.println("Voisin : " + voisin.getStation().getNom());
                Trajet trajet = tete.getStation().getTrajet(voisin.getStation()); //trajet reliant tête à voisin

                //if (trajet == null || tete.getHeureArrivee() == null) {continue;}

                LocalTime prochainHoraire;

                if(tete.getHeureArrivee() == null){continue;}
                if (trajet.getListeHoraires().ceiling(tete.getHeureArrivee()) == null) {
                    prochainHoraire = trajet.getListeHoraires().first();
                } else {
                    prochainHoraire = trajet.getListeHoraires().ceiling(tete.getHeureArrivee());
                }

                //if (prochainHoraire == null) {continue;}
                int attente = (int) Duration.between(tete.getHeureArrivee(), prochainHoraire).toMinutes();

                if (tete.tempsTrajet + attente + trajet.getDuree() < voisin.tempsTrajet) {
                    voisin.update(tete.getStation(),
                            tete.getHeureArrivee().plusMinutes(attente).plusMinutes(trajet.getDuree()), attente + trajet.getDuree());
                }
            }
            sortTickets(queue);
        }

    }

    /**
     * Trie la liste de Ticket passée en paramètre par le temps de trajet minimum trouvé.
     * @param liste la liste à trier.
     */
    static private void sortTickets(ArrayList<Ticket> liste){
        //System.out.println("SORT : liste = " + liste);
        liste.sort((e1, e2) -> Integer.compare(e1.getTempsTrajet(), e2.getTempsTrajet()));
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
