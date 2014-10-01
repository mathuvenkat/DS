package three.cross.chess.engine;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;


import three.cross.chess.domain.ChessPiece;
import three.cross.chess.domain.ICreate;
import three.cross.chess.domain.IDestroy;
import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;
import three.cross.chess.exception.DestroyException;
import three.cross.chess.exception.InitializeException;
import three.cross.chess.exception.KnightStrandedException;
import three.cross.chess.filter.WinningCaseFilter;
import three.cross.chess.route.WinningCaseRoute;
import three.cross.chess.router.BoundaryCaseRouter;
import three.cross.chess.router.IRouter;

public class Game implements ICreate , IDestroy {

	private ChessPiece player;
	boolean initialized = false ; 

	IRouter<SquareLocation > boundaryCaseRouter;	
	WinningCaseFilter winfilter ;
	WinningCaseRoute winRouter;



	public Game() throws InitializeException {
		init();
		player = Knight.getKnightInstance();
	}

	public void init() throws InitializeException
	{
		boundaryCaseRouter = new BoundaryCaseRouter() ;
		winfilter = new WinningCaseFilter();
		winRouter = new WinningCaseRoute();
		initialized = true ;

	}

	public void clean() throws DestroyException
	{
		boundaryCaseRouter.clean();
		initialized = false;
	}

	@MethodHeaderAnnotation
	(
			input = "SquareLocation : starting position of the knight",
			comments = "Method aims to move the knight from starting position to target position "

			)
	public void play(SquareLocation start) throws Exception  {

		TraversalHistory.getHistoryInstance().addHistory(start);
		//TODO : if boundary case at the beginning, exit
		if (boundaryCaseRouter.process(start)){
			return;
		}

		ArrayList<SquareLocation> positions = player.nextMove(start);

		if(CollectionUtils.isEmpty(positions)){
			throw new KnightStrandedException();
		}

		SquareLocation parent = start;

		for( int i= 0 ; i < positions.size() ; i++)
		{
			SquareLocation child = positions.get(i);
			TraversalHistory.getHistoryInstance().addHistory(child);
			traverseToLeaf( child, parent);
			TraversalHistory.getHistoryInstance().printAndFlushHistory();
			TraversalHistory.getHistoryInstance().addHistory(start);
		}

	}


	@MethodHeaderAnnotation
	(
			input = "chid , parent whose move lead to the child",
			comments = "For a 3*3 grid , there can only be a maximum of" +
					"2 valid moves from every location . Except for the starting point, for which " +
					"both paths have to be traversed, every other location " +
					"will have one location which leads to its parent which we can eliminiate" +
					"to avoid going in cycles."

			)
	public void traverseToLeaf( SquareLocation child , SquareLocation parent)
	{
		SquareLocation left;
		SquareLocation right;

		while( !winfilter.accept(child))
		{		
			ArrayList<SquareLocation> possibles = player.nextMove(child);

			left = possibles.get(0);
			right = possibles.get(1);

			//eliminate move that leads to parent
			if(left.equals(parent)){
				parent = child;
				child = right;			
			}
			else{		
				parent = child;
				child = left;
			}

			TraversalHistory.getHistoryInstance().addHistory(child);
		}
		winRouter.process(child);
	}
}


