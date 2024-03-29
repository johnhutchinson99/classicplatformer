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
 * LevelTwoGUI creates the scene (and shows it) for level one of the GUI based
 * game. Here, the world is instantiated with it's dimensions. The player is
 * set, the enemies of the level are set, the platforms are set. In other words,
 * all the world objects of the level are created here. As well, the keyboard
 * listener for the level/scene is called for here.
 *
 */
public class LevelTwoGUI extends GameplayGUI {

	private static final int WORLDWIDTH = 800;
	private static final int WORLDHEIGHT = 1000;
	private static final int STARTX = 20;
	private static final int STARTY = 750;

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
		World levelTwo = new World(WORLDWIDTH, WORLDHEIGHT, STARTX, STARTY);

		// Create background
		createBackground(root, "/assets/image/Full-Background.png", 800, 500, 0, 0);
		createBackground(root, "/assets/image/layer-2-mountain.png", 800, 500, 0, 0);

		// Add player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Player player = new Player(35, 35, 35, 35);
		player.setXYCoord(STARTX, STARTY);
		player.setWorld(levelTwo);
		levelTwo.addPlayer(player);

		// Create an ImageView representation of the player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Image im = new Image("/assets/image/giphy.gif", false);
		ImageView iv = new ImageView(im);
		iv.setFitHeight(player.getHeight());
		iv.setFitWidth(player.getWidth());
		root.getChildren().add(iv);

		// Add bullet
		Bullet bullet = new Bullet(WORLDHEIGHT + 100, WORLDWIDTH + 100, 10, 5, levelTwo);
		Rectangle bulletRect = new Rectangle(bullet.getxCoord(), bullet.getyCoord(), bullet.getWidth(),
				bullet.getHeight());
		root.getChildren().add(bulletRect);
		bulletRect.setFill(Color.DARKGREY);

		// Add the enemies
		createPoundCake(root, levelTwo, worldObjectMap, 580, 210 + 450, 560, 650, 40, 40);
		createPoundCake(root, levelTwo, worldObjectMap, 600, 115 + 450, 580, 640, 40, 40);
		createPoundCake(root, levelTwo, worldObjectMap, 440, 75 + 450, 430, 480, 40, 40);
		createDirtCube(root, levelTwo, worldObjectMap, 310, 340 + 450, 315 + 450, 345 + 450, 30, 30);
		createMace(root, levelTwo, worldObjectMap, 180, 300 + 450, 260 + 450, 340 + 450, 40, 40);
		createMace(root, levelTwo, worldObjectMap, 760, 200 + 450, 100 + 450, 185 + 450, 40, 40);
		createMace(root, levelTwo, worldObjectMap, 300, 65 + 450, 0 + 450, 50 + 450, 40, 40);

		// Create the platforms in the level
		createPlatform(root, levelTwo, platformGUIMap, 0, WORLDHEIGHT - 50, 1000, 50);
		createPlatform(root, levelTwo, platformGUIMap, 0, 440, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 140 + 50, 380, 50, 20);
		createPlatform(root, levelTwo, platformGUIMap, 420 - 50, 305, 50, 20);
		createPlatform(root, levelTwo, platformGUIMap, 550, 230, 50, 20);
		createPlatform(root, levelTwo, platformGUIMap, 600, 100, 30, 20);
		createPlatform(root, levelTwo, platformGUIMap, 380, 150, 30, 20);
		createPlatform(root, levelTwo, platformGUIMap, 180, 250, 30, 20);
		createPlatform(root, levelTwo, platformGUIMap, 50, 180, 30, 20);
		createPlatform(root, levelTwo, platformGUIMap, 0, 100, 30, 20);
		createPlatform(root, levelTwo, platformGUIMap, 750, 180, 50, 20);
		createPlatform(root, levelTwo, platformGUIMap, 140, 50, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 0, 440 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 140, 380 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 280, 345 + 450, 100, 30);
		createPlatform(root, levelTwo, platformGUIMap, 420, 305 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 560, 265 + 450, 150, 20);
		createPlatform(root, levelTwo, platformGUIMap, 750, 225 + 450, 50, 20);
		createPlatform(root, levelTwo, platformGUIMap, 140, 50 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 280, 90 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 420, 130 + 450, 100, 20);
		createPlatform(root, levelTwo, platformGUIMap, 580, 170 + 450, 100, 20);

		// Create the EndPoint for the world
		createEndPoint(root, levelTwo, worldObjectMap, 150, 0, 50, 50);

		// Create coins
		createCoin(root, levelTwo, worldObjectMap, 20, 380, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 20, 350, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 20, 320, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 100, 780, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 240, 750, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 770, 630, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 399, 510, 25, 25);
		createCoin(root, levelTwo, worldObjectMap, 162, 10, 25, 25);

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