package three.cross.chess.engine;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;

import three.cross.chess.domain.ChessPiece;
import three.cross.chess.domain.ClassHeaderAnnotation;
import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;
import three.cross.chess.filter.OutofBoundsFilter;
import three.cross.chess.filter.WinningCaseFilter;


@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Singleton class which implentns rules for this particular piece- Knight." +
				"In this game there is only one player of a type"

		)
public class Knight implements ChessPiece {

	private static Knight singletoninstance;

	private int [][] knightOffsets = {

			{ -2 , 1 },
			{-1 , 2 },
			{1, 2 },
			{2, 1 },
			{2 , -1 },
			{1 , -2},
			{ -1 , -2 },
			{ -2 , -1 }			
	};

	SquareLocation current;

	private OutofBoundsFilter outOfBoundsFilter = new OutofBoundsFilter();
	private WinningCaseFilter winningCaseFilter = new WinningCaseFilter();

	public void init()
	{

	}

	public void clean()
	{

	}

	private Knight()
	{
		//do nothing 
	}

	public static Knight getKnightInstance() {

		if( singletoninstance == null)
			singletoninstance = new Knight();

		return singletoninstance;
	}

	@Override
	@MethodHeaderAnnotation
	(
			input = "SquareLocation loc",
			comments = "Returns True if the location provided has a valid next move" 					

	)
	public boolean hasNext( SquareLocation loc)
	{
		if( null == loc || (CollectionUtils.isEmpty(this.nextMove(loc))))
			return false ;

		return true ;		
	}

	@Override
	@MethodHeaderAnnotation
	(
			input = "SquareLocation loc",
			comments = "Returns an ArrayList of possible locations the given input Loc can move to. Returns" +
					"empty ArrayList if the input Loc is the target location"

	)
	public ArrayList<SquareLocation> nextMove( SquareLocation loc )
	{	
		ArrayList<SquareLocation> possibleMoves = new ArrayList<SquareLocation>();

		if (null == loc || winningCaseFilter.accept(loc)){
			return possibleMoves;
		}

		current = new SquareLocation( loc.getX() , loc.getY());

		int x; int y;

		for ( int i = 0; i < knightOffsets.length ; i++)
		{
			x = loc.getX() + knightOffsets[i][0];
			y = loc.getY() + knightOffsets[i][1];
			current.setLocation( x, y ) ;

			if( outOfBoundsFilter.accept(current)) {
				continue;
			}
			else {					
				possibleMoves.add(new SquareLocation(x, y));
			}
		}
		return possibleMoves;	
	}

}
