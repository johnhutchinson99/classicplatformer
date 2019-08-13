
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

	private static final int WORLDWIDTH = 1600;
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
		createBackground(root, "assets/image/Full-Background.png", 800, 500, 0, 0);
		createBackground(root, "assets/image/layer-4.png", 800, 500, 0, 0);
		createBackground(root, "assets/image/layer-2-mountain.png", 800, 500, 0, 0);

		

		
		// Create moving background elements (flowers, signs, trees)
		createBackground(root, "assets/image/wood1.png", 70, 100, 20, 350, movingBackground);
		
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
		Image im = new Image("assets/image/giphy.gif", false);
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
		createEnemyType1(root, levelOne, worldObjectMap, 580, 220, 560, 660, 40, 40);
		createTrapType1(root, levelOne, worldObjectMap, 310, 340, 315, 345, 35, 30);
		createFlyingEnemy(root, levelOne, worldObjectMap, 180, 300, 260, 345, 40, 40);

		// Create the platforms in the level
		createTallPlatform(root, levelOne, platformGUIMap, 800, 300, 100, 200);

		createPlatform(root, levelOne, platformGUIMap, 0, 420, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 140, 380, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 280, 345, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 420, 305, 100, 30);
		createPlatform(root, levelOne, platformGUIMap, 560, 265, 150, 30);
		createPlatform(root, levelOne, platformGUIMap, 750, 225, 50, 30);
		createPlatform(root, levelOne, platformGUIMap, 0, 450, WORLDWIDTH, 50);

		// Create coins
		createCoin(root, levelOne, worldObjectMap, 150, 420, 25, 25);

		// Create the EndPoint for the world
		createEndPoint(root, levelOne, worldObjectMap, 770, 175, 50, 50);

		// Call the keyboard listener for the level
		gameAnimation(stage, scene, player, iv, worldObjectMap, platformGUIMap,bullet,bulletRect, movingBackground);

		// Add Mute button

		Button muteButton = createMuteButton(scene);
		root.getChildren().add(muteButton);

		//Add Help Button
		Button helpButton = createHelpButton(scene);
		root.getChildren().add(helpButton);
		
		// Set the scene and show it
		stage.setScene(scene);
		stage.show();

	}

}