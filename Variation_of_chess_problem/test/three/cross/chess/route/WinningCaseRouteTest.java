package three.cross.chess.route;

import junit.framework.TestCase;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import three.cross.chess.domain.SquareLocation;

public class WinningCaseRouteTest extends TestCase{

	Mockery context = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new Mockery();
		context.setImposteriser(ClassImposteriser.INSTANCE);
	}



	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		context = null;
	}



	@Test 
	public void testAcceptWithWinningCase()  {
		final SquareLocation loc = context.mock(SquareLocation.class);
		WinningCaseRoute route = new WinningCaseRoute();
		route.process(loc);
	}

	@Test 
	public void testAcceptWithNull()  {
		WinningCaseRoute route = new WinningCaseRoute();
		// process does not go down
		route.process(null);
	}

}
