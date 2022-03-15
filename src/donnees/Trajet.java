package donnees;

import java.util.Date;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    public Station arrivee;
    protected TreeSet<Date> listeHoraires = new TreeSet<>();
    protected MoyenTransport moyenTransport;
    public int duree;
    //ajout de la variable de temps 
    //songer a un convertisseur en heure apres lajout de la duree sur le temps de depart
    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int Duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree =Duree;
        this.moyenTransport = moyenTransport;
    }



}
