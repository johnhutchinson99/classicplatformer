//import java.util.Scanner;
/**
 * This class stores and calculates the position,velocity and acceleration of an
 * Object
 * 
 *
 */
public abstract class PhysicsEntity extends WorldObject {

	public static final double GRAVITY = 100;
	public static final double UNDERWATERGRAVITY = 20;

	private double xVelocity = 0;
	private double yVelocity = 0;

	private double xAcceleration = 0;
	private double yAcceleration = 5;

	private long lastTimeX = System.currentTimeMillis();
	private long lastTimeY = System.currentTimeMillis();


	public PhysicsEntity(int xCoord, int yCoord, int width, int height) {
		super(xCoord, yCoord, width, height);
	}

	public PhysicsEntity(int xCoord, int yCoord, int width, int height, World world) {
		super(xCoord, yCoord, width, height, world);
	}

	public PhysicsEntity(PhysicsEntity p) {
		super(p);
	}
//
//	/**
//	 * 
//	 * @param x Beginning X Position
//	 * @param y Beginning Y Position
//	 */
//
////Physics equations were derived from Physics 223 with Anna Harlick
//	/**
//	 * Constructor for Physics Class
//	 * 
//	 * @param x       Beginning X Position
//	 * @param y       Beginning Y Position
//	 * @param maximum value of X
//	 * @param maximum value of Y
//	 */
//	public PhysicsEntity(int x, int y, int maxX, int maxY) {
//
//		// Physics calculations are based on real time. Meaning that calculations are
//		// done based on how much actual time has passed since last calculation
//		lastTime = System.currentTimeMillis();
//		if (x >= 0)
//			xPosition = x;
//		if (y >= 0)
//			yPosition = y;
//
//		maxXPosition = maxX;
//		maxYPosition = maxY;
//	}
//
//	/**
//	 * Constructor for Physics class Only maximum is the maximun int size
//	 */
//	public PhysicsEntity(int x, int y) {
//		this(x, y, Integer.MAX_VALUE, Integer.MAX_VALUE);
//	}
//
//	/**
//	 * Copies a physics object
//	 * 
//	 * @param Input Physics object
//	 */
//	public PhysicsEntity(PhysicsEntity p) {
//		xPosition = p.getXPosition();
//		yPosition = p.getYPosition();
//
//		xVelocity = p.getXVelocity();
//		yVelocity = p.getYVelocity();
//
//		xAcceleration = p.getXAcceleration();
//		yAcceleration = p.getYAcceleration();
//	}

	/**
	 * Recalculates instance variables. Intended to be called only within the Class
	 * Called whenever the instance variables are changing
	 */
	public void update() {
		double secondsPassedX = (System.currentTimeMillis() - lastTimeX) / 1000.0;

		int xterminal;
		int possibleX;
		if (xVelocity > 0) {
			xterminal = 3;
			possibleX = (int) Math.round((getxCoord() + (int) (Math.max((xVelocity * 0.5), xterminal))));
		} else if (xVelocity < 0) {
			xterminal = -3;
			possibleX = (int) Math.round((getxCoord() + (int) (Math.max((xVelocity * 0.5), xterminal))));
		} else {
			xterminal = 0;
			possibleX = (int) (getxCoord() + 0);
		}

		setxCoord(possibleX);
////
//		int possibleY = (int) Math.round(
//				yPosition + (yVelocity * secondsPassed) + ((0.5) * (yAcceleration) * secondsPassed * secondsPassed));
//		if (possibleY >= 0 && possibleY <= maxYPosition - 50) {
//			yPosition = possibleY;
//		}
//////		
		// Calculating new velocity using V(final) = V(initial) + acceleration(time)

		if (xVelocity > 0) {
			xVelocity = xVelocity - xAcceleration * secondsPassedX;
		} else if (xVelocity < 0) {
			xVelocity = xVelocity + xAcceleration * secondsPassedX;
		}
		if (Math.abs(xVelocity) < 0.9) {
			xVelocity = 0;
		}

		if (xVelocity > 5) {
			xVelocity = 5;
		} else if (xVelocity < -5) {
			xVelocity = -5;
		}

		double secondsPassedY = (System.currentTimeMillis() - lastTimeY) / 1000.0;

		if (getWorld().collidePlatform(this, getyCoord() + 1, getxCoord())) {
			yVelocity = 0;
			secondsPassedY = 0;
		}
		
		int possibleY;

		if (!getWorld().collidePlatform(this, getyCoord(), getxCoord())) {

			possibleY = (int) (getyCoord() + (yVelocity * secondsPassedY)
					+ yAcceleration * secondsPassedY * secondsPassedY);

			if (getWorld().collidePlatform(this, possibleY, getxCoord()) || possibleY >= getWorld().getWorldHeight()) {
				possibleY = getyCoord() + 1;
			}
		} else {
			possibleY = getyCoord() + 1;
		}

		for (int i = getyCoord(); i <= possibleY; i++) {
			if (getWorld().collidePlatform(this, i, getxCoord())) {
				possibleY = getyCoord() + 1;
			}
		}
		
		setyCoord(possibleY);

		if (Math.abs(yVelocity) > 0) {
			yVelocity = yVelocity + yAcceleration * secondsPassedY;
		}
		

	}

	public void jump() {
		setyVelocity(-25);
	}

	/**
	 * 
	 * @return Current X Velocity(speed)
	 */
	public double getxVelocity() {
		return xVelocity;
	}

	/**
	 * 
	 * @return Current Y Velocity(speed)
	 */
	public double getyVelocity() {
		return yVelocity;
	}

	/**
	 * 
	 * @param Desired X Velocity(speed)
	 */
	public void setxVelocity(double speed) {
		if (Math.abs(xVelocity) <= 5) { // Where 5 is the walkspeed => TODO: get rid of magic numbers
			xVelocity = speed;
			
				lastTimeX = System.currentTimeMillis();
		} 
	}

	/**
	 * 
	 * @param Desired Y Velocity(speed)
	 */
	public void setyVelocity(double speed) {
		if (getWorld().collidePlatform(this, getyCoord() + 1, getxCoord())) {
			yVelocity = speed;
			setyCoord(getyCoord() - 3);
			
				lastTimeY = System.currentTimeMillis();
		} 
	}

	/**
	 * 
	 * @param Desired X acceleration
	 */
	public void setXAcceleration(double x) {
		xAcceleration = x;
	}

	/**
	 * 
	 * @param Desired Y acceleration
	 */
	public void setYAcceleration(double y) {
		yAcceleration = y;
	}
	
	/**
	 * 
	 * @return Current X Acceleration
	 */
	public double getXAcceleration() {
		return xAcceleration;
	}
	/**
	 * 
	 * @return Current Y Acceleration
	 */
	public double getYAcceleration() {
		return yAcceleration;
	}
	
	

}
