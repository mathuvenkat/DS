package three.cross.chess.exception;

public class DestroyException extends Exception{

	private static final long serialVersionUID = -2829508398061725078L;

	private String errorMessage = "Could not destroy...";
	
	public DestroyException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public DestroyException() {
		super();
	}

	@Override
	public String getMessage(){
		return errorMessage;
	}	


}
