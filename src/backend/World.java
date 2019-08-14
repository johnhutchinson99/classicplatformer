package backend;

/**
* The World superclass contains information about the world.
* This class stores important data such as the maximum X and Y of a level, the starting position of a player and the location of the goal. 
*	This class also stores enemies in an Arraylist.
*/

import java.util.ArrayList;

import entity.fixed.EndPoint;
import entity.fixed.Platform;
import entity.moveable.Enemy;
import entity.moveable.EnemyGUI;
import entity.moveable.Player;

public class World {

	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private ArrayList<EnemyGUI> enemyList = new ArrayList<EnemyGUI>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();

	private int coinsCount;

	private Player thePlayer;

	private int worldWidth;
	private int worldHeight;
	private int worldStartX;
	private int worldStartY;
	private EndPoint endPoint = new EndPoint(0, 0, 0, 0);

	public World() {
		worldWidth = Integer.MAX_VALUE;
		worldHeight = Integer.MAX_VALUE;
	}

	public World(int width, int height, int startX, int startY) {
		worldWidth = width;
		worldHeight = height;
		worldStartX = startX;
		worldStartY = startY;
	}

	/**
	 * Add score points to the world's count of coins collected.
	 * 
	 * @param points - the score points to add, usually the coin value
	 */
	public void addToCoinCount(int points) {
		coinsCount += points;
	}

	/**
	 * Reset the world's count of coins collected
	 */
	public void resetCoinsCount() {
		coinsCount = 0;
	}

	/**
	 * Getter for the count of coins collected (coinsCount)
	 * 
	 * @return coinsCount - the count of coins collected (in score points)
	 */
	public int getCoinsCount() {
		return coinsCount;
	}

	/**
	 * Getter for world width
	 * 
	 * @return world width as an int
	 */
	public int getWorldWidth() {
		return worldWidth;
	}

	/**
	 * Getter for world height
	 * 
	 * @return world height as an int
	 */
	public int getWorldHeight() {
		return worldHeight;
	}

	/**
	 * Getter for array list that contains the world's enemies
	 * 
	 * @return listOfEnemies - a list of enemies in the world
	 */
	public ArrayList<Enemy> getEnemyList() {
		return listOfEnemies;
	}

	/**
	 * Setter for the world's end point/desired goal location
	 * 
	 * @param end - the endpoint of the world
	 */
	public void setEndPoint(EndPoint end) {
		endPoint = end;
	}

	/**
	 * Getter for the world's end point/desired goal location
	 * 
	 * @return endPoint - the endpoint of the world
	 */
	public EndPoint getEndPoint() {
		return endPoint;
	}

	/**
	 * Add a player to the world
	 * 
	 * @param player - the player to add to the world
	 */
	public void addPlayer(Player player) {
		thePlayer = player;
	}

	/**
	 * Getter for the world's player
	 * 
	 * @return thePlayer - the world's player
	 */
	public Player getPlayer() {
		return thePlayer;
	}

	/**
	 * Add enemies to the list of enemies in the world
	 * 
	 * @param enemy - the enemy to add to the list of enemies in the world
	 */
	public void addEnemy(EnemyGUI enemy) {
		listOfEnemies.add(enemy);
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
	 * Adds the given enemy to the world's list of enemies.
	 * 
	 * @param anEnemy - The enemy to add to the world.
	 */
	public void addToListOfEnemies(Enemy anEnemy) {
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
	 * Collision detection method between to world objects. Does the collection
	 * method of the second object with the first passed as a parameter.
	 * 
	 * @param object1 - the first object to check whether collision will occur with.
	 *                The object that will be acted upon.
	 * @param object2 - the second object to check whether collision will occur
	 *                with. The object that will act upon the other object.
	 * @return boolean - true if colliding, false if not.
	 */
	public boolean isCollide(WorldObject object1, WorldObject object2) {

		WorldObject smallerWidthObject, largerWidthObject, smallerHeightObject, largerHeightObject;

		// First determine which object has the smaller width and which has the smaller
		// height
		if (object1.getWidth() < object2.getWidth()) {
			smallerWidthObject = object1;
			largerWidthObject = object2;
		} else {
			smallerWidthObject = object2;
			largerWidthObject = object1;
		}

		if (object1.getHeight() < object2.getHeight()) {
			smallerHeightObject = object1;
			largerHeightObject = object2;
		} else {
			smallerHeightObject = object2;
			largerHeightObject = object1;
		}

		// Check if the smaller width object has a corner within the other object
		boolean inRXArea = (smallerWidthObject.getxCoord() + smallerWidthObject.getWidth() >= largerWidthObject
				.getxCoord()
				&& smallerWidthObject.getxCoord() + smallerWidthObject.getWidth() <= largerWidthObject.getxCoord()
						+ largerWidthObject.getWidth());
		boolean inLXArea = (smallerWidthObject.getxCoord() >= largerWidthObject.getxCoord()
				&& smallerWidthObject.getxCoord() <= largerWidthObject.getxCoord() + largerWidthObject.getWidth());

		// Likewise, check if the smaller height object has a corner within the other
		// object
		boolean inLYArea = (smallerHeightObject.getyCoord() + smallerHeightObject.getHeight() >= largerHeightObject
				.getyCoord()
				&& smallerHeightObject.getyCoord() + smallerHeightObject.getHeight() <= largerHeightObject.getyCoord()
						+ largerHeightObject.getHeight());
		boolean inUYArea = (smallerHeightObject.getyCoord() >= largerHeightObject.getyCoord()
				&& smallerHeightObject.getyCoord() <= largerHeightObject.getyCoord() + largerHeightObject.getHeight());

		// If there are corners that overlap in both the horizontal and vertical
		// directions, then do the collision method
		if ((inRXArea || inLXArea) && (inLYArea || inUYArea)) {
			return object2.doCollision(object1);
		} else {
			return false;
		}
	}

	/**
	 * Check if a world object is colliding with a platform at a given x and y
	 * coordinate (can be used to check whether they will collide at some future
	 * point).
	 * 
	 * @param o      - the object to check whether they will collide with platform
	 * @param xCoord - the x coordinate of the object to check at
	 * @param yCoord - the y coordinate of the object to check at
	 * @return willCollide - true if colliding, false if not
	 */
	public boolean collidePlatform(WorldObject o, int xCoord, int yCoord) {

		boolean willCollide = false;

		int objectHeight = o.getHeight();
		int objectWidth = o.getWidth();

		for (Platform p : platforms) {

			// First determine the smaller width object
			WorldObject largerWidthObject;
			WorldObject smallerWidthObject;

			int largeLeft;
			int largeRight;
			int smallLeft;
			int smallRight;

			// Check whether the horizontal edge of the smaller is within that of the larger
			// object (i.e. colliding in the x direction)
			if (p.getWidth() > objectWidth) {
				largerWidthObject = p;
				smallerWidthObject = o;
				largeLeft = largerWidthObject.getxCoord();
				largeRight = largerWidthObject.getxCoord() + largerWidthObject.getWidth();
				smallLeft = xCoord;
				smallRight = xCoord + smallerWidthObject.getWidth();
			} else {
				largerWidthObject = o;
				smallerWidthObject = p;
				largeLeft = xCoord;
				largeRight = xCoord + largerWidthObject.getWidth();
				smallLeft = smallerWidthObject.getxCoord();
				smallRight = smallerWidthObject.getxCoord() + smallerWidthObject.getWidth();
			}

			// Check for which object has the smaller height
			if ((largeLeft <= smallLeft && smallLeft <= largeRight)
					|| (largeLeft <= smallRight && smallRight <= largeRight)) {

				WorldObject largerHeightObject;
				WorldObject smallerHeightObject;
				int largeTop;
				int largeBottom;
				int smallTop;
				int smallBottom;

				// Check whether vertical edge of smaller height is with the larger object
				// (colliding in y direction)
				if (p.getHeight() > objectHeight) {
					largerHeightObject = p;
					smallerHeightObject = o;
					largeTop = largerHeightObject.getyCoord();
					largeBottom = largerHeightObject.getyCoord() + largerHeightObject.getHeight();
					smallTop = yCoord;
					smallBottom = yCoord + smallerHeightObject.getHeight();
				} else {
					largerHeightObject = o;
					smallerHeightObject = p;
					largeTop = yCoord;
					largeBottom = yCoord + largerHeightObject.getHeight();
					smallTop = smallerHeightObject.getyCoord();
					smallBottom = smallerHeightObject.getyCoord() + smallerHeightObject.getHeight();
				}

				// If colliding in and horizontal vertical directions, return true that it will
				// collide
				if ((largeTop <= smallTop && smallTop <= largeBottom)
						|| (largeTop <= smallBottom && smallBottom <= largeBottom)) {
					willCollide = true;
				}

			}

		}

		return willCollide;
	}

}
