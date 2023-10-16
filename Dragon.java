///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           DragonTreasureGame Program
// Course:          CS 300, Summer, 2023
//
// Author:          Max Liss-'s-Gravemade
// Email:           lisssgravema@wisc.edu
// Lecturer's Name: Michelle Jensen
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Room.html 
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * Represents a dragon character in the Dragon Treasure Game.
 * 
 * It extends the Character class and implements the Moveable interface.
 */
public class Dragon extends Character implements Moveable {
	private Random randGen;
	private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
	private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

	/**
	 * 
	 * Creates a new instance of Dragon with the specified initial room.
	 * 
	 * @param currentRoom the room where the dragon is initially located
	 */
	public Dragon(Room currentRoom) {
		super(currentRoom, "Dragon");
		randGen = new Random();
	}

	/**
	 * 
	 * Checks if the dragon can move to the specified destination room.
	 * 
	 * @param destination the room to move to
	 * @return true if the dragon can move to the destination room, false otherwise
	 */
	public boolean canMoveTo(Room destination) {
		return getCurrentRoom().isAdjacent(destination);
	}

	/**
	 * 
	 * Moves the dragon to the specified destination room.
	 * 
	 * @param destination the room to move to
	 * @return true if the dragon successfully moved to the destination room, false
	 *         otherwise
	 */
	public boolean changeRoom(Room destination) {
		if (canMoveTo(destination)) {
			setCurrentRoom(destination);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Returns the encounter message when the player encounters the dragon.
	 * 
	 * @return the encounter message
	 */
	public static String getDragonEncounter() {
		return DRAGON_ENCOUNTER;
	}

	/**
	 * 
	 * Returns the warning message when the dragon is nearby.
	 * 
	 * @return the warning message
	 */
	public static String getDragonWarning() {
		return DRAGON_WARNING;
	}

	/**
	 * 
	 * Picks a random adjacent room for the dragon to move to.
	 * 
	 * @return the randomly picked adjacent room
	 */
	public Room pickRoom() {
		ArrayList<Room> adjacentRooms = getAdjacentRooms();
		int randomIndex = randGen.nextInt(adjacentRooms.size());
		return adjacentRooms.get(randomIndex);
	}
}
