package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Terica;

/**
 * Ocena trenutne pozicije
 * 
 * @author andrej
 *
 */
public class Ocena {
	public static final int ZMAGA = (1 << 20); // vrednost zmage, več kot vsaka druga ocena pozicije
	public static final int ZGUBA = -ZMAGA;  // vrednost izgube, mora biti -ZMAGA
	public static final int NEODLOCENO = 0;  // vrednost neodločene igre


	/**
	 * @param k
	 * @return koliko je vredna terica, kjer ima igralec k svojih polj, ostala so prazna
	 */
	public static int vrednostTerice(int k) {
		assert (k < Igra.N);
		return (ZMAGA >> (4 * (Igra.N - k))); // hevristična ocena
	}
	
	/**
	 * @param jaz igralec, ki želi oceno
	 * @param igra trentno stanje igre (ne spreminjaj tega objekta!)
	 * @return ocena vrednosti pozicije (če je igre konec, je ocena zagotovo pravilna)
	 */
	public static int oceniPozicijo(Igralec jaz, Igra igra) {
		switch (igra.stanje()) {
		case ZMAGA_O:
			return (jaz == Igralec.O ? ZMAGA : ZGUBA);
		case ZMAGA_X:
			return (jaz == Igralec.X ? ZMAGA : ZGUBA);
		case NEODLOCENO:
			return NEODLOCENO;
		case NA_POTEZI_O:
		case NA_POTEZI_X:
			// Preštejemo, koliko teric ima vsak igralec
			Polje[][] plosca = igra.getPlosca();
			int vrednostX = 0;
			int vrednostO = 0;
			for (Terica t : Igra.terice) {
				int poljaX = 0;
				int poljaO = 0;
				for (int k = 0; k < Igra.N && (poljaX == 0 || poljaO == 0); k++) {
					switch (plosca[t.x[k]][t.y[k]]) {
					case O: poljaO += 1; break;
					case X: poljaX += 1; break;
					case PRAZNO: break;
					}
				}
				if (poljaX == 0 && poljaO > 0) { vrednostO += vrednostTerice(poljaX); }
				if (poljaO == 0 && poljaX > 0) { vrednostX += vrednostTerice(poljaO); }
			}
			// To deljenje z 2 je verjetno brez veze ali celo narobe
			return (jaz == Igralec.X ? (vrednostX - vrednostO/2) : (vrednostO - vrednostX/2));
		}
		assert false;
		return 42; // Java je blesava
	}
	
}
