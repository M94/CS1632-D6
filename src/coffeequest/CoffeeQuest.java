package coffeequest;
/**
 * Austin Choi
 * CS 1632 Deliverable 6
 * Coffee Maker Quest 412
 * In this game, the player must collect three items in a series of rooms to concoct a winning beverage.
 * The program is designed to be test-friendly.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoffeeQuest {
	
	public enum Command {
		NORTH, SOUTH, LOOK, INVENTORY, HELP, DRINK, INVALID
	}
	
	public static void main(String[] args) throws IOException {
		/* Initialization */
		System.out.print("Coffee Maker Quest 412:\nNationality Rooms Edition\n\n");
		Player player = new Player();
		Room[] rooms = createRooms();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/* Game loop */
		while (true) {
			Room currentRoom = rooms[player.getLocation()];
			printRoom(currentRoom);
			System.out.println("\n INSTRUCTIONS (N, S, L, I, D, H) >");
			String input = reader.readLine();							// Wait for user input
			Command command = parseInput(input);		// Convert user input into command
			executeCommand(command, player, rooms);	// Run the command
		}
	}
	
	public static void executeCommand(Command command, Player player, Room[] rooms) {
		Room currentRoom =  rooms[player.getLocation()];
		switch (command) {
			case NORTH: 			// Go North
				goNorth(player, currentRoom);
				break;
			case SOUTH: 			// Go South
				goSouth(player, currentRoom);
				break;
			case LOOK: 				// Look for Items
				lookForItems(player, currentRoom);
				break;
			case INVENTORY: 	// Show inventory
				printInventory(player);
				break;
			case HELP:					// Show help menu
				printHelp();
				break;
			case DRINK:				// Drink current items
				boolean win = drinkCoffee(player);
				if (win) 
					System.out.println("You win!");
				else
					System.out.println("You lose!");
				System.exit(0);
				break;
			case INVALID:			// Command not recognized
			default:
				System.out.println("What?");
				break;			
		}
		System.out.println("");
	}
	
	/**
	 * If the room has a northern door, moves the player north one room.
	 */
	public static void goNorth(Player player, Room room) {
		if (room.hasNorth()) {
			player.goNorth();
		} else {
			System.out.println("You try to go north, but you run into a wall.");
		}
	}

	/**
	 * If the room has a southern door, moves the player southern one room.
	 */
	public static void goSouth(Player player, Room room) {
		if (room.hasSouth()) {
			player.goSouth();
		}else {
			System.out.println("You try to go south, but you run into a wall.");
		}
	}
	
	/**
	 * If an item is present in the room, gives it to the player.
	 */
	public static void lookForItems(Player player, Room room) {
		if (room.hasItem()) {
			int item = room.item;
			player.giveItem(item);
			String itemStr = "n/a";
			if (item == Item.COFFEE) itemStr = "caffeinated coffee";
			else if (item == Item.CREAM) itemStr = "creamy cream";
			else if (item == Item.SUGAR) itemStr = "sparkling sugar";
			System.out.println("You found some " + itemStr + ".");
		} else {
			System.out.println("You don't see anything out of the ordinary.");
		}
	}
	
	public static void printHelp() {
		String[] commands = {
			"N: Go north",
			"S: Go south",
			"L: Look for items",
			"I: Inventory",
			"D: Drink",
			"H: Help"
		};
		for (String cmd: commands) {
			System.out.println(cmd);
		}
	}
	
	public static void printInventory(Player player) {
		if (player.hasItem(Item.COFFEE))
			System.out.println("You have a cup of delicious coffee.");
		else
			System.out.println("YOU HAVE NO COFFEE!");
		if (player.hasItem(Item.CREAM))
			System.out.println("You have some fresh cream.");
		else
			System.out.println("YOU HAVE NO CREAM!");
		if (player.hasItem(Item.SUGAR))
			System.out.println("You have some sparkling sugar.");
		else
			System.out.println("YOU HAVE NO SUGAR!");
	}
	
	/**
	 * Returns true if player has coffee, cream, and sugar
	 * Returns false if player lacks one or more ingredients.
	 */
	public static boolean drinkCoffee(Player player) {
		if (!player.hasItem(Item.COFFEE)) {
			System.out.println("You have no coffee. You cannot study.");
			return false;
		}
		if (!player.hasItem(Item.SUGAR)) {
			System.out.println("No sugar? Blasphemy! You cannot study.");
			return false;
		}
		if (!player.hasItem(Item.CREAM)) {
			System.out.println("No cream? Blasphemy! You cannot study.");
			return false;
		}
		System.out.println("You drink the beverage and are ready to study!");
		return true;
	}
	
	public static Room[] createRooms() {
		Room[] rooms = {
			new Room("Austrian", "Dusty chandelier", "Magenta", null, Item.CREAM),
			new Room("Chinese", "Dusty wok", "Beige", "Massive", Item.NOTHING),
			new Room("German", "DVD copy of Grown Ups 2 signed by Chancellor Angela Merkel", "Dead", "Smart", Item.COFFEE),
			new Room("French", "Fiery candle", "Vivacious", "Slim", Item.NOTHING),
			new Room("Norwegian", "Fjord", "Black", "Metal", Item.NOTHING),
			new Room("Polish", "Delicious bowl of pierogies", null, "Minimalist", Item.SUGAR),
		};
		return rooms;
	}
	
	/**
	 * Print the room's adjective, furnishing, and any doors.
	 */
	public static void printRoom(Room room) {
		System.out.println("You see a " + room.adjective + " room.");
		System.out.println("It has a " + room.furnishing + ".");
		if (room.hasNorth()) System.out.println("A " + room.door_north + " door leads North.");
		if (room.hasSouth()) System.out.println("A " + room.door_south + " door leads South.");
	}
	
	/**
	 * Returns an appropriate command based on the string input.
	 * Case-insensitive.
	 * If input is not recognized, returns "Command.INVALID"
	 */
	public static Command parseInput(String input) {
		Command cmd;
		switch (input.toUpperCase()) {
			case "N":
				cmd = Command.NORTH;
				break;
			case "S":
				cmd = Command.SOUTH;
				break;
			case "L":
				cmd = Command.LOOK;
				break;
			case "I":
				cmd = Command.INVENTORY;
				break;
			case "H":
				cmd = Command.HELP;
				break;
			case "D":
				cmd = Command.DRINK;
				break;
			default:
				cmd = Command.INVALID;
		}
		return cmd;
	}

}
