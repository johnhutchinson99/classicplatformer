package entity.moveable;

import java.util.ArrayList;

import backend.PhysicsEntity;
import backend.World;


/**
 * This class creates a Player This class extends PhysicsEntity. This class
 * keeps track of player data, such as remainingAttacks allowed by the player,
 * position, player name, player alive or dead state, and the state of the
 * surrounding world. The player is able to track enemies in the world, allowing
 * for collision detection and attacks. In the next release, the player will be
 * capable of jumping.
 */

public class Player extends PhysicsEntity {
	private String playerName; // Optional, will be implemented later
	private int remainingAttacks; // How many more times a player is allowed to attack an enemy
	private boolean aliveness = true; // Whether or not there is a game over
	public static final int WALKSPEED = 200;
	public static final int JUMPPOWER = 400;
	private boolean isFacingRight = true;

	public Player(Player p) {
		super(p);
	}

	public Player(int xCoord, int yCoord, int width, int height) {
		super(xCoord, yCoord, width, height);
	}

	public Player(int xCoord, int yCoord, int width, int height, World world) {
		super(xCoord, yCoord, width, height, world);
	}

	/**
	 * Check if player is alive.
	 * 
	 * @return aliveness (true if alive, false if not alive)
	 */
	public boolean isAlive() {
		return aliveness;
	}

	/**
	 * Revives the player by changing their aliveness state to true.
	 */
	public void revive() {
		aliveness = true;
	}

	/**
	 * Kills the player by changing their aliveness state to false.
	 */
	public void kill() {
		aliveness = false;
	}

	/**
	 * Attacks the enemies in coordinates/"squares" surrounding the player.
	 * Attacking enemies means removing/killing enemies from the list of enemies
	 * (i.e. they die with a single attack).
	 */
	public void attack() {

		if (getWorld().getWorldWidth() < 100) {

			Enemy left = whoIsThere(getxCoord() - 1, getyCoord());
			if (left != null) {
				getWorld().removeFromListOfEnemies(left);
			}

			Enemy right = whoIsThere(getxCoord() + 1, getyCoord());
			if (right != null) {
				getWorld().removeFromListOfEnemies(right);
			}

			Enemy up = whoIsThere(getxCoord(), getyCoord() - 1);
			if (up != null) {
				getWorld().removeFromListOfEnemies(up);
			}

			Enemy down = whoIsThere(getxCoord(), getyCoord() + 1);
			if (down != null) {
				getWorld().removeFromListOfEnemies(down);
			}
		} else {
			ArrayList<Enemy> enemyList = getWorld().getListOfEnemies();
			for (Enemy e : enemyList) {
				getWorld().removeFromListOfEnemies(e);
			}
		}

	}

	/**
	 * Setter for whether the player should be facing right.
	 * 
	 * @param boolean, true if they should be facing right, false if not
	 */
	public void facingRight(boolean b) {
		isFacingRight = b;
	}

	/**
	 * Getter for whether the player should be facing right.
	 * 
	 * @return isFacingRight, true if they should be facing right, false if not
	 */
	public boolean isFacingRight() {
		return isFacingRight;
	}

	/**
	 * Checks if enemy is at the given X and Y coordinates.
	 * 
	 * @param checkX - The X coordinate to check.
	 * @param checkY - The Y coordinate to check.
	 * @return The enemy, if present.
	 */
	private Enemy whoIsThere(int checkX, int checkY) {
		for (Enemy toTest : getWorld().getListOfEnemies()) {

			if (toTest.getxCoord() == checkX && toTest.getyCoord() == checkY) {
				return toTest;

			}
		}

		return null;

	}

}
