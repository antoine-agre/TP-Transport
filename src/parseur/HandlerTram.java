package parseur;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

	final class HandlerTram extends DefaultHandler {
	/*Cette classe Handler permet d'extraire des donnees dans un fichier xml en associant evenements sur balise à des intructions definies.
	*Tram.xml
	*
	*-> etatLigne,etatStation,etatHeure : aide gestion des actions liee a l'ouverture et de la fermeture de balise.
	*-> tab: liste contenant les noms des stations .->indice :intervient dans la correspondance station--heure de depart
	*->listeStation : liste des "Station"
	*->depart,arriv et x,y : pour la gestion des station deja presente dans listeStation et dont on besoin dans le processus courant.  
	*-> j et c : serve a identifie des tours de boucle "for" 
	*-> bool: (pour le tout premier remplissage de listeStation)
	*->Sortie_last : de la dernier boucle "for"
	*
	*/
	
	private boolean etatLigne=false,bool=true,sortie_last;
	private boolean etatStation=false;
	private boolean etatHeure=false;
	private int indice=0; 
	private boolean depart,arriv;
	static  ArrayList<Station> listeStation = new ArrayList<>();
	private ArrayList<String> tab = new ArrayList<String>();
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
		
		 //exemple test
		/*int i=-1;
		 for(Station s : listeStation) {
			 i++;
			if(i==0) {
			System.out.println(s.getNom());
			 System.out.println( (s.getListeTrajets().get(0).listeHoraires.size()));
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
			  indice=tab.size();

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
				//reception des donnees, de l'ouverture a la fermerture de la balise stations.
				String liste = new String(ch, start,length);
				String tableau[]= liste.split(" ");
				//repertorie le noms de toutes les stations en gardant leur positions par ordre de reception.
				for(int i=0; i< tableau.length;i++) {	
					tab.add(tableau[i]);
				}
			}
			
			if(etatHeure) {
				//reception des donnees, de l'ouverture a la fermerture de la balise heures-passages.
				String liste=new String(ch,start,length);
				//mise en tableau des donnees.
				String tableauHeure[] = liste.split(" ");
				
				for(int i=0;i< tableauHeure.length-1;i++) {
					//calcul de la duree correspondante.
					int duree=ParseurXML.calculDuree(tableauHeure[i],tableauHeure[i+1]);
					//si la station depart et arrivee sont identique, passe au prochain tour de boucle.
					if(tab.get(i+indice).equalsIgnoreCase(tab.get(i+indice+1))){;continue;}
					
					//bool: au debut du traiement la liste de station est vide -
					//- pour le premier tour de boucle, remplit la liste de station.
					if(bool) {
						Station d= new Station(tab.get(i+indice));
						Station p= new Station(tab.get(i+indice+1));
						Trajet e =new Trajet(d,p,MoyenTransport.TRAM,duree);
						e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
						d.addTrajet(e);
						listeStation.add(d);
						listeStation.add(p);
						bool=false;
					}
					//----------------------------------traitement general(apres le remplissage initial)---------------------------------
					else{
						
						depart=true;
						arriv=true;
						int j=0,x=0,y=0;
						for(Station s1:listeStation) {
							j++;
							if(s1.getNom().equalsIgnoreCase(tab.get(i+indice)) && depart){
								depart=false;
								x=j-1;
							}
							int c=0;
							for(Station s2:listeStation) {
								c++;
								if(s2.getNom().equalsIgnoreCase(tab.get(i+1+indice)) && arriv){
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
											t.addHoraire(ParseurXML.horaire(tableauHeure[i]));
											break;
										}
										if(indice_trajet==listeStation.get(x).getListeTrajets().size()){
											
											Trajet e =new Trajet(listeStation.get(x),listeStation.get(y),MoyenTransport.TRAM,duree);
											e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
											listeStation.get(x).addTrajet(e);
											break;
										}
									}
								
									if(listeStation.get(x).getListeTrajets().size()==0) {
										Trajet e =new Trajet(listeStation.get(x),listeStation.get(y),MoyenTransport.TRAM,duree);
										e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
										listeStation.get(x).addTrajet(e);
									}
									break;
								}
								//---cas ou depart et arrivee sont pas present dans listeStation.
								if(depart && arriv) {
									Station d= new Station(tab.get(i+indice));
									Station p= new Station(tab.get(i+indice+1));
									Trajet e =new Trajet(d,p,MoyenTransport.TRAM,duree);
									e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
									d.addTrajet(e);
									listeStation.add(d);
									listeStation.add(p);
									break;
								}
								//--- cas depart absent && arrivee present.
								if(depart && !arriv) {
									Station d= new Station(tab.get(i+indice));
									Trajet e =new Trajet(d,listeStation.get(y),MoyenTransport.TRAM,duree);
									e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
									d.addTrajet(e);
									listeStation.add(d);
									break;
								}
								//---cas depart present && arrivee absent.
								if(!depart && arriv) {
									Station p= new Station(tab.get(i+indice+1));
									Trajet e =new Trajet(listeStation.get(x),p,MoyenTransport.TRAM,duree);
									e.addHoraire(ParseurXML.horaire(tableauHeure[i]));
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
	
	
	}