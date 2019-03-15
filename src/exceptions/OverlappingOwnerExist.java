package exceptions;

public class OverlappingOwnerExist extends Exception {
	 private static final long serialVersionUID = 1L;

	public OverlappingOwnerExist() {
		super();
	}
	public OverlappingOwnerExist(String s) {
		super(s);
	}
}
