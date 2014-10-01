package three.cross.chess.domain;


@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Chess Board is a simple class which defines the size of the board. It can be enhanced to define a GUI for thre same game." +
				"currently its defined for a 3*3 grid."
		
			
)

public class ChessBoard {

	public static int min_x = 1;
	public static int min_y = 1;
	public static int max_x = 3 ;
	public static int max_y = 3;

	public ChessBoard() {
		super();
		
	}

}
