package entity.moveable;

import backend.PhysicsEntity;
import backend.World;

/**
 * 
 * Creates a Bullet object This bullet can be fired by an enemy and will collide
 * with objects
 *
 */
public class Bullet extends PhysicsEntity {

	private int range = 200;
	private int bulletSpeed = 300;
	private int initialX;
	private int initialY;

	/**
	 * Constructs a Bullet
	 * @param startX Initial X coordinate of Bullet
	 * @param startY Initial Y coordinate of Bullet
	 * @param width Width of bullet
	 * @param height Height of bullet
	 * @param world World that Bullet is to be placed in
	 */
	public Bullet(int startX, int startY, int width, int height, World world) {
		super(startX, startY, width, height, world);
		setYAcceleration(0);// Cancels gravity
	}

	/**
	 * Fires the player weapon
	 * 
	 * @return boolean - whether or not the gun fired successfully
	 */
	public boolean fire() {

		if (getxVelocity() == 0) {// Bullet can only fire if it is not currently moving
			setxCoord(getWorld().getPlayer().getxCoord() + getWorld().getPlayer().getWidth() / 2);
			setyCoord(getWorld().getPlayer().getyCoord() + getWorld().getPlayer().getHeight() / 2);
			initialX = getWorld().getPlayer().getxCoord();
			initialY = getWorld().getPlayer().getyCoord();

			if (getWorld().getPlayer().isFacingRight()) {// Changes bullet direction based on what direction the player
															// is facing
				setxVelocity(bulletSpeed);
			} else {
				setxVelocity(-1 * bulletSpeed);
			}

			super.update();
			return true;
		}
		return false;

	}

	/**
	 * Overrides update Checks if bullet should be reset(removed from screen) Bullet
	 * will be reset if
	 */
	@Override
	public void update() {

		super.update();
		int distance = Math.abs(getxCoord() - initialX);

		if (distance > range/* Is within range */ || getWorld().collidePlatform(this, getxCoord() + 10, getyCoord())
				|| getWorld().collidePlatform(this, getxCoord() - 10, getyCoord())/* Isn't stuck in platform */
				|| getxCoord() < 20 || getxCoord() > getWorld().getWorldWidth() - 20) {
			setxVelocity(0);
			moveOffScreen();

		}

	}

}
