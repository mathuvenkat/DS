package three.cross.chess.engine;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import three.cross.chess.domain.SquareLocation;

public class TraversalHistoryTest extends TestCase{

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
	public void testGetHistoryInstance() {
		TraversalHistory history = TraversalHistory.getHistoryInstance();
		Assert.assertTrue(history instanceof TraversalHistory);
	}


	@Test
	public void testClearHistory() {
		TraversalHistory history = TraversalHistory.getHistoryInstance();
		SquareLocation loc = context.mock(SquareLocation.class);
		history.addHistory(loc);
		history.clearHistory();
		Assert.assertEquals(0, history.getHistory().size());		
	}

	@Test
	public void testAddHistory() {
		TraversalHistory history = TraversalHistory.getHistoryInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		final int x = 1; final int y = 3;
		context.checking(new Expectations() {
			{
				allowing(loc).getX(); will(returnValue(x));
				allowing(loc).getY(); will(returnValue(y));
			}
		});
		history.addHistory(loc);
		Assert.assertEquals(x, history.getHistory().size());
		Assert.assertEquals(x, history.getHistory().get(0).getX());
		Assert.assertEquals(y, history.getHistory().get(0).getY());
	}

}
