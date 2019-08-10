
/**
* The World superclass contains information about the world.
* This class stores important data such as the maximum X and Y of a level, the starting position of a player and the location of the goal. 
*	This class also stores enemies in an Arraylist.
*/

import java.util.ArrayList;
public class World {

	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private ArrayList<EnemyGUI> enemyList = new ArrayList<EnemyGUI>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();

//	public Player player;
	private Player thePlayer; 
	
	private int worldWidth;
	private int worldHeight;
	private EndPoint endPoint = new EndPoint(0,0,0,0);
	
	public World() {
		worldWidth = Integer.MAX_VALUE;
		worldHeight = Integer.MAX_VALUE;
	}
	
	public World(int w, int h) {
		worldWidth = w;
		worldHeight = h;
	}

//	public World(int maxX, int maxY, int startX, int startY, int finalX, int finalY,boolean isWater) {
//		worldMaxXCoord = maxX;
//		worldMaxYCoord = maxY;
//		startXCoord = startX;
//		startYCoord = startY;
//		goalXCoord = finalX;
//		goalYCoord = finalY;
//	}
	
	
	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}
	

	public ArrayList<EnemyGUI> getEnemyList() {
		return enemyList;
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
		enemyList.add(e);
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

	

	public boolean isCollide(Player p, WorldObject o) {
		boolean inRXArea = (p.getxCoord()+p.getWidth() >= o.getxCoord() && p.getxCoord()+p.getWidth() <= o.getxCoord()+o.getWidth());
		boolean inLXArea = (p.getxCoord() >= o.getxCoord() && p.getxCoord() <= o.getxCoord()+o.getWidth());
		boolean inLYArea = (p.getyCoord()+p.getHeight() >= o.getyCoord() && p.getyCoord()+p.getHeight() <= o.getyCoord()+o.getHeight());
		boolean inUYArea = (p.getyCoord() >= o.getyCoord() && p.getyCoord() <= o.getyCoord()+o.getHeight());
		if ((inRXArea || inLXArea) && (inLYArea || inUYArea)) {
			return o.doCollision(p);
		} else {
			return false;
		}
	}

	public boolean collidePlatform(WorldObject o, int yCoord, int xCoord) {

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
