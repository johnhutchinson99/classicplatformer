package entity.moveable;


import backend.World;
import backend.WorldObject;

/**
 * This abstract class creates enemies for the GUI (has logic for moving
 * animation methods).
 *
 */

public abstract class EnemyGUI extends Enemy {

	private int leftMax;
	private int rightMax;
	private boolean isAlive = true;

	
	
	/**
	 * 
	 * @param xCoord initial x coordinate
	 * @param yCoord initial y coordinate
	 * @param width Enemy width
	 * @param height Enemy height
	 * @param world World to be placed in
	 * @param lMax furthest left enemy can go
	 * @param rMax furthest right enemy can go
	 */
	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world, int lMax, int rMax) {
		super(world, xCoord, yCoord, width, height);
		leftMax = lMax;
		rightMax = rMax;
	}

	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world) {
		super(world, xCoord, yCoord, width, height);
	}

	/**
	 * Move method for the enemy.
	 */
	public void move() {
	}

	/**
	 * The doCollision method for the enemies. Used when if collision with another
	 * world object is true and the enemy must act on the other colliding world
	 * object.
	 */
	public boolean doCollision(WorldObject o) {
		super.doCollision(o);

		if (o instanceof Bullet) {
			enemyDead();
		}

		return true;
	}

	/**
	 * Setter for making the enemy dead.
	 */
	public void enemyDead() {
		isAlive = false;
	}

	/**
	 * The isAlive method checks whether the enemy is alive (and returns true if so). 
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Getter for the left maximum x coordinate the enemy can be in. 
	 * @return leftMax  - the left max x coordinate the enemy can be in. 
	 */
	public int getLeftMax() {
		return this.leftMax;
	}

	/**
	 * Getter for the right maximum x coordinate the enemy can be in. 
	 * @return leftMax  - the right max x coordinate the enemy can be in. 
	 */
	public int getRightMax() {
		return this.rightMax;
	}

	/**
	 * Setter for the left maximum x coordinate the enemy can be in. 
	 * @return leftMax  - the left max x coordinate the enemy can be in. 
	 */
	public void setLeftMax(int leftMax) {
		this.leftMax = leftMax;
	}

	/**
	 * Setter for the right maximum x coordinate the enemy can be in. 
	 * @return leftMax  - the right max x coordinate the enemy can be in. 
	 */
	public void setRightMax(int rightMax) {
		this.leftMax = rightMax;
	}
}
