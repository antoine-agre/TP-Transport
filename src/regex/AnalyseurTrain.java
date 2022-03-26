package regex;

import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

final class  AnalyseurTrain extends DefaultHandler {
	
	private boolean horaires,line,junction,station,heure;
	static boolean resultat=true;
	int a=0,b=0,c=0,d=0;
	
	
	Pattern ligneStation = Pattern.compile("([A-Z]).\\w+");
	Pattern ligneHeure = Pattern.compile("(\\d){4}");
	
	public void startDocument() throws SAXException {
		System.out.println("ouverture du fichier Train.xml");
	}
	

	public void endDocument() throws SAXException {
		System.out.println("fin du fichier Train.xml");
				}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("horaires")) {
			 horaires=true;
		}
		
		if (qName.equalsIgnoreCase("line")) {
			 line=true;
			a++;
			b=0;
		}
		
		if (qName.equalsIgnoreCase("junction")) {
			 junction=true;
			b++;
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
			
			heure=true;
		}
	}

	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		

		if (qName.equalsIgnoreCase("horaires")) {
			 horaires=false;
		}
		

		if (qName.equalsIgnoreCase("line")) {
		
			 line=false;
		}
		
		
		if (qName.equalsIgnoreCase("junction")) {
			 c=0;d=0;
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
		String donnee,nom;
		if(horaires) {
			if(line) {
				if(junction) {
					if(station) {
						c++;
						donnee=new String(ch,start,length);
						if(c==1) nom="start-";
						else nom="arrival-";
						if(ligneStation.matcher(donnee).matches()){
							System.out.println("balise <ligne> "+a+" ,balise junction "+b+" ,balise<"+nom+"station> ");
	                    }
						else {
						 System.err.println("Erreur : ligne " +"balise <ligne> "+a+" ,balise junction "
						 		+b+" ,balise<"+nom+"station>\n format non compatible");
						 resultat=false;
						 System.exit(1);
	                     
						}
					}
					if(heure){
						d++;
						donnee=new String(ch,start,length);
						if(d==1) nom="start-";
						else nom="arrival-";
						if(ligneHeure.matcher(donnee).matches()){
							System.out.println("balise <ligne> "+a+" ,balise junction "+b+" ,balise<"+nom+"hour> ");
	                    }
						else {
						 System.err.println("Erreur : ligne " +"balise <ligne> "+a+" ,balise junction "+b+" ,balise<"+nom+"hour>\n format non compatible");
						 resultat=false;
						 System.exit(1);
	                     
						}
						
					}
				}
			
			}
		}
			
	}
}



