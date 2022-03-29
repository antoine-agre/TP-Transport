package parseur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

public class ParseurMetro {
	
	static  ArrayList<Station> listeStation;
	
	
	//-les methodes
	
	//initialize la liste de stations avec les donnees.
	public static void initialisisation_listeStation(String fileName) {
		try{
		    listeStation = new ArrayList<>();
		    //lescture en format utf-8
		    InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/"+fileName),"utf8");   
			Scanner scanner = new Scanner(file);
			String station[]={};
			while(scanner.hasNextLine()){
				String a=scanner.nextLine();
				if(a.equalsIgnoreCase("%stations")) {
					station=scanner.nextLine().split(" ");
					break;
					}	
			}
			//recuparation des noms de station et creation des stations correspondantes
			for(String s: station) {
				Station d= new Station(s);
				listeStation.add(d);
			}
			//fermeture du fichier
			scanner.close();    
			}catch(IOException e){e.printStackTrace();}
	}
	
	
	
	
	/*
	 * methode statique qui renvoie liste de liste de (depart,arrive,duree)
	 */
	static ArrayList<ArrayList<String>> liaison(String fileName) {
		
		ArrayList<ArrayList<String>> tableau_donnee = new ArrayList<ArrayList<String>>();
		try{
			InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/"+fileName),"utf8");   
		    Scanner scanner = new Scanner(file);
		    String liaison[]=null;
		    while(scanner.hasNextLine()){
		    	String a=scanner.nextLine();
		    	//nous permet de connaitre le format des elements(depart,duree,arrive) 
		    	if(a.equalsIgnoreCase("%liaisons")){
		    		liaison=scanner.nextLine().split(" ");
	    			liaison[0]=liaison[0].substring(1);
	    			break;
	    			}
		    }
	    	for(int j=0;j<liaison.length;j++){
	    		ArrayList<String> a= new ArrayList<String>();
	    		a.add(liaison[j]);
	    		tableau_donnee.add(a);
	    	}
	    	//copie des elements.
		    while(scanner.hasNextLine()){
		    	String a=scanner.nextLine();
		    	if(a.equalsIgnoreCase("")) {break;}
		    		liaison=a.split(" ");
		    		for(int j=0;j<liaison.length;j++){
		    			tableau_donnee.get(j).add(liaison[j]);
		    		}
		    }
		    scanner.close();    
		    }catch(IOException e){e.printStackTrace();}
		return tableau_donnee;
	}
	
	
	
	//renvoie une liste de liste d'horaire de depart.
	static ArrayList<ArrayList<LocalTime>> plageHoraire(ArrayList<String> liste,String fileName) {
		
		//initialisation de la variable retourner.
		ArrayList<ArrayList<LocalTime>> local=new ArrayList<ArrayList<LocalTime>>();
		for(@SuppressWarnings("unused") String s: liste) {
			local.add(new ArrayList<LocalTime>());}
		try{
			InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/"+fileName),"utf8");
			LocalTime initial ,debut = null,fin = null;
			@SuppressWarnings("unused")
			int duree=0;String a;
			Scanner scanner = new Scanner(file);  
			while(scanner.hasNextLine()){
				a=scanner.nextLine();
		    	//debut: 7h00 et fin:23h30 dans notre cas.
		    	if(a.equalsIgnoreCase("%à partir de")){
		    		debut=ParseurXML.horaire(scanner.nextLine());
		    	}
		    	if(a.equalsIgnoreCase("%toutes les x minutes")){
		    		duree=Integer.parseInt(scanner.nextLine());
		    	}
		    	if(a.equalsIgnoreCase("%dernier départs de Gare")){
		    		fin=ParseurXML.horaire(scanner.nextLine());
		    	}
			}
			//fermeture du fichier.
			scanner.close();
			//remplissage des liste d'horaires de depart.
			int i=-1;
			for(String s :liste) {
				i++;
				initial=debut;
				while(initial.compareTo(fin)<=0) {
					local.get(i).add(initial);
		    		initial=initial.plusMinutes(Integer.parseInt(s));
				}
		    	debut=debut.plusMinutes(Integer.parseInt(s));
			}
		}catch(IOException e){e.printStackTrace();}
		return local;
	}
	
	
	
	
	//parseur du fichier metro.txt
	public static ArrayList<Station> parseurMetro(String fileName) {
		ArrayList<Station> resultat;
		int i=0,id_depart = 0,id_arriv = 0,id_duree = 0;
		initialisisation_listeStation(fileName);
		ArrayList<ArrayList<String>> liste=liaison(fileName);
		/**
		 * la premiere case des  3 ArrayList "s" contiennent les infos sur la nature des elements de "s".	
		 * pour rappel : s.size=3
		 * on supprime donc les premieres cases apres reception de l'informations.
		 */
		for(ArrayList<String> s:liste) {
			if(s.get(0).equalsIgnoreCase("depart")){id_depart=i;i++;s.remove(0);continue;}
			if(s.get(0).equalsIgnoreCase("arrivee")){id_arriv=i;i++;s.remove(0);continue;}
			if(s.get(0).equalsIgnoreCase("duree")) {id_duree=i;s.remove(0);i++;}
		}
		//en fonction des indice de position des nom de station depart,arrivee et de la duree, remplisage de la listeStation.
		ArrayList<ArrayList<LocalTime>> local=plageHoraire(liste.get(id_duree),fileName);
		Station x,y;
		String str1;
		//j :gere les indices de positions. 
		int j=-1;
		for(String str: liste.get(id_depart)) {
			j++;
			if(j<liste.get(id_arriv).size()) {
				str1=liste.get(id_arriv).get(j);
				x=Station.listeGet(listeStation, str);
				y=Station.listeGet(listeStation, str1);
				Trajet e =new Trajet(x,y,MoyenTransport.METRO,Integer.valueOf(liste.get(id_duree).get(j)));
				e.getListeHoraires().addAll(local.get(j));
				x.addTrajet(e);
			}
		}
		resultat=listeStation;
		return resultat;
	}
	

	public static void main(String args[]){
		ArrayList<Station> x=parseurMetro("metro.txt");
		System.out.println(x);
	}
}
