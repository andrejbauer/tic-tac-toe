package test;

import junit.framework.TestCase;
import logika.Stanje;
import logika.Terica;

public class TestStanje extends TestCase {

	public void testSetZmagovalna() {
		Terica t = new Terica(new int[] {0, 0, 0}, new int[] {0, 1, 2});
		Stanje s = Stanje.ZMAGA_O;
		s.setZmagovalna(t);
		assertEquals (s.getZmagovalna(), t);
		
//		// Ne sme delovati
//		Stanje u = Stanje.NA_POTEZI_O;
//		u.setZmagovalna(t);
//		fail("Nastavili smo zmagovalno trojko, čeprav ni konec igre.");
	}

}
