package logika;

public enum Stanje {
	NA_POTEZI_X,
	NA_POTEZI_O,
	ZMAGA_X,
	ZMAGA_O,
	NEODLOCENO;
	
	/**
	 * Zmagovalna trojka, ali null, če je ni
	 */
	private Terica zmagovalna;
	
	private Stanje() { zmagovalna = null; }

	public void setZmagovalna(Terica t) {
		assert (this == ZMAGA_O || this == ZMAGA_X);
		zmagovalna = t;
	}
	
	public Terica getZmagovalna() {
		return zmagovalna;
	}
}
