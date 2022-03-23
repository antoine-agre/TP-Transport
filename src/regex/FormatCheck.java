package regex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.*;

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
     * @param nomFichier    le nom du fichier à vérifier, qui sera récupéré dans src/fichiers.
     * @return  un booléen indiquant si le fichier correspond bien au modèle attendu.
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
                    System.out.println(i + " Commentaire");
                }
                else if(ligneSeparateur.matcher(line).matches()){ //Séparateur
                    if(separateurRencontre){
                        System.err.println("Erreur : ligne " + i + " : 2ème séparateur rencontré.");
                        return false;
                    } else{
                        separateurRencontre = true;
                        System.out.println(i + " Séparateur");
                    }
                }
                else if(separateurRencontre){ //après séparateur
                    if(ligneHoraire.matcher(line).matches()){
                        System.out.println(i + " Horaire");
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
                        System.out.println(i + " Liaison");
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

    public static void main(String[] args) {
        System.out.println("Résultat : " + checkCar("InterCites.txt"));
    }

}
