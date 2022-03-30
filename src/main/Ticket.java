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

    public void update(Station origine, LocalTime heureArrivee, int tempsTrajet){
        this.origine = origine;
        this.heureArrivee = heureArrivee;
        this.tempsTrajet = tempsTrajet;
    }

    public static ArrayList<String> chemin(Ticket ticket, ArrayList<Ticket> reseau){

        ArrayList<String> chemin = new ArrayList<>();
        Ticket temp = ticket;

        while(!(temp.getOrigine() == null)){
            chemin.add(temp.getHeureArrivee().minusMinutes(temp.getTempsTrajet()) + " " + temp.getOrigine().getNom() + " --" + temp.getTempsTrajet() + "min--> " + temp.getStation().getNom() +
                    " " + temp.getHeureArrivee().toString());
            System.out.println("origine : " + temp.getOrigine().getNom());
            System.out.println("reseau : " + reseau);
            temp = getTicket(reseau, temp.getOrigine());
        }

        Collections.reverse(chemin);
        return chemin;
    }

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
