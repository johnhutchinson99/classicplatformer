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

public abstract class Entity {

	private int width;
	private int height;

	private Physics myPhysics;

	public Entity() {

	}

	public Entity(int newWidth, int newHeight) {
		if (newWidth > 0) {
			width = newWidth;
		}
		if (newHeight > 0) {
			height = newHeight;
		}
	}
	
	public Entity(int x, int y,int newWidth,int newHeight) {
		this(newWidth,newHeight);
		myPhysics.setXPosition(x);
		myPhysics.setYPosition(y);
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

	/**
	 * Setter for an entities x coordinate position.
	 * 
	 * @param newX - The x coordinate position to put the entity in.
	 */
	public void setXCoord(int newX) {
		myPhysics.setXPosition(newX);
	}

	/**
	 * Setter for an entities y coordinate position.
	 * 
	 * @param newY - The y coordinate position to put the entity in.
	 */
	public void setYCoord(int newY) {
		myPhysics.setXPosition(newY);

	}

	/**
	 * Getter for an entities x coordinate.
	 * 
	 * @return The x coordinate of the entity.
	 */
	public int getXCoord() {
		return myPhysics.getXPosition();
	}

	/**
	 * Getter for an entities y coordinate.
	 * 
	 * @return The y coordinate of the entity.
	 */
	public int getYCoord() {
		return myPhysics.getYPosition();
	}

	/**
	 * To string method to print the entity's x and y coordinate.
	 * 
	 * @return The string of the entity's x and y coordinate.
	 */
	public String toString() {
		return (myPhysics.getXPosition() + "," + myPhysics.getYPosition());
	}

	/**
	 * 
	 * @return physics Object
	 */
	public Physics getPhysics() {
		return myPhysics;
	}

	/**
	 * 
	 * allows for setting/initializing physics object
	 */
	public void setPhysics(Physics newPhysics) {
		myPhysics = newPhysics;

	}

	
	/**
	 * 
	 * @param Other entity to test
	 * @return Whether or not this has one of it's corners inside the inputted Entity
	 */
	private boolean isCornerInsideEntity(Entity otherEntity) {

		// Entity must be colliding when corner of one entity is inside the other

		// Checking if otherEntity has a corner inside this Entity

		// Top Left corner
		if (this.isWithinEntity(otherEntity.getXCoord(), otherEntity.getYCoord()))
			return true;
		// Top Right corner
		if (this.isWithinEntity(otherEntity.getXCoord() + otherEntity.getWidth(), otherEntity.getYCoord()))
			return true;
		// Bottom Left corner
		if (this.isWithinEntity(otherEntity.getXCoord(), otherEntity.getYCoord() + otherEntity.getHeight()))
			return true;
		if (this.isWithinEntity(otherEntity.getXCoord() + otherEntity.getWidth(),
				otherEntity.getYCoord() + otherEntity.getHeight()))
			return true;

		return false;

	}

	/**
	 * 
	 * @param otherEntity to test
	 * @return Whether or not the entities are colliding at the moment
	 */
	public boolean isCollidingWith(Entity otherEntity) {

		// Entity must be colliding when corner of one entity is inside the other
		return this.isCornerInsideEntity(otherEntity) || otherEntity.isCornerInsideEntity(this);
	}

	/**
	 * 
	 * @param x to check
	 * @param y to check
	 * @return whether or not the inputed coordinates are inside the Entity
	 */
	public boolean isWithinEntity(int x, int y) {

		// checking x

		if (x >= getXCoord() && x <= getXCoord() + getWidth()) {
			if (y >= getYCoord() && y <= getYCoord() + getHeight()) {
				return true;
			}
		}

		return false;
	}

}
