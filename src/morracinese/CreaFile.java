package morracinese;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreaFile {
	public static void salvaSuFile (Partita partita) throws FileNotFoundException{
		File nuovo = new File("statistiche.txt");
		PrintWriter in = new PrintWriter(nuovo);
		try {
			in.println("Vittorie computer: " + partita.getNumeroVittorieComputer() + "\nVittorie giocatore: "
					+ partita.getNumeroVittorieGiocatore() + "\nPareggi: " + partita.getNumeroPareggi());
		}
		finally {
			System.out.println("Vittorie computer: " + partita.getNumeroVittorieComputer() + "\nVittorie giocatore: "
					+ partita.getNumeroVittorieGiocatore()+ "\nPareggi: " + partita.getNumeroPareggi());
			in.close();
		}
	}
}
