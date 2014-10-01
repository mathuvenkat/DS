package three.cross.chess.filter;

import three.cross.chess.domain.ChessBoard;
import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;

public class OutofBoundsFilter implements IFilter<SquareLocation> {

	@Override
	@MethodHeaderAnnotation
	(		
			input = "SquareLocation",
			comments = "If input location co-ordinates exceed the board co-ordinates it returns true" +
					"else returns false"
	)
	public boolean accept(SquareLocation loc) {

		if (null == loc){
			return false;
		}

		if( loc.getX() > ChessBoard.max_x ||	loc.getX() < ChessBoard.min_x ||
				loc.getY() < ChessBoard.min_y ||	loc.getY() > ChessBoard.max_y	){
			return true;
		}
		
		return false;
	}

}
