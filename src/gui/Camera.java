package gui;

import entity.moveable.Player;
import javafx.scene.Scene;

/**
 * Camera class to calculate where items should render based on where the player
 * is in the world (i.e. the camera follows the player).
 */

public class Camera {

	/*
	 * How the code works:
	 * 
	 * Get players position and if it's between half screen width and worldwidth
	 * minus half of screen width, then add an offset to the rendering of all
	 * objects in the world (in the main game timer).
	 * 
	 * Do the likewise calculation for the height.
	 * 
	 */
	private int offsetX = 0;
	private int offsetY = 0;
	private int halfSceneWidth;
	private int halfSceneHeight;
	private int worldWidth;
	private int worldHeight;

	/**
	 * Constructor for camera
	 * @param scene - the JavaFX scene the camera belongs to/acts in
	 * @param player - the player the camera should follow
	 */
	public Camera(Scene scene, Player player) {
		halfSceneWidth = (int) (scene.getWidth() / 2);
		worldWidth = player.getWorld().getWorldWidth();

		halfSceneHeight = (int) (scene.getHeight() / 2);
		worldHeight = player.getWorld().getWorldHeight();
	}

	/**
	 * Updates the camera by recalculating the offset (which objects should render
	 * to) based on the player's position.
	 * 
	 * @param player - the player to focus on / calculate the offset based off of.
	 */
	public void updateCamera(Player player) {
		if (player.getxCoord() >= halfSceneWidth && player.getxCoord() <= (worldWidth - halfSceneWidth)) {
			offsetX = halfSceneWidth - player.getxCoord();
		}

		if (player.getyCoord() <= worldHeight - halfSceneHeight) { // && player.getyCoord() <=
																	// (worldHeight-halfSceneHeight)) {
			offsetY = halfSceneHeight - player.getyCoord();
		}

	}

	/**
	 * Getter for the offset in the x direction.
	 * @return offsetX - offset in the x direction
	 */
	public int getOffsetX() {
		return offsetX;
	}
	
	/**
	 * Getter for the offset in the y direction.
	 * @return offsetY - offset in the y direction
	 */
	public int getOffsetY() {
		return offsetY;
	}

}
