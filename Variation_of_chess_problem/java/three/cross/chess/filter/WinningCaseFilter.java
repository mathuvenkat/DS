package three.cross.chess.filter;

import three.cross.chess.domain.ChessBoard;
import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;

public class WinningCaseFilter implements IFilter<SquareLocation>{

	@Override
	@MethodHeaderAnnotation
	(		
			input = "SquareLocation",
			comments = "Returns true, if input location co-ordinates equal target location of the game" +
					"otherwise returns false"
	)
	public boolean accept(SquareLocation loc) {
		if (null == loc){
			return false;
		}
		
		if( loc.getX() == ChessBoard.max_x && loc.getY() == ChessBoard.max_y)
		{
			return true ;
		}
		return false; 
		
	}

}
