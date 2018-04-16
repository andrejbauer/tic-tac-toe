package test;

import junit.framework.TestCase;
import logika.Igra;
import logika.Stanje;

public class TestLogikaIgre extends TestCase {

	public void testIgra() {
		Igra igra = new Igra();
		// Na začetku je na potezi O
		assertEquals(Stanje.NA_POTEZI_O, igra.stanje());
		// Na začetku imamo N * N potez
		assertEquals(Igra.N * Igra.N, igra.poteze().size());
		// Naredimo eno potezo
		igra.odigraj(igra.poteze().get(0));
		// Sedaj je na potezi X in imamo eno potezo manj
		assertEquals(Stanje.NA_POTEZI_X, igra.stanje());
		assertEquals(Igra.N * Igra.N - 1, igra.poteze().size());
	}

}
