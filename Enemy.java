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
	
	private int ExCoord;
	private int EyCoord;
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private boolean isAlive; 
	
	public Enemy() {}
	
	public Enemy(int startX, int startY, World newWorld){
		if (startX <= world.getWorldMaxXCoord()) {
			ExCoord = startX;
		}else {
			isAlive = false;
		}
		if (startY <= world.getWorldMaxYCoord()) {
			EyCoord = startY;
		}else {
			isAlive = false;
		}
		isAlive = true;
	}
	
	public Enemy (Enemy toCopy) {
		ExCoord = toCopy.ExCoord;
		EyCoord = toCopy.EyCoord;
	}
	
	public int getExCoord() {
		return ExCoord;
	}
	
	public int setExCoord(int newX) {
		if (newX <= world.getWorldMaxXCoord()) {
			ExCoord = newX;
		}else {
			this.isAlive = false;
		}
	}
	
	public int getEyCoord() {
		return EyCoord;
	}
	
	public int setEyCoord(int newY) {
		if (newY <= world.getWorldMaxYCoord()) {
			EyCoord = newY;
		}else {
			this.isAlive = false;
		}
	}
	
	public void addEnemy(Enemy enemy) {
		if (isAlive == true) {
			enemyList.add(new Enemy(enemy));
		}
	}
	
	public ArrayList<Enemy> getEnemyList(){
		return enemyList;
	}
	
			
	public void move() {
		Random r = new Random();
		//int moveX = r.nextInt(20);
		//int moveY = r.nextInt(20);
		setExCoord(r.nextInt(20));
		setEyCoord(r.nextInt(20));
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
