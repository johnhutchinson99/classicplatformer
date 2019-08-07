
public class Bullet extends WorldObject{
	
	private int range;
	
	public Bullet(int plaverX, int playerY) {
		super(plaverX,playerY);
	}
	
	public Bullet() {  //Create a Bullet
		range = 0;
		Bullet b = new Bullet(getPlayerX(),getPlayerY());
		if (range <= 20) {
			b.setxCoord(getxCoord()+1);
			range += 1;
		}else {
			b = null;
			System.gc();
		}
	}
}
