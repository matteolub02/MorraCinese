package morracinese;

import java.io.FileNotFoundException;

/**
 * @author Matteo Lublanis
 * <p>Classe Main: scegli modalità di gioco fra 3 disponibili.</p>
 *
 */
public class Main {
	
	private static final String MSGBENVENUTO = "MORRA CINESE! Quale modalità vuoi giocare?\n0)Morra cinese normale\n"
			+"1) Morra cinese difficile\n"
            +"2) Morra cinese con lizard e spock\n";
	private static final int MASSIMASCELTA = 2;		
	
	public static void main(String args[]) throws FileNotFoundException {
		Partita generaPartita = new Partita();
		int scelta = InputDati.leggiIntero(MSGBENVENUTO, 0, MASSIMASCELTA);
		switch (scelta) {
		case 0:
			Partita partita = generaPartita.creaPartitaMorraCineseNormale(false);
			UIManager.controlloPartita(partita);
			break;
		case 1:
			Partita partita1 = generaPartita.creaPartitaMorraCineseNormale(true);
			UIManager.controlloPartita(partita1);
			break;
		case 2:
			Partita partita2 = generaPartita.creaPartitaMorraCineseSpeciale();
			UIManager.controlloPartita(partita2);
			break;
		}

	}
}
