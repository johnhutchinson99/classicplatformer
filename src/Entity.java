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

	private Physics myPhysics;

	public Entity() {

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

}
