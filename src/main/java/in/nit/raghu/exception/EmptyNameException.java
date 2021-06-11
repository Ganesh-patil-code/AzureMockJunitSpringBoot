package in.nit.raghu.exception;

public class EmptyNameException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyNameException() {
		super();
	}
	
	public EmptyNameException(String message){
		super(message);
	}

}
