import java.util.Scanner;

/**
 * 
 * @author John Hutchinson
 * 
 * This class creates a Player
 * This class extends Entity.
 * This class keeps track of player data, such as remainingAttacks allowed by the player, position, player name, player alive or dead state, and the state of the surrounding world.
 * The player is able to track enemies in the world, allowing for collision detection and attacks.
 * In the next release, the player will be capable of jumping.
 * 
 *
 */






public class Player extends Entity {
private String playerName; //Optional, will be implemented later
private int remainingAttacks; //How many more times a player is allowed to attack an enemy
private boolean isAlive; //Whether or not there is a game over








public Player(World newWorld,int allowedAttacks) {
	
	super(newWorld);
	
	
	if(remainingAttacks>0) 
		remainingAttacks = allowedAttacks;
	
	isAlive = true;
}

public Player(String newName, World newWorld,int allowedAttacks) {
	
	this(newWorld,allowedAttacks);
	if(newName!=null)
		playerName = newName;
	
}




public void stop() {
	getPhysics().fullStop();
	updateGravity();
}


/**
 * Setter for moving player right. 
 * @param translate - The amount you want to move/translate the player by. 
 */
public void moveRight(int translate) {
	setXCoord(getXCoord()+translate);
}
/**
 * Setter for moving player left. 
 * @param translate - The amount you want to move/translate the player by. 
 */
public void moveLeft(int translate) {
	setXCoord(getXCoord()-translate);
	
}
/**
 * Setter for moving player up. 
 * @param translate - The amount you want to move/translate the player by. 
 */
public void moveUp(int translate) {
	setYCoord(getYCoord()-translate);
	
}
/**
 * Setter for moving player down. 
 * @param translate - The amount you want to move/translate the player by. 
 */
public void moveDown(int translate) {
	setYCoord(getYCoord()+translate);
	
}
/**
 * Checks if player is colliding with other objects (e.g. enemies).
 * @return boolean true if colliding, false otherwise. 
 */
public boolean isCollidingWithEnemy() {

	if(whoIsThere(getXCoord(),getYCoord())!=null) {
		return true;
	}
	
	return false;
}



/**
 * Checks if player is alive.
 * @return boolean true if alive, false otherwise.
 */
public boolean isAlive() {
	if(isAlive&&!isCollidingWithEnemy()) {
		return true;
	}
	return  false;
}


/**
 * Attacks the enemies in coordinates/"squares" surrounding the
 * player. Attacking enemies means removing/killing enemies from
 * the list of enemies (i.e. they die with a single attack). 
 */
public void attack() {
	
	
	
	Enemy left = whoIsThere(getXCoord()-1,getYCoord());
	if(left!=null) {
		getWorld().removeFromListOfEnemies(left);
	}
	
	Enemy right = whoIsThere(getXCoord()+1,getYCoord());
	if(right!=null) {
		getWorld().removeFromListOfEnemies(right);
	}
	
	Enemy up = whoIsThere(getXCoord(),getYCoord()-1);
	if(up!=null) {
		getWorld().removeFromListOfEnemies(up);
	}
	
	Enemy down = whoIsThere(getXCoord(),getYCoord()+1);
	if(down!=null) {
		getWorld().removeFromListOfEnemies(down);
	}
	
	


}




/**
 * Checks if enemy is at the given X and Y coordinates.
 * @param checkX - The X coordinate to check. 
 * @param checkY - The Y coordinate to check.
 * @return The enemy, if present.
 */
private Enemy whoIsThere(int checkX,int checkY) {
	for(Enemy toTest:getWorld().getListOfEnemies()) {
		
		if(toTest.getXCoord()==checkX&&toTest.getYCoord()==checkY) {
			return toTest;
			
		}
	}
	
	return null;
			
}




private void updateGravity() {
	if(getWorld().isAPlatform(getXCoord(), getYCoord())) {
		getPhysics().fullStop();
		getXCoord();
	}
}


public void update() {
	updateGravity();
	if (this.isCollidingWithEnemy()) {
		isAlive = false;
	}
}


/**
 * Asks user/player for instructions on what to do with character.
 * Opens an input space that allows the user to instruct the program
 * to move the player left/right or attack. 
 */
public void askUserInstruction() {

	Scanner keyboard = new Scanner(System.in);

	System.out.println(
			"Enter L to move left. \n" + "Enter R to move right. \n" + "Enter A to attack surrounding squares. ");

	String userInput = keyboard.nextLine();

	switch (userInput) {
	case "L":
		this.moveLeft(1);
		break;
	case "R":
		this.moveRight(1);
		break;
	case "A":
		this.attack();
		break;
	default:
		System.out.print("Input not valid. Please try again.");
		break;
	}

}

}
