
public class Coins extends PhysicsEntity{
	
	private static int numOfCoins = 0;
	
	public Coins(World newWorld,int enemyXCoord,int enemyYCoord, int width, int height) {
		super(enemyXCoord,enemyYCoord,width,height,newWorld);
		}
	
	public int getNumOfCoins() {
		return numOfCoins;
	}
	
	public void addCoin() {
		numOfCoins++;
	}
	
	public void minsCoin() {
		numOfCoins--;
	}
	
	public void zeroCoin() {
		numOfCoins = 0;
	}
}
