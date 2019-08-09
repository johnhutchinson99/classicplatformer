/**
 * 
 * Colliding with a Checkpoint object leads to a game win
 *
 */
public class Checkpoint extends Entity{

	public Checkpoint(int newWidth, int newHeight) {
		super(newWidth,newHeight);
	}
	public Checkpoint(int x, int y, int newWidth, int newHeight) {
		super(x,y,newWidth,newHeight);
	}

}
