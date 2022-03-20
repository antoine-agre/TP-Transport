package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Trajet {

    //Attributs 
    
    protected Station depart;
    protected Station arrivee;
    protected TreeSet<LocalTime> listeHoraires = new TreeSet<>();
    protected ArrayList<String> listeHoraire = new ArrayList<>(); //?? à supprimer ou au moins renommer
    protected MoyenTransport moyenTransport;
    protected int duree;
    
    
    //Constructeur
    
    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int Duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
        this.moyenTransport = moyenTransport;
    }
    
    
    //Overrides
    
    
    @Override
    public String toString(){
        return "Trajet : " + this.depart.getNom() + " à " + this.arrivee.getNom() + "\n" + this.moyenTransport.toString() +
                "\n" + duree + "min" + "\nHoraires :\n" + this.listeHoraires.toString();
    }
    
    //Getters/Setters
    
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
    
   
    //Méthodes
    
    public void addHoraire(LocalTime h) { //à unifier ?
		listeHoraires.add(h);
	}
    
    public void addHoraire(int heures, int minutes) {
        listeHoraires.add(LocalTime.of(heures, minutes));
    }

	
    //Méthodes statiques

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

    
    //Main (debug)
    
	public static void main(String[] args) {
		/*int k=2,b;
    	Trajet a =new Trajet(new Station("ali"),new Station("ali"),MoyenTransport.CAR,4);
    	b=k;
    	for(int i=0;i<5;i++){
    		b++;
    		a.listeHoraire.add("b");
	    	for(String t : a.listeHoraire) {
	    		t=t.toUpperCase();
	    		a.listeHoraire.add("k");
	    		break;
	    	}
    	System.out.println(a.listeHoraire.toString());
		}
    	System.out.println();
    	*/
	}

}
