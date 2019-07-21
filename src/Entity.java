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
public int x;
public int y;



public Entity(int startX,int startY) {
	x = startX;
	y = startY;
}



public void setXCoord(int newX) {
	x = newX;
}
public void setYCoord(int newY) {
	y = newY;
}
public int getXCoord() {
	return x;
}
public int getYCoord() {
	return y;
}


public String toString() {
	return (x+","+y);
}


}
