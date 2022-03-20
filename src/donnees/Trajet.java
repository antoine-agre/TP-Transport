package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    protected Station arrivee;
    protected TreeSet<LocalTime> listeHoraires = new TreeSet<>();
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


    @Override
    public String toString(){
        return "Trajet : " + this.depart.getNom() + " à " + this.arrivee.getNom() + "\n" + this.moyenTransport.toString() +
                "\n" + duree + "min" + "\nHoraires :\n" + this.listeHoraires.toString();
    }

    public Station getArrivee() {
        return arrivee;
    }

    public TreeSet<LocalTime> getListeHoraires() {
        return listeHoraires;
    }

    /**
     * Retourne le Trajet dont le nom de l'arrivée est donnée existant dans l'ArrayList donnée.
     * @param liste     la liste de trajets
     * @param destination   le nom de l'arrivée du Trajet recherché
     * @return	le Trajet trouvée dans la liste.
     */
    public static Trajet listeGet(ArrayList<Trajet> liste, String destination){
        for(Trajet t : liste){
            if(t.getArrivee().getNom().equals(destination)){return t;}
        }
        return null;
    }

    public void addHoraire(int heures, int minutes) {
        listeHoraires.add(LocalTime.of(heures, minutes));
    }

}
