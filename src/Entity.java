import java.util.ArrayList;

/**
 * 
 * 
 * The Entity class contains generic methods and variables that will be used for any entity in the game (Player, Enemies)
 * This class primarily contains code related to the position of the Entity but more functionality will be added in later releases
 * Additionally, this class contains the world object, which will allow children to access it.
 * 
 *
 */


public abstract class Entity {
private World world;
private Physics myPhysics;
private int walkSpeed; //Measured in pixels/second
private int jumpPower; //Measured in pixels/second


public Entity(World newWorld) {
	
	myPhysics = new Physics(newWorld.getStartXCoord(),newWorld.getStartXCoord());
	world = newWorld;
	
	walkSpeed = 1;
	jumpPower = 1;
	
	
	applyGravity();
	
}


/**
 * Setter for an entities x coordinate position. 
 * @param newX - The x coordinate position to put the entity in. 
 */
public void setXCoord(int newX) { 
	myPhysics.setXPosition(newX);
}
/**
 * Setter for an entities y coordinate position. 
 * @param newY - The y coordinate position to put the entity in. 
 */
public void setYCoord(int newY) { 
		myPhysics.setXPosition(newY);

}


/**
 * Getter for an entities x coordinate. 
 * @return The x coordinate of the entity. 
 */
public int getXCoord() {
	return myPhysics.getXPosition();
}
/**
 * Getter for an entities y coordinate. 
 * @return The y coordinate of the entity. 
 */
public int getYCoord() {
	return myPhysics.getYPosition();
}

/**
 * Updates gravity(acceleration) when called
 * Run inside walk and jump methods
 */
private void applyGravity() {
	
	
	
	ArrayList<String> coordinates= world.getPlatformCoordinates();
	
	//I don't really understand how platforms work so I'm doing it how I think it is.
	//Will likely be reimplemented
	
	int playerX = myPhysics.getXPosition();
	int underPlayerY = myPhysics.getYPosition()+1; //Underneath player
	
	
	
	
	for(String toParse:coordinates) {
		int x = Integer.parseInt(toParse.substring(0,toParse.indexOf(',')).trim());
		int y = Integer.parseInt(toParse.substring(toParse.indexOf(',')+1,toParse.length()).trim());
		
		if(x==playerX&&y==underPlayerY) {
			//Player is on playform
			return;
		}
		
		
	}
	
	//Player is not on a platform
	
	myPhysics.setYAcceleration(-1*Physics.GRAVITY);
	
	
	
	
	
	
	}


		








/**
 * Getter for the world the entity is in. 
 * @return The world the entity is in.  
 */
public World getWorld() {
	return world;
}

/**
 * 
 * @param Sets power with with the Entity will walk
 */
public void setWalkSpeed(int walk) {
	if(walk>0) {
		walkSpeed = walk;
	}
}


/**
 * 
 * @return current walking speed
 */
public int getWalkSpeed() {
	return walkSpeed;	
}

/**
 * 
 * @param Sets the jump intensity for Entity
 */
public void setJumpPower(int jump) {
	if(jump>0) {
		jumpPower = jump;
	}
}
/**
 * 
 * @return gets the jump intensity for Entity
 */
public int getJumpPower() {
	return jumpPower;	
}



/**
 * To string method to print the entity's x and y coordinate. 
 * @return The string of the entity's x and y coordinate.
 */
public String toString() {
	return (myPhysics.getXPosition()+","+myPhysics.getYPosition());
}



/**
 * Initiate an Entity jump based on it's jumping power
 */
public void jump() {
	
	myPhysics.setYVelocity(-1*jumpPower);
	applyGravity();
}
/**
 * Make the Entity walk to the left of the screen based on walking speed
 */
public void walkLeft() {
	myPhysics.setXVelocity(-1*walkSpeed);
	applyGravity();
}
/**
 * Make the Entity walk to the right of the screen based on walking speed
 */
public void walkRight() {
	myPhysics.setXVelocity(walkSpeed);
	applyGravity();
}



/**
 * 
 * @return physics Object
 */
public Physics getPhysics() {
	return myPhysics;
}

}
