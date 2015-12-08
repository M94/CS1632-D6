/**
 * Austin Choi
 * CS 1632 Deliverable 6
 * Unit tests for Room class
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
	
	@Test
	public void testHasItem() {
		// An empty room should have nothing
		Room room = new Room();
		boolean expected = false;
		boolean observed = room.hasItem();
		assertEquals(expected, observed);	
		// A room with an item should indicate such
		room = new Room(null, null, Item.COFFEE);
		expected = true;
		observed = room.hasItem();
		assertEquals(expected, observed);	
	}

	@Test
	public void testHasNorth() {
		// Room with no doors should not have north door
		Room room = new Room();
		boolean expected = false;
		boolean observed = room.hasNorth();
		assertEquals(expected, observed);
		// Room with north door should indicate such
		room = new Room("test", null, Item.NOTHING);
		expected = true;
		observed = room.hasNorth();
		assertEquals(expected, observed);
	}
	
	@Test
	public void testHasSouth() {
		// Room with no doors should not have south door
		Room room = new Room();
		boolean expected = false;
		boolean observed = room.hasSouth();
		assertEquals(expected, observed);
		// Room with south door should indicate such
		room = new Room(null, "test", Item.NOTHING);
		expected = true;
		observed = room.hasSouth();
		assertEquals(expected, observed);
	}

}
