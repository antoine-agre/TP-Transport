package main;

import donnees.Station;
import java.time.LocalTime;
import donnees.MoyenTransport;
import parseur.ParseurCar;
import parseur.ParseurMetro;
import parseur.ParseurXML;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void rechercheItineraire(ArrayList<Station> listeStations){

		Scanner scanner = new Scanner(System.in);

		while(true) {

			System.out.println("Gare de départ : ");
			String depart = scanner.nextLine();
			System.out.println("Gare d'arrivée : ");
			String arrivee = scanner.nextLine();
			System.out.println("Heure de départ (format HH:MM) : ");
			String temps = scanner.nextLine();

			if(!Station.listeContient(listeStations, depart)) {
				System.err.println("La station de départ n'existe pas.");
				continue;
			}
			else if(!Station.listeContient(listeStations, arrivee)) {
				System.err.println("La station d'arrivée n'existe pas.");
				continue;
			}

			System.out.println("Arrivée : " + arrivee);
			System.out.println("Départ : " + depart);


			Integer heures = Integer.parseInt(temps.strip().substring(0, 2));
			Integer minutes = Integer.parseInt(temps.strip().substring(3, 5));

			System.out.println("LocalTime : " + LocalTime.of(heures, minutes));

			Itineraire.trouveChemin(listeStations, Station.listeGet(listeStations, depart),
					Station.listeGet(listeStations, arrivee), LocalTime.of(heures, minutes));

			//Itineraire.trouveChemin(listeStations, Station.listeGet(listeStations, "Limo"),
			//		Station.listeGet(listeStations, "Parc"), LocalTime.of(7,0));

		}


	}

    public static void main(String[] args) {
	
		ArrayList<Station> listeStations = new ArrayList<Station>();

		listeStations = Station.unionStation(ParseurXML.parseXML("tram.xml", MoyenTransport.TRAM),
				ParseurXML.parseXML("train.xml", MoyenTransport.TRAIN));
		listeStations = Station.unionStation(listeStations, ParseurMetro.parseMetro("metro.txt"));
		listeStations = Station.unionStation(listeStations, ParseurCar.parseCar("InterCites.txt"));
		
		//System.out.println(listeStations);
		//System.out.println(Station.listeGet(listeStations, "Gare"));

        //Itineraire.trouveChemin(listeStations, Station.listeGet(listeStations, "Limo"),
		//		Station.listeGet(listeStations, "Parc"), LocalTime.of(7,0));

		rechercheItineraire(listeStations);

		
    }
}