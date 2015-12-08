package coffeequest;
/**
 * Property tests for the main Coffee Maker Quest program
 */

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import coffeequest.CoffeeQuest.Command;

public class CoffeeQuestTest {

	/**
	 *  The user shall be able to enter one of six commands:
	 * "N" to go North, "S" to go South, "L" to Look for items, "I" for Inventory, 
	 * "H" for Help, or "D" to Drink
	 */
	@Test
	public void commandTest() {
		assertEquals(Command.NORTH, CoffeeQuest.parseInput("N"));
		assertEquals(Command.SOUTH, CoffeeQuest.parseInput("S"));
		assertEquals(Command.LOOK, CoffeeQuest.parseInput("L"));
		assertEquals(Command.INVENTORY, CoffeeQuest.parseInput("I"));
		assertEquals(Command.HELP, CoffeeQuest.parseInput("H"));
		assertEquals(Command.DRINK, CoffeeQuest.parseInput("D"));
	}
	
	/**
	 * If the player enters an unknown command, the system will recognize it as such.
	 */
	@Test
	public void unknownTest() {
		assertEquals(Command.INVALID, CoffeeQuest.parseInput("w"));
		assertEquals(Command.INVALID, CoffeeQuest.parseInput("lol"));
		assertEquals(Command.INVALID, CoffeeQuest.parseInput("0"));
		assertEquals(Command.INVALID, CoffeeQuest.parseInput("They see me rollin"));
	}
	
	/**
	 * The system shall be case-insensitive in regards to input values.
	 */
	@Test
	public void capsTest() {
		assertEquals(CoffeeQuest.parseInput("n"), CoffeeQuest.parseInput("N"));
		assertEquals(CoffeeQuest.parseInput("s"), CoffeeQuest.parseInput("S"));
		assertEquals(CoffeeQuest.parseInput("l"), CoffeeQuest.parseInput("L"));
		assertEquals(CoffeeQuest.parseInput("i"), CoffeeQuest.parseInput("I"));
		assertEquals(CoffeeQuest.parseInput("h"), CoffeeQuest.parseInput("H"));
		assertEquals(CoffeeQuest.parseInput("d"), CoffeeQuest.parseInput("D"));
	}
	
	/**
	 * The player shall win the game if and only if Coffee, Sugar, and Cream have been collected
	 * by the player and then drunk.
	 * The player shall lose if all above items have not been collected.
	 */
	@Test
	public void winTest() {
		// Player should win if all three items are held.
		Player player = new Player(new int[]{Item.COFFEE, Item.CREAM, Item.SUGAR});
		boolean win = CoffeeQuest.drinkCoffee(player);
		assertEquals(true, win);
		// Player should lose if only two  items collected
		player = new Player(new int[]{Item.COFFEE, Item.CREAM});
		win = CoffeeQuest.drinkCoffee(player);
		assertEquals(false, win);
		// Player should lose if only one item collected
		player = new Player(new int[]{Item.COFFEE});
		win = CoffeeQuest.drinkCoffee(player);
		assertEquals(false, win);
		// Player should lose if no items collected
		player = new Player();
		win = CoffeeQuest.drinkCoffee(player);
		assertEquals(false, win);
	}
	
	/**
	 * When the player looks for an item in the room, any items found
	 * shall be added to the player's inventory.
	 */
	@Test
	public void lookTest() {
		/*
		In a room containing cream, the player should have cream
		in his inventory after searching for items.
		*/
		Player player = new Player();
		Room room = new Room(null, null, Item.CREAM);
		CoffeeQuest.lookForItems(player, room);
		assertEquals(true, player.hasItem(Item.CREAM));
	}
	
	/**
	 * Each room shall have a unique adjective describing it.
	 */
	@Test
	public void uniqueAdjectiveTest() {
		/*
		 *  The array from the createRooms() method should
		 *  not contain any rooms with duplicate adjectives.
		 */
		Room[] rooms = CoffeeQuest.createRooms();
		HashSet<String> adjectives = new HashSet<String>();
		for (Room room: rooms) {
			String adj = room.adjective;
			assertFalse(adjectives.contains(adj));
			adjectives.add(adj);
		}
	}
	
	/**
	 * Each room shall have a unique furnishing.
	 */
	@Test
	public void uniqueFurnishingTest() {
		/*
		 *  The array from the createRooms() method should
		 *  not contain any rooms with duplicate furnishings.
		 */		
		Room[] rooms = CoffeeQuest.createRooms();
		HashSet<String> furnishings = new HashSet<String>();
		for (Room room: rooms) {
			String furn = room.furnishing;
			assertFalse(furnishings.contains(furn));
			furnishings.add(furn);
		}
	}
	
	/**
	 * The map shall contain all three ingredients so that
	 * the player may collect them and win the game
	 */
	@Test
	public void winnableTest() {
		/*
		 * The array from the createRooms() method should
		 *  contain at least one coffee, one cream, and one sugar.
		 */
		Room[] rooms = CoffeeQuest.createRooms();
		boolean[] ingredients = new boolean[Item.N];
		for (Room room: rooms) {
			int item = room.item;
			if (item > Item.NOTHING && item < Item.N) {
				ingredients[item] = true;
			}
		}
		assertTrue(ingredients[Item.COFFEE] && ingredients[Item.CREAM] && ingredients[Item.SUGAR]);
	}

}
