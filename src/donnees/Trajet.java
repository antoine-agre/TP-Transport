package donnees;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    public Station arrivee;
    protected TreeSet<Date> listeHoraires = new TreeSet<>();
    protected MoyenTransport moyenTransport;
    public int duree;
    //ajout de la variable de temps 
    //songer a un convertisseur en heure apres lajout de la duree sur le temps de depart
    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree=duree;
        this.moyenTransport = moyenTransport;
    }



}
