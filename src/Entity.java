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

	public int getWidth() {
		return width;
	}

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
			if (y >= getYCoord() && y <= getYCoord() + getWidth()) {
				return true;
			}
		}

		return false;
	}

}
