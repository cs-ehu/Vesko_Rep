package exceptions;

public class OverlappingClientExists extends Exception {
	private static final long serialVersionUID = 1L;

	public OverlappingClientExists() {
		super();
	}

	public OverlappingClientExists(String s) {
		super(s);
	}

}
