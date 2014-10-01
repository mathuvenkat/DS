package three.cross.chess.route;

import three.cross.chess.domain.SquareLocation;

public class WinningCaseRoute implements IRoute<SquareLocation>{
	
	//private Log logger = LogFactory.getLog(WinningCaseRoute.class);
	
	@Override
	public void process(SquareLocation value) {
		if (null == value){
			return;
		}
		System.out.println ("WIN!!! Reached destination - no more moves required ");
	}
	

}
