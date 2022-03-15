package parseur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ParseurCar {

	public static void main(String[] args){

		String cwd = new File("").getAbsolutePath();
		System.out.println("CWD : " + cwd);
		File test = new File("./src/fichiers/InterCites.txt");
		try {
			Scanner scanner = new Scanner(test);
			//FileInputStream stream = new FileInputStream(test);
			System.out.println(scanner.nextLine());
		} catch (FileNotFoundException e){
			System.err.println("Erreur : fichier non trouvé.");
			e.printStackTrace();
		}

	}

}
