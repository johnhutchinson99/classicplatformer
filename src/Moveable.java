
public abstract class Moveable extends Entity{
	
	private World world;
	private double walkSpeed; 
	private double jumpPower; 
	
	public Moveable(World newWorld, int width, int height) {
		
		super(width, height);
		world = newWorld;
		
		walkSpeed = 50;
		jumpPower = 1000;
		
	
		
	}
	
	
	
	public Moveable(int width, int height) {
		super(width, height);
	}



	/**
	 * Updates gravity(acceleration) when called
	 * Run inside walk and jump methods
	 */
	public void applyGravity() {
		
		
		//Checking whether or not on platform
		boolean isOnPlatform = false;
		
		for(Platform p: getWorld().getPlatforms()) {
			
			//Testing left side
			
			if(p.isWithinEntity(getXCoord(), getYCoord()+getHeight()+p.getHeight())) {
				isOnPlatform = true;
				System.out.println("TOUCHING PLATFORM");
			}
			// Testing right side
			if(p.isWithinEntity(getXCoord()+getWidth(), getYCoord()+getHeight()+p.getHeight())) {
				isOnPlatform = true;
				System.out.println("TOUCHING PLATFORM");
			}
			
			
		}
		
		
		if(!isOnPlatform)
		getPhysics().setYAcceleration(Physics.GRAVITY);
		else {
			getPhysics().setYAcceleration(0);
			getPhysics().setYVelocity(0);
		}
			
		
		
		
		
		
		
		
		}
	
	
	
	
	/**
	 * Getter for the world the entity is in. 
	 * @return The world the entity is in.  
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Setter for the world the entity is in. 
	 */
	public void setWorld(World aWorld) {
		world = aWorld;
	}

	/**
	 * 
	 * @param Sets power with with the Entity will walk
	 */
	public void setWalkSpeed(double walk) {
		if(walk>0) {
			walkSpeed = walk;
		}
	}


	/**
	 * 
	 * @return current walking speed
	 */
	public double getWalkSpeed() {
		return walkSpeed;	
	}

	/**
	 * 
	 * @param Sets the jump intensity for Entity
	 */
	public void setJumpPower(double jump) {
		if(jump>0) {
			jumpPower = jump;
		}
	}
	/**
	 * 
	 * @return gets the jump intensity for Entity
	 */
	public double getJumpPower() {
		return jumpPower;	
	}
	
	
	
	/**
	 * Initiate an Entity jump based on it's jumping power
	 */
	public void jump() {
		
		getPhysics().setYVelocity(-1*jumpPower);
		applyGravity();
		
		//myPhysics.setYPosition(myPhysics.getYPosition()-40);
	}
	/**
	 * Make the Entity walk to the left of the screen based on walking speed
	 */
	public void walkLeft() {
		getPhysics().setXVelocity(-1*walkSpeed);
		applyGravity();
	}
	/**
	 * Make the Entity walk to the right of the screen based on walking speed
	 */
	public void walkRight() {
		getPhysics().setXVelocity(walkSpeed);
		applyGravity();
	}
	
	
}
