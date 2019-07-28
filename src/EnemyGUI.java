import java.util.ArrayList;

public abstract class Enemy {//implements Runnable {
	
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Enemy> removedEnemyList = new ArrayList<>();
	private boolean isAlive; 
		
	
	public Enemy(World newWorld){
		super(newWorld);
		move();	
	}
	
	
	public Enemy() {}

	
	public Enemy(Enemy toCopy) {}
	
	
	public void move() {}
	
	
	public void enemyDead() {
		for (int i = 0;i < enemyList.size();i++) {
			if (enemyList.get(i).isAlive = false) {
				removedEnemyList.add(enemyList.remove(i));
			}
		}
	}
	
		
	
}
