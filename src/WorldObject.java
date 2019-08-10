import java.util.ArrayList;

/**
 * 
 * 
 * The Entity class contains generic methods and variables that will be used for
 * any entity in the game (Player, Enemies) This class primarily contains code
 * related to the position of the Entity but more functionality will be added in
 * later releases Additionally, this class contains the world object, which will
 * allow children to access it.
 * 
 *
 */

public abstract class WorldObject {

	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private World world;
	
	public WorldObject() {

	}

	public WorldObject(int x, int y, int newWidth, int newHeight) {
		xCoord = x;
		yCoord = y;
		if (newWidth > 0) {
			width = newWidth;
		}
		if (newHeight > 0) {
			height = newHeight;
		}
	}
	
	public WorldObject(int x, int y,int newWidth,int newHeight, World aWorld) {
		this(x,y,newWidth,newHeight);
		world = aWorld;
	}

	public WorldObject(WorldObject toCopy) {
		xCoord = toCopy.xCoord;
		yCoord = toCopy.yCoord;
		width = toCopy.width;
		height = toCopy.height;
		world = toCopy.world;
	}
	
	public void setWorld(World wo) {
		world = wo;
	}
	
	/**
	 * 
	 * @return Width of the object (in X)
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * 
	 * @return Height of object (in Y)
	 */
	public int getHeight() {
		return height;
	}

	public void setXYCoord(int x, int y) {
		xCoord = x;
		yCoord = y;
	}
	
	/**
	 * Setter for an entities x coordinate position.
	 * 
	 * @param newX - The x coordinate position to put the entity in.
	 */
	public void setxCoord(int xCoord) {
		if (xCoord < (getWorld().getWorldWidth() - width) && xCoord >= 0 &&
				!getWorld().collidePlatform(this, xCoord, getyCoord())) {
			this.xCoord = xCoord;
		}
	}

	/**
	 * Setter for an entities y coordinate position.
	 * 
	 * @param newY - The y coordinate position to put the entity in.
	 */
	public void setyCoord(int yCoord) {
		if (yCoord <= (getWorld().getWorldHeight() - height) && yCoord > 0 &&
				!getWorld().collidePlatform(this, getxCoord(), yCoord)) {
			this.yCoord = yCoord;
		}
	}
	
	public void supersetxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public void supersetyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * Getter for an entities x coordinate.
	 * 
	 * @return The x coordinate of the entity.
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * Getter for an entities y coordinate.
	 * 
	 * @return The y coordinate of the entity.
	 */
	public int getyCoord() {
		return yCoord;
	}
	

	public World getWorld() {
		return world;
	}
	
	public boolean doCollision(Player p) {
		return true;
	}
	
	public void update() {
		
	}
//
//	/**
//	 * 
//	 * @param Other entity to test
//	 * @return Whether or not this has one of it's corners inside the inputted Entity
//	 */
//	private boolean isCornerInsideEntity(WorldObject otherEntity) {
//
//		// Entity must be colliding when corner of one entity is inside the other
//
//		// Checking if otherEntity has a corner inside this Entity
//
//		// Top Left corner
//		if (this.isWithinEntity(otherEntity.getXCoord(), otherEntity.getYCoord()))
//			return true;
//		// Top Right corner
//		if (this.isWithinEntity(otherEntity.getXCoord() + otherEntity.getWidth(), otherEntity.getYCoord()))
//			return true;
//		// Bottom Left corner
//		if (this.isWithinEntity(otherEntity.getXCoord(), otherEntity.getYCoord() + otherEntity.getHeight()))
//			return true;
//		if (this.isWithinEntity(otherEntity.getXCoord() + otherEntity.getWidth(),
//				otherEntity.getYCoord() + otherEntity.getHeight()))
//			return true;
//
//		return false;
//
//	}
//
//	/**
//	 * 
//	 * @param otherEntity to test
//	 * @return Whether or not the entities are colliding at the moment
//	 */
//	public boolean isCollidingWith(WorldObject otherEntity) {
//
//		// Entity must be colliding when corner of one entity is inside the other
//		return this.isCornerInsideEntity(otherEntity) || otherEntity.isCornerInsideEntity(this);
//	}
//
//	/**
//	 * 
//	 * @param x to check
//	 * @param y to check
//	 * @return whether or not the inputed coordinates are inside the Entity
//	 */
//	public boolean isWithinEntity(int x, int y) {
//
//		// checking x
//
//		if (x >= getXCoord() && x <= getXCoord() + getWidth()) {
//			if (y >= getYCoord() && y <= getYCoord() + getHeight()) {
//				return true;
//			}
//		}
//
//		return false;
//	}

}
