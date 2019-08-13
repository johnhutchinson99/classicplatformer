package entity.moveable;
import backend.World;

public class FlyingEnemy extends EnemyGUI {  //This trap is move up and move down(underground)
	
	private boolean isUp;
	private int upMax;
	private int downMax;
	private boolean isStop = false;
	private int count1 = 0;
	private int count2 = 0;
	//Thread t = new Thread();
	private boolean isLeft = true;

	@Override
	public void update() {
		if (this.getyCoord() == this.upMax) {
			isUp = false;
		}
		if (this.getyCoord() == this.downMax) {
			isUp = true;
		}
		
		if (isUp == true) {
			supersetyCoord(getyCoord()-1);
		}
		if (isUp == false) {
			supersetyCoord(getyCoord()+1);
		}
	}
		
		public FlyingEnemy(World newWorld,int enemyXCoord,int enemyYCoord, int width, int height, boolean isUp,int upMax, int downMax) {
			
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
