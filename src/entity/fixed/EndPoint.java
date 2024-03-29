package entity.fixed;
import backend.WorldObject;
import entity.moveable.Player;

/**
 * Class for the EndPoint.
 * Colliding with an EndPoint object allows the user to advance to the next level/win the game. 
 *
 */
public class EndPoint extends WorldObject{

	/**
	 * Constructor for the EndPoint / level finish location
	 * @param x - the x coordinate to set the end point to
	 * @param y - the y coordinate to set the end point to
	 * @param newWidth - the width to set the end point to
	 * @param newHeight - the height to set the end point to
	 */
	public EndPoint(int x, int y, int newWidth, int newHeight) {
		super(x,y,newWidth,newHeight);
	}
	
	/**
	 * The doCollision method is what the EndPoint does (to the player) when it
	 * collides with the player.
	 * 
	 * @param Player player - the player that has collided with the object.
	 * @return boolean true - returns true in order to signify the method has
	 *         finished running
	 */
	public boolean doCollision(Player player) {
		return true;
	}

}
