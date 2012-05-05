package graph;

public final class NoPathFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoPathFoundException(final Object to) {
		super(to.toString());
	}
}
