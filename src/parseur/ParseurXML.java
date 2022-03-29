package parseur;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

import donnees.MoyenTransport;
import donnees.Station;

public class ParseurXML {

		
	public static ArrayList<Station> parseXML(String fileName, MoyenTransport trans){
		
		try {
			if(trans.equals(MoyenTransport.TRAM)) {
				SAXParserFactory factoryTram= SAXParserFactory.newInstance();
				SAXParser saxParserTram = factoryTram.newSAXParser();
				HandlerTram  handlerTram =  new HandlerTram();
				try {
					saxParserTram.parse("./src/fichiers/"+fileName,handlerTram);
					return HandlerTram.listeStation;
				}catch (IOException e) {e.printStackTrace();}
			}
			else {
				if(trans.equals(MoyenTransport.TRAIN)) {
					SAXParserFactory factoryTrain= SAXParserFactory.newInstance();
					SAXParser saxParserTrain = factoryTrain.newSAXParser();
					HandlerTrain handlerTrain = new HandlerTrain();
					try {
						saxParserTrain.parse("./src/fichiers/"+fileName,handlerTrain);
						return HandlerTrain.listeStation;
					}catch (IOException e) {e.printStackTrace();}
				}
				else
					System.out.println("Moyen de transport non pris en charge");
			}
		}catch (ParserConfigurationException e) {e.printStackTrace();}
		 catch (SAXException e) {e.printStackTrace();}	
		
		return null;
	}
	
	

	public static int calculDuree(String depart,String arrive) {
		
		int duree = Integer.valueOf(arrive)- Integer.valueOf(depart);
		if( ! depart.substring(0,2).equalsIgnoreCase(arrive.substring(0,2)) ){
			String a1= depart.substring(0,2);
			String b1= arrive.substring(0,2);
			duree-=(Integer.valueOf(b1)- Integer.valueOf(a1))*40;
		}
		return duree;
	}
	
	
	public static LocalTime horaire(String depart) {
		String a= depart.substring(0,2);
		String b= depart.substring(2,4);
		return LocalTime.of(Integer.valueOf(a),Integer.valueOf(b));
	}
	
	
	
	
	
	public static void main(String[] args) {
		ArrayList<Station> styu= parseXML("tram.xml",MoyenTransport.TRAM);
		System.out.println(styu);
	    }
	
}
	
