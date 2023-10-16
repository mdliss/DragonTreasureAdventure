
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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Player.html
// 
//
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.util.ArrayList;

/**
 * 
 * Represents the player character in the Dragon Treasure game. It extends the
 * Character class and includes additional functionality specific to the player.
 */
public class Player extends Character implements Moveable {
	private boolean hasKey;

	/**
	 * 
	 * Constructs a new Player object with the specified current room.
	 * 
	 * @param currentRoom the initial room where the player is located
	 */
	public Player(Room currentRoom) {
		super(currentRoom, "Player");
		hasKey = false;
	}

	/**
	 * 
	 * Checks if the player can move to the specified destination room.
	 * 
	 * @param destination the room to which the player wants to move
	 * @return true if the player can move to the destination, false otherwise
	 */
	public boolean canMoveTo(Room destination) {
		return getCurrentRoom().isAdjacent(destination);
	}

	/**
	 * 
	 * Moves the player to the specified destination room.
	 * 
	 * @param destination the room to which the player wants to move
	 * @return true if the player successfully changed rooms, false otherwise
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
	 * Checks if the player has the key.
	 * 
	 * @return true if the player has the key, false otherwise
	 */
	public boolean hasKey() {
		return hasKey;
	}

	/**
	 * 
	 * Checks if the player is near a dragon.
	 * 
	 * @param dragon the dragon character to check
	 * @return true if the player is near the dragon, false otherwise
	 */
	public boolean isDragonNearby(Dragon dragon) {
		Room playerRoom = getCurrentRoom();
		ArrayList<Room> adjacentRooms = playerRoom.getAdjacentRooms();
		for (Room room : adjacentRooms) {
			if (room.containsCharacter(dragon)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Checks if the player is near a portal.
	 * 
	 * @return true if the player is near a portal, false otherwise
	 */
	public boolean isPortalNearby() {
		ArrayList<Room> adjacentRooms = getAdjacentRooms();
		for (Room room : adjacentRooms) {
			if (room instanceof PortalRoom) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Checks if the player is near a treasure.
	 * 
	 * @return true if the player is near a treasure, false otherwise
	 */
	public boolean isTreasureNearby() {
		ArrayList<Room> adjacentRooms = getAdjacentRooms();
		for (Room room : adjacentRooms) {
			if (room instanceof TreasureRoom) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Obtains the key. Sets the hasKey flag to true.
	 */
	public void obtainKey() {
		hasKey = true;
	}

	/**
	 * 
	 * Performs teleportation if the player is in a portal room.
	 * 
	 * @return true if the player successfully teleported, false otherwise
	 */
	public boolean teleport() {
		if (getCurrentRoom() instanceof PortalRoom) {
			PortalRoom currentPortalRoom = (PortalRoom) getCurrentRoom();
			Room teleportLocation = currentPortalRoom.getTeleportLocation();
			setCurrentRoom(teleportLocation);
			System.out.println(PortalRoom.getTeleportMessage());
			return true;
		}
		return false;
	}
}
