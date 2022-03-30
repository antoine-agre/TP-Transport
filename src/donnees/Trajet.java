package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une liaison à sens unique entre deux stations du réseau, au sens d'une "ligne" qui possède des voyages
 * réguliers, et non d'un trajet ponctuel réalisé par un véhicule.
 */
public class Trajet {

    /**
     * Station de départ.
     * <br>C'est aussi dans cette station que la liste des trajets sera conservée.
     */
    protected Station depart;

    /**
     * Station d'arrivée.
     */
    protected Station arrivee;

    /**
     * Liste des horaires pour cette liaison.
     */
    protected TreeSet<LocalTime> listeHoraires = new TreeSet<>();

    /**
     * Moyen de transport de cette liaison.
     */
    protected MoyenTransport moyenTransport;

    /**
     * Durée du trajet en minutes.
     */
    protected int duree; //durée du trajet en minutes


    /**
     * Constructeur de Trajet, initialise les attributs attributs, arrivee, moyenTransport et duree.
     * @param depart station de départ.
     * @param arrivee station d'arrivée.
     * @param moyenTransport moyen de transport.
     * @param duree durée du trajet, en minutes.
     */
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


    /**
     * Ajoute l'horaire passé en paramètre à la liste d'horaires.
     * @param horaire l'horaire a rajouter.
     */
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
