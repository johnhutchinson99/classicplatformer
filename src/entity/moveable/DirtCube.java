package entity.moveable;

import java.util.Random;

import backend.World;

/**
 * Dirt cube is an enemy which moves up and down / above and below a platform at
 * a random interval, surprising the player.
 *
 */

public class DirtCube extends EnemyGUI {

	private Random rand = new Random();
	private int timeAbovePlatform = rand.nextInt(100) + 50;
	private int timeBelowPlatform = rand.nextInt(100) + 50;
	private boolean isUp;
	private int upMax;
	private int downMax;
	private boolean isStop = false;
	private int count1 = 0;
	private int count2 = 0;
	
	public DirtCube(World newWorld, int enemyXCoord, int enemyYCoord, int width, int height, boolean isUp, int upMax,
			int downMax) {

		super(enemyXCoord, enemyYCoord, width, height, newWorld, enemyXCoord, enemyXCoord);
		this.isUp = isUp;
		this.upMax = upMax;
		this.downMax = downMax;
	}

	/**
	 * Update method which moves the enemy or makes the enemy stay above/below the
	 * platform (for a random time interval) based on the enemy state.
	 */
	@Override
	public void update() {
		if (this.getyCoord() == this.upMax) {
			isUp = false;
			isStop = true;
			count1++;
			if (count1 == timeAbovePlatform) {
				timeAbovePlatform = rand.nextInt(100) + 50;
				isStop = false;
				this.count1 = 0;
			} else {
				supersetyCoord(getyCoord());
			}
		}
		if (this.getyCoord() == this.downMax) {
			isUp = true;
			isStop = true;
			count2++;
			if (count2 == timeBelowPlatform) {
				timeBelowPlatform = rand.nextInt(100) + 50;
				isStop = false;
				this.count2 = 0;
			} else {
				supersetyCoord(getyCoord());
			}
		}

		if (isUp == true && isStop == false) {
			supersetyCoord(getyCoord() - 5);
		}
		if (isUp == false && isStop == false) {
			supersetyCoord(getyCoord() + 5);
		}

	}

	/**
	 * Getter for max y (lower numbers are higher) coordinate the enemy can pop up
	 * to.
	 * 
	 * @return upMax - the max coordinate the enemy can pop up to
	 */
	public int getUpMax() {
		return this.upMax;
	}
	
	/**
	 * Getter for max y (lower numbers are higher) coordinate the enemy can sink below
	 * to.
	 * 
	 * @return downMax - the max coordinate the enemy can sink below to
	 */
	public int getDownMax() {
		return this.downMax;
	}

}
