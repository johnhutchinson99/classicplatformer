

public class Player extends Entity {
private String playerName;
private int remainingAttacks;
private boolean isAlive;
private World world;



public Player(int startX, int startY, World newWorld,int allowedAttacks) {
	
	super(startX,startY);
	
	
	if(newWorld!=null)
		world=newWorld;
	
	if(remainingAttacks>0) 
		remainingAttacks = allowedAttacks;
	
	isAlive = true;
}

public Player(String newName,int startX, int startY, World newWorld,int allowedAttacks) {
	
	this(startX,startY,newWorld,allowedAttacks);
	if(newName!=null)
		playerName = null;
	
}


public void moveRight(int translate) {
	if(translate>0) {
		x+=translate;
	}
}
public void moveLeft(int translate) {
	if(translate>0) {
		x-=translate;
	}
	
}
public void moveUp(int translate) {
	if(translate>0) {
		y-=translate;
	}
	
}
public void moveDown(int translate) {
	if(translate>0) {
		y+=translate;
	}
	
}
public boolean isColliding() {

	if(whoIsThere(x,y)!=null) {
		return true;
	}
	
	return false;
}
public void attack() {
	
	
	


}


private Enemy whoIsThere(int checkX,int checkY) {
	for(Enemy toTest:world.getEnemies()) {
		if(toTest.getX()==checkX&&toTest.getY()==checkY) {
			return toTest;
		}
	}
	
	return null;
			
}


}


//TODO Prevent player from moving past domain of world