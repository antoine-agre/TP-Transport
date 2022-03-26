package regex;

import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AnalyseurTram extends DefaultHandler{
	
	
	static boolean resultat=true;
	private boolean reseau,lignes,ligne,stations,heure;
	int a=0,b;
	
	Pattern ligneStation = Pattern.compile("(([A-Z]).\\w+\\s*)+");
	Pattern ligneHeure = Pattern.compile("(\\d{4}\\s*)+");
	
	//ouverture et fermeture du tram.xml
	@Override
	public void startDocument() throws SAXException{
		System.out.println("debut du fichier xml");
		}
	public void  endDocument() {
		System.out.println("fin du fichier xml");
	}
		
	@Override
	
	public void startElement(String uri, String localName,String qName, Attributes attributes) {
		
		if (qName.equalsIgnoreCase("reseau")) {
			reseau = true;
		 }
		
		if (qName.equalsIgnoreCase("lignes")) {
			lignes = true;
		 }
		
		if (qName.equalsIgnoreCase("ligne")) {
			a++;
			b=0;
			ligne = true;
		 }
		 
		 if (qName.equalsIgnoreCase("stations")) {
			 stations=true;
			 
		 }
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  b++;
			  heure = true;
		 }
	}
	
	//fermeture
	@Override
	public void endElement(String uri, String localName,String qName) {
		
		if (qName.equalsIgnoreCase("reseau")) {
			
			  reseau = false;
		 }
		
		if (qName.equalsIgnoreCase("lignes")) {
				
				  lignes = false;
		}
				
		if (qName.equalsIgnoreCase("ligne")) {
			  ligne = false;
		 }
		 
		if (qName.equalsIgnoreCase("stations")) {
			 stations=false;
		 }
		 
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 heure = false;
			 
		 }
	}
	
public void characters(char[] ch, int start, int length) throws SAXException {
	String donnee;
		if(reseau) {
			if(lignes) {
				if(ligne) {
								
					if(stations) {
						donnee=new String(ch,start,length);
						if(ligneStation.matcher(donnee).matches()){
							System.out.println("balise <stations> de la balise <ligne> "+a);
	                    }
						else {
						 System.err.println("Erreur: balise <stations> de la balise <ligne> "+a+"\nformat non conforme");
						 resultat=false;
						}
					}
					
					if(heure){
						donnee=new String(ch,start,length);
						if(ligneHeure.matcher(donnee).matches()){
							System.out.println("balise <heure> "+b+" de la balise <ligne> "+a);
	                    }
						else {
						 System.err.println("Erreur: balise <heure> "+b+" de la balise <ligne> "+a+"\nformat non conforme");
						 resultat=false;
						}
					
					}
			}
		}
		else {
			if(stations) {
				donnee=new String(ch,start,length);
				if(ligneStation.matcher(donnee).matches()){
					System.out.println("balise <stations>, liste des stations de toutes les lignes");
                }
				else {
				 System.err.println("Erreur: balise <stations> de toutes les lignes\nformat non conforme");
				 resultat=false;
				}
				
			}
		
	}
}
}
}

