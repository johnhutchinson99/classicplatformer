package entity.moveable;
import backend.World;

public class FlashMoveEnemy extends EnemyGUI{

		private boolean isALive;
		private boolean isMoved = false;
		private int count = 0;
		
		public FlashMoveEnemy(World newWorld,int enemyXCoord,int enemyYCoord, int width, int height) {
			super(enemyXCoord,enemyYCoord,width,height,newWorld);
			}

		public void update() {
			count ++;
			if (isMoved == false && count == 200) {                    //set when enemy move
				supersetxCoord(getWorld().getPlayer().getxCoord()+5);  //Can change the distance with player
				supersetyCoord(getWorld().getPlayer().getyCoord());
			}
				
		}	
		//enemy can pop up in front of player. Create one by define XYCood, move to
}
