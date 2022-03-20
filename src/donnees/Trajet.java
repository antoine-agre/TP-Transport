package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    protected Station arrivee;
    //protected
    public TreeSet<LocalTime> listeHoraires = new TreeSet<>();
    protected ArrayList<String> listeHoraire = new ArrayList<>();
    
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

	
    protected MoyenTransport moyenTransport;
    protected int duree;
    
    public Trajet(Station depart, Station arrivee, MoyenTransport moyenTransport, int duree){
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree=duree;
        this.moyenTransport = moyenTransport;
    }
    
    public void addHoraire(LocalTime h) {
		listeHoraires.add(h);
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

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
