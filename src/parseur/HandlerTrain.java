package parseur;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

	final class  HandlerTrain extends DefaultHandler {
	private boolean a,c,d =false;
	private int indice =0;
	private ArrayList<String> junction = new ArrayList<>();
	static  ArrayList<Station> listeStation = new ArrayList<>();
	
	//public static void main(String[] args) {}
	

	@Override
	public void startDocument() throws SAXException {
		System.out.println("ouverture du fichier Train.xml");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("fin du fichier Train.xml");
		/*System.out.println(listeStation.size());
		 for(int i=0; i<listeStation.size() ;i++) {
			System.out.println(  (listeStation.get(i).getNom()) );
			 for(int j=0;j<listeStation.get(i).getListeTrajets().size();j++) {
				System.out.println(listeStation.get(i).getListeTrajets().get(j).duree);
				//System.out.println(  (listeStation.get(i) .getListeTrajets() .get(0).duree));

			}
		 }*/
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("junction")) {
			 a=true;
			 junction = new ArrayList<>();
		}
		if (qName.equalsIgnoreCase("start-station")) {
			
			 c=true;
		}
		if (qName.equalsIgnoreCase("arrival-station")) {
			
			 
			c=true;
		}
		if (qName.equalsIgnoreCase("start-hour")) {
			
			 c=true;
		}
		if (qName.equalsIgnoreCase("arrival-hour")) {
			d=true;
			c=true;
		}
	}

	
	
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("junction")) {
			 
			 a=false;
		}
	
		if (qName.equalsIgnoreCase("start-station")) {
			 
			 c=false;
		}
		
		if (qName.equalsIgnoreCase("arrival-station")) {
			 
			 c=false;
		}
		if (qName.equalsIgnoreCase("start-hour")) {
			 
			 c=false;
		}
		if (qName.equalsIgnoreCase("arrival-hour")) {
			 d=false;
			 c=false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (a) {
			if(c){
				junction.add(new String(ch,start,length));
				//System.out.println(new String(ch,start,length));
			}
			if(d) {
				indice=0;
				Station s_depart=new Station(junction.get(0));
				Station s_arrive=new Station(junction.get(1));
				int duree= HandlerTram.calculDuree(junction.get(2),junction.get(3));
				Trajet trajet =new Trajet(s_depart,s_arrive,MoyenTransport.TRAIN,duree);
				for (int i=0;i<listeStation.size();i++) {
					if(s_depart.getNom().equalsIgnoreCase(listeStation.get(i).getNom())) {
						listeStation.get(i).addTrajet(trajet);
						indice++;
						break;
					}
				}
				if(indice==0) {
					s_depart.addTrajet(trajet);
					listeStation.add(s_depart);
				}
			}
		}
		
	}


}
