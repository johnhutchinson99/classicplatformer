//import java.util.Scanner;
/**
 * This class stores and calculates the position,velocity and acceleration of an Object
 * 
 *
 */
public class Physics {
	
public static final double GRAVITY = 10;	
	
private int maxXPosition;
private int maxYPosition;

private int xPosition; 
private int yPosition;



private double xVelocity;
private double yVelocity;

private double xAcceleration;
private double yAcceleration;

private long lastTime;

/**
 * 
 * @param x Beginning X Position
 * @param y Beginning Y Position
 */

//Physics equations were derived from Physics 223 with Anna Harlick
/**
 * Constructor for Physics Class
 * @param x Beginning X Position
 * @param y Beginning Y Position
 * @param maximum value of X
 * @param maximum value of Y
 */
public Physics(int x, int y, int maxX, int maxY) {
	
	//Physics calculations are based on real time. Meaning that calculations are done based on how much actual time has passed since last calculation
	lastTime = System.currentTimeMillis();
	if(x>=0)
	xPosition = x;
	if(y>=0)
	yPosition = y;
	
	
	
	maxXPosition = maxX;
	maxYPosition = maxY;
}



/**
 * Constructor for Physics class
 * Initial values (position,velocity,acceleration) are zero
 */
public Physics(int maxX, int maxY) {
	this(0,0,maxX,maxY);
}
/**
 * Copies a physics object
 * @param Input Physics object
 */
public Physics(Physics p){
	xPosition = p.getXPosition();
	yPosition = p.getYPosition();
	
	xVelocity = p.getXVelocity();
	yVelocity = p.getYVelocity();
	
	xAcceleration = p.getXAcceleration();
	yAcceleration = p.getYAcceleration();
}


/**
 * Recalculates instance variables. 
 * Intended to be called only within the Class
 * Called whenever the instance variables are changing
 */
private void update() {
	double secondsPassed = (System.currentTimeMillis() - lastTime)/1000.0;
	

	
	if(secondsPassed>0.04) {
	// System.out.println(secondsPassed);
	// 1000 milliseconds is one second
	
	//First, calculating final position
	// using x(final) = x(initial) +velocity(initial)*time + (1/2)(acceleration)(time)^2
	//Calculating X and Y separately 
	int possibleX = (int) Math.round(xPosition + (xVelocity*secondsPassed)+ ((0.5)*(xAcceleration)*secondsPassed*secondsPassed));
	if(possibleX>=0&&possibleX<=maxXPosition-50) {
		xPosition = possibleX;
		}
	
	
	
	
	int possibleY = (int) Math.round(yPosition + (yVelocity*secondsPassed)+ ((0.5)*(yAcceleration)*secondsPassed*secondsPassed));
	if(possibleY>=0&&possibleY<=maxYPosition-50) {
		yPosition = possibleY;
		}
	//Calculating new velocity using V(final) = V(initial) + acceleration(time)
	
	
	xVelocity = (int)Math.round(xVelocity + xAcceleration*secondsPassed);
	yVelocity = (int)Math.round(yVelocity + yAcceleration*secondsPassed);
	

	lastTime = System.currentTimeMillis();
	}
}





/**
 * 
 * @return Max X position of the physics object
 */
public int getMaxXPosition() { 
	update();
	return maxXPosition;
}


/**
 * 
 * @return  Max Y position of physics object
 */
public int getMaxYPosition() { 
	update();
	return maxYPosition;
}

/**
 * 
 * @param Desired Max X Position
 */
public void setMaxXPosition(int x) {
	if(x>=0) {
	maxXPosition = x;
	}
	update();
}




/**
 * 
 * @param Desired Max Y Position
 */
public void setMaxYPosition(int y) {
	if(y>=0) {
	maxYPosition = y;
	}
	update();
}





/**
 * 
 * @return X position of the physics object
 */
public int getXPosition() { 
	update();
	return xPosition;
}


/**
 * 
 * @return Y position of physics object
 */
public int getYPosition() { 
	update();
	return yPosition;
}

/**
 * 
 * @param Desired X Position
 */
public void setXPosition(int x) {
	if(x>=0&&x<=maxXPosition) {
	xPosition = x;
	}
	update();
}




/**
 * 
 * @param Desired Y Position
 */
public void setYPosition(int y) {
	if(y>=0&&y<=maxYPosition) {
	yPosition = y;
	}
	update();
}





/**
 * 
 * @return Current X Velocity(speed)
 */
public double getXVelocity() {
	update();
	return xVelocity;
}
/**
 * 
 * @return Current Y Velocity(speed)
 */
public double getYVelocity() {
	update();
	return yVelocity;
}
/**
 * 
 * @param Desired X Velocity(speed)
 */
public void setXVelocity(double x) {
	update();
	xVelocity = x;
	update();
}
/**
 * 
 * @param Desired Y Velocity(speed)
 */
public void setYVelocity(double y) {
	update();
	yVelocity = y;
	update();
}



/**
 * 
 * @return Current X acceleration
 */
public double getXAcceleration() {
	update();
	return xAcceleration;
}
/**
 * 
 * @return Current Y acceleration
 */
public double getYAcceleration() {
	update();
	return yAcceleration;
}
/**
 * 
 * @param Desired X acceleration
 */
public void setXAcceleration(double x) {
	update();
	xAcceleration = x;
	update();
}
/**
 * 
 * @param Desired Y acceleration
 */
public void setYAcceleration(double y) {
	update();
	yAcceleration = y;
	update();
}

	
	
/**
 * 
 * @return Whether or not the Object is traveling left.
 */
public boolean isGoingLeft() {
	if(xVelocity<0) {
		return true;
	}
	return false;
}
	
/**
 * 
 * @return Whether or not the Object is traveling right.
 * 
 */
public boolean isGoingRight() {
	if(xVelocity>0) {
		return true;
	}
	return false;
}
/**
 * 
 * @return Whether or not the Object is traveling upwards.
 */
public boolean isGoingUp() {
	if(yVelocity<0) {
		return true;
	}
	return false;
}

/**
 * 
 * @return Whether or not the Object is traveling downwards.
 */
public boolean isGoingDown() {
	if(yVelocity>0) {
		return true;
	}
	return false;
}

/**
 * Kills all momentum and accleration
 */

public void fullStop() {
	xAcceleration = 0;
	yAcceleration = 0;
	xVelocity = 0;
	yVelocity = 0;
}





/*
public static void main(String[] args) {
		Physics test = new Physics(5,5);
		test.setXAcceleration(3);
		test.setXVelocity(2);
		
		
		

		
		Scanner hi = new Scanner(System.in);
		
		hi.nextLine();
		
		
		System.out.println(test.getXPosition());
		
		
		
		
		
		
}
*/


	
}
