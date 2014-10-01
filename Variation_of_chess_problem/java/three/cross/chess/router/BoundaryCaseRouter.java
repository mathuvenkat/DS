package three.cross.chess.router;

import java.util.HashMap;
import java.util.Map;

import three.cross.chess.domain.MethodHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;
import three.cross.chess.exception.DestroyException;
import three.cross.chess.exception.InitializeException;
import three.cross.chess.filter.IFilter;
import three.cross.chess.filter.OutofBoundsFilter;
import three.cross.chess.filter.WinningCaseFilter;
import three.cross.chess.route.IRoute;
import three.cross.chess.route.OutofBoundsRoute;
import three.cross.chess.route.WinningCaseRoute;

public class BoundaryCaseRouter implements IRouter<SquareLocation>{

	private Map<IFilter<SquareLocation > , IRoute<SquareLocation>> boundaryCaseProcesors; 
	boolean initialized  = false; 

	// TODO implement this class
	public BoundaryCaseRouter() throws InitializeException
	{
		super();
		init();
	}


	@Override
	public void init() throws InitializeException {

		boundaryCaseProcesors = new HashMap<IFilter<SquareLocation > , IRoute<SquareLocation>>();
		boundaryCaseProcesors.put( new WinningCaseFilter(), new WinningCaseRoute());
		boundaryCaseProcesors.put( new OutofBoundsFilter(), new OutofBoundsRoute());

		initialized = true ;
	}

	@Override
	public void clean() throws DestroyException {

		boundaryCaseProcesors.clear();
		initialized = false; 
	}

	@Override
	@MethodHeaderAnnotation
	(		
			input = "SquareLocation",
			comments = "Router pattern which processes given location through all possible filters " +
					"If filter accepts a given value, the corresponding route for the filter is processed." +
					"Returns True if any filter has accepted the location, else returns false"
	)
	public boolean process(SquareLocation value){

		for( IFilter<SquareLocation> filter : boundaryCaseProcesors.keySet())
		{
			if( filter.accept(value))
			{
				IRoute<SquareLocation> route = boundaryCaseProcesors.get(filter);
				try {
					route.process(value);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;

	}

}
