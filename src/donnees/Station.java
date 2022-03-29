package donnees;


import java.util.ArrayList;
import java.util.Hashtable;

public class Station {

    protected String nom;
    protected  ArrayList<Trajet> listeTrajets = new ArrayList<>();

    public Station(String nom){
        this.nom = nom;
    }


	@Override
	public String toString(){
		return "Station : " + this.nom + "\n   Trajets :\n   " + this.listeTrajets.toString() + "\n\n";
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

	
	public static ArrayList<Station> unionStation(ArrayList<Station> l1, ArrayList<Station> l2) {
		/*
		 * Union de deux ensembles de stations.
		 * --HYPOTHESE: chaque liste (l1 et l2) contiennent des stations dont les noms sont 2 a 2 distincts. 
		 * Le resultat obtenue est une liste de stations contenants les stations de l1 et l2.
		 * */
		ArrayList<Station> new_liste= new ArrayList<Station>(l1);
		Hashtable<Station,Integer> hashtable = new Hashtable<Station, Integer>();
		Integer nonMarquer=Integer.valueOf(0);
		Integer marquer=Integer.valueOf(1);
		boolean acces;
		for (Station t: l2) {
			hashtable.put(t,nonMarquer);
		}
		
		int i=-1;
		for(Station s1: l1) {
			i++;
			for(Station s2: l2) {
				if (hashtable.get(s2).equals(nonMarquer)){
					if(s1.getNom().equalsIgnoreCase(s2.getNom())){
						new_liste.get(i).listeTrajets.addAll(s2.listeTrajets);
						hashtable.replace(s2,marquer);
					}
					else {
						acces=true;
						for( int j=i+1;j<l1.size();j++) {
							if(l1.get(j).getNom().equalsIgnoreCase(s2.getNom())){
							acces=false;
							break;
							}
						}
						if(acces) {
							new_liste.add(s2);
							hashtable.replace(s2,marquer);
						}	
					}
				}
			}
		}
		return new_liste;
	}
	


}
