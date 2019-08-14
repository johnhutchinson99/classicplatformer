package entity.fixed;

import backend.World;
import backend.WorldObject;
import entity.moveable.Player;

/**
 * Creates a coin object. Coins can be collected to increase the player score.
 */
public class Coin extends WorldObject {

	private int coinValue = 5;
	private boolean pickedUp = false;

	/**
	 * Constructor for coin object.
	 * 
	 * @param xCoord - the x coordinate to set the coin to.
	 * @param yCoord - the y coordinate to set the coin to.
	 * @param width  - the width to set the coin to.
	 * @param height - the height to set the coin to.
	 * @param aWorld - the world the coin is in.
	 */
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld) {
		super(xCoord, yCoord, width, height, aWorld);
	}

	/**
	 * Constructor for coin object.
	 * 
	 * @param xCoord - the x coordinate to set the coin to.
	 * @param yCoord - the y coordinate to set the coin to.
	 * @param width  - the width to set the coin to.
	 * @param height - the height to set the coin to.
	 * @param aWorld - the world the coin is in.
	 * @param value  - the value of the coin (how many score points it's worth)
	 */
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld, int value) {
		super(xCoord, yCoord, width, height);
		coinValue = value;
	}

	/**
	 * Allows the player to pick up the coin. Will only be picked up if colliding
	 * with an instance of player. Returns true for successful collision/pick up.
	 */
	public boolean doCollision(WorldObject object) {
		if (object instanceof Player) {
			object.getWorld().addToCoinCount(coinValue);
			pickedUp = true;
		}
		return true;
	}

	/**
	 * Returns whether or not the coin is still in the world/has been picked up yet.
	 */
	public boolean isAlive() {
		return !pickedUp;
	}

}
