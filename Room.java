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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Room.html#addToAdjacentRooms(Room)
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * 
 * Represents a room in the Dragon Treasure game. Each room has a unique ID,
 * description, list of adjacent rooms, list of characters present in the room,
 * image, and reference to the PApplet processing object.
 */
public class Room {
	private String description;
	private ArrayList<Room> adjRooms;
	private ArrayList<Character> characters;
	private final int ID;
	protected static PApplet processing;
	private PImage image;

	/**
	 * 
	 * Constructs a new Room object with the specified ID, description, and image.
	 * 
	 * @param ID          the unique ID of the room
	 * @param description the description of the room
	 * @param image       the image representing the room
	 */
	public Room(int ID, String description, PImage image) {
		this.ID = ID;
		this.description = description;
		this.image = image;
		this.adjRooms = new ArrayList<>();
		this.characters = new ArrayList<>();
	}

	/**
	 * 
	 * Adds the specified room to the list of adjacent rooms.
	 * 
	 * @param toAdd the room to be added as an adjacent room
	 */
	public void addToAdjacentRooms(Room toAdd) {
		adjRooms.add(toAdd);
	}

	/**
	 * 
	 * Adds the specified character to the room.
	 * 
	 * @param character the character to be added
	 */

	public void addCharacter(Character character) {
		characters.add(character);
	}

	/**
	 * 
	 * Removes the specified character from the room.
	 * 
	 * @param character the character to be removed
	 */
	public void removeCharacter(Character character) {
		characters.remove(character);
	}

	/**
	 * 
	 * Checks if the room contains the specified character.
	 * 
	 * @param character the character to check
	 * @return true if the room contains the character, false otherwise
	 */
	public boolean containsCharacter(Character character) {
		return characters.contains(character);
	}

	/**
	 * 
	 * Draws the room on the screen using the PApplet processing object.
	 */
	public void draw() {
		processing.image(image, 0, 0);
		processing.fill(-7028);
		processing.rect(0, 500, 800, 600);
		processing.fill(0);
		processing.text(toString(), 300, 525);
	}

	/**
	 * 
	 * Checks if the room is equal to the specified object.
	 * 
	 * @param other the object to compare
	 * @return true if the room is equal to the object, false otherwise
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Room)) {
			return false;
		}
		Room otherRoom = (Room) other;
		return ID == otherRoom.ID;
	}

	/**
	 * 
	 * Returns a list of adjacent rooms.
	 * 
	 * @return the list of adjacent rooms
	 */
	public ArrayList<Room> getAdjacentRooms() {
		return adjRooms;
	}

	/**
	 * 
	 * Returns the description of the room.
	 * 
	 * @return the description of the room
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * Returns the ID of the room.
	 * 
	 * @return the ID of the room
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Checks if the room is adjacent to the specified room.
	 *
	 * @param room the room to check for adjacency
	 * @return true if the room is adjacent to the specified room, false otherwise
	 */
	public boolean isAdjacent(Room room) {
		return adjRooms.contains(room);
	}

	/**
	 * Sets the processing object for the Room class.
	 *
	 * @param processing the PApplet processing object
	 */
	public static void setProcessing(PApplet processing) {
		Room.processing = processing;
	}

	/**
	 * Returns a string representation of the room.
	 *
	 * @return the string representation of the room
	 */
	@Override
	public String toString() {
		return ID + ":\n" + description + "\n";
	}

}
