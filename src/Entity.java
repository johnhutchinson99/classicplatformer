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
private double walkSpeed; 
private double jumpPower; 


public Entity(World newWorld) {
	
	myPhysics = new Physics(newWorld.getStartXCoord(),newWorld.getStartXCoord(),newWorld.getWorldMaxXCoord(),newWorld.getWorldMaxYCoord());
	world = newWorld;
	
	walkSpeed = 50;
	jumpPower = 1000;
	
	
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
	myPhysics.setYAcceleration(Physics.GRAVITY);
	
	
	
	
	
	
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
public void setWalkSpeed(double walk) {
	if(walk>0) {
		walkSpeed = walk;
	}
}


/**
 * 
 * @return current walking speed
 */
public double getWalkSpeed() {
	return walkSpeed;	
}

/**
 * 
 * @param Sets the jump intensity for Entity
 */
public void setJumpPower(double jump) {
	if(jump>0) {
		jumpPower = jump;
	}
}
/**
 * 
 * @return gets the jump intensity for Entity
 */
public double getJumpPower() {
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
	
	//myPhysics.setYPosition(myPhysics.getYPosition()-40);
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
