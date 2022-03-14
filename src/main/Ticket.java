package main;

import donnees.Station;

import java.util.Date;


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
    protected Date temps;

    public Ticket(Station station, Station origine, Date temps) {
        this.station = station;
        this.origine = origine;
        this.temps = temps;
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

    public Date getTemps() {
        return temps;
    }

    public void setTemps(Date temps) {
        this.temps = temps;
    }
}
