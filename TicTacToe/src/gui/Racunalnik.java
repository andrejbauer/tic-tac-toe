package gui;

import javax.swing.SwingWorker;

import inteligenca.NakljucnaInteligenca;
import logika.Poteza;

/**
 * @author andrej
 *
 * Objekt, ki igra računalnikove poteze. Odvisen je od "inteligence",
 * ki je zenkrat fiksirana na naključno izbiro poteze.
 */
public class Racunalnik extends Strateg {
	private GlavnoOkno master;
	private SwingWorker<Poteza,Object> mislec;
	
	/**
	 * Ustvari nov objekt, ki vleče računalnikove poteze.
	 * 
	 * @param master okno, v katerem računalnik vleče poteze
	 */
	public Racunalnik(GlavnoOkno master) {
		this.master = master;
	}
	
	@Override
	public void na_potezi() {
		// Začnemo razmišljati
		mislec = new NakljucnaInteligenca(master);
		mislec.execute();
	}

	@Override
	public void prekini() {
		if (mislec != null) {
			mislec.cancel(true);
		}
	}

	@Override
	public void klik(int i, int j) {
		// Klike ignoriramo
	}

}
