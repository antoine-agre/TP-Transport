package parseur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import donnees.MoyenTransport;
import donnees.Station;

public class ParseurXML {

	public static void main(String[] args) {}
		
	public static ArrayList<Station> parsingXML(){
		ArrayList<Station> listeStationTram,listeStationTrain;
		try {
			SAXParserFactory factory= SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			HandlerTram  handlerTram =  new HandlerTram();
			HandlerTrain handlerTrain = new HandlerTrain();
		
			try {
				saxParser.parse("./src/fichiers/tram.xml",handlerTram);
				saxParser.parse("./src/fichiers/train.xml",handlerTrain);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		listeStationTram = HandlerTram.listeStation;
		listeStationTrain = HandlerTrain.listeStation;
		
		return listeStationTrain ;
		}
}
	
