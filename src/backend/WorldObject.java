package backend;

/**
 * 
 * 
 * The Entity class contains generic methods and variables that will be used for
 * any entity in the game (Player, Enemies) This class primarily contains code
 * related to the position of the Entity but more functionality will be added in
 * later releases Additionally, this class contains the world object, which will
 * allow children to access it.
 * 
 *
 */

public abstract class WorldObject {

	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private World world;

	/**
	 * Default constructor for world object.
	 */
	public WorldObject() {

	}

	/**
	 * Constructor for world object
	 * 
	 * @param x         - the x coordinate to set the world object to
	 * @param y         - the y coordinate to set the world object to
	 * @param newWidth  - the width of the world object
	 * @param newHeight - the height of the world object
	 */
	public WorldObject(int x, int y, int newWidth, int newHeight) {
		xCoord = x;
		yCoord = y;
		if (newWidth > 0) {
			width = newWidth;
		}
		if (newHeight > 0) {
			height = newHeight;
		}
	}

	/**
	 * Constructor for world object
	 * 
	 * @param x         - the x coordinate to set the world object to
	 * @param y         - the y coordinate to set the world object to
	 * @param newWidth  - the width of the world object
	 * @param newHeight - the height of the world object
	 * @param aWorld    - the world the world object is in
	 */
	public WorldObject(int x, int y, int newWidth, int newHeight, World aWorld) {
		this(x, y, newWidth, newHeight);
		world = aWorld;
	}

	/**
	 * Copy constructor for the world object
	 * 
	 * @param toCopy - the worldobject to copy from
	 */
	public WorldObject(WorldObject toCopy) {
		xCoord = toCopy.xCoord;
		yCoord = toCopy.yCoord;
		width = toCopy.width;
		height = toCopy.height;
		world = toCopy.world;
	}

	/**
	 * Setter for the world object's world it is in
	 * 
	 * @param wo - the world the object is in
	 */
	public void setWorld(World wo) {
		world = wo;
	}

	/**
	 * 
	 * @return Width of the object (in X)
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return Height of object (in Y)
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the x and y coordinate of the object at once
	 * 
	 * @param x - the x coordinate to set the object to
	 * @param y - the y coordinate to set the object to
	 */
	public void setXYCoord(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	/**
	 * Setter for an entities x coordinate position.
	 * 
	 * @param newX - The x coordinate position to put the entity in.
	 */
	public void setxCoord(int xCoord) {
		if (xCoord < (getWorld().getWorldWidth() - width) && xCoord >= 0
				&& !getWorld().collidePlatform(this, xCoord, getyCoord())) {
			this.xCoord = xCoord;
		}
	}

	/**
	 * Setter for an entities y coordinate position.
	 * 
	 * @param newY - The y coordinate position to put the entity in.
	 */
	public void setyCoord(int yCoord) {
		if (yCoord <= (getWorld().getWorldHeight() - height) && yCoord > 0
				&& !getWorld().collidePlatform(this, getxCoord(), yCoord)) {
			this.yCoord = yCoord;
		}
	}

	/**
	 * Ignore obstacles/platforms and set the object to the given x coordinate.
	 * 
	 * @param xCoord - the x coordinate to set the object to
	 */
	public void supersetxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * Ignore obstacles/platforms and set the object to the given y coordinate.
	 * 
	 * @param yCoord - the y coordinate to set the object to
	 */
	public void supersetyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * Getter for an entities x coordinate.
	 * 
	 * @return The x coordinate of the entity.
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * Move the object off screen by setting their coordinates much beyond the
	 * bounds of the world.
	 */
	public void moveOffScreen() {
		supersetxCoord(getWorld().getWorldWidth() + 1000);
		supersetyCoord(getWorld().getWorldHeight() + 1000);

	}

	/**
	 * Getter for an entities y coordinate.
	 * 
	 * @return The y coordinate of the entity.
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * Getter for the world object's world
	 * 
	 * @return world - the world that the world object belongs to
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * The do collision method for a world object (usually called when isColliding
	 * is true, and thus a world object will perform some specific task according to
	 * doCollision or just return true to confirm there was collision with the
	 * specific object)
	 * 
	 * @param actOnObject - the world object the doCollision will act on
	 * @return
	 */
	public boolean doCollision(WorldObject actOnObject) {
		return true;
	}

	/**
	 * The update method for world objects
	 */
	public void update() {
	}

	/**
	 * The method to check whether a world object is "alive". All objects are alive
	 * by default/unless method is overriden.
	 * 
	 * @return true by default
	 */
	public boolean isAlive() {
		return true;
	}

}
