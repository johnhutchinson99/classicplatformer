//import java.util.Scanner;

public class Physics {
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

//TODO cite my physics class

public Physics(int x, int y) {
	
	//Physics calculations are based on real time. Meaning that calculations are done based on how much actual time has passed since last calculation
	lastTime = System.currentTimeMillis();
	if(x>=0)
	xPosition = x;
	if(y>=0)
	yPosition = y;
}



private void update() {//Recalculates instance variables based on current time and instance variables past state
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
public void setXVelocity(int x) {
	update();
	xVelocity = x;
	update();
}
public void setYVelocity(int y) {
	update();
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
	update();
	xAcceleration = x;
	update();
}

public void setYAcceleration(int y) {
	update();
	yAcceleration = y;
	update();
}

	
	
	//TODO Java docs
	

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
