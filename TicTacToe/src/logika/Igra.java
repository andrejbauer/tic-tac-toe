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
		int[][] smer = {{1,0}, {0,1}, {1,1}, {1,-1}};
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int[] s : smer) {
					int dx = s[0];
					int dy = s[1];
					// če je skrajno polje trojke še na plošči, jo dodamo med trojke
					if ((0 <= x + (N-1) * dx) && (x + (N-1) * dx < N) && 
						(0 <= y + (N-1) * dy) && (y + (N-1) * dy < N)) {
						int[] trojka_x = new int[N];
						int[] trojka_y = new int[N];
						for (int k = 0; k < N; k++) {
							trojka_x[k] = x + dx * k;
							trojka_y[k] = y + dy * k;
						}
						trojke.add(new Trojka(trojka_x, trojka_y));
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
	 * @param igra nova kopija dane igre
	 */
	public Igra(Igra igra) {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = igra.plosca[i][j];
			}
		}
		this.naPotezi = igra.naPotezi;
	}

	public Polje[][] getPlosca() {
		return plosca;
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
	 * Ta metoda bi lahko bila prepočasna. Ideje za pohitritev:
	 * ...
	 * 
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
				Stanje s = Stanje.ZMAGA_X;
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
