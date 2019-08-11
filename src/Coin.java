
public class Coin extends WorldObject{
	
	private int coinValue = 5;
	private boolean pickedUp = false;
	
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld) {
		super(xCoord, yCoord, width, height, aWorld);
	}
	
	public Coin(int xCoord, int yCoord, int width, int height, World aWorld, int value) {
		super(xCoord, yCoord, width, height);
		coinValue = value;
	}
	
	public boolean doCollision(WorldObject object) {
		if (object instanceof Player) {
			object.getWorld().addToCoinCount(coinValue);
			pickedUp = true; 
		}
		return true;
	}
	
	public boolean isAlive() {
		return !pickedUp;
	}
	
}
