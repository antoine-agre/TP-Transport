package regex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

final class  AnalyseurTrain extends DefaultHandler {
	
	private boolean line,heure,junction,station;
	static boolean resultat;
	int a=0,b=0,c=0,d=0;
	String name;
	
	public void startDocument() throws SAXException {
		System.out.println("ouverture du fichier Train.xml");
	}
	

	public void endDocument() throws SAXException {
		System.out.println("fin du fichier Train.xml");
				}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("line")) {
			 line=true;
			
		}
		
		if (qName.equalsIgnoreCase("junction")) {
			 junction=true;
			
		}
		if (qName.equalsIgnoreCase("start-station")) {
			//le boolean "c" informe de l'ouverture/fermeture des sous-balises de <junction>.
			 station=true;
		}
		if (qName.equalsIgnoreCase("arrival-station")) {
			station=true;
		}
		if (qName.equalsIgnoreCase("start-hour")) {
			
			 heure=true;
		}
		if (qName.equalsIgnoreCase("arrival-hour")) {
			// le boolean "d" informe de l'ouverture de la derniere sous-balise.
			heure=true;
		}
	}

	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		

		if (qName.equalsIgnoreCase("line")) {
			 
			 line=false;
		}
		
		
		if (qName.equalsIgnoreCase("junction")) {
			 
			 junction=false;
		}
	
		if (qName.equalsIgnoreCase("start-station")) {
			 
			 station=false;
		}
		
		if (qName.equalsIgnoreCase("arrival-station")) {
			 
			 station=false;
		}
		if (qName.equalsIgnoreCase("start-hour")) {
			 
			 heure=false;
		}
		if (qName.equalsIgnoreCase("arrival-hour")) {
			 heure=false;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if(line) {
			if(junction) {
				
				
				if(station) {
					
				}
				
				if(heure){
					
				}
			}
		
		}
		
	}
}



