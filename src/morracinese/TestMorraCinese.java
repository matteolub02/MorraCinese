package morracinese;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMorraCinese {	
	Partita generaPartita = new Partita();
	
	@Test //Stampa comportamento morra cinese
	public void testMorraCineseRandom () throws Exception{
		Partita partita = generaPartita.creaPartitaMorraCineseNormale(false);
		int indiceComputer = partita.generaSegnoComputeRandom("SASSO");
		partita.stampaSegniUsati("SASSO", indiceComputer);
		System.out.print(partita.faseAttacco("SASSO", indiceComputer));
	}
	
	@Test //Test lotta sasso contro carta 
	public void testMorraCineseSassoCarta() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseNormale(false);
		assertEquals(-1, partita.faseAttacco("SASSO", 1));
	}
	
	@Test //Test lotta sasso contro forbice 
	public void testMorraCineseSassoForbice() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseNormale(false);
		assertEquals(1, partita.faseAttacco("SASSO", 2));
	}
	@Test //Test lotta sasso contro sasso
	public void testMorraCineseSassoSasso() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseNormale(false);
		assertEquals(0, partita.faseAttacco("SASSO", 0));
	}
	
	@Test
	public void testMorraCineseDifficile() throws Exception { //Controlla che non ci sia caso di parità
		Partita partita = generaPartita.creaPartitaMorraCineseNormale(true);
		for (int i = 0; i < 100; i++) {
			assertNotEquals(0, partita.faseAttacco("SASSO", partita.generaSegnoComputeRandom("SASSO")));
		}
	}
	
	@Test
	public void testMorraCineseSpeciale() throws Exception { //Controlla che non ci sia caso di parità
		Partita partita = generaPartita.creaPartitaMorraCineseSpeciale();
		partita.getMatrice().stampaMatrice();
	}
	
	@Test //Test lotta spock contro carta
	public void testMorraCineseSpecialeSpockCarta() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseSpeciale();
		assertEquals(-1, partita.faseAttacco("SPOCK", 1));
	}
	
	@Test //Test lotta spock contro forbici
	public void testMorraCineseSpecialeSpockForbici() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseSpeciale();
		assertEquals(1, partita.faseAttacco("SPOCK", 2));
	}
	
	@Test
	public void testMorraCineseSpecialeSpockSpock() throws Exception {
		Partita partita = generaPartita.creaPartitaMorraCineseSpeciale();
		assertEquals(0, partita.faseAttacco("SPOCK", 3));
	}
}