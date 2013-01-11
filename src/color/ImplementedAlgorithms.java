package color;

public enum ImplementedAlgorithms {
	
	DSATUR(Dsatur.class,"DSATUR");
	
	private Class<? extends ColorationAlgorithm> type;
	private String name;

	ImplementedAlgorithms(Class<? extends ColorationAlgorithm> type, String s) {
		this.type = type;
		this.name = s;
	}

	public Class<? extends ColorationAlgorithm> getAlgoClass() {
		return this.type;
	}

	public String toString() {
		return name;
	}
}
