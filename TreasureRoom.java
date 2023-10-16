
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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/TreasureRoom.html
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import processing.core.PImage;

/**
 * Represents a special type of room in the Dragon Treasure game. It extends the
 * Room class and includes additional functionality related to treasure.
 */
public class TreasureRoom extends Room {
	private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
	private static PImage treasureBackground;

	/**
	 * Constructs a new TreasureRoom object with the specified ID.
	 *
	 * @param ID the unique ID of the treasure room
	 */
	public TreasureRoom(int ID) {
		super(ID, "In the back of this room, you spot a treasure chest.", treasureBackground);
	}

	/**
	 * Returns the warning message indicating that there is treasure nearby.
	 *
	 * @return the treasure warning message
	 */
	public static String getTreasureWarning() {
		return TREASURE_WARNING;
	}

	/**
	 * Sets the background image for the treasure room.
	 *
	 * @param treasureBackground the background image for the treasure room
	 */
	public static void setTreasureBackground(PImage treasureBackground) {
		TreasureRoom.treasureBackground = treasureBackground;
	}

	/**
	 * Determines whether or not the player can open the treasure chest in the room.
	 *
	 * @param p the player attempting to grab the treasure
	 * @return true if the player can grab the treasure, false otherwise
	 */
	public boolean playerCanGrabTreasure(Player p) {
		return p.hasKey();
	}
}
