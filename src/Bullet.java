
public class Bullet extends PhysicsEntity{
	
	private int range = 200;
	private int bulletSpeed = 100;
	private int initialX;
	private int initialY;
	
	public Bullet(int startX, int startY, int width,int height, World world) {
		super(startX,startY,width,height,world);
	}
	
	public void fire() {
		setxCoord(getWorld().getPlayer().getxCoord());
		setyCoord(getWorld().getPlayer().getyCoord());
		initialX = getWorld().getPlayer().getxCoord();
		initialY = getWorld().getPlayer().getyCoord();
		
		
		//Find enemy to target
		
					
			if(getWorld().getPlayer().isFacingRight()) {
				setxVelocity(bulletSpeed);
			}else {
				setxVelocity(-1*bulletSpeed);
			}
		
		
		super.update();
		
		

		
		
		
		
	}
	
	public void update() {
		super.update();
		
		if(getDistanceAway(initialX,initialY,getxCoord(),getyCoord())>range) {
			setxVelocity(0);
			supersetxCoord(getWorld().getWorldWidth()+200);//Off screen
			supersetyCoord(getWorld().getWorldHeight()+200);
			
		}
		
		
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
