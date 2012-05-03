package world;

import static org.junit.Assert.*;

import org.junit.Test;

import apoSkunkman.ai.ApoSkunkmanAIConstants;

public class WorldObjectFactoryTest {

	@Test
	public void test() {
		int type = ApoSkunkmanAIConstants.LEVEL_FREE;
		AbstractWO free = WOFactory.createWorldObject(type, 5, 10);
		
		assertEquals(free.getX(), 5);
		assertEquals(free.getY(), 10);
		assertEquals(free.rating(), WORating.GOOD);
		assertTrue(free.isWalkable());
		
		assertTrue(free instanceof FreeField);
		
		try {
			WOFactory.createWorldObject(42, 5, 10);
			fail("This should throw an exception!");
		} catch (IllegalArgumentException e) {
		}
	}

}
