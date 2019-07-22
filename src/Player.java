




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








public Player(int startX, int startY, World newWorld,int allowedAttacks) {
	
	super(startX,startY,newWorld);
	
	
	if(remainingAttacks>0) 
		remainingAttacks = allowedAttacks;
	
	isAlive = true;
}

public Player(String newName,int startX, int startY, World newWorld,int allowedAttacks) {
	
	this(startX,startY,newWorld,allowedAttacks);
	if(newName!=null)
		playerName = newName;
	
}


public void moveRight(int translate) {
	setXCoord(getXCoord()+translate);
}
public void moveLeft(int translate) {
	setXCoord(getXCoord()-translate);
	
}
public void moveUp(int translate) {
	setYCoord(getYCoord()-translate);
	
}
public void moveDown(int translate) {
	setYCoord(getYCoord()+translate);
	
}
public boolean isColliding() {

	if(whoIsThere(getXCoord(),getYCoord())!=null) {
		return true;
	}
	
	return false;
}


public boolean isAlive() {
	if(isAlive&&!isColliding()) {
		return true;
	}
	return  false;
}



public void attack() {
	//Checking left
	Enemy left = whoIsThere(getXCoord()-1,getYCoord());
	if(left!=null) {
		left.moveOffScreen();
	}
	
	Enemy right = whoIsThere(getXCoord()+1,getYCoord());
	if(right!=null) {
		right.moveOffScreen();
	}
	
	Enemy up = whoIsThere(getXCoord(),getYCoord()-1);
	if(up!=null) {
		up.moveOffScreen();
	}
	
	Enemy down = whoIsThere(getXCoord(),getYCoord()+1);
	if(down!=null) {
		down.moveOffScreen();
	}
	
	


}


public void jump() {
	//TODO implement for next release
}


private Enemy whoIsThere(int checkX,int checkY) {
	for(Enemy toTest:getWorld().getEnemies()) {
		if(toTest.getXCoord()==checkX&&toTest.getYCoord()==checkY) {
			return toTest;
		}
	}
	
	return null;
			
}


}
