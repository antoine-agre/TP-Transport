package parseur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseurTram {

	public static void main(String[] args) {
		try {

			SAXParserFactory factory= SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			HandlerLecture1 handler = new HandlerLecture1();

			try {
				saxParser.parse("tram.xml",handler);
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
