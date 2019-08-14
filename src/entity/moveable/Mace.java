package entity.moveable;

import backend.World;

/**
 * Creates the mace enemy which moves up and down within a specific y
 * coordinate.
 */

public class Mace extends EnemyGUI { 

	private boolean isUp;
	private int upMax;
	private int downMax;

	/**
	 * 
	 * @param newWorld World to be placed in
	 * @param enemyXCoord Initial X position
	 * @param enemyYCoord Initial Y position
	 * @param width Width of object
	 * @param height Height of object
	 * @param isUp Is at highest possible position
	 * @param upMax Highest position
	 * @param downMax Lowest position
	 */
	public Mace(World newWorld, int enemyXCoord, int enemyYCoord, int width, int height, boolean isUp, int upMax,
			int downMax) {

		super(enemyXCoord, enemyYCoord, width, height, newWorld, enemyXCoord, enemyXCoord);
		this.isUp = isUp;
		this.upMax = upMax;
		this.downMax = downMax;
	}

	/**
	 * Update method which moves the enemy / makes the enemy go up and down based on
	 * the state of the enemy.
	 */
	@Override
	public void update() {
		if (this.getyCoord() == this.upMax) {
			isUp = false;
		}
		if (this.getyCoord() == this.downMax) {
			isUp = true;
		}

		if (isUp == true) {
			supersetyCoord(getyCoord() - 1);
		}
		if (isUp == false) {
			supersetyCoord(getyCoord() + 1);
		}
	}

	/**
	 * Getter for upMax, the max y coordinate the enemy can go to.
	 * 
	 * @return upMax - the max y coordinate the enemy can go to.
	 */
	public int getUpMax() {
		return this.upMax;
	}

	/**
	 * Getter for downMax, the min y coordinate the enemy can go to.
	 * 
	 * @return downMax - the min y coordinate the enemy can go to.
	 */
	public int getDownMax() {
		return this.downMax;
	}

}
