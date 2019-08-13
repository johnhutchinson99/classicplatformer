package entity.moveable;
import java.util.Random;

import backend.World;

public class DirtCube extends EnemyGUI {  //This trap is move up and move down(underground)
	
	private Random rand = new Random();
	private int timeAbovePlatform = rand.nextInt(100) + 50;
	private int timeBelowPlatform = rand.nextInt(100) + 50;
	private boolean isUp;
	private int upMax;
	private int downMax;
	private boolean isStop = false;
	private int count1 = 0;
	private int count2 = 0;
	//Thread t = new Thread();

	@Override
	public void update() {
		/*
		if (count1 == 80) {
			isStop = false;
		}else {
			count1 ++;
		}
		*/
		
		
		
		if (this.getyCoord() == this.upMax) {
			isUp = false;
			isStop = true;
			count1++;
			if (count1 == timeAbovePlatform) {
				timeAbovePlatform = rand.nextInt(100) + 50;
				isStop = false;
				this.count1 = 0;
			}else {
				supersetyCoord(getyCoord());
			}
		}
		if (this.getyCoord() == this.downMax) {
			isUp = true;
			isStop = true;
			count2 ++;
			if (count2 == timeBelowPlatform) {
				timeBelowPlatform = rand.nextInt(100) + 50;
				isStop = false;
				this.count2 = 0;
			}else {
				supersetyCoord(getyCoord());
			}
		}
		
		if (isUp == true && isStop == false) {
			supersetyCoord(getyCoord()-5);
		}
		if (isUp == false && isStop == false) {
			supersetyCoord(getyCoord()+5);
		}
		
	}
		
		public DirtCube(World newWorld,int enemyXCoord,int enemyYCoord, int width, int height, boolean isUp,int upMax, int downMax) {
			
			super(enemyXCoord,enemyYCoord, width, height, newWorld, enemyXCoord, enemyXCoord);
//			super(newWorld, enemyXCoord,enemyYCoord);
			this.isUp = isUp;
			this.upMax = upMax;
			this.downMax = downMax;
		}
		
		public int getUpMax() {
			return this.upMax;
		}
		
		public int getDownMax() {
			return this.downMax;
		}
	
//	public TrapType1(TrapType1 toCopy){
//		super(toCopy.getWorld(), toCopy.getxCoord(), toCopy.getyCoord());
//		this.isUp = toCopy.isUp;
//		this.upMax = toCopy.upMax;
//	}
		
}
