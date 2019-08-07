import java.util.Random;

public class FlashMoveEnemy extends EnemyGUI{

	private boolean isALive;
	
	public FlashMoveEnemy(World newWorld,int enemyXCoord,int enemyYCoord,int width, int height,World world) {
		super(enemyXCoord,enemyYCoord,width,height,world);
		}

	public void update() {
		supersetxCoord(getPlayerXCoord()+3);
		supersetyCoord(getPlayerYCoord());
	}
	
	//enemy can pop up in front of player

}
