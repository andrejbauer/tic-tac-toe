package logika;

/**
 * @author andrej
 * Možne vrednosti polj na plošči.
 */
public enum Polje {
	PRAZNO,
	X,
	O;

	public String toString() {
		switch (this) {
		case PRAZNO: return " ";
		case X: return "X";
		case O: return "O";
		default: return "?";
		}
	}
}
