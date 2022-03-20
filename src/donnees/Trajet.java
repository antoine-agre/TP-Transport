package donnees;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Trajet {

    protected Station depart;
    protected Station arrivee;
    
    public TreeSet<LocalTime> listeHoraires = new TreeSet<>();
    
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
		
	
	}

}
