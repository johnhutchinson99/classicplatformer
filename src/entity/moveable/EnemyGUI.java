package entity.moveable;
import java.util.ArrayList;

import backend.World;
import backend.WorldObject;

public abstract class EnemyGUI extends Enemy{
	
	private int leftMax;
	private int rightMax;
	private boolean isAlive = true; 
		
	
	
	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world, int lMax, int rMax) {
		super(world,xCoord,yCoord,width,height);
		leftMax = lMax;
		rightMax = rMax;
	}
	
	//public EnemyGUI() {}

	
	//public EnemyGUI(EnemyGUI toCopy) {}
	
	
	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world) {
		super(world,xCoord,yCoord,width,height);
	}

	public void move() {}
	
	public boolean doCollision(WorldObject o) {
		super.doCollision(o);
		
		if(o instanceof Bullet) {
			enemyDead();
		}
		
		return true;
	}
	
	public void enemyDead() {
//		System.out.println("KILLING");
		isAlive = false;
	}
	public boolean isAlive() {
		return isAlive;
	}
	
	public int getLeftMax() {
		return this.leftMax;
	}
	
	public int getRightMax() {
		return this.rightMax;
	}
	
	public void setLeftMax(int leftMax) {
		this.leftMax = leftMax;
	}
	
	public void setRightMax(int rightMax) {
		this.leftMax = rightMax;
	}
}
