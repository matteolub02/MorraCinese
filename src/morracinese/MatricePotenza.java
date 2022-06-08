package morracinese;

/**
 * @author Matteo Lublanis
 * {@summary} Rappresenta matrice di adiacenza: serve per segnare relazioni tra segni.
 *
 */
public class MatricePotenza {
	private static final int SPOCKPOS = 3;
	private static final int SASSOPOS = 0;
	private static final int CARTAPOS = 1;
	private static final int FORBICIPOS = 2;
	private static final int LUCERTOLAPOS = 4;
	private int[][] matrice; //Matrice adiacenza
	
	public MatricePotenza(int size) {
		matrice = new int[size][size];
	}
	
	public void impostaMorraCineseNormale() {
		/*		SASSO	CARTA	FORBICE
		 * SASSO 0		 -1			1
		 * CARTA 1		  0			-1
		 * FORBICE -1	  1			0
		 * 
		 * Essendo un caso MOLTO particolare, imposto valore per valore
		 */
		for (int i = CARTAPOS, j = SASSOPOS; i < matrice.length; i++) {
			if (i == 1) {
				matrice[i][j] = 1;
				matrice[j][i] = -1;
			}
			else {
				matrice[i][j] = -1;
				matrice[j][i] = 1;
			}
		}
		for (int i = FORBICIPOS, j = 1; i < matrice.length; i++) {
			matrice[i][j] = 1;
			matrice[j][i] = -1;
		}
	}
	
	public void impostaMorraCineseSpeciale() {
		for (int i = matrice.length - 1; i > 0; i--) {
			matrice[i][i-1] = 1;
			matrice[i-1][i] = -1;
		}
		matrice[SASSOPOS][matrice.length - 1] = 1;
		matrice[matrice.length - 1][SASSOPOS] = -1;
		
		for (int i = FORBICIPOS, j = 0; i < matrice.length - 1; i++) {
			if (i == FORBICIPOS) {
				matrice[i][j] = -1;
				matrice[j][i] = 1;
			}
			if (i == 3){
				matrice[i][j] = 1;
				matrice[j][i] = -1;
			}
		}
		for (int i = SPOCKPOS, j = 1; i < matrice.length; i++) {
			if (i == SPOCKPOS) {
				matrice[i][j] = -1;
				matrice[j][i] = 1;
			}
			else {
				matrice[i][j] = 1;
				matrice[j][i] = -1;
			}
		}
		
		matrice[LUCERTOLAPOS][FORBICIPOS] = -1;
		matrice[FORBICIPOS][LUCERTOLAPOS] = 1;
	}
	
	public int getPotenza(int segnoGiocatore, int segnoComputer) {
		return matrice[segnoGiocatore][segnoComputer];
	}
	
	public void stampaMatrice() {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice.length; j++) System.out.print(matrice[i][j] + "\t");
			System.out.println();
		}
	}
}
