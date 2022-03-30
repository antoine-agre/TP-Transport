package main;

import donnees.Station;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Représente une station dans la Priority Queue de l'algorithme de Dijkstra.
 * */
public class Ticket {

    /**
     * Station représentée par le Ticket.
     * */
    protected Station station;

    /**
     * Station précédente dans le chemin.
     */
    protected Station origine;

    /**
     * Heure d'arrivée à cette station pour le chemin le plus court,
     * utilisée pour reconstituer le chemin à la fin de l'algorithme.
     */
    protected LocalTime heureArrivee;

    /**
     * Temps, en minutes, pris par le chemin le plus court jusqu'à cette station.
     */
    protected int tempsTrajet;

    public Ticket(Station station){
        this.station = station;
        //this.heure = heure;
        this.tempsTrajet = Integer.MAX_VALUE;
    }

    public Ticket(Station station, int tempsTrajet){
        this.station = station;
        //this.heure = heure;
        this.tempsTrajet = tempsTrajet;
    }

    public Ticket(Station station, int tempsTrajet, LocalTime heureArrivee){
        this.station = station;
        this.heureArrivee = heureArrivee;
        this.tempsTrajet = tempsTrajet;
    }

    public Ticket(Station station, Station origine, LocalTime heureArrivee) {
        this.station = station;
        this.origine = origine;
        this.heureArrivee = heureArrivee;
    }

    /**
     * Met à jour le Ticket.
     * @param origine nouvelle station d'origine.
     * @param heureArrivee nouvelle heure d'arrivée.
     * @param tempsTrajet nouvelle durée du trajet, en minutes.
     */
    public void update(Station origine, LocalTime heureArrivee, int tempsTrajet){
        this.origine = origine;
        this.heureArrivee = heureArrivee;
        this.tempsTrajet = tempsTrajet;
    }

    /**
     * Remonte les tickets issus de Itineraire.trouveChemin pour retourner une liste de chaînes de caractères décrivant
     * le trajet trouvé.
     * @param ticket le dernier ticket traité.
     * @param reseau l'ensemble des tickets.
     * @return une ArrayList de String décrivant l'itinéraire.
     */
    public static ArrayList<String> chemin(Ticket ticket, ArrayList<Ticket> reseau){

        ArrayList<String> chemin = new ArrayList<>();
        Ticket temp = ticket;

        while(!(temp.getOrigine() == null)){
            chemin.add("\n" + temp.getHeureArrivee().minusMinutes(temp.getTempsTrajet()) + " " + temp.getOrigine().getNom() + " --" +
                    temp.getTempsTrajet() + "min|" + temp.getOrigine().getTrajet(temp.getStation()).getMoyenTransport().toString()
                    + "--> " + temp.getStation().getNom() + " " + temp.getHeureArrivee().toString());
            System.out.println("origine : " + temp.getOrigine().getNom());
            System.out.println("reseau : " + reseau);
            temp = getTicket(reseau, temp.getOrigine());
        }

        Collections.reverse(chemin);
        return chemin;
    }

    /**
     * Cherche le ticket concernant une station passée en paramètre dans la liste de tickets passé en paramètre.
     * @param reseau la liste de tickets.
     * @param station la station du ticket recherché.
     * @return le ticket trouvé.
     */
    private static Ticket getTicket(ArrayList<Ticket> reseau, Station station){
        for(Ticket t : reseau){
            if(t.getStation().equals(station)){
                return t;
            }
        }
        return null;
    }


    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Station getOrigine() {
        return origine;
    }

    public void setOrigine(Station origine) {
        this.origine = origine;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public int getTempsTrajet() { return tempsTrajet; }

    public void setTempsTrajet(int tempsTrajet) { this.tempsTrajet = tempsTrajet; }
}
