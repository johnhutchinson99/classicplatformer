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
private int x;
private int y;
private World world;


public Entity(int startX,int startY,World newWorld) {
	x = startX;
	y = startY;
	world = newWorld;
}



public void setXCoord(int newX) {
	if(x<=world.getfinalDestinationXCoord()) {
	x = newX;
	}
}

public void setYCoord(int newY) {
	if(x<=world.getfinalDestinationXCoord()) {
		x = newY;
	}
}


public void moveOffScreen() {
	x = 1000;
	y = 1000;
}

public int getXCoord() {
	return x;
}
public int getYCoord() {
	return y;
}
public World getWorld() {
	return world;
}


public String toString() {
	return (x+","+y);
}


}
