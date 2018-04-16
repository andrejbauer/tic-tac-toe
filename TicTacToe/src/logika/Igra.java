package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {

	/**
	 * Velikost igralne pološče je N x N.
	 */
	public static final int N = 3;

	
	/**
	 * Pomožen seznam vseh trojk na plošči.
	 */
	private static final List<Trojka> trojke = new LinkedList<Trojka>();

	// Atributi objekta iz razreda Igra

	private Polje[][] plosca;
	private Igralec naPotezi;

	{   // Ta koda se izvede na začetku, ko se prvič požene program.
		// Njena naloga je, da inicializira vrednosti statičnih
		// spremenljivk.
		
		// Iniciraliziramo trojke
		int[][] smer = {{1,0}, {0,1}, {1,1}};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int[] s : smer) {
					int di = s[0];
					int dj = s[1];
					if ((i + (N-1) * di < N) && (j + (N-1) * dj < N)) {
						int[] x = new int[3];
						int[] y = new int[3];
						for (int k = 0; k < N; k++) {
							x[0] = i + di * k;
							y[1] = j + dj * k;
						}
						trojke.add(new Trojka(x, y));
					}
				}
			}
		}
	}

	/**
	 * Nova igra, v začetni poziciji je prazna in na potezi je O.
	 */
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.O;
	}

	/**
	 * @return seznam možnih potez
	 */
	public List<Poteza> poteze() {
		LinkedList<Poteza> ps = new LinkedList<Poteza>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					ps.add(new Poteza(i, j));
				}
			}
		}
		return ps;
	}

	/**
	 * @param t
	 * @param p
	 * @return ali so v trojki t vsa polja enaka polju p?
	 */
	private boolean vse_enake(Trojka t, Polje p) {
		for (int k = 0; k < N; k++) {
			if (plosca[t.x[k]][t.y[k]] != p) { return false; }
		}
		return true;
	}

	/**
	 * @return trenutno stanje igre
	 */
	public Stanje stanje() {
		// Ali imamo zmagovalca?
		for (Trojka t : trojke) {
			if (vse_enake(t, Polje.O)) {
				// Našli smo trojko s tremi O
				Stanje s = Stanje.ZMAGA_O;
				s.setZmagovalna(t);
				return s;
			}
			else if (vse_enake(t, Polje.X)) {
				Stanje s = Stanje.ZMAGA_O;
				s.setZmagovalna(t);
				return s;
			}
		}
		// Ali imamo kakšno prazno polje?
		// Če ga imamo, igre ni konec in je nekdo na potezi
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					if (naPotezi == Igralec.O) {
						return Stanje.NA_POTEZI_O;
					}
					else {
						return Stanje.NA_POTEZI_X;
					}
				}
			}
		}
		// Polje je polno, rezultat je neodločen
		return Stanje.NEODLOCENO;
	}

	/**
	 * Odigraj potezo p.
	 * 
	 * @param p
	 * @return true, če je bila poteza uspešno odigrana
	 */
	public boolean odigraj(Poteza p) {
		if (plosca[p.getX()][p.getY()] == Polje.PRAZNO) {
			plosca[p.getX()][p.getY()] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			return true;
		}
		else {
			return false;
		}
	}
}
