package entity.moveable;
/**
 * This class creates enemies for the for the game.
 *
 */

import java.util.Random;

import backend.PhysicsEntity;
import backend.World;
import backend.WorldObject;

public class Enemy extends PhysicsEntity {

	
	
	/**
	 * Initial position is 0,0
	 * @param newWorld World enemy is to be placed in
	 * @param width width of enemy object
	 * @param height height of enemy object
	 */
	public Enemy(World newWorld, int width, int height) {
		super(0, 0, width, height, newWorld);
	}
		
	/**
	 * @param newWorld World enemy is to be placed in
	 * @param Initial X coordinate
	 * @param Initial Y Coordinate
	 * @param width width of enemy object
	 * @param height height of enemy object
	 */
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
	
	


	

}
