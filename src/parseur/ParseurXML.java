package parseur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseurXML {

	public static void main(String[] args) {
		try {
			//tram
			SAXParserFactory factory= SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			HandlerTram handler = new HandlerTram();
			
			//train
			SAXParserFactory factory1= SAXParserFactory.newInstance();
			SAXParser saxParser1 = factory1.newSAXParser();
			HandlerTrain handler1 = new HandlerTrain();
			try {
				//tram
				saxParser.parse("C:\\Users\\USER\\eclipse-workspace\\TP-Transport\\src\\fichiers\\tram.xml",handler);
				//train
				saxParser.parse("C:\\Users\\USER\\eclipse-workspace\\TP-Transport\\src\\fichiers\\tram.xml",handler1);
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
		

	}

}
