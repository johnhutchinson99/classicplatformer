
/**
* The World superclass contains information about the world.
* This class stores important data such as the maximum X and Y of a level, the starting position of a player and the location of the goal. 
*	This class also stores enemies in an Arraylist.
*/

import java.util.ArrayList;

// TODO: Make sure start and goal are within world dimensions

public class World {

	private int worldMaxXCoord;
	private int worldMaxYCoord;

	private int startXCoord;
	private int startYCoord;

	private int goalXCoord;
	private int goalYCoord;

	private ArrayList<Platform> platforms = new ArrayList<Platform>();

	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();

	public Player player;

	public World() {

	}

	public World(int maxX, int maxY, int startX, int startY, int finalX, int finalY) {
		worldMaxXCoord = maxX;
		worldMaxYCoord = maxY;
		startXCoord = startX;
		startYCoord = startY;
		goalXCoord = finalX;
		goalYCoord = finalY;
	}

	public void addPlatform(Platform p) {
		platforms.add(p);
	}

	/**
	 * Getter for listOfEnemies (the arraylist containing all enemies in the world).
	 * 
	 * @return listOfEnemies - a list of enemies.
	 */
	public ArrayList<Enemy> getListOfEnemies() {
		ArrayList<Enemy> returnedListOfEnemies = new ArrayList<Enemy>(listOfEnemies);

		return returnedListOfEnemies;
	}


	/**
	 * Getter for the world's max X coordinate (world size).
	 * 
	 * @return worldMaxXCoord - the world's max X coordinate allowed.
	 */
	public int getWorldMaxXCoord() {
		return worldMaxXCoord;
	}

	/**
	 * Setter for the world's max X coordinate (world size).
	 * 
	 * @param worldMaxXCoord - The desired max X coordinate.
	 */
	public void setWorldMaxXCoord(int maxXCoord) {
		worldMaxXCoord = maxXCoord;
	}

	/**
	 * Getter for the world's max Y coordinate (world size).
	 * 
	 * @return worldMaxXCoord - the world's max Y coordinate allowed.
	 */
	public int getWorldMaxYCoord() {
		return worldMaxYCoord;
	}

	/**
	 * Setter for the world's max Y coordinate (world size).
	 * 
	 * @param worldMaxXYoord - The desired max Y coordinate.
	 */
	public void setWorldMaxYCoord(int maxYCoord) {
		worldMaxYCoord = maxYCoord;
	}

	/**
	 * Getter for the player's starting X coordinate.
	 * 
	 * @return startXCoord - The player's starting X coordinate.
	 */
	public int getStartXCoord() {
		return startXCoord;
	}

	/**
	 * Setter for the player's starting X coordinate.
	 * 
	 * @param startXCoord - The player's desired starting X coordinate.
	 */
	public void setStartXCoord(int XCoord) {
		startXCoord = XCoord;
	}

	/**
	 * Getter for the player's starting Y coordinate.
	 * 
	 * @return startXCoord - The player's starting Y coordinate.
	 */
	public int getStartYCoord() {
		return startYCoord;
	}

	/**
	 * Setter for the player's starting Y coordinate.
	 * 
	 * @param startXCoord - The player's desired starting Y coordinate.
	 */
	public void setStartYCoord(int YCoord) {
		startYCoord = YCoord;
	}

	/**
	 * Getter for the player's goal X coordinate.
	 * 
	 * @return goalXCoord - The goal X coordinate for the player to reach.
	 */
	public int getGoalXCoord() {
		return goalXCoord;
	}

	/**
	 * Setter for the player's goal X coordinate.
	 * 
	 * @param goalXCoord - The desired goal X coordinate for the player to reach.
	 */
	public void setGoalXCoord(int XCoord) {
		goalXCoord = XCoord;
	}

	/**
	 * Getter for the player's goal Y coordinate.
	 * 
	 * @return goalYCoord - The goal Y coordinate for the player to reach.
	 */
	public int getGoalYCoord() {
		return goalYCoord;
	}

	/**
	 * Setter for the player's goal Y coordinate.
	 * 
	 * @param goalYCoord - The desired goal Y coordinate for the player to reach.
	 */
	public void setGoalYCoord(int YCoord) {
		goalYCoord = YCoord;
	}

	/**
	 * Adds the given enemy to the world's list of enemies.
	 * 
	 * @param anEnemy - The enemy to add to the world.
	 */
	public void addToListOfEnemies(Enemy anEnemy) {
		// We do want this privacy leak in? Since we want the same enemy so we can
		// remove later??
		// TODO: Need to consider this more thoroughly
		listOfEnemies.add(anEnemy);
	}

	/**
	 * Removes the given enemy from the world's list of enemies. Used in instances
	 * where the enemy is "killed" or gotten rid of.
	 * 
	 * @param anEnemy - The enemy to add to the world.
	 */
	public void removeFromListOfEnemies(Enemy anEnemy) {
		listOfEnemies.remove(anEnemy);
	}

	/**
	 * Checks whether the given player is at/has reached the world's specified goal
	 * X and Y coordinates.
	 * 
	 * @param player - The player you would like to check (whether they are at the
	 *               goal desination).
	 * @return boolean true if player has reached the destination, false otherwise
	 */
	public boolean isPlayerAtGoal(Player player) {
		if (player.getXCoord() >= goalXCoord - 10 && player.getXCoord() <= goalXCoord + 10
				&& player.getYCoord() == goalYCoord) {
			return true;
		} else {
			return false;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	/**
	 * Draws the world (player, enemies, final destination) in the text-based
	 * version of our game.
	 * 
	 * @param player - The main character/player of the game (to be drawn).
	 */
	public void drawWorld(Player player, int worldMaxXCoord, int worldMaxYCoord, int goalXCoord, int goalYCoord) {
		/*
		 * Once we get everything running, I'm going to split this into 2 methods: an
		 * updateWorldContents method and a drawWorld method.
		 * 
		 * Also, this can only take world sizes up to 9x9 since the axis will display
		 * differently otherwise.
		 */
		int playerXCoord = player.getXCoord();
		int playerYCoord = player.getYCoord();

		// The character array for the world
		char[][] allWorldContents = new char[worldMaxYCoord + 1][worldMaxXCoord + 1];

		// Initializing the world with space char
		for (int i = 0; i < worldMaxYCoord; i++) {
			for (int j = 0; j < worldMaxXCoord; j++) {
				allWorldContents[i][j] = ' ';
			}
		}

		// Add the player to the world (if they are visible)
		if (0 <= playerYCoord && 0 <= playerXCoord) {
			allWorldContents[playerYCoord][playerXCoord] = '>';
		}
		// Add the final destination to the world
		allWorldContents[goalYCoord][goalXCoord] = 'O';

		// Add enemies to the world
		for (Enemy anEnemy : listOfEnemies) {
			allWorldContents[anEnemy.getYCoord()][anEnemy.getXCoord()] = 'E';
		}

		// Make the top and bottom borders.
		String horizontalBorder = "#";
		for (int i = 0; i <= worldMaxXCoord; i++) {
			horizontalBorder += "|#";
		}
		horizontalBorder += "|#";

		// Print the world and its borders.
		System.out.println("\n");
		System.out.println(horizontalBorder);
		for (int i = (worldMaxYCoord); i >= 0; i--) {
			String worldContentsString = "#";
			for (int j = 0; j <= worldMaxXCoord; j++) {
				worldContentsString += "|" + allWorldContents[i][j];
			}
			worldContentsString += "|#";
			System.out.println(worldContentsString);
		}
		System.out.println(horizontalBorder + "\n");
	}

}
