
public class Bullet extends PhysicsEntity{
	
	private int range = 100;
	private Enemy target;
	
	public Bullet(int plaverX, int playerY, int width,int height, World world) {
		super(plaverX,playerY,width,height,world);
	}
	
	public void fire() {
		setxCoord(getWorld().getPlayer().getxCoord());
		setyCoord(getWorld().getPlayer().getyCoord());
		
		
		//Find enemy to target
		
		
		
		
		for(Enemy e: getWorld().getListOfEnemies()) {
			if(getDistanceAway(getxCoord(),getyCoord(),e.getxCoord(),e.getyCoord())<=range) {
				target = e;
				return;
			}
		}
		
		
		
		
	}
	
	public void update() {
		//Target is left
		if(getxCoord()-target.getxCoord()>0) {
			setxVelocity(-10.0);
		}else {
			setxVelocity(10.0);
		}
		
		if(getyCoord()-target.getyCoord()>0) {
			setyVelocity(-10.0);
		}else {
			setyVelocity(10.0);
		}
		
		super.update();
		
		
		
		
	}
	
	
	
	
	private int getDistanceAway(int startX, int startY, int endX, int endY) {
		//Using pythagoreans theorem
		
		int x = startX - endX;
		
		if(x<0) {
			x*=-1;
		}
		int y = startY - endY;
		
		if(y<0) {
			y*=-1;
		}
		
		
		
		
		return (int)Math.round(Math.sqrt(x*x+y*y));
	}
	
	
	
	
}
