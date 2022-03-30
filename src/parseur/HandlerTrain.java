package parseur;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

	final class  HandlerTrain extends DefaultHandler {
	private boolean a,c,d,first_in=true;
	private ArrayList<String> junction = new ArrayList<>();
	static  ArrayList<Station> listeStation;
	private boolean depart,arriv,sortie_last;
	
	/*Handlertrain
	 *
	 * listeStation: recoie les "Station"
	 *junction : liste qui receptionne les donnees a chaque ouverture de la balise <junction>
	 *junction.get(i):
	 *---------------- pour i=0: nom de Station depart,  pour i=2: temps depart.
	 *-----------------pour i=1 : "   "   "   " arrivee, pour i=3: "   " arrivee.
	 *sortie_last: permet de sortir d'une boucle "for".
	 *les autres variables ont (~) les memes roles que leurs identiques dans la classe Handler.Tram
	 */
	

	@Override
	public void startDocument() throws SAXException {
		listeStation = new ArrayList<>();
		//System.out.println("ouverture du fichier Train.xml");
	}

	@Override
	public void endDocument() throws SAXException {
		//System.out.println("fin du fichier Train.xml");
		//System.out.println(listeStation.size());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("junction")) {
			a=true;
			//initialisation de la liste junction a chaque ouverture de la balise.
			junction = new ArrayList<>();
		}
		if (qName.equalsIgnoreCase("start-station")) {
			//le boolean "c" informe de l'ouverture/fermeture des sous-balises de <junction>.
			c=true;
		}
		if (qName.equalsIgnoreCase("arrival-station")) {	 
			c=true;
		}
		if (qName.equalsIgnoreCase("start-hour")) {
			c=true;
		}
		if (qName.equalsIgnoreCase("arrival-hour")) {
			// le boolean "d" informe de l'ouverture de la derniere sous-balise.
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
			//ouverture la balise <junction>
			if(c){
				//ouverture des sous-balises de <junctions>
				//junction : est la liste qui receptionne les informations successives-
				//-,entre l'ouvertures et fermettures des sous-balise.
				junction.add(new String(ch,start,length));
			}
			if(d) {
				//ouverture derniere sous-balise et remplissage de "listeStation" 
				int duree= ParseurXML.calculDuree(junction.get(2),junction.get(3));
				//remplissage initial
				if(first_in) {
					Station d= new Station(junction.get(0));
					Station p= new Station(junction.get(1));
					Trajet e =new Trajet(d,p,MoyenTransport.TRAIN,duree);
					e.addHoraire(ParseurXML.horaire(junction.get(2)));
					d.addTrajet(e);
					listeStation.add(d);
					listeStation.add(p);
					first_in=false;
				}
				//remplissage general (apres celui initial).
				else {
					depart=true;
					arriv=true;
					int j=0,x=0,y=0;
					for(Station s1:listeStation) {
						j++;
						if(s1.getNom().equalsIgnoreCase(junction.get(0)) && depart){
							depart=false;
							x=j-1;
						}
						int c=0;
						for(Station s2:listeStation) {
							c++;
							if(s2.getNom().equalsIgnoreCase(junction.get(1)) && arriv){
								arriv=false;
								y=c-1;
							}
							// pour s1 et s2 present dans listeStation  listeStation.get(x)   listeStation.get(y)
							
							//4 cas.--- cas ou depart et arrivee existe dans listeStation
							if(j==listeStation.size()) {
								sortie_last=true;
								//--- cas ou depart et arrivee existe dans listeStation
								if(!depart && !arriv) {
									int indice_trajet=0;
									for(Trajet t: listeStation.get(x).getListeTrajets()){
										indice_trajet++;
										if(t.getArrivee().getNom().equalsIgnoreCase(listeStation.get(y).getNom()) && t.getDepart().getNom().equalsIgnoreCase(listeStation.get(x).getNom())) {
											t.addHoraire(ParseurXML.horaire(junction.get(2)));
											break;
										}
										if(indice_trajet==listeStation.get(x).getListeTrajets().size()){
											Trajet e =new Trajet(listeStation.get(x),listeStation.get(y),MoyenTransport.TRAIN,duree);
											e.addHoraire(ParseurXML.horaire(junction.get(2)));
											listeStation.get(x).addTrajet(e);
											break;
										}
									}
									
									if(listeStation.get(x).getListeTrajets().size()==0) {
										Trajet e =new Trajet(listeStation.get(x),listeStation.get(y),MoyenTransport.TRAIN,duree);
										e.addHoraire(ParseurXML.horaire(junction.get(2)));
										listeStation.get(x).addTrajet(e);
									}
									break;
								}
								//---cas ou depart et arrivee sont pas present dans listeStation.
								if(depart && arriv) {
									Station d= new Station(junction.get(0));
									Station p= new Station(junction.get(1));
									Trajet e =new Trajet(d,p,MoyenTransport.TRAIN,duree);
									e.addHoraire(ParseurXML.horaire(junction.get(2)));
									d.addTrajet(e);
									listeStation.add(d);
									listeStation.add(p);
									break;
								}
								//--- cas depart absent && arrivee present.
								if(depart && !arriv) {
									Station d= new Station(junction.get(0));
									Trajet e =new Trajet(d,listeStation.get(y),MoyenTransport.TRAIN,duree);
									e.addHoraire(ParseurXML.horaire(junction.get(2)));
									d.addTrajet(e);
									listeStation.add(d);
									break;
								}
								//---cas depart present && arrivee absent.
								if(!depart && arriv) {
									Station p= new Station(junction.get(1));
									Trajet e =new Trajet(listeStation.get(x),p,MoyenTransport.TRAIN,duree);
									e.addHoraire(ParseurXML.horaire(junction.get(2)));
									listeStation.get(x).addTrajet(e);
									listeStation.add(p);
									break;
								}
							}
						}
						//sortie de la boucle "for"
						if(sortie_last) {sortie_last =false;break;}
					}
				}
			}
		}
	}
	}
