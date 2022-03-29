package main;

import donnees.MoyenTransport;
import parseur.ParseurCar;
import parseur.ParseurMetro;
import parseur.ParseurXML;
import regex.FormatCheck;

public class Main {

    public static void main(String[] args) {
		//System.out.println(ParseurXML.parseXML("train.xml", MoyenTransport.TRAIN));
		//System.out.println(ParseurXML.parseXML("tram.xml", MoyenTransport.TRAM));
		//System.out.println(ParseurMetro.parseMetro("metro.txt"));
		//System.out.println(ParseurCar.parseCar("InterCites.txt"));
  
		System.out.println("Car : " + FormatCheck.checkCar("InterCites.txt"));
		System.out.println("Métro : " + FormatCheck.checkMetro("metro.txt"));
		System.out.println("Train : " + FormatCheck.checkTrain("train.xml"));
		System.out.println("Tram : " + FormatCheck.checkTram("tram.xml"));
    }
}
