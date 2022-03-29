package parseur;

import donnees.MoyenTransport;
import donnees.Station;
import donnees.Trajet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseurCar {

	/**
	 * Parse le fichier InterCites.txt, correspondant au réseau de cars.
	 * @return la liste des stations construites.
	 */
	public static ArrayList<Station> parseCar(String fileName){
        
		ArrayList<Station> output = new ArrayList<Station>();
		File source = new File("./src/fichiers/"+fileName);

		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(source);

			//Liste des liaisons
			while(scanner.hasNext()) {

				String line = scanner.nextLine();

				if(line.startsWith("%")){continue;} //ignore lignes commentées avec %
				if(line.equals("//")){break;} //sort de la boucle while au séparateur '//'

				String[] el = line.split("\\s+");

				if(!Station.listeContient(output, el[0])) {output.add(new Station(el[0]));}
				
				if(!Station.listeContient(output, el[1])) {output.add(new Station(el[1]));}

				Station.listeGet(output, el[0]).addTrajet(new Trajet(
						Station.listeGet(output,el[0]), Station.listeGet(output,el[1]),
						MoyenTransport.CAR, Integer.parseInt(el[2])));
				Station.listeGet(output, el[1]).addTrajet(new Trajet(
						Station.listeGet(output,el[1]), Station.listeGet(output,el[0]),
						MoyenTransport.CAR, Integer.parseInt(el[2])));

			}

			//Liste d'horaires
			while(scanner.hasNext()) {

				String line = scanner.nextLine();

				if(line.startsWith("%")){continue;}

				String[] el = line.split("\\s+");

				int heure = Integer.parseInt(el[2].substring(0, 2));
				int minutes = Integer.parseInt(el[2].substring(2, 4));

				Trajet.listeGet(Station.listeGet(output, el[0]).getListeTrajets(), el[1]).addHoraire(heure, minutes);

			}
			return output;

		} catch (FileNotFoundException e){
			System.err.println("Erreur : fichier non trouvé.");
			e.printStackTrace();
		}


		return null;
	}
	
	
	
	
	public static void main(String[] args){
		System.out.println(parseCar("InterCites.txt"));
	}

}
