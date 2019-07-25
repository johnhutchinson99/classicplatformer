
public class Physics {
private int xPosition;
private int yPosition;
private int xVelocity;
private int yVelocity;
private int xAcceleration;
private int yAcceleration;

private long startTime;

/**
 * 
 * @param x Beginning X Position
 * @param y Beginning Y Position
 */

public Physics(int x, int y) {
	
}



private void update() {
	//Recalculates instance variables based on current time and instance variables past state
}


public int getXPosition() { 
	update();
	return xPosition;
}
public int getYPosition() { 
	update();
	return yPosition;
}


public void setXPosition(int x) {
	xPosition = x;
	update();
}

public void setYPosition(int y) {
	yPosition = y;
	update();
}






public int getXVelocity() {
	update();
	return xVelocity;
}
public int getYVelocity() {
	update();
	return yVelocity;
}
public void setXVelocity(int y) {
	yVelocity = y;
	update();
}
public void setYVelocity(int y) {
	yVelocity = y;
	update();
}




public int getXAcceleration() {
	update();
	return xAcceleration;
}

public int getYAcceleration() {
	update();
	return yAcceleration;
}
public void setXAcceleration(int x) {
	xAcceleration = x;
	update();
}

public void setYAcceleration(int y) {
	yAcceleration = y;
	update();
}

	
	
	
	
	
}
