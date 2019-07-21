/**
 * 
 * @author John Hutchinson
 * 
 * The Entity class contains generic methods and variables that will be used for any entity in the game (Player, Enemies)
 * This class primarily contains code related to the position of the Entity but more functionality will be added in later releases
 *
 * 
 *
 */


public class Entity {
private int xCoord;
private int yCoord;
private World world;


public Entity(int startX,int startY,World newWorld) {
	xCoord = startX;
	yCoord = startY;
	world = newWorld;
}



public void setXCoord(int newX) {
	if(xCoord<=world.getfinalDestinationXCoord()) {
	xCoord = newX;
	}
}

public void setYCoord(int newY) {
	if(yCoord<=world.getfinalDestinationYCoord()) {
		yCoord = newY;
	}
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
