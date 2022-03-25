package regex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AnalyseurTram extends DefaultHandler{
	
	private boolean etatLignes,etatLigne,etatStation,etatHeure;

	
	//ouverture et fermeture du tram.xml
	@Override
	public void startDocument() throws SAXException{
		System.out.println("debut du fichier xml");
		}
	public void  endDocument() {
		System.out.println("fin du fichier xml");
	}
		
	@Override
	
	public void startElement(String uri, String localName,
            String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("lignes")) {
			
			  etatLignes = true;
			
		 }
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
		

			if (qName.equalsIgnoreCase("lignes")) {
				
				  etatLignes = false;
			}
				
		
		 
		 if (qName.equalsIgnoreCase("ligne")) {
			  etatLigne = false;


		 }
		 
		 
		 if (qName.equalsIgnoreCase("stations")) {
			 etatStation=false;
			 
		 }
		 
		 
		 if (qName.equalsIgnoreCase("heures-passage")) {
			  
			 etatHeure = false;
			 
		 }
	}
	
public void characters(char[] ch, int start, int length) throws SAXException {
		
		if(etatLignes) {
			
			
			if(etatLigne) {
								
				if(etatStation) {
					
				}
				
				if(etatHeure){
					
				}
			}
		}
		else {
			if(etatStation) {
				
			}
		
	}
}
	
	
	
	
	
	

}
