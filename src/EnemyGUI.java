import java.util.ArrayList;

public abstract class EnemyGUI extends Enemy{//implements Runnable {
	
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Enemy> removedEnemyList = new ArrayList<>();
	private boolean isAlive; 
		
	
	public EnemyGUI(World newWorld){
		super(newWorld);
		move();	
	}
	
	
	//public EnemyGUI() {}

	
	//public EnemyGUI(EnemyGUI toCopy) {}
	
	
	public void move() {}
	
	
	public void enemyDead() {
		for (int i = 0;i < enemyList.size();i++) {
			
				removedEnemyList.add(enemyList.remove(i));
			
		}
	}
	
		
	
}
