package donnees;

import java.util.ArrayList;

/**
 * Représente une station du réseau de transport.
 */
public class Station {

    /**
     * Le nom de la station.
     */
    protected String nom;

    /**
     * La liste des trajets partant de cette station.
     */
    protected ArrayList<Trajet> listeTrajets = new ArrayList<>();


    /**
     * Constructeur de Station, initialise l'attribut nom.
     * @param nom le nom de la station.
     */
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

    /**
     * Recherche d'un trajet dans la liste des trajets partant de cette station, par destination.
     * @param station la station de destination du trajet recherché.
     * @return le trajet trouvé.
     */
    public Trajet getTrajet(Station station){
        for(Trajet t : this.listeTrajets){
            if(t.getArrivee().equals(station)){
                return t;
            }
        }
        return null;
    }
}
