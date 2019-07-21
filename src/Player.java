




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

	if(whoIsThere(x,y)!=null) {
		return true;
	}
	
	return false;
}
public void attack() {
	//Checking left
	Enemy left = WhoIsThere(x-1,y);
	if(left!=null) {
		left.setXCoord(100); //Putting enemies off screen (will be reimplemented)
		left.setYCoord(100);
	}
	
	Enemy right = WhoIsThere(x+1,y);
	if(right!=null) {
		right.setXCoord(100); //Putting enemies off screen (will be reimplemented)
		right.setYCoord(100);
	}
	
	Enemy up = WhoIsThere(x,y-1);
	if(up!=null) {
		up.setXCoord(100); //Putting enemies off screen (will be reimplemented)
		up.setYCoord(100);
	}
	
	Enemy down = WhoIsThere(x,y+1);
	if(down!=null) {
		down.setXCoord(100); //Putting enemies off screen (will be reimplemented)
		down.setYCoord(100);
	}
	
	


}


public void jump() {
	//TODO implement for next release
}


private Enemy whoIsThere(int checkX,int checkY) {
	for(Enemy toTest:getWorld().getEnemies()) {
		if(toTest.getX()==checkX&&toTest.getY()==checkY) {
			return toTest;
		}
	}
	
	return null;
			
}


}


//TODO Prevent player from moving past domain of world