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
// 
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import processing.core.PImage;

/**
 * 
 * Represents a room in the game that serves as the entrance to a cave holding
 * treasure.
 * 
 * It is a specialized type of Room.
 */
public class StartRoom extends Room {
	/**
	 * 
	 * Creates a new StartRoom object with the specified ID and image.
	 * 
	 * @param ID    the unique identifier of the room
	 * @param image the image associated with the room
	 */
	public StartRoom(int ID, PImage image) {
		super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
	}

}
