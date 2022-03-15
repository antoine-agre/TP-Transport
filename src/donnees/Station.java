package donnees;

import java.sql.Array;
import java.util.ArrayList;

public class Station {

    protected String nom;
    protected  ArrayList<Trajet> listeTrajets = new ArrayList<>();

    public Station(String nom){
        this.nom = nom;
    }

    
	public String getNom() {
		return nom;
	}

	
	public ArrayList<Trajet> getListeTrajets() {
		return listeTrajets;
	}

	public void addTrajet(Trajet trajet) {
		listeTrajets.add(trajet);
	}



}
