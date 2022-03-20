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
	static  ArrayList<Station> listeStation = new ArrayList<>();
	
	static int correspondance(ArrayList<Station> liste,String s) {
		int i=-1;
		for(Station r: liste) {
			i++;
			if(r.getNom().equalsIgnoreCase(s)) {
				break;
			}
		}
		return i;
	}
	
	public static void initialisisation_listeStation() {
		    try
		    {
		      InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/metro.txt"),"utf8");   
		      Scanner scanner = new Scanner(file);
		      String station[]={};
		      while(scanner.hasNextLine()){
		    	String a=scanner.nextLine();
		    		if(a.equalsIgnoreCase("%stations")) {
		    			station=scanner.nextLine().split(" ");
		    			break;
		    		}	
		      }
		      for(String s: station) {
		    	  System.out.println(s);
		    	  	Station d= new Station(s);
		    	  	listeStation.add(d);
		      }
		      scanner.close();    
		    }
		    catch(IOException e)
		    {
		      e.printStackTrace();
		    }
	
	}
	
	static ArrayList<ArrayList<String>> liaison() {
		int i=0;
		ArrayList<ArrayList<String>> tableau_donnee = new ArrayList<ArrayList<String>>();
		 try
		    {
		      InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/metro.txt"),"utf8");   
		      Scanner scanner = new Scanner(file);
		      String liaison[]=null;
		      while(scanner.hasNextLine()){
		    	String a=scanner.nextLine();
		    		if(a.equalsIgnoreCase("%liaisons")) {
		    			liaison=scanner.nextLine().split(" ");
		    			liaison[0]=liaison[0].substring(1);
		    			break;
		    		}	
		      }
		      
	    	 for(int j=0;j<liaison.length;j++) {
	    		  tableau_donnee.get(i).add(liaison[i]);
	    	  }
	    	 
		      while(scanner.hasNextLine()){
		    	  String a=scanner.nextLine();
		    	  if(a.equalsIgnoreCase("")) {break;}
		    	  liaison=a.split(" ");
		    	  for(int j=0;j<liaison.length;j++) {
		    		  tableau_donnee.get(i).add(liaison[i]);
		    	  }
		      }
		      
		      scanner.close();    
		    }
		    catch(IOException e)
		    {
		      e.printStackTrace();
		    }
		return tableau_donnee;
	}
	
	static ArrayList<ArrayList<LocalTime>> plageHoraire(ArrayList<String> liste) {
	    
		
		ArrayList<ArrayList<LocalTime>> local=new ArrayList<ArrayList<LocalTime>>();
		try {
	      InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/metro.txt"),"utf8");
	      LocalTime initial ,debut = null,fin = null;
	      int duree;
	      Scanner scanner = new Scanner(file);  
	      while(scanner.hasNextLine()){
	    	String a=scanner.nextLine();
	    		
	    	if(a.equalsIgnoreCase("%à partir de")) {
	    			debut=ParseurXML.horaire(scanner.nextLine());
	    		}
	    		
	    	if(a.equalsIgnoreCase("%toutes les x minutes")) {
	    			duree=Integer.valueOf(scanner.nextLine());
	    	}
	    	
	    	if(a.equalsIgnoreCase("%dernier départs de Gare")) {
	    		fin=ParseurXML.horaire(scanner.nextLine());
	    	}
	      }
	      scanner.close();

	      int i=-1;
	      for(String s :liste) {
	    	  i++;
	    	  initial=debut;
	    	  while(initial.compareTo(fin)<=0) {
	    		  local.get(i).add(initial);
	    		  initial=initial.plusMinutes(Integer.valueOf(s));
	    	  }
	    	  debut=debut.plusMinutes(Integer.valueOf(s));
	      } 
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
		return local;
	}
	
	
	static void parseurMetro() {
		int i,id_depart = 0,id_arriv = 0,id_duree = 0;
		initialisisation_listeStation();
		ArrayList<ArrayList<String>> liste=liaison();
		System.out.println(liste.size());
		
		i=0;
		for(ArrayList<String> s:liste) {
			if(s.get(0).equalsIgnoreCase("depart")){id_depart=i;i++;continue;}
			if(s.get(0).equalsIgnoreCase("arrivee")){id_arriv=i;i++;continue;}
			if(s.get(0).equalsIgnoreCase("duree")) {id_duree=i;i++;}
		}
		ArrayList<ArrayList<LocalTime>> local=plageHoraire(liste.get(id_duree));
		int x,y;
		int j=-1;
		for(String str: liste.get(id_depart)) {
			for(String str1: liste.get(id_arriv)) {
				j++;
				x=correspondance(listeStation, str);
				y=correspondance(listeStation, str1);
				Trajet e =new Trajet(listeStation.get(x),listeStation.get(y),MoyenTransport.METRO,Integer.valueOf(liste.get(id_duree).get(j)));
				e.getListeHoraires().addAll(local.get(j));
				listeStation.get(x).addTrajet(e);
			}	
		}
	}
	
	
	 public static void main(String args[])
	  {
		parseurMetro();
		 //liaison();
		// ParseurMetro.initialisisation_listeStation();
	  }
	    /*try
	    {
	      // Le fichier d'entrée
	      InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/metro.txt"),"utf8");   
	      Scanner scanner = new Scanner(file);  
	      //scanner.
	      //renvoie true s'il y a une autre ligne à lire
	      while(scanner.hasNextLine()){
	    	  
	    	String a=scanner.nextLine();
	    		if(a.equalsIgnoreCase("%station")) {
	    			String station[]=scanner.nextLine().split(" ");
	    		
	    		
	      }
	      }
	      scanner.close();    
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	  }*/
}
