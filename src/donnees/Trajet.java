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
    protected int duree; //durée du trajet en minutes

    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.moyenTransport = moyenTransport;
        this.duree = duree;
    }

    @Override
    public String toString(){
        return "Trajet : " + this.depart.getNom() + " à " + this.arrivee.getNom() + "\n" + this.moyenTransport.toString() +
                "\nHoraires :\n" + this.listeHoraires.toString();
    }

    public void addHoraire(LocalTime horaire){
        this.listeHoraires.add(horaire);
    }

    public Station getDepart() {
        return depart;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public void setArrivee(Station arrivee) {
        this.arrivee = arrivee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public TreeSet<LocalTime> getListeHoraires() {
        return listeHoraires;
    }

    public void setListeHoraires(TreeSet<LocalTime> listeHoraires) {
        this.listeHoraires = listeHoraires;
    }
}
