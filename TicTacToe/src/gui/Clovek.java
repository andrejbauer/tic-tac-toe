package gui;

import logika.Igralec;
import logika.Poteza;

/**
 * Objekt, ki igra človekove poteze.
 * 
 * @author andrej
 */
public class Clovek extends Strateg {
	private GlavnoOkno master;
	private Igralec jaz;
	
	public Clovek(GlavnoOkno master, Igralec jaz) {
		this.master = master;
		this.jaz = jaz;
	}
	
	@Override
	public void na_potezi() {
		// Ignoriramo sporočilo, da smo zdaj na potezi.
	}

	@Override
	public void prekini() {
		// Ignoriramo sporočilo, da nismo več na potezi.
	}

	@Override
	public void klik(int i, int j) {
		master.odigraj(new Poteza(i, j));
	}

}
