package entity.moveable;
import java.util.ArrayList;
import java.util.Scanner;

import backend.PhysicsEntity;
import backend.World;

import java.util.Iterator;

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
	public static final int JUMPPOWER =400;
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

	public boolean isAlive() {
		return aliveness;
	}

	public void revive() {
		aliveness = true;
	}

	
	public void kill() {
		aliveness = false;
	}
	
	////////////////////////////////////
//	/**
//	 * Setter for moving player right.
//	 * 
//	 * @param translate - The amount you want to move/translate the player by.
//	 */
//	public void moveRight(int translate) {
//		setXCoord(getXCoord() + translate);
//	}
//
//	/**
//	 * Setter for moving player left.
//	 * 
//	 * @param translate - The amount you want to move/translate the player by.
//	 */
//	public void moveLeft(int translate) {
//		setXCoord(getXCoord() - translate);
//
//	}
//
//	/**
//	 * Setter for moving player up.
//	 * 
//	 * @param translate - The amount you want to move/translate the player by.
//	 */
//	public void moveUp(int translate) {
//		setYCoord(getYCoord() - translate);
//
//	}
//
//	/**
//	 * Setter for moving player down.
//	 * 
//	 * @param translate - The amount you want to move/translate the player by.
//	 */
//	public void moveDown(int translate) {
//		setYCoord(getYCoord() + translate);
//
//	}

//	/**
//	 * Checks if player is colliding with other objects (e.g. enemies).
//	 * 
//	 * @return boolean true if colliding, false otherwise.
//	 */
//	public boolean isCollidingWithEnemy() {
//		if (getWorld().getWorldMaxXCoord() > 100) {// Runs when the world is large
//
//			for (Enemy p : getWorld().getListOfEnemies()) {
//
//				if (this.isCollidingWith(p))
//					return true;
//			}
//
//		} else {
//
//			if (whoIsThere(getXCoord(), getYCoord()) != null) {
//				return true;
//			}
//		}
//		return false;
//	}

//	/**
//	 * Checks if player is alive.
//	 * 
//	 * @return boolean true if alive, false otherwise.
//	 */
//	public boolean isAlive() {
//		if (aliveness && !isCollidingWithEnemy()) {
//			return true;
//		}
//		return false;
//	}

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
//			System.out.println(getWorld().getListOfEnemies().size());
			for (Enemy e : enemyList) {
				getWorld().removeFromListOfEnemies(e);
			}
//			System.out.println(getWorld().getListOfEnemies().size());
		}

	}

	
	

	public void facingRight(boolean b) {
		isFacingRight = b;
	}
	
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
