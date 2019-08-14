package backend;

//import java.util.Scanner;
/**
 * This class stores and calculates the position,velocity and acceleration of an
 * Object
 * 
 *
 */
public abstract class PhysicsEntity extends WorldObject {

	public static final double GRAVITY = 900;
	public static final double UNDERWATERGRAVITY = 20;

	private double xVelocity = 0;
	private double yVelocity = 0;

	private double xAcceleration = 0;
	private double yAcceleration = GRAVITY;

	private long lastTime = System.currentTimeMillis();

	/**
	 * Constructor for physics entity. 
	 * 
	 * @param xCoord - x coordinate to set entity to. 
	 * @param yCoord - y coordinate to set entity to. 
	 * @param width - width to set entity to. 
	 * @param height - height to set entity to. 
	 */
	public PhysicsEntity(int xCoord, int yCoord, int width, int height) {
		super(xCoord, yCoord, width, height);
	}

	/**
	 * Constructor for physics entity. 
	 * 
	 * @param xCoord - x coordinate to set entity to. 
	 * @param yCoord - y coordinate to set entity to. 
	 * @param width - width to set entity to. 
	 * @param height - height to set entity to. 
	 * @param world - the world to set the entity to. 
	 */
	public PhysicsEntity(int xCoord, int yCoord, int width, int height, World world) {
		super(xCoord, yCoord, width, height, world);
	}

	/**
	 * Copy constructor for physics entity. 
	 * 
	 * @param toCopy - the physics entity to copy from
	 */
	public PhysicsEntity(PhysicsEntity toCopy) {
		super(toCopy);
	}

	/**
	 * The update method recalculates the values of a physic entities' position (x
	 * and y) and velocity (x and y) based on the player's acceleration and physics
	 * formulas. (Reference: Anna Harlick's Physics 233 Winter Course)
	 */
	public void update() {
		double secondsPassed = (System.currentTimeMillis() - lastTime) / 1000.0;

		// First, calculating final position
		// using x(final) = x(initial) +velocity(initial)*time +
		// (1/2)(acceleration)(time)^2
		// Calculating X and Y separately
		int possibleX = (int) Math.round(
				getxCoord() + (xVelocity * secondsPassed) + ((0.5) * (xAcceleration) * secondsPassed * secondsPassed));
		setxCoord(possibleX);

		int possibleY = (int) Math.round(
				getyCoord() + (yVelocity * secondsPassed) + ((0.5) * (yAcceleration) * secondsPassed * secondsPassed));

		if (!getWorld().collidePlatform(this, getxCoord(), possibleY))
			setyCoord(possibleY);
		else {
			secondsPassed = 0;
			yVelocity = 0;
		}

		// Calculating new velocity using V(final) = V(initial) + acceleration(time)
		xVelocity = (int) Math.round(xVelocity + xAcceleration * secondsPassed);
		yVelocity = (int) Math.round(yVelocity + yAcceleration * secondsPassed);

		lastTime = System.currentTimeMillis();

	}

	/**
	 * A jump method that when called on a physics entity, makes the player jump
	 * with a given "jump power" (the "upward" velocity that the entity will jump
	 * to).
	 * 
	 * @param jumpPower - the "upward" velocity that the entity will jump to
	 */
	public void jump(int jumpPower) {
		if (getWorld().collidePlatform(this, getxCoord(), getyCoord() + 1)) {
			setyVelocity(-jumpPower);
			setyCoord(getyCoord() + 1);
		}

	}

	/**
	 * Getter for x velocity. 
	 * @return Current X Velocity(speed)
	 */
	public double getxVelocity() {
		return xVelocity;
	}

	/**
	 * Getter for y velocity. 
	 * @return Current Y Velocity(speed)
	 */
	public double getyVelocity() {
		return yVelocity;
	}

	/**
	 * Setter for x velocity. 
	 * @param Desired X Velocity(speed)
	 */
	public void setxVelocity(double speed) {

		xVelocity = speed;

	}

	/**
	 * Setter for y velocity. 
	 * @param Desired Y Velocity(speed)
	 */
	public void setyVelocity(double speed) {
		yVelocity = speed;
	}

	/**
	 * Setter for x acceleration. 
	 * @param Desired X acceleration
	 */
	public void setXAcceleration(double x) {
		xAcceleration = x;
	}

	/**
	 * Setter for y acceleration. 
	 * @param Desired Y acceleration
	 */
	public void setYAcceleration(double y) {
		yAcceleration = y;
	}

	/**
	 * Getter for x acceleration. 
	 * @return Current X Acceleration
	 */
	public double getXAcceleration() {
		return xAcceleration;
	}

	/**
	 * Getter for y acceleration. 
	 * @return Current Y Acceleration
	 */
	public double getYAcceleration() {
		return yAcceleration;
	}

}
