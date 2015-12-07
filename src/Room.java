
public class Room {
	
	public String adjective;
	public String furnishing;
	public String door_north;
	public String door_south;
	public int item;
	
	/**
	 * 
	 * @param adjective Adjective describing the room
	 * @param furnishing Single furnishing in the room
	 * @param door_north Name of door leading north; null or "" indicate no door
	 * @param door_south Name of door leading south; null or "" indicate no door
	 * @param item Single item that can be picked up by the player
	 */
	public Room(String adjective, String furnishing, String door_north, String door_south, int item) {
		this.adjective = adjective;
		this.furnishing = furnishing;
		this.door_north = door_north;
		this.door_south = door_south;
		this.item = item;
	}
	
	public boolean hasItem() {
		return item > 0;
	}
	
	public boolean hasNorth() {
		return (door_north != null && door_north != "");
	}
	
	public boolean hasSouth() {
		return (door_south != null && door_south != "");
	}
	

}
