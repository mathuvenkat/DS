package three.cross.chess.engine;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import three.cross.chess.domain.SquareLocation;

public class KnightTest extends TestCase{

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
	public void testGetKnightInstance() {
		Knight knight = Knight.getKnightInstance();
		Assert.assertTrue(knight instanceof Knight);
	}

	@Test
	public void testHasNextWithNull() {
		Knight knight = Knight.getKnightInstance();
		Assert.assertFalse(knight.hasNext(null));
	}

	@Test
	public void testHasNextWithValidCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(1));
				allowing(loc).getY();will(returnValue(2));
			}
		});
		Assert.assertTrue(knight.hasNext(loc));
	}
	
	@Test
	public void testHasNextWithErrorCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(2));
				allowing(loc).getY();will(returnValue(2));
			}
		});
		Assert.assertFalse(knight.hasNext(loc));
	}

	@Test
	public void testHasNextWithWinningCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(3));
				allowing(loc).getY();will(returnValue(3));
			}
		});
		Assert.assertFalse(knight.hasNext(loc));
	}
	
	
	@Test
	public void testNextMoveWithNull() {
		Knight knight = Knight.getKnightInstance();
		Assert.assertEquals(0, knight.nextMove(null).size());
	}

	@Test
	public void testNextMoveWithValidCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(1));
				allowing(loc).getY();will(returnValue(2));
			}
		});
		Assert.assertNotNull(knight.nextMove(loc));
	}
	
	@Test
	public void testNextMoveWithErrorCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(2));
				allowing(loc).getY();will(returnValue(2));
			}
		});
		Assert.assertEquals(0,knight.nextMove(loc).size());
	}

	@Test
	public void testNextMoveWithWinningCase() {
		Knight knight = Knight.getKnightInstance();
		final SquareLocation loc = context.mock(SquareLocation.class);
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(3));
				allowing(loc).getY();will(returnValue(3));
			}
		});
		Assert.assertNotNull(knight.nextMove(loc));
	}

	
}
