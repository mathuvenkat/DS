package three.cross.chess.exception;

public class KnightStrandedException extends Exception{

	private static final long serialVersionUID = 1301496234939469313L;
	private String errorMessage = "LOST !!! Knight has nowhere to go";

	public KnightStrandedException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public KnightStrandedException() {
		super();
	}

	@Override
	public String getMessage(){
		return errorMessage;
	}	

}
