package three.cross.chess.route;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import three.cross.chess.domain.SquareLocation;

public class OutofBoundsRoute implements IRoute<SquareLocation>{

	private Log logger = LogFactory.getLog(OutofBoundsRoute.class);
	
	@Override
	public void process(SquareLocation value) {
		if (null == value){
			return;
		}
		logger.info(" Location out of the boundary...");
	}

}
