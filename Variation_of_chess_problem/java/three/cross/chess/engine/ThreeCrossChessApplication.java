package three.cross.chess.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import three.cross.chess.domain.ClassHeaderAnnotation;
import three.cross.chess.domain.SquareLocation;

@ClassHeaderAnnotation
(
		author = "VM" ,
		date = "17Aug/2102",
		comments = "Test class with main method to execute the game." +
				"Arguments accept x and y co-ordinates of starting position of Knight" 
		
			
)
public class ThreeCrossChessApplication {

	private static Log logger = LogFactory.getLog(ThreeCrossChessApplication.class);

	public static void main(String args[]) throws Exception{

		int x = 3; int y = 3;
		try
		{
			x = Integer.parseInt(args[0]);
			y = Integer.parseInt(args[1]);			

		}
		catch( NumberFormatException nfe)
		{
			System.out.println( "Please enter a number");
		}
		SquareLocation start = new SquareLocation(x ,y );
		logger.info ("Starting game...");
		new Game().play(start);
	}

}
