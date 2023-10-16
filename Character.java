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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Character.html
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;

/**
 * Represents a character in the game that can move between rooms. It implements
 * the Moveable interface.
 */
public class Character implements Moveable {
	private Room currentRoom;
	private String label;

	/**
	 * Creates a new Character object with the specified initial room and label.
	 * 
	 * @param currentRoom the initial room the character is located in
	 * @param label       the label of the character
	 */
	public Character(Room currentRoom, String label) {
		this.currentRoom = currentRoom;
		this.label = label;
	}

	/**
	 * Retrieves the adjacent rooms of the character's current room.
	 * 
	 * @return an ArrayList of adjacent rooms
	 */
	public ArrayList<Room> getAdjacentRooms() {
		return currentRoom.getAdjacentRooms();
	}

	/**
	 * Retrieves the current room of the character.
	 * 
	 * @return the current room
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Retrieves the label of the character.
	 * 
	 * @return the character's label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the current room of the character to the specified room.
	 * 
	 * @param newRoom the new current room
	 */
	public void setCurrentRoom(Room newRoom) {
		currentRoom = newRoom;
	}

	/**
	 * Moves the character to the specified destination room.
	 * 
	 * @param destination the destination room to move to
	 * @return true if the character successfully moves to the destination, false
	 *         otherwise
	 */
	public boolean changeRoom(Room destination) {
		if (canMoveTo(destination)) {
			currentRoom = destination;
			return true;
		}
		return false;
	}

	/**
	 * Checks if the character can move to the specified destination room.
	 * 
	 * @param destination the destination room to check
	 * @return true if the character can move to the destination, false otherwise
	 */
	public boolean canMoveTo(Room destination) {
		return currentRoom.isAdjacent(destination);
	}
}