package donnees;

import java.sql.Array;
import java.util.ArrayList;

public class Station {

    protected String nom;
    protected ArrayList<Trajet> listeTrajets = new ArrayList<>();

    public Station(String nom){
        this.nom = nom;
    }

    @Override
    public String toString(){
        return "Station : " + this.nom + "\nTrajets :\n" + this.listeTrajets.toString() + "\n";
    }

    public ArrayList<Trajet> getListeTrajets() {
        return listeTrajets;
    }

    public void setListeTrajets(ArrayList<Trajet> listeTrajets) {
        this.listeTrajets = listeTrajets;
    }

    public String getNom() {
        return nom;
    }

    public void addTrajet(Trajet trajet) {
        this.listeTrajets.add(trajet);
    }

    public Trajet getTrajet(Station station){
        for(Trajet t : this.listeTrajets){
            if(t.getArrivee().equals(station)){
                return t;
            }
        }
        return null;
    }
}
