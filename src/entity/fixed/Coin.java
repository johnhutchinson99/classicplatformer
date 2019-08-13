package entity.fixed;
import backend.World;
import backend.WorldObject;
import entity.moveable.Player;

/**
 * 
 * Creates a coin object
 * Coins can be collected to increase the player score
 */
public class Coin extends WorldObject{
	
	private int coinValue = 5;
	private boolean pickedUp = false;
	
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld) {
		super(xCoord, yCoord, width, height, aWorld);
	}
	
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld, int value) {
		super(xCoord, yCoord, width, height);
		coinValue = value;
	}
	
	/**
	 * Allows the player to pick up the coin. Returns true for successful collision
	 */
	public boolean doCollision(WorldObject object) {
		if (object instanceof Player) {
			object.getWorld().addToCoinCount(coinValue);
			pickedUp = true; 
		}
		return true;
	}
	
	/**
	 * Returns whether or not the coin is still in the world
	 */
	public boolean isAlive() {
		return !pickedUp;
	}
	
}

