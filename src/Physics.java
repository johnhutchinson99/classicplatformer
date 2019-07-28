//import java.util.Scanner;
/**
 * This class stores and calculates the position,velocity and acceleration of an Object
 * 
 *
 */
public class Physics {
	
public static final int GRAVITY = 5;	
	
	
private int xPosition; //Units: pixels
private int yPosition;



private int xVelocity;//Units: pixels/second
private int yVelocity;

private int xAcceleration;//Units pixels/second/second
private int yAcceleration;

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
 */
public Physics(int x, int y) {
	
	//Physics calculations are based on real time. Meaning that calculations are done based on how much actual time has passed since last calculation
	lastTime = System.currentTimeMillis();
	if(x>=0)
	xPosition = x;
	if(y>=0)
	yPosition = y;
}



/**
 * Constructor for Physics class
 * Initial values (position,velocity,acceleration) are zero
 */
public Physics() {
}
/**
 * Copies a physics object
 * @param Input Physics object
 */
public Physics(Physics p){
	xPosition = p.getXPosition();
	yPosition = p.getYVelocity();
	
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
	System.out.println(secondsPassed);
	// 1000 milliseconds is one second
	
	//First, calculating final position
	// using x(final) = x(initial) +velocity(initial)*time + (1/2)(acceleration)(time)^2
	//Calculating X and Y separately 
	xPosition = (int) Math.round(xPosition + (xVelocity*secondsPassed)+ ((0.5)*(xAcceleration)*secondsPassed*secondsPassed));
	yPosition = (int) Math.round(yPosition + (yVelocity*secondsPassed)+ ((0.5)*(yAcceleration)*secondsPassed*secondsPassed));
	
	//Calculating new velocity using V(final) = V(initial) + acceleration(time)
	
	
	xVelocity = (int)Math.round(xVelocity + xAcceleration*secondsPassed);
	yVelocity = (int)Math.round(yVelocity + yAcceleration*secondsPassed);
	

	
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
	xPosition = x;
	update();
}




/**
 * 
 * @param Desired Y Position
 */
public void setYPosition(int y) {
	yPosition = y;
	update();
}





/**
 * 
 * @return Current X Velocity(speed)
 */
public int getXVelocity() {
	update();
	return xVelocity;
}
/**
 * 
 * @return Current Y Velocity(speed)
 */
public int getYVelocity() {
	update();
	return yVelocity;
}
/**
 * 
 * @param Desired X Velocity(speed)
 */
public void setXVelocity(int x) {
	update();
	xVelocity = x;
	update();
}
/**
 * 
 * @param Desired Y Velocity(speed)
 */
public void setYVelocity(int y) {
	update();
	yVelocity = y;
	update();
}



/**
 * 
 * @return Current X acceleration
 */
public int getXAcceleration() {
	update();
	return xAcceleration;
}
/**
 * 
 * @return Current Y acceleration
 */
public int getYAcceleration() {
	update();
	return yAcceleration;
}
/**
 * 
 * @param Desired X acceleration
 */
public void setXAcceleration(int x) {
	update();
	xAcceleration = x;
	update();
}
/**
 * 
 * @param Desired Y acceleration
 */
public void setYAcceleration(int y) {
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
