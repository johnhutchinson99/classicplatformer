package textbased;
import java.util.Scanner;

import backend.World;
import entity.moveable.Enemy;
import entity.moveable.Player;

/**
 * Gameplay contains the main method which runs the game; in other words, this
 * class controls the introduction to the game and runs each level of the game.
 */

public class Gameplay {
	
	private Scanner kb = new Scanner(System.in);

	/**
	 * Draws the world (player, enemies, final destination) in the text-based
	 * version of our game.
	 * 
	 * @param player - The main character/player of the game (to be drawn).
	 */
	public void drawWorld(Player player, World aWorld) { // int worldMaxXCoord, int worldMaxYCoord, int goalXCoord, int
															// goalYCoord) {
		/*
		 * Once we get everything running, I'm going to split this into 2 methods: an
		 * updateWorldContents method and a drawWorld method.
		 * 
		 * Also, this can only take world sizes up to 9x9 since the axis will display
		 * differently otherwise.
		 */
		int playerXCoord = player.getxCoord();
		int playerYCoord = player.getyCoord();

		// The character array for the world
		char[][] allWorldContents = new char[aWorld.getWorldHeight() + 1][aWorld.getWorldWidth() + 1];

		// Initializing the world with space char
		for (int i = 0; i < aWorld.getWorldHeight(); i++) {
			for (int j = 0; j < aWorld.getWorldWidth(); j++) {
				allWorldContents[i][j] = ' ';
			}
		}

		// Add the player to the world (if they are visible)
		if (0 <= playerYCoord && 0 <= playerXCoord) {
			allWorldContents[playerYCoord][playerXCoord] = '>';
		}
		// Add the final destination to the world
		allWorldContents[aWorld.getEndPoint().getyCoord()][aWorld.getEndPoint().getxCoord()] = 'O';

		// Add enemies to the world
		for (Enemy anEnemy : player.getWorld().getListOfEnemies()) {
			allWorldContents[anEnemy.getyCoord()][anEnemy.getxCoord()] = 'E';
		}

		// Make the top and bottom borders.
		String horizontalBorder = "#";
		for (int i = 0; i <= aWorld.getWorldWidth(); i++) {
			horizontalBorder += "|#";
		}
		horizontalBorder += "|#";

		// Print the world and its borders.
		System.out.println("\n");
		System.out.println(horizontalBorder);
		for (int i = (aWorld.getWorldHeight()); i >= 0; i--) {
			String worldContentsString = "#";
			for (int j = 0; j <= aWorld.getWorldWidth(); j++) {
				worldContentsString += "|" + allWorldContents[i][j];
			}
			worldContentsString += "|#";
			System.out.println(worldContentsString);
		}
		System.out.println(horizontalBorder + "\n");
	}

	/**
	 * Asks user/player for instructions on what to do with character. Opens an
	 * input space that allows the user to instruct the program to move the player
	 * left/right or attack.
	 * 
	 * @param player - the player that the input will act on
	 */
	public void askUserInstruction(Player player) {

		System.out.println(
				"Enter L to move left. \n" + "Enter R to move right. \n" + "Enter A to attack surrounding squares. ");

		String userInput = kb.nextLine();

		switch (userInput) {
		case "L":
			player.setxCoord(player.getxCoord()-1);
			break;
		case "R":
			player.setxCoord(player.getxCoord()+1);
			break;
		case "A":
			player.attack();
			break;
		default:
			System.out.print("Input not valid. Please try again.");
			break;
		}
	}

	/**
	 * Delays the next step of the game by the given time in miliseconds.
	 * 
	 * @param miliseconds - the time in miliseconds to delay next step by.
	 */
	public void delayGame(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Prints the instructions for the game.
	 */
	public void printInstructions() {
		System.out.println("INSTRUCTIONS: \n" + "This is the text-based version of our CPSC 233 game (title TBA).\n"
				+ "In this platformer-like game, the goal is to get your character (>) to\n"
				+ "  reach a final destination (O) in the map, avoiding enemies. \n"
				+ "The symbol > represents your character and it's direction.\n"
				+ "The symbol O represents the goal destination.\n" + "The symbol E represents an enemy.\n"
				+ "If you touch an enemy before reaching the final destination, \n" + "  then you die/lose.\n"
				+ "At each time, you will have the options to move left/right, \n"
				+ "  jump, or kill an enemy in a surrounding square. \n" + "Good luck.\n\n "

		);
	}

	/**
	 * The main program which runs the game by calling and playing each level. After
	 * the levels are finished playing, announce whether the game has been won/lost.
	 * 
	 * @param args - The standard parameter for a main method.
	 */
	public static void main(String[] args) {

		Gameplay game = new Gameplay();
		LevelOne levelOne = new LevelOne();
		LevelTwo levelTwo = new LevelTwo();

		// Game Intro / Instructions
		System.out.println("Welcome to Game Man! \n");
		game.printInstructions();

		// Begin to play the levels, print whether they have won or lost
		if (levelOne.playLevelOne() && levelTwo.playLevelTwo()) {
			System.out.print("CONGRATULATIONS! YOU HAVE WON!");
		} else {
			System.out.print("YOU HAVE DIED. GAME OVER");
		}
		
	}

}
