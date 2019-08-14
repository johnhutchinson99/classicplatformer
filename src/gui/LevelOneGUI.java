package gui;

import java.util.HashMap;
import java.util.Map;
import backend.World;
import backend.WorldObject;
import entity.fixed.Platform;
import entity.moveable.Bullet;
import entity.moveable.Player;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * LevelOneGUI creates the scene (and shows it) for level one of the GUI based
 * game. Here, the world is instantiated with it's dimensions. The player is
 * set, the enemies of the level are set, the platforms are set. In other words,
 * all the world objects of the level are created here. As well, the keyboard
 * listener for the level/scene is called for here.
 *
 */

public class LevelOneGUI extends GameplayGUI {

	private static final int WORLDWIDTH = 2000;
	private static final int WORLDHEIGHT = 500;
	private static final int STARTX = 20;
	private static final int STARTY = 300;

	private Map<WorldObject, ImageView> worldObjectMap = new HashMap<WorldObject, ImageView>();
	private Map<Platform, HBox> platformGUIMap = new HashMap<Platform, HBox>();
	private Map<Point2D, ImageView> movingBackground = new HashMap<Point2D, ImageView>();

	/**
	 * The method which creates the level including its world, player, enemies,
	 * platforms, other world objects and calls the keyboard listener. The method
	 * also then shows the scene on the stage given.
	 * 
	 * @param stage   - the stage of the javaFX application
	 * @param aPlayer - the player to copy into this level
	 */
	public void create(Stage stage) {

		// Set scene
		Pane root = new Pane();
		Scene scene = new Scene(root, APPLICATIONWIDTH, APPLICATIONHEIGHT);

		// Set world
		World levelOne = new World(WORLDWIDTH, WORLDHEIGHT, STARTX, STARTY);

		// Create background
		createBackground(root, "/assets/image/Full-Background.png", 800, 500, 0, 0);
		createBackground(root, "/assets/image/layer-4.png", 800, 500, 0, 0);
		createBackground(root, "/assets/image/layer-2-mountain.png", 800, 500, 0, 0);

		// Create moving background elements (flowers, signs, trees)
		createBackground(root, "/assets/image/wood1.png", 70, 100, 20, 350, movingBackground);
		createBackground(root, "/assets/image/HELP.png", 264, 178, 20, 120, movingBackground);

		// Add player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Player player = new Player(35, 35, 35, 35);
		player.setXYCoord(STARTX, STARTY);
		player.setWorld(levelOne);
		levelOne.addPlayer(player);

		// Create an ImageView representation of the player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Image im = new Image("/assets/image/giphy.gif", false);
		ImageView iv = new ImageView(im);
		iv.setFitHeight(player.getHeight());
		iv.setFitWidth(player.getWidth());
		root.getChildren().add(iv);

		// Add bullet
		Bullet bullet = new Bullet(WORLDHEIGHT + 100, WORLDWIDTH + 100, 10, 5, levelOne);
		Rectangle bulletRect = new Rectangle(bullet.getxCoord(), bullet.getyCoord(), bullet.getWidth(),
				bullet.getHeight());
		bulletRect.setFill(Color.DARKGREY);
		root.getChildren().add(bulletRect);

		// Add the enemies
		createPoundCake(root, levelOne, worldObjectMap, 600, 400, 250, 690, 40, 40);
		createDirtCube(root, levelOne, worldObjectMap, 310, 340, 315, 345, 35, 30);
		createMace(root, levelOne, worldObjectMap, 180, 300, 260, 345, 40, 40);
		createMace(root, levelOne, worldObjectMap, 600, 230, 200, 280, 40, 40);
		createMace(root, levelOne, worldObjectMap, 660, 280, 180, 280, 40, 40);
		createPoundCake(root, levelOne, worldObjectMap, 1300, 300, 1100, 1800, 40, 40);
		createDirtCube(root, levelOne, worldObjectMap, 1510, 230, 200, 230, 35, 30);
		createMace(root, levelOne, worldObjectMap, 1280, 140, 100, 200, 40, 40);
		createMace(root, levelOne, worldObjectMap, 1720, 140, 100, 200, 40, 40);
		createPoundCake(root, levelOne, worldObjectMap, 1500, 300, 1100, 1800, 40, 40);
		createPoundCake(root, levelOne, worldObjectMap, 1700, 300, 1100, 1800, 40, 40);

		// Create the platforms in the level
		createTallPlatform(root, levelOne, platformGUIMap, 800, 300, 100, 200);
		createTallPlatform(root, levelOne, platformGUIMap, 1900, 200, 100, 300);
		createTallPlatform(root, levelOne, platformGUIMap, 900, 375, 50, 300);
		createPlatform(root, levelOne, platformGUIMap, 1050, 280, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 1200, 230, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 1350, 180, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 1485, 230, 80, 30);
		createPlatform(root, levelOne, platformGUIMap, 1650, 180, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 1800, 230, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 140, 380, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 280, 345, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 420, 305, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 580, 330, 150, 30);
		createPlatform(root, levelOne, platformGUIMap, 0, 450, WORLDWIDTH, 50);

		// Create coins
		createCoin(root, levelOne, worldObjectMap, 70, 420, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 250, 250, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 450, 180, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 640, 230, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 640, 200, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 750, 200, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 750, 170, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 910, 150, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 950, 150, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 990, 150, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1030, 150, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1070, 150, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1210, 70, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1210, 100, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1360, 70, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1360, 100, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1510, 70, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1510, 100, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1510, 130, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1660, 40, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1660, 70, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1660, 100, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1840, 60, 25, 25);
		createCoin(root, levelOne, worldObjectMap, 1840, 90, 25, 25);

		// Create the EndPoint for the world
		createEndPoint(root, levelOne, worldObjectMap, 1920, 150, 50, 50);

		// Call the keyboard listener for the level
		gameAnimation(stage, scene, player, iv, worldObjectMap, platformGUIMap, bullet, bulletRect, movingBackground);

		// Add Mute button

		Button muteButton = createMuteButton(scene);
		root.getChildren().add(muteButton);

		// Add Help Button
		Button helpButton = createHelpButton(scene);
		root.getChildren().add(helpButton);

		// Set the scene and show it
		stage.setScene(scene);
		stage.show();

	}

}