package three.cross.chess.router;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import three.cross.chess.domain.SquareLocation;
import three.cross.chess.exception.InitializeException;
import three.cross.chess.exception.KnightStrandedException;

public class BoundaryCaseRouterTest extends TestCase{

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
	public void testAcceptWithWinningCase() throws InitializeException {
		BoundaryCaseRouter router = new BoundaryCaseRouter();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(3));
				allowing(loc).getY();will(returnValue(3));
			}
		});

		context.assertIsSatisfied();
		router.process(loc);
	}
	
	@Test
	public void testAcceptWithNonBoundaryCase() throws InitializeException {
		BoundaryCaseRouter router = new BoundaryCaseRouter();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(1));
				allowing(loc).getY();will(returnValue(2));
			}
		});

		context.assertIsSatisfied();
		router.process(loc);
	}
	
	@Test (expected = KnightStrandedException.class)
	public void testAcceptWithNull() throws InitializeException {
		BoundaryCaseRouter router = new BoundaryCaseRouter();
		router.process(null);
	}
	
}
