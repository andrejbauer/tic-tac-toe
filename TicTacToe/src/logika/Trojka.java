package logika;

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
}
