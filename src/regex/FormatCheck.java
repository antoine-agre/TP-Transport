package regex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Scanner;
import java.util.regex.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * Contient les fonctionnalités de vérification de fichiers du programme.
 *
 * <p> Des méthodes sont définies pour chaque format de fichier (au sens de chaque exploitant),
 * qui prennent en argument le nom du fichier à vérifier.
 *
 * <p> Une méthode générale servira à tester tous les fichiers, et utilisera les méthodes pour chaque fichier.
 */
public final class FormatCheck {

    //Pas de constructeur ; toutes les méthodes seront statiques

    /**
     * Vérifie si le fichier, dont le nom est passé en paramètre, correspond au modèle attendu de
     * l'exploitant du réseau de car intercité.
     * @param nomFichier le nom du fichier à vérifier, qui sera recherché dans src/fichiers.
     * @return un booléen indiquant si le fichier correspond bien au modèle attendu.
     */
	public static boolean checkCar(String nomFichier){

        try {

            Pattern ligneLiaison = Pattern.compile("\\w+\\s+\\w+\\s+\\d+");
            Pattern ligneHoraire = Pattern.compile("\\w+\\s+\\w+\\s+\\d{4}");
            Pattern ligneSeparateur = Pattern.compile("//");
            Pattern ligneCommentaire = Pattern.compile("%.*");

            Scanner scan = new Scanner(new File("./src/fichiers/" + nomFichier));

            boolean separateurRencontre = false; //indique si séparateur déjà rencontré
            String line;
            int i = 0; //compteur numéro de ligne

            while (scan.hasNext()){

                line = scan.nextLine();
                i++;

                if(ligneCommentaire.matcher(line).matches()){ //Commentaire
                    //System.out.println(i + " Commentaire");
                }
                else if(ligneSeparateur.matcher(line).matches()){ //Séparateur
                    if(separateurRencontre){
                        System.err.println("Erreur : ligne " + i + " : 2ème séparateur rencontré.");
                        return false;
                    } else{
                        separateurRencontre = true;
                        //System.out.println(i + " Séparateur");
                    }
                }
                else if(separateurRencontre){ //après séparateur
                    if(ligneHoraire.matcher(line).matches()){
                        //System.out.println(i + " Horaire");
                    }
                    else if(ligneLiaison.matcher(line).matches()){
                        System.err.println("Erreur : ligne " + i + " : Mauvais format d'heure.");
                        return false;
                    }
                    else{
                        System.err.println("Erreur : ligne " + i + " : Format non reconnu.");
                        return false;
                    }
                }
                else { //avant séparateur
                    if(ligneLiaison.matcher(line).matches()){
                        //System.out.println(i + " Liaison");
                    }
                    else{
                        System.err.println("Erreur : ligne " + i + " : Format non reconnu.");
                        return false;
                    }
                }

            }

            return true;


        } catch(IOException e){e.printStackTrace(); return false;}

    }

	/**
	 * Vérifie si le fichier, dont le nom est passé en paramètre, correspond au modèle attendu de
	 * l'exploitant du réseau de métro.
	 * @param nomFichier le nom du fichier à vérifier, qui sera recherché dans src/fichiers.
	 * @return un booléen indiquant si le fichier correspond bien au modèle attendu.
	 */
	public static boolean checkMetro(String nomFichier) {
		try {
			
			//les patterns pour les differents matching
			Pattern ligneLiaison = Pattern.compile("([A-Z]\\w+\\s+){2}\\d+");
			Pattern ligneHeure = Pattern.compile("\\d{4}");
            Pattern ligneIntervalle = Pattern.compile("\\d+");
            Pattern ligneStation = Pattern.compile("([A-Z]\\w+\\s+)+");
            
            // format utf8
			InputStreamReader file = new InputStreamReader(new FileInputStream("./src/fichiers/" + nomFichier),"utf8");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			String ligne; // recupère l'element de chaque ligne
			
			int compteur = 0; // sert à situer la ligne
			boolean liaison = false;  // active et desactive l'attente d'une ligne liaison
			
			
			//debut de la lecture du fichier
			while(scanner.hasNextLine()){

				ligne = scanner.nextLine();
				compteur++;
				
				//si la ligne est vide
				if(ligne.isEmpty()) {
					//System.out.println(compteur + " ligne vide");
                    continue;
				}
				
				//titre
				if(ligne.equalsIgnoreCase("%depart arrivee duree")) {
					//System.out.println(compteur + " titre : " + ligne);
					liaison = true;
					continue;
				}
				
				//titre
				if(ligne.equalsIgnoreCase("%stations")) {
					//System.out.println(compteur + " titre : " + ligne);
					ligne = scanner.nextLine();
					compteur++;
					if(ligneStation.matcher(ligne).matches()){
                        //System.out.println(compteur + " liste de stations");
                        continue;
                    }
					else {
						System.err.println("Erreur : ligne " + compteur + " : Liste de stations attendue.");
						return false;
                    }
				}
				
				//titre
				if(ligne.equalsIgnoreCase("%toutes les x minutes")) {
					//System.out.println(compteur + " titre : " + ligne);
					ligne = scanner.nextLine();
					compteur++;
					if(ligneIntervalle.matcher(ligne).matches()){
                        //System.out.println(compteur + " minutes d'intervalles entre les departs");
                        continue;
                    }
					else {
						System.err.println("Erreur : ligne " + compteur + " : Intervalle attendu.");
						return false;
                    }
				}
				
				//titre
				Collator coll = Collator.getInstance();
				coll.setStrength(Collator.NO_DECOMPOSITION);

				if(coll.compare(ligne, "%à partir de") == 0){
					//System.out.println(compteur + " titre : " + ligne);
					liaison = false;
					ligne = scanner.nextLine();
					compteur++;
					if(ligneHeure.matcher(ligne).matches()){
                        //System.out.println(compteur + " Heure du premier depart");
                        continue;
                    }
					else {
						System.err.println("Erreur : ligne " + compteur + " : Heure attendue.");
						return false;
					}
				}
				
				
				if(ligne.equalsIgnoreCase("%dernier départs de Gare")) {
					//System.out.println(compteur + " titre : " + ligne);
					ligne=scanner.nextLine();
					compteur++;
					if(ligneHeure.matcher(ligne).matches()){
                        //System.out.println(compteur + "Heure du  dernier depart");
                        continue;
                    }
					else {
						System.err.println("Erreur : ligne " + compteur + " : Heure attendue.");
						return false;
                    }
				}
				 
				//ligne liaison
				if(liaison) {
					if(ligneLiaison.matcher(ligne).matches()){
                        //System.out.println(compteur + " liaison");
                        continue;
                    }
					else {
						System.err.println("Erreur : ligne " + compteur + " : Ligne de liaison attendue.");
						return false;
                    }
				}
				
				// autres ligne
				else {
					//System.out.println(compteur+" titre : " + ligne);
				}
			}
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return true;
	}

	/**
	 * Vérifie si le fichier, dont le nom est passé en paramètre, correspond au modèle attendu de
	 * l'exploitant du réseau trains.
	 * @param nomFichier le nom du fichier à vérifier, qui sera recherché dans src/fichiers.
	 * @return un booléen indiquant si le fichier correspond bien au modèle attendu.
	 */
	public static boolean checkTrain(String nomFichier) {
			
			try {
				SAXParserFactory factoryTrain= SAXParserFactory.newInstance();
				SAXParser saxParserTrain = factoryTrain.newSAXParser();
				AnalyseurTrain handlerTrain = new AnalyseurTrain();
			
				try {
					saxParserTrain.parse("./src/fichiers/" + nomFichier,handlerTrain);
				}catch (IOException e) {e.printStackTrace();}
				
			}catch (ParserConfigurationException e) {e.printStackTrace();}
			 catch (SAXException e) {e.printStackTrace();}	
			
			
			return AnalyseurTrain.resultat ;
		}

	/**
	 * Vérifie si le fichier, dont le nom est passé en paramètre, correspond au modèle attendu de
	 * l'exploitant du réseau de tram.
	 * @param nomFichier le nom du fichier à vérifier, qui sera recherché dans src/fichiers.
	 * @return un booléen indiquant si le fichier correspond bien au modèle attendu.
	 */
	public static boolean checkTram(String nomFichier) {
		try {
			SAXParserFactory factoryTram= SAXParserFactory.newInstance();
			SAXParser saxParserTram = factoryTram.newSAXParser();
			AnalyseurTram  handlerTram =  new AnalyseurTram();
			try {
				saxParserTram.parse("./src/fichiers/" + nomFichier,handlerTram);
				
			}catch (IOException e) {e.printStackTrace();}
			
		}catch (ParserConfigurationException e) {e.printStackTrace();}
		 catch (SAXException e) {e.printStackTrace();}	
		
		return AnalyseurTram.resultat ;
	}

	
	public static void main(String[] args) {
		System.out.println("PATH : " + new File(".").getAbsolutePath());
		System.out.println("Métro : " + checkMetro("metro.txt"));
		System.out.println("Train : " + checkTrain("train.xml"));
		System.out.println("Tram : " + checkTram("tram.xml"));
    }

}
