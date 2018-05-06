package logika;

import java.util.Arrays;

/**
 * @author andrej
 * Objekt, ki predstavlja eno trojko na plošči.
 */
public class Trojka {
	// Trojka na plošči je predstavljena z dvema tabelama doložine 3.
	// To sta tabeli x in y koordinat.
	public int[] x;
	public int[] y;
	
	public Trojka(int[] x, int y[]) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Trojka [x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + "]";
	}
}
