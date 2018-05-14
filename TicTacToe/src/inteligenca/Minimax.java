package inteligenca;

import javax.swing.SwingWorker;

import gui.GlavnoOkno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

/**
 * Inteligenca, ki uporabi algoritem minimax.
 * 	
 * @author andrej
 *
 */
public class Minimax extends SwingWorker<Poteza, Object> {

	/**
	 * Glavno okno, v katerem poteka ta igra
	 */
	private GlavnoOkno master;

	/**
	 * Globina, do katere pregleduje minimax
	 */

	private int globina;

	/**
	 * Ali račualnik igra X ali O?
	 */
	private Igralec jaz; // koga igramo
	
	/**
	 * @param master glavno okno, v katerem vlečemo poteze
	 * @param globina koliko potez naprej gledamo
	 * @param jaz koga igramo
	 */
	public Minimax(GlavnoOkno master, int globina, Igralec jaz) {
		this.master = master;
		this.globina = globina;
		this.jaz = jaz;
	}
	
	@Override
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.copyIgra();
		OcenjenaPoteza p = minimax(0, igra);
		assert (p.poteza != null);
		return p.poteza;
	}
	
	@Override
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) { master.odigraj(p); }
		} catch (Exception e) {
		}
	}

	/**
	 * Z metodo minimax poišči najboljšo potezo v dani igri.
	 * 
	 * @param k števec globine, do kje smo že preiskali
	 * @param igra
	 * @return najboljša poteza (ali null, če ji ni), skupaj z oceno najboljše poteze
	 */
	private OcenjenaPoteza minimax(int k, Igra igra) {
		Igralec naPotezi = null;
		// Ugotovimo, ali je konec, ali je kdo na potezi?
		switch (igra.stanje()) {
		case NA_POTEZI_O: naPotezi = Igralec.O; break;
		case NA_POTEZI_X: naPotezi = Igralec.X; break;
		// Igre je konec, ne moremo vrniti poteze, vrnemo le vrednost pozicije
		case ZMAGA_X:
			return new OcenjenaPoteza(
					null,
					(jaz == Igralec.X ? Ocena.ZMAGA : Ocena.ZGUBA));
		case ZMAGA_O:
			return new OcenjenaPoteza(
					null,
					(jaz == Igralec.O ? Ocena.ZMAGA : Ocena.ZGUBA));
		case NEODLOCENO:
			return new OcenjenaPoteza(null, Ocena.NEODLOCENO);
		}
		assert (naPotezi != null);
		// Nekdo je na potezi, ugotovimo, kaj se splača igrati
		if (k >= globina) {
			// dosegli smo največjo dovoljeno globino, zato
			// ne vrnemo poteze, ampak samo oceno pozicije
			return new OcenjenaPoteza(
					null,
					Ocena.oceniPozicijo(jaz, igra));
		}
		// Hranimo najboljšo do sedaj videno potezo in njeno oceno.
		// Tu bi bilo bolje imeti seznam do sedaj videnih najboljših potez, ker je lahko
		// v neki poziciji več enakovrednih najboljših potez. Te bi lahko zbrali
		// v seznam, potem pa vrnili naključno izbrano izmed najboljših potez, kar bi
		// popestrilo igro računalnika.
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		for (Poteza p : igra.poteze()) {
			// V kopiji igre odigramo potezo p
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj(p);
			// Izračunamo vrednost pozicije po odigrani potezi p
			int ocenaP = minimax(k+1, kopijaIgre).vrednost;
			// Če je p boljša poteza, si jo zabeležimo
			if (najboljsa == null // še nimamo kandidata za najboljšo potezo
				|| (naPotezi == jaz && ocenaP > ocenaNajboljse) // maksimiziramo
				|| (naPotezi != jaz && ocenaP < ocenaNajboljse) // minimiziramo
				) {
				najboljsa = p;
				ocenaNajboljse = ocenaP;
			}
		}
		// Vrnemo najboljšo najdeno potezo in njeno oceno
		assert (najboljsa != null);
		return new OcenjenaPoteza(najboljsa, ocenaNajboljse);
	}
	
}
