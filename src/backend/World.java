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

//	public World(int maxX, int maxY, int startX, int startY, int finalX, int finalY,boolean isWater) {
//		worldMaxXCoord = maxX;
//		worldMaxYCoord = maxY;
//		startXCoord = startX;
//		startYCoord = startY;
//		goalXCoord = finalX;
//		goalYCoord = finalY;
//	}

	public void addToCoinCount(int points) {
		coinsCount += points;
	}

	public void resetCoinsCount() {
		coinsCount = 0;
	}

	public int getCoinsCount() {
		return coinsCount;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public ArrayList<Enemy> getEnemyList() {
		return listOfEnemies;
	}

	public void setEndPoint(EndPoint e) {
		endPoint = e;
	}

	public EndPoint getEndPoint() {
		return endPoint;
	}

	public void addPlayer(Player p) {
		thePlayer = p;
	}

	public Player getPlayer() {
		return thePlayer;
	}

	public void addEnemy(EnemyGUI e) {
		listOfEnemies.add(e);
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

//	/**
//	 * Getter for platforms (the arraylist containing all platforms in the world).
//	 * 
//	 * @return platforms - a list of platforms.
//	 */
//	public ArrayList<Platform> getPlatforms() {
//		
//
//		return platforms;
//	}
//
	/**
	 * Adds the given enemy to the world's list of enemies.
	 * 
	 * @param anEnemy - The enemy to add to the world.
	 */
	public void addToListOfEnemies(Enemy anEnemy) {
//		System.out.println("ADDED");
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
//			System.out.println("Collding");
			return object2.doCollision(object1);
		} else {
			return false;
		}
	}

	public boolean collidePlatform(WorldObject o, int xCoord, int yCoord) {

		boolean willCollide = false;

		int objectHeight = o.getHeight();
		int objectWidth = o.getWidth();

		for (Platform p : platforms) {

			WorldObject largerWidthObject;
			WorldObject smallerWidthObject;

			int largeLeft;
			int largeRight;
			int smallLeft;
			int smallRight;

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

			if ((largeLeft <= smallLeft && smallLeft <= largeRight)
					|| (largeLeft <= smallRight && smallRight <= largeRight)) {

				WorldObject largerHeightObject;
				WorldObject smallerHeightObject;
				int largeTop;
				int largeBottom;
				int smallTop;
				int smallBottom;

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

				if ((largeTop <= smallTop && smallTop <= largeBottom)
						|| (largeTop <= smallBottom && smallBottom <= largeBottom)) {
					willCollide = true;
				}

			}

		}

		return willCollide;
	}

}
