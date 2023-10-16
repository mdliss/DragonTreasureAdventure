
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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/PortalRoom.html
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import processing.core.PImage;
import java.util.Random;

/**
 * 
 * Represents a special type of room in the Dragon Treasure game. It extends the
 * Room class and includes additional functionality related to portals.
 */
public class PortalRoom extends Room {
	private Random randGen;
	private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
	private static final String TELEPORT_MESSAGE = "The space distortion teleported you to another room!\n";
	private static PImage portalImage;

	/**
	 * 
	 * Constructs a new PortalRoom object with the specified ID, description, and
	 * image.
	 * 
	 * @param ID          the unique ID of the portal room
	 * @param description the description of the portal room
	 * @param image       the image associated with the portal room
	 */
	public PortalRoom(int ID, String description, PImage image) {
		super(ID, description, image);
		randGen = new Random();
	}

	/**
	 * 
	 * Draws the portal room on the screen using the PApplet processing object.
	 * Overrides the draw method in the Room class.
	 */
	public void draw() {
		super.draw();
		processing.image(portalImage, 325, 225);
	}

	/**
	 * 
	 * Returns a random adjacent room to which the player can teleport.
	 * 
	 * @return a random adjacent room for teleportation
	 */
	public Room getTeleportLocation() {
		int randomIndex = randGen.nextInt(getAdjacentRooms().size());
		return getAdjacentRooms().get(randomIndex);
	}

	/**
	 * 
	 * Returns the warning message indicating the presence of a portal nearby.
	 * 
	 * @return the portal warning message
	 */
	public static String getPortalWarning() {
		return PORTAL_WARNING;
	}

	/**
	 * 
	 * Returns the message displayed when the player is teleported through the
	 * portal.
	 * 
	 * @return the teleportation message
	 */
	public static String getTeleportMessage() {
		return TELEPORT_MESSAGE;
	}

	/**
	 * 
	 * Sets the image for the portal room.
	 * 
	 * @param portalImage the image for the portal room
	 */
	public static void setPortalImage(PImage portalImage) {
		PortalRoom.portalImage = portalImage;
	}
}