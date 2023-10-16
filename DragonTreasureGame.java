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
// https://canvas.wisc.edu/courses/355989/files/32985264?wrap=1
// https://canvas.wisc.edu/courses/355989/files/32985267?wrap=1
// https://canvas.wisc.edu/courses/355989/files/32985269?wrap=1
// https://canvas.wisc.edu/courses/355989/files/32986037?wrap=1
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Character.html
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Room.html#addToAdjacentRooms(Room)
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Room.html 
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/TreasureRoom.html
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/PortalRoom.html
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2022/p5/javadocs/Player.html
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Represents a game application where the player explores rooms to find
 * treasure and avoid the dragon displayed using the Processing library.
 */
public class DragonTreasureGame extends PApplet {
	private ArrayList<Room> roomList;
	private File roomInfo;
	private File mapInfo;
	private ArrayList<Character> characters;
	private boolean isDragonTurn;
	private int gameState;

	/**
	 * Sets up the size of the game window.
	 */
	@Override
	public void settings() {
		size(800, 600);
	}

	/**
	 * Initializes the game environment and loads necessary resources.
	 */
	@Override
	public void setup() {
		this.getSurface().setTitle("Dragon Treasure Adventure");
		this.imageMode(PApplet.CORNER);
		this.rectMode(PApplet.CORNERS);
		this.focused = true;
		this.textAlign(CENTER);
		this.textSize(20);

		TreasureRoom.setTreasureBackground(loadImage("images/treasure.jpg"));
		PortalRoom.setPortalImage(loadImage("images/portal.png"));

		roomList = new ArrayList<>();
		Room.setProcessing(this);

		roomInfo = new File("roominfo.txt");
		mapInfo = new File("map.txt");

		loadRoomInfo();
		loadMap();

		characters = new ArrayList<>();
		loadCharacters();

		isDragonTurn = false;
		gameState = 0;
	}

	/**
	 * Loads room information and creates room objects accordingly.
	 */
	private void loadRoomInfo() {
		System.out.println("Loading rooms...");
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(roomInfo);
			while (fileReader.hasNext()) {
				String nextLine = fileReader.nextLine();
				String[] parts = nextLine.split(" \\| ");
				int ID = Integer.parseInt(parts[1].trim());
				String imageName = null;
				String description = null;
				PImage image = null;
				Room newRoom = null;

				if (parts.length >= 3) {
					imageName = parts[2].trim();
					image = this.loadImage("images" + File.separator + imageName);
				}

				if (parts.length == 4) {
					description = parts[3].trim();
				}

				switch (parts[0].trim()) {
				case "S":
					newRoom = new StartRoom(ID, image);
					break;
				case "R":
					newRoom = new Room(ID, description, image);
					break;
				case "P":
					newRoom = new PortalRoom(ID, description, image);
					break;
				case "T":
					newRoom = new TreasureRoom(ID);
					break;
				default:
					break;
				}

				if (newRoom != null) {
					roomList.add(newRoom);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null)
				fileReader.close();
		}
	}

	/**
	 * Loads the map information and establishes the adjacent relationships between
	 * rooms.
	 */
	private void loadMap() {
		System.out.println("Loading map...");
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(mapInfo);
			while (fileReader.hasNext()) {
				String nextLine = fileReader.nextLine();
				String[] parts = nextLine.split(" ");
				int id = Integer.parseInt(parts[0]);
				Room toEdit = getRoomByID(id);

				for (int i = 1; i < parts.length; i++) {
					Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
					toEdit.addToAdjacentRooms(toAdjAdd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null)
				fileReader.close();
		}
	}

	/**
	 * Retrieves a room from the roomList based on its ID.
	 * 
	 * @param id the ID of the room to retrieve
	 * @return the room with the specified ID, or null if not found
	 */

	private Room getRoomByID(int id) {
		int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
		return roomList.get(indexToEdit);
	}

	/**
	 * Updates and renders the game state.
	 */
	@Override
	public void draw() {
		Room playerRoom = null;
		Player player = null;

		for (Room room : roomList) {
			for (Character character : characters) {
				if (character instanceof Player && character.getCurrentRoom() == room) {
					playerRoom = room;
					player = (Player) character;
					break;
				}
			}
			if (playerRoom != null) {
				break;
			}
		}

		if (playerRoom != null) {
			playerRoom.draw();
		}

		if (playerRoom != null && playerRoom instanceof TreasureRoom && player != null && player.hasKey()) {
			System.out.println("Congratulations! You found the treasure and escaped!");
		}

		Dragon dragon = null;
		Character keyHolder = null;

		for (Character character : characters) {
			if (character instanceof Dragon) {
				dragon = (Dragon) character;
			} else if (character.getLabel().equals("KEYHOLDER")) {
				keyHolder = character;
			}
		}

		if (player != null) {
			if (player.isDragonNearby(dragon)) {
				System.out.println(Dragon.getDragonWarning());
			}

			if (player.isPortalNearby()) {
				System.out.println(PortalRoom.getPortalWarning());
			}

			if (player.isTreasureNearby()) {
				System.out.println(TreasureRoom.getTreasureWarning());
			}
		}

		if (player != null && keyHolder != null && player.getCurrentRoom().equals(keyHolder.getCurrentRoom())
				&& !player.hasKey()) {
			player.obtainKey();
			System.out.println("KEY OBTAINED");
		}

		if (player != null && player.teleport()) {
			System.out.println(PortalRoom.getTeleportMessage());
		}

		if (isDragonTurn && gameState == 0) {
			Room destination = dragon.pickRoom();
			if (dragon.changeRoom(destination)) {
				isDragonTurn = false;
			}
		}

		if (player != null && player.getCurrentRoom() instanceof TreasureRoom && player.hasKey() && gameState == 0) {
			gameState = 1;
			System.out.println("Congratulations! You won!");
		}

		if (player != null && player.getCurrentRoom().containsCharacter(dragon) && gameState == 0) {
			gameState = 2;
			System.out.println(Dragon.getDragonEncounter());
		}
	}

	/**
	 * Handles key presses to allow the player to navigate through the game.
	 */
	@Override
	public void keyPressed() {
		if (gameState != 0)
			return;

		Player player = null;

		for (Character character : characters) {
			if (character instanceof Player) {
				player = (Player) character;
				break;
			}
		}

		if (player != null) {
			int keyID = (int) key - '0';
			Room destination = getRoomByID(keyID);

			if (player.changeRoom(destination)) {
				isDragonTurn = true;
			} else {
				System.out.println("Invalid room. Please choose a valid room.");
			}
		}
	}

	/**
	 * Loads character information and adds characters to the game.
	 */
	private void loadCharacters() {
		System.out.println("Adding characters...");
		characters.add(new Dragon(getRoomByID(5)));
		characters.add(new Player(getRoomByID(1)));
		characters.add(new Character(getRoomByID(9), "KEYHOLDER") {
			@Override
			public String getLabel() {
				return "KEYHOLDER";
			}
		});
	}

	public static void main(String[] args) {
		PApplet.main("DragonTreasureGame");
	}
}
