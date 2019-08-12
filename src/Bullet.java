
public class Bullet extends PhysicsEntity{
	
	private int range = 200;
	private int bulletSpeed = 300;
	private int initialX;
	private int initialY;
	
	public Bullet(int startX, int startY, int width,int height, World world) {
		super(startX,startY,width,height,world);
		setYAcceleration(0);
	}
	
	public void fire() {
		if(getxVelocity() == 0) {
			setxCoord(getWorld().getPlayer().getxCoord()+getWorld().getPlayer().getWidth()/2);
			setyCoord(getWorld().getPlayer().getyCoord()+getWorld().getPlayer().getHeight()/2);
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
		
		
		

		
		
		
		
	}
	
	public void update() {
		
		

		super.update();
		
		if(getDistanceAway(initialX,initialY,getxCoord(),getyCoord())>range||getWorld().collidePlatform(this, getxCoord()+10, getyCoord())||getWorld().collidePlatform(this, getxCoord()-10, getyCoord())) {
			setxVelocity(0);
			moveOffScreen();
			
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
