/**
 * This class creates enemies for the text-based version of the game.
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends PhysicsEntity {

	// TODO why is their another enemyList here?? Shouldn't it use the one in world??
	
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private boolean isAlive;

	public Enemy(World newWorld, int width, int height) {
		super(4, 0, width, height, newWorld);
	}
		

	public Enemy(World newWorld,int x, int y,int width, int height) {
		super(x, y, width, height,newWorld);
	}
	/**
	 * Moves the enemy
	 */
	public void move() {
		Random r = new Random();
		setxCoord(r.nextInt(getWorld().getWorldWidth()));

		if (getxCoord() == getWorld().getPlayer().getxCoord()) {
			move();
		}

	}

	/**
	 * The doCollision method is what the enemy does (to the player) when it
	 * collides with an object.
	 * 
	 * @param WorldObject o- the object the enemy will act on
	 * @return boolean true - returns true in order to signify the method has
	 *         finished running
	 */
	public boolean doCollision(WorldObject o) {
		if( o instanceof Player) {
			Player p = (Player)o;
			p.kill();
		}
		

		return true;
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
