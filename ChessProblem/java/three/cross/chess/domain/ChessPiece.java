package three.cross.chess.domain;

import java.util.ArrayList;

@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Every piece involved in the game must inherit this. Currently game support only a knight"
			
)
public interface ChessPiece extends ICreate, IDestroy {
	
	boolean hasNext(SquareLocation loc );
	
	ArrayList<SquareLocation> nextMove (SquareLocation loc);

}
