




/**
 * 
 * @author John Hutchinson
 * 
 * This class creates a Player
 * This class extends Entity.
 * This class keeps track of player data, such as remainingAttacks allowed by the player, position, player name, player alive or dead state, and the state of the surrounding world.
 * The player is able to track enemies in the world, allowing for collision detection and attacks.
 *
 * 
 *
 */






public class Player extends Entity {
private String playerName;
private int remainingAttacks;
private boolean isAlive;








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
public void attack() {
	//Checking left
	Enemy left = WhoIsThere(getXCoord()-1,getYCoord());
	if(left!=null) {
		left.moveOffScreen();
	}
	
	Enemy right = WhoIsThere(getXCoord()+1,getYCoord());
	if(right!=null) {
		right.moveOffScreen();
	}
	
	Enemy up = WhoIsThere(getXCoord(),getYCoord()-1);
	if(up!=null) {
		up.moveOffScreen();
	}
	
	Enemy down = WhoIsThere(getXCoord(),getYCoord()+1);
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


//TODO Prevent player from moving past domain of world