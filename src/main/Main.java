package main;

import regex.FormatCheck;

public class Main {

    public static void main(String[] args) {
		System.out.println("Car : " + FormatCheck.checkCar("InterCites.txt"));
		System.out.println("Métro : " + FormatCheck.checkMetro("metro.txt"));
		System.out.println("Train : " + FormatCheck.checkTrain("train.xml"));
		System.out.println("Tram : " + FormatCheck.checkTram("tram.xml"));
    }
}
