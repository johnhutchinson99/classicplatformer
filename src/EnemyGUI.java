import java.util.ArrayList;

public abstract class EnemyGUI extends Entity{//implements Runnable {
	
	private int leftMax;
	private int rightMax;
	private int enemyXCoord;
	private int enemyYCoord;
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Enemy> removedEnemyList = new ArrayList<>();
	private boolean isAlive; 
		
	
	public EnemyGUI(World newWorld,int enemyXCoord,int enemyYCoord){
		super(newWorld);
		newWorld.setStartXCoord(enemyXCoord);
		newWorld.setStartXCoord(enemyYCoord);
		}
	
	
	//public EnemyGUI() {}

	
	//public EnemyGUI(EnemyGUI toCopy) {}
	
	
	public void move() {}
	
	
	public void enemyDead() {
		for (int i = 0;i < enemyList.size();i++) {
			removedEnemyList.add(enemyList.remove(i));
			
		}
	}
	
	public int getEnemyXCoord() {
		return this.enemyXCoord;
	}
	
	public int getEnemyYCoord() {
		return this.enemyYCoord;
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
