package morracinese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * @author Matteo Lublanis
 * {@summary} Classe Partita: gestisce partita e sua modalità
 *
 */
public class Partita {
	
	private static final String SEGNO_NON_PRESENTE = "Segno non presente!";
	private static final String QUALE_SEGNO_VUOI_SCEGLIERE = "\nQuale segno vuoi scegliere?";
	private static final int DIMENSIONEMORRACINESENORMALE = 3, DIMENSIONEMORRACINESESPECIALE = 5;
	private static final String[] ELEMENTIMORRACINESE = {"SASSO", "CARTA", "FORBICE"};
	private static final String[] ELEMENTIMORRACINESESPECIALE = {"SPOCK", "LUCERTOLA"};
	private boolean partitaDifficile;
	private HashMap<String, Integer> indiceSegni; //Per trovarli su matrice di adiacenza
	private ArrayList<String> segniInGioco;
	private MatricePotenza matricePotenza;
	private int numeroVittorieGiocatore = 0, numeroVittorieComputer = 0, numeroPareggi = 0;
	public Partita() { }
	
	public Partita(HashMap<String, Integer> indiceSegni, ArrayList<String> segniInGioco, MatricePotenza matricePotenza, boolean partitaDifficile) {
		this.indiceSegni = indiceSegni;
		this.segniInGioco = segniInGioco;
		this.matricePotenza = matricePotenza;
		this.partitaDifficile = partitaDifficile;
	}
	
	public Partita creaPartitaMorraCineseNormale (boolean isDifficile) {
		HashMap<String, Integer> indice = new HashMap<String, Integer>();
		ArrayList<String> segni = new ArrayList<>();
		MatricePotenza matrice = new MatricePotenza(DIMENSIONEMORRACINESENORMALE);
		matrice.impostaMorraCineseNormale();
		for (int i = 0; i < ELEMENTIMORRACINESE.length; i++) {
			String segno = (ELEMENTIMORRACINESE[i]);
			indice.put(ELEMENTIMORRACINESE[i], i);
			segni.add(segno);
		}
		return new Partita(indice, segni, matrice, isDifficile);
	}
	
	public Partita creaPartitaMorraCineseSpeciale () {
		HashMap<String, Integer> indice = new HashMap<String, Integer>();
		ArrayList<String> segni = new ArrayList<>();
		MatricePotenza matrice = new MatricePotenza(DIMENSIONEMORRACINESESPECIALE);
		matrice.impostaMorraCineseSpeciale();
		for (int i = 0; i < ELEMENTIMORRACINESE.length + ELEMENTIMORRACINESESPECIALE.length; i++) {
			String segno;
			if (i < ELEMENTIMORRACINESE.length) {
				segno = (ELEMENTIMORRACINESE[i]);
				indice.put(ELEMENTIMORRACINESE[i], i);
				segni.add(segno);
			}
			else {
				segno = (ELEMENTIMORRACINESESPECIALE[i-3]);
				indice.put(ELEMENTIMORRACINESESPECIALE[i-3], i);
				segni.add(segno);
			}
		}
		return new Partita(indice, segni, matrice, false);
	}
	
	public String scegliSegno() {
		String segno = ("");
		do {
			for (int i = 0; i < segniInGioco.size(); i++) System.out.print(segniInGioco.get(i) + "\t");
			segno = (InputDati.leggiStringa(QUALE_SEGNO_VUOI_SCEGLIERE).toUpperCase()); 
			if (!segniInGioco.contains(segno)) System.out.println(SEGNO_NON_PRESENTE);
		} while (!segniInGioco.contains(segno));
		return segno.toUpperCase();
	}
	
	public int faseAttacco (String segnoGiocatore, int indiceSegnoComputer) {
		int indiceSegnoGiocatore = indiceSegni.get(segnoGiocatore);
		return matricePotenza.getPotenza(indiceSegnoGiocatore, indiceSegnoComputer);
	}
	
	public void stampaSegniUsati (String segnoGiocatore, int indiceSegnoComputer) {
		System.out.println("Giocatore: " + segnoGiocatore + "\tComputer: " + segniInGioco.get(indiceSegnoComputer));
	}
	
	public int generaSegnoComputeRandom(String segnoGiocatore) {
		Random rand = new Random();
		if (!partitaDifficile) return rand.nextInt(0, segniInGioco.size());
		else {
			if (segniInGioco.size() == 3) {
				int indice;
				do {
					indice = rand.nextInt(0, segniInGioco.size());
				} while (indice == indiceSegni.get(segnoGiocatore));
				return indice;
			}
			else {
				int indiceDaEvitare, indice;
				do {
					indiceDaEvitare = rand.nextInt(0, segniInGioco.size());
				} while (faseAttacco(segnoGiocatore, indiceDaEvitare) != 1);
				do {
					indice = rand.nextInt(0, segniInGioco.size());
				} while(indice == indiceDaEvitare || indice == indiceSegni.get(segnoGiocatore));
				return indice;
			}
		}
	}
	
	public MatricePotenza getMatrice() {
		return matricePotenza;
	}

	public int getNumeroVittorieComputer() {
		return numeroVittorieComputer;
	}

	public void setNumeroVittorieComputer(int numeroVittorieComputer) {
		this.numeroVittorieComputer = numeroVittorieComputer;
	}

	public int getNumeroVittorieGiocatore() {
		return numeroVittorieGiocatore;
	}

	public void setNumeroVittorieGiocatore(int numeroVittorieGiocatore) {
		this.numeroVittorieGiocatore = numeroVittorieGiocatore;
	}

	public int getNumeroPareggi() {
		return numeroPareggi;
	}

	public void setNumeroPareggi(int numeroPareggi) {
		this.numeroPareggi = numeroPareggi;
	}
}
