package entity.moveable;
import backend.World;

public class PoundCake extends EnemyGUI{  //enemy type1:move around in an area, go back when touch Obstructs

	private boolean canLeft = true;		
	private boolean canRight = true;
	private boolean onLand = false;
	private boolean isLeft = true;
	private boolean isALive;
	//Thread t = new Thread();
	
	@Override
	public void update() {
		if (this.getxCoord() >= getRightMax()) {
			isLeft = true;
		}
		if (this.getxCoord() <= getLeftMax()) {
			isLeft = false;
		}
		
		if (isLeft == true) {
			supersetxCoord(getxCoord()-1);
		}
		if (isLeft == false) {
			supersetxCoord(getxCoord()+1);
		}
		super.update();
	}
		
		public PoundCake(World newWorld,int enemyXCoord,int enemyYCoord,boolean isLeft,int leftMax,int rightMax, int width, int height) {
			super(enemyXCoord,enemyYCoord, width, height, newWorld, leftMax, rightMax);
			this.isLeft = isLeft;
			isALive = true;
		}


}
