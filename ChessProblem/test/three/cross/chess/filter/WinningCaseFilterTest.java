package three.cross.chess.filter;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import three.cross.chess.domain.SquareLocation;

public class WinningCaseFilterTest extends TestCase{

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
	public void testAcceptWithWinningCase() {
		final SquareLocation loc = context.mock(SquareLocation.class);
		WinningCaseFilter filter = new WinningCaseFilter();
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(3));
				allowing(loc).getY();will(returnValue(3));
			}
		});

		Assert.assertTrue(filter.accept(loc));
		context.assertIsSatisfied();
	}

	@Test
	public void testAcceptWithNull() {
		final SquareLocation loc = context.mock(SquareLocation.class);
		WinningCaseFilter filter = new WinningCaseFilter();
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(2));
				allowing(loc).getY();will(returnValue(2));
			}
		});

		Assert.assertFalse(filter.accept(null));
		context.assertIsSatisfied();
	}

	@Test
	public void testAcceptWithNoError() {
		final SquareLocation loc = context.mock(SquareLocation.class);
		WinningCaseFilter filter = new WinningCaseFilter();
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(1));
				allowing(loc).getY();will(returnValue(2));
			}
		});

		Assert.assertFalse(filter.accept(loc));
		context.assertIsSatisfied();	
	}

	@Test
	public void testAcceptWithOutofboundary() {
		final SquareLocation loc = context.mock(SquareLocation.class);
		WinningCaseFilter filter = new WinningCaseFilter();
		context.checking(new Expectations() {
			{
				allowing(loc).getX();will(returnValue(4));
				allowing(loc).getY();will(returnValue(4));
			}
		});

		Assert.assertFalse(filter.accept(loc));
		context.assertIsSatisfied();	
	}

}
