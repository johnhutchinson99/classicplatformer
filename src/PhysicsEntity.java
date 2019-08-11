//import java.util.Scanner;
/**
 * This class stores and calculates the position,velocity and acceleration of an
 * Object
 * 
 *
 */
public abstract class PhysicsEntity extends WorldObject {

	public static final double GRAVITY = 300;
	public static final double UNDERWATERGRAVITY = 20;

	private double xVelocity = 0;
	private double yVelocity = 0;

	private double xAcceleration = 0;
	private double yAcceleration = 0;

	private long lastTime = System.currentTimeMillis();

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
		double secondsPassed = (System.currentTimeMillis() - lastTime) / 1000.0;

		// First, calculating final position
		// using x(final) = x(initial) +velocity(initial)*time +
		// (1/2)(acceleration)(time)^2
		// Calculating X and Y separately



			int possibleX = (int) Math.round(getxCoord() + (xVelocity * secondsPassed)
					+ ((0.5) * (xAcceleration) * secondsPassed * secondsPassed));
					setxCoord(possibleX);

			int possibleY = (int) Math.round(getyCoord() + (yVelocity * secondsPassed)
					+ ((0.5) * (yAcceleration) * secondsPassed * secondsPassed));
			
			
			
			if(!getWorld().collidePlatform(this,getxCoord(),possibleY))
					setyCoord(possibleY);

			
		
		
		
			// Calculating new velocity using V(final) = V(initial) + acceleration(time)

			xVelocity = (int) Math.round(xVelocity + xAcceleration * secondsPassed);
			yVelocity = (int) Math.round(yVelocity + yAcceleration * secondsPassed);

			lastTime = System.currentTimeMillis();
		

	}

	public void jump(int jumpPower) {
		if (getWorld().collidePlatform(this, getxCoord(), getyCoord() + 3)) {
			setyVelocity(-jumpPower);
		}

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

		xVelocity = speed;

	}

	/**
	 * 
	 * @param Desired Y Velocity(speed)
	 */
	public void setyVelocity(double speed) {
		yVelocity = speed;
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
	
	
	public void moveOffScreen() {
		setxCoord(getWorld().getWorldWidth()+1000);
		setyCoord(getWorld().getWorldHeight()+1000);
	}
	
	
	

}
