package inteligenca;

import logika.Poteza;

/**
 * Poteza z oceno, kako dobra je ocena.
 * Poteza je lahko tudi {@null} (če je npr. konec igre),
 * v tem primeru ocena pove, kako dobra je pozicija.
 * 
 * @author andrej
 *
 */
public class OcenjenaPoteza {
	Poteza poteza;
	int vrednost;
	
	public OcenjenaPoteza(Poteza poteza, int vrednost) {
		super();
		this.poteza = poteza;
		this.vrednost = vrednost;
	}

	@Override
	public String toString() {
		return "OcenjenaPoteza [poteza=" + poteza + ", vrednost=" + vrednost + "]";
	}
	
}
