package three.cross.chess.exception;

public class InitializeException extends Exception{

	private static final long serialVersionUID = -1301721297661147440L;
	
	private String errorMessage = "Could not initialize...";
	
	public InitializeException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public InitializeException() {
		super();
	}

	@Override
	public String getMessage(){
		return errorMessage;
	}	


}
