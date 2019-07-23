/**
 * 
 * 
 * The Entity class contains generic methods and variables that will be used for any entity in the game (Player, Enemies)
 * This class primarily contains code related to the position of the Entity but more functionality will be added in later releases
 * Additionally, this class contains the world object, which will allow children to access it.
 * 
 *
 */


public class Entity {
private int xCoord;
private int yCoord;
private World world;


public Entity(World newWorld) {
	xCoord = newWorld.getStartXCoord();
	yCoord = newWorld.getStartYCoord();
	world = newWorld;
}


/**
 * Setter for an entities x coordinate position. 
 * @param newX - The x coordinate position to put the entity in. 
 */
public void setXCoord(int newX) { 
	xCoord = newX;
}
/**
 * Setter for an entities y coordinate position. 
 * @param newY - The y coordinate position to put the entity in. 
 */
public void setYCoord(int newY) { 
		yCoord = newY;

}


/**
 * Getter for an entities x coordinate. 
 * @return The x coordinate of the entity. 
 */
public int getXCoord() {
	return xCoord;
}
/**
 * Getter for an entities y coordinate. 
 * @return The y coordinate of the entity. 
 */
public int getYCoord() {
	return yCoord;
}
/**
 * Getter for the world the entity is in. 
 * @return The world the entity is in.  
 */
public World getWorld() {
	return world;
}

/**
 * To string method to print the entity's x and y coordinate. 
 * @return The string of the entity's x and y coordinate.
 */
public String toString() {
	return (xCoord+","+yCoord);
}


}
