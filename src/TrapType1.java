
public class TrapType1 extends EnemyGUI {  //This trap is move up and move down(underground)
	
	private boolean isUp;
	private int upMax;
	private int downMax;
	
	//Thread t = new Thread();

	@Override
	public void move() {
		if (this.getEnemyYCoord() >= this.upMax) {
			isUp = false;
		}
		if (this.getEnemyYCoord() <= this.downMax) {
			isUp = true;
		}
		
		if (isUp == true) {
			setXCoord(getEnemyYCoord()+1);
		}
		if (isUp == false) {
			setXCoord(getEnemyYCoord()-1);
		}
	}
		
		public TrapType1(World newWorld,int enemyXCoord,int enemyYCoord,boolean isUp,int upMax) {
			super(newWorld, enemyXCoord,enemyYCoord);
			this.isUp = isUp;
			this.upMax = upMax;
		}
	
	public TrapType1(TrapType1 toCopy){
		super(toCopy.getWorld(), toCopy.getEnemyXCoord(), toCopy.getEnemyYCoord());
		this.isUp = toCopy.isUp;
		this.upMax = toCopy.upMax;
	}
}
