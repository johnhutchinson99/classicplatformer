/**
 * The class for creating platform objects. 
 */

public class Platform extends WorldObject {

	/**
	 * Platform constructor, sets the top left coordinates and width/height of the
	 * platform.
	 * 
	 * @param topLeftX X coordinate of top left corner of platform
	 * @param topLeftY Y coordinate of top left corner of platform
	 * @param width    how far the platform extends in the X direction
	 * @param height   how far the platform extends in the Y direction
	 */
	public Platform(int topLeftX, int topLeftY, int width, int height) {
		super(topLeftX, topLeftY, width, height);
	}

}
