public class Player {

	private int location;
	private boolean inventory[]; // Boolean array indexed by item value; true indicates that the item is in possession
	public final int INVENTORY_SIZE = 3;
	
	public Player() {
		location = 0;
		inventory = new boolean[INVENTORY_SIZE];
	}

	/**
	 * Returns true if player currently has the item.
	 * Returns false otherwise.
	 */
	public boolean hasItem(int item) {
		if (item < 0 || item > INVENTORY_SIZE - 1) return false;
		return inventory[item - 1];
	}
	
	/**
	 * Returns true if the item was successfully given to the player.
	 * Returns false otherwise.
	 */
	public boolean giveItem(int item) {
		if (item < 0 || item > INVENTORY_SIZE) return false;
		return inventory[item] = true;
	} 
	
	public void goNorth() {
		location++;
	}
	
	public void goSouth() {
		location--;
	}
	
	public int getLocation() {
		return location;
	}
	
}
