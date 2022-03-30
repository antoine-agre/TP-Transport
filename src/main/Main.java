package main;

import donnees.Station;
import java.time.LocalTime;
import donnees.MoyenTransport;
import donnees.Station;
import parseur.ParseurCar;
import parseur.ParseurMetro;
import parseur.ParseurXML;
import regex.FormatCheck;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	
		ArrayList<Station> listeStations = new ArrayList<Station>();

		listeStations = Station.unionStation(ParseurXML.parseXML("tram.xml", MoyenTransport.TRAM),
				ParseurXML.parseXML("train.xml", MoyenTransport.TRAIN));
		listeStations = Station.unionStation(listeStations, ParseurMetro.parseMetro("metro.txt"));
		listeStations = Station.unionStation(listeStations, ParseurCar.parseCar("InterCites.txt"));
		
		//System.out.println(listeStations);
		//System.out.println(Station.listeGet(listeStations, "Gare"));

		listeStations = Itineraire.donneesTest();
		System.out.println(Station.listeGet(listeStations, "E"));

        Itineraire.trouveChemin(listeStations, Station.listeGet(listeStations, "A"),
				Station.listeGet(listeStations, "E"), LocalTime.of(10,24));
		
    }
}