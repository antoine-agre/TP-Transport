package parseur;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

public class HandlerTram extends DefaultHandler {
	//Cette classe Handler permet d'extraire des donnees dans un fichier xml en associant evenements sur balise à des intructions definies.
	//Tram.xml
	
	//aide gestion des actions liee a l'ouverture et de la fermeture de balise.
	boolean etatLigne=false;
	boolean etatStation=false;
	boolean etatHeure=false;
	
	int indice=0; //aide a la gestion du remplissage de @listeStation
	
	//liste des stations; chaque stations contient sa liste de trajet.
	private static  ArrayList<Station> listeStation = new ArrayList<>();
	
	public HandlerTram() {
		super();
	}
	
	
	//ouverture et fermeture du tram.xml
	@Override
	public void startDocument() throws SAXException{
		System.out.println("debut du fichier xml");
		}
	public void  endDocument() {
		System.out.println("fin du fichier xml");
		/*
		 //exemple test
		 for(int i=0; i< 11;i++) {
			if(i!=4 && i!=10) {
			System.out.println(listeStation.get(i).getNom());
			System.out.println(  (listeStation.get(i) .getListeTrajets() .get(0).Duree));
			}
		}*/
	}
	
	//ouverture et fermeture des balises necessaires a l'extraction des elements qui nous interessent
	
	//Ouverture
	@Override
	public void startElement(String uri, String localName,
            String qName, Attributes attributes) {
		
		 if (qName.equalsIgnoreCase("ligne")) {
			
			  etatLigne = true;
			
		 }
		 
		 if (qName.equalsIgnoreCase("stations")) {
			 etatStation=true;
			 
		 }
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 etatHeure = true;
		 }
	}
	
	//fermeture
	@Override
	public void endElement(String uri, String localName,
            String qName) {
		
		 
		 if (qName.equalsIgnoreCase("ligne")) {
			  etatLigne = false;
			  indice=listeStation.size();
			  //System.out.println(indice);
		 }
		 
		 
		 if (qName.equalsIgnoreCase("stations")) {
			 etatStation=false;
			 
		 }
		 
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 etatHeure = false;
			 
		 }
	}
	
	
	
	//fonction de recuperations des elements
	@Override
	public void characters(char ch[], int start,int length) throws SAXException{
		
		if (etatLigne) {
			
			
			if(etatStation) {
				
				String liste = new String(ch, start,length);
				String tableau[]= liste.split(" ");
				
				for(int i=0; i< tableau.length;i++) {
					
					Station s=new Station(tableau[i]);
					listeStation.add(s);
					
				}
				
			
			}
			
			if(etatHeure) {
				
				String liste=new String(ch,start,length);
				String tableauHeure[] = liste.split(" ");
				//System.out.println(liste);
				for(int i=0; i< tableauHeure.length-1;i++) {
					Station s=listeStation.get(i+indice);
					//System.out.println(indice+i);
					int duree=calculDuree(tableauHeure[i],tableauHeure[i+1]);
					Trajet e =new Trajet(s,listeStation.get(indice+i+1),MoyenTransport.TRAM,duree);
					s.addTrajet(e);
					
				}	
			}
		}
	}
	
	public int calculDuree(String depart,String arrive) {
		
		int duree = Integer.valueOf(arrive)- Integer.valueOf(depart);
		
		if( ! depart.substring(0,2).equalsIgnoreCase(arrive.substring(0,2)) ){
			String a1= depart.substring(0,2);
			String b1= arrive.substring(0,2);
			duree-=(Integer.valueOf(b1)- Integer.valueOf(a1))*40;
		}
		return duree;
	}
}