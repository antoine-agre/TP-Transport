package donnees;

import java.sql.Array;
import java.util.ArrayList;

public class Station {

    protected String nom;
    protected  ArrayList<Trajet> listeTrajets = new ArrayList<>();

    public Station(String nom){
        this.nom = nom;
    }


	@Override
	public String toString(){
		return "Station : " + this.nom + "\nTrajets :\n" + this.listeTrajets.toString() + "\n";
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

	/**
	 * Vérifie si une station d'un nom donné existe dans l'ArrayList donnée.
	 * @param liste	la liste de stations
	 * @param nom	le nom de la station recherchée
	 * @return	un booléen indiquant si la station existe dans la liste.
	 */
	public static boolean listeContient(ArrayList<Station> liste, String nom){
    	for(Station s : liste){
    		if(s.getNom().equals(nom)){return true;}
		}
    	return false;
	}

	/**
	 * Retourne la station du nom donné existant dans l'ArrayList donnée.
	 * @param liste	la liste de stations
	 * @param nom	le nom de la station recherchée
	 * @return	la Station trouvée dans la liste.
	 */
	public static Station listeGet(ArrayList<Station> liste, String nom){
    	for(Station s : liste){
    		if(s.getNom().equals(nom)){return s;}
		}
    	return null;
	}


}
