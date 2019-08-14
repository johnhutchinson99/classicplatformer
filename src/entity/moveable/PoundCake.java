package entity.moveable;

import backend.World;

/**
 * Creates the pound cake enemy which moves back and forth within given x
 * coordinates.
 */

public class PoundCake extends EnemyGUI {

	private boolean canLeft = true;
	private boolean canRight = true;
	private boolean onLand = false;
	private boolean isLeft = true;
	private boolean isALive;
	
	
	
	
	/**
	 * 
	 * @param newWorld World that DirtCube is to be placed in
	 * @param enemyXCoord Initial x coordinate
	 * @param enemyYCoord Initial y coordinate
	 * @param isLeft is at leftmost position
	 * @param leftMax Furthest left poundcake can go
	 * @param rightMax Furthest right poundcake can go
	 * @param width Width of poundcake
	 * @param height height of poundcake
	 */
	public PoundCake(World newWorld, int enemyXCoord, int enemyYCoord, boolean isLeft, int leftMax, int rightMax,
			int width, int height) {
		super(enemyXCoord, enemyYCoord, width, height, newWorld, leftMax, rightMax);
		this.isLeft = isLeft;
		isALive = true;
	}

	/**
	 * Update method which moves the enemy / makes the enemy go horizontally back
	 * and forth based on the state of the enemy.
	 */
	@Override
	public void update() {
		if (this.getxCoord() >= getRightMax()) {
			isLeft = true;
		}
		if (this.getxCoord() <= getLeftMax()) {
			isLeft = false;
		}

		if (isLeft == true) {
			supersetxCoord(getxCoord() - 1);
		}
		if (isLeft == false) {
			supersetxCoord(getxCoord() + 1);
		}
		super.update();
	}

}
