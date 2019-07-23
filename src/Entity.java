/**
 * 
 * @author John Hutchinson
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



public boolean setXCoord(int newX) { //Returns true if coordinates were changed successfully. Returns false if coordinates could not be changed (likely due to coordinates being outside of bounds)
	if(xCoord<=world.getWorldMaxXCoord()) {
	xCoord = newX;
	return true;
	}
	return false;
}

public boolean setYCoord(int newY) { //Returns true if coordinates were changed successfully. Returns false if coordinates could not be changed (likely due to coordinates being outside of bounds)
	if(yCoord<=world.getWorldMaxYCoord()) {
		yCoord = newY;
		return true;
	}
	return false;
}


public void moveOffScreen() {
	xCoord = 1000;
	yCoord = 1000;
}

public int getXCoord() {
	return xCoord;
}
public int getYCoord() {
	return yCoord;
}
public World getWorld() {
	return world;
}


public String toString() {
	return (xCoord+","+yCoord);
}


}
