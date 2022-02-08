package donnees;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    protected Station arrivee;
    protected TreeSet<Date> listeHoraires = new TreeSet<>();
    protected MoyenTransport moyenTransport;

    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport){
        this.depart = depart;
        this.arrivee = arrivee;
        this.moyenTransport = moyenTransport;
    }



}
