
public abstract class Moveable extends Entity{
	
	private World world;
	private double walkSpeed; 
	private double jumpPower; 
	
	public Moveable(World newWorld) {
		
		world = newWorld;
		
		walkSpeed = 50;
		jumpPower = 1000;
		
	
		
	}
	
	
	
	/**
	 * Updates gravity(acceleration) when called
	 * Run inside walk and jump methods
	 */
	public void applyGravity() {
		getPhysics().setYAcceleration(Physics.GRAVITY);
		
		
		
		
		
		
		}
	
	
	
	
	/**
	 * Getter for the world the entity is in. 
	 * @return The world the entity is in.  
	 */
	public World getWorld() {
		return world;
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
