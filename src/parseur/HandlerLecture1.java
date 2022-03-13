package parseur;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerLecture1 extends DefaultHandler {
	boolean etatLigne=false;
	boolean etatStation=false;
	boolean etatHeure=false;
	
	public HandlerLecture1() {
		super();
	}
	
	public void startDocument() throws SAXException{
		System.out.println("debut du fichier xml");
		}
	public void  endDocument() {
		System.out.println("fin du fichier xml");
	}
	
	
	
	
	public void startElement(String uri, String localName,
            String qName, Attributes attributes) {
		
		 if (qName.equalsIgnoreCase("ligne")) {
			 //System.out.println("fin du fichier xml "+qName);
			 
			  etatLigne = true; 
		 }
		 
		 if (qName.equalsIgnoreCase("station")) {
			 etatStation=true;
			 
		 }
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 etatHeure = true;
		 }
		 	
	}
	
	
	public void endElement(String uri, String localName,
            String qName) {
		
		 
		 if (qName.equalsIgnoreCase("station")) {
			 etatStation=false;
			 
		 }
		 
		 if (qName.equalsIgnoreCase("ligne")) {
			 
			  etatLigne = false; 
		 }
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 etatHeure = false;
		 }
		 
		 
		
	}

	
	
	
	public void characters(char ch[], int start,int length) throws SAXException{
		
		if(etatLigne) {
			ArrayList<String> listeStations = new ArrayList<>();
			if(etatStation) {
				String liste = new String(ch, start,length);
				String tableauHeure[] = liste.split(" ");
				for (int i = 0; i < tableau.length-1; i++) {
					int duree=(int)tableauHeure[i]-(int)tableauHeure[i+1]
					new Trajet(tableauStation[i],tableauStation[i+1],MoyenTransport.TRAM,duree);	
				}
			}
			
			else {
				 String liste = new String(ch, start,length);
				 String tableauStation[] = liste.split(" ");
				 
			}
			
			
			
			
			
			
			
			
			
		}
	
	
	
	
	
	
	}
	
}