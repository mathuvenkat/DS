package three.cross.chess.engine;

import java.util.ArrayList;
import java.util.List;

import three.cross.chess.domain.ICreate;
import three.cross.chess.domain.IDestroy;
import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;

public class TraversalHistory implements ICreate, IDestroy{

	private List<SquareLocation> history;
	private static TraversalHistory singletonInstance;

	private TraversalHistory() {
		super();
		init();
	}

	public static TraversalHistory getHistoryInstance(){
		if(null == singletonInstance){
			singletonInstance = new TraversalHistory();
		}
		return singletonInstance;
	}

	public void printHistory(){

		System.out.println( "Path from start to target...") ;
		for (SquareLocation loc : history){
			System.out.println( loc.toString());			
		}
	}

	public void clearHistory(){
		this.clean();
	}

	@MethodHeaderAnnotation
	(
			input = "SquareLocation loc",
			comments = "Adds the given input loc to the history"
	)
	public void addHistory(SquareLocation loc){
		this.history.add(loc);
	}

	@Override
	public void init() {
		history = new ArrayList<SquareLocation>();
	}

	@Override
	public void clean() {
		history.clear();
	}

	public List<SquareLocation> getHistory() {
		return history;
	}

	@MethodHeaderAnnotation
	(		
			input = "None",
			comments = "Prints history and clears contents of history"
	)
	public void printAndFlushHistory() {
		this.printHistory();
		this.clearHistory();
	}

}
