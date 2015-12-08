package coffeequest;
/**
 * Unit tests for Player class
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void testHasItem() {
		// Player with no items should not have coffee
		Player player = new Player();
		int testItem = Item.COFFEE;
		boolean expected =  false;
		boolean observed = player.hasItem(testItem);
		assertEquals(expected, observed);
		// Player with coffee should have coffee
		player = new Player(testItem);
		expected = true;
		observed = player.hasItem(testItem);
		assertEquals(expected, observed);
	}
	
	@Test
	public void testGiveItem() {
		// Player should not receive "nothing"
		Player player = new Player();
		boolean expected =  false;
		boolean observed = player.giveItem(Item.NOTHING);
		assertEquals(expected, observed);
		// Player should accept coffee when given
		expected =  true;
		observed = player.giveItem(Item.COFFEE);
		assertEquals(expected, observed);
	}

}
