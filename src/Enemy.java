
/**
 * 
 * 
 * 
 * @author Gao XingYu(Ryan)
 * 
 * This class creates enemies 
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Moveable {

	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private boolean isAlive;

	public Enemy(World newWorld, int width, int height) {
		super(newWorld, width, height);
		setPhysics(new Physics(0,0));
		move();
		applyGravity();

	}

	// public void addEnemy(Enemy enemy) {
	// if (isAlive == true) {
	// enemyList.add(new Enemy(enemy));
	// }
	// }

	/**
	 * Moves the enemy
	 */
	public void move() {
		Random r = new Random();
		setXCoord(r.nextInt(getWorld().getWorldMaxXCoord()));
		setYCoord(r.nextInt(getWorld().getWorldMaxYCoord()));
	}
	
	/**
	 * Temporary move method
	 */
	public void move2() {
		Random r = new Random();
		setXCoord(100);
		setYCoord(1);
	}

	/**
	 * Check if enemies in the list of enemies are alive and removes dead enemies.
	 */
	public void checkEnemy() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).isAlive = false) {
				enemyList.remove(i);
			}
		}
	}
	/*
	 * public void reset() { this.x = super.x; this.y = super.y; }
	 */

}
