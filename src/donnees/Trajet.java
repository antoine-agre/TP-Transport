package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    protected Station arrivee;
    protected TreeSet<LocalTime> listeHoraires = new TreeSet<>(); /////////////////
    protected MoyenTransport moyenTransport;
    protected int duree; //durée du trajet en minutess

    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.moyenTransport = moyenTransport;
        this.duree = duree;
    }

    public void addHoraire(LocalTime horaire){
        this.listeHoraires.add(horaire);
    }



}
