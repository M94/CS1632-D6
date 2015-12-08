public class Player {

	private int location;					// Integer used to mark position (in an array of rooms)
	private boolean inventory[]; // Boolean array indexed by item value; true indicates that the item is in possession
	
	public Player() {
		location = 0;
		inventory = new boolean[Item.N];
	}
	
	public Player(int item) {
		location = 0;
		inventory = new boolean[Item.N];
		inventory[item] = true;
	}

	/**
	 * Returns true if player currently has the item.
	 * Returns false otherwise.
	 */
	public boolean hasItem(int item) {
		if (item < 0 || item >= inventory.length) return false;
		return inventory[item];
	}
	
	/**
	 * Returns true if the item was successfully given to the player.
	 * Returns false otherwise.
	 */
	public boolean giveItem(int item) {
		if (item < 0 || item > inventory.length) return false;
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
