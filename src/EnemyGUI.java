import java.util.ArrayList;

public abstract class EnemyGUI extends PhysicsEntity{
	
	private int leftMax;
	private int rightMax;
	private ArrayList<EnemyGUI> enemyList = new ArrayList<>();
	private ArrayList<EnemyGUI> removedEnemyList = new ArrayList<>();
	private boolean isAlive; 
		
	
	public EnemyGUI(int xCoord, int yCoord, int width, int height, int lMax, int rMax) {
		super(xCoord,yCoord,width,height);
		leftMax = lMax;
		rightMax = rMax;
	}
	
	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world, int lMax, int rMax) {
		super(xCoord,yCoord,width,height,world);
		leftMax = lMax;
		rightMax = rMax;
	}
	
	//public EnemyGUI() {}

	
	//public EnemyGUI(EnemyGUI toCopy) {}
	
	
	public EnemyGUI(int xCoord, int yCoord, int width, int height, World world) {
		super(xCoord,yCoord,width,height,world);
	}

	public void move() {}
	
	
	public boolean doCollision(Player p) {
		p.kill();
		return true;
	}
	
	public void enemyDead() {
		for (int i = 0;i < enemyList.size();i++) {
			removedEnemyList.add(enemyList.remove(i));
			
		}
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
