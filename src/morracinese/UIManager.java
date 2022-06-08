package morracinese;

import java.io.FileNotFoundException;


public class UIManager {	
	
	public static void controlloPartita(Partita partita) throws FileNotFoundException {
		boolean finito = false;
		do {
			int scelta = InputDati.leggiIntero("0) Gioca 1) Termina\n", 0, 1);
			switch (scelta) {
			case 0:
				String segno = partita.scegliSegno();
				int indiceComputer = partita.generaSegnoComputeRandom(segno);
				partita.stampaSegniUsati(segno, indiceComputer);
				int risultatoLotta = partita.faseAttacco(segno, indiceComputer);
				if (risultatoLotta == 0) {
					System.out.println("PARITA");
					partita.setNumeroPareggi(partita.getNumeroPareggi() + 1);
				}
				else if (risultatoLotta == 1) {
					System.out.println("VITTORIA");
					partita.setNumeroVittorieGiocatore(partita.getNumeroVittorieGiocatore() + 1);
				}
				else {
					System.out.println("SCONFITTA");
					partita.setNumeroVittorieComputer(partita.getNumeroVittorieComputer() + 1);
				}
				break;
			case 1:
				finito = true;
				break;
			}
		} while (!finito);
		CreaFile.salvaSuFile(partita);
		System.out.print("TERMINAZIONE PROGRAMMA...");
	}
}
