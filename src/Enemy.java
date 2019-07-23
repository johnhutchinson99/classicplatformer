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

public class Enemy extends Entity{
	
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private boolean isAlive; 
	

	public Enemy(World newWorld){
		super(newWorld);
		move();
		
	}
	
	
	
	
	
	//public void addEnemy(Enemy enemy) {
	//	if (isAlive == true) {
	//		enemyList.add(new Enemy(enemy));
	//	}
	//}
	
	
			
	public void move() {
		Random r = new Random();
		setXCoord(r.nextInt(getWorld().getWorldMaxXCoord()));
		setYCoord(r.nextInt(getWorld().getWorldMaxYCoord()));
		
		
		
		
		
		
	}
	
	public void checkEnemy() {
		for (int i = 0;i < enemyList.size();i++) {
			if (enemyList.get(i).isAlive = false) {
				enemyList.remove(i);
			}
		}
	}
	/*
	public void reset() {
		this.x = super.x;
		this.y = super.y;
	}
	*/

}
