import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

	private Map<WorldObject, ImageView> worldObjectMap = new HashMap<WorldObject, ImageView>();
	Map<Platform, HBox> platformGUIMap = new HashMap<Platform, HBox>();

	/**
	 * The method which creates the level including its world, player, enemies,
	 * platforms, other world objects and calls the keyboard listener. The method
	 * also then shows the scene on the stage given.
	 * 
	 * @param stage   - the stage of the javaFX application
	 * @param aPlayer - the player to copy into this level
	 */
	public void create(Stage stage, Player aPlayer) {

		// Set scene
		Pane root = new Pane();
		Scene scene = new Scene(root, 800, WORLDHEIGHT);

		// Set world
		World levelOne = new World(WORLDWIDTH, WORLDHEIGHT);

		// Create the EndPoint for the world
		createEndPoint(root, levelOne, 770, 175, 50, 50);

		// Create background
		createBackground(root, "Full-Background.png", 800, 500, 0, 0);
		createBackground(root, "layer-4.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		
		// Add player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Player player = new Player(aPlayer);
		player.setXYCoord(20, 300);
		player.setWorld(levelOne);
		levelOne.addPlayer(player);

		// Create an ImageView representation of the player
		// Note: Don't turn into method as need to be updated on keyboard
		// press/separately from other world objects
		Image im = new Image("giphy.gif", false);
		ImageView iv = new ImageView(im);
		iv.setFitHeight(player.getHeight());
		iv.setFitWidth(player.getWidth());
		root.getChildren().add(iv);
		
		
		//Add bullet
		Bullet bullet = new Bullet(WORLDHEIGHT+100,WORLDWIDTH+100,10,10,levelOne);
		Rectangle bulletRect = new Rectangle(bullet.getxCoord(),bullet.getyCoord(),bullet.getWidth(),bullet.getHeight());
		root.getChildren().add(bulletRect);
		
		// Add the enemies
		createEnemyType1(root, levelOne, worldObjectMap, 580, 230, 560, 670, 20, 20);
		createTrapType1(root, levelOne, worldObjectMap, 310, 350, 325, 345, 20, 20);
		createFlyingEnemy(root, levelOne, worldObjectMap, 180, 300, 260, 345, 30, 35);

		// Create the platforms in the level
		createPlatform(root, levelOne, platformGUIMap, 0, 450, WORLDWIDTH, 50);
		createPlatform(root, levelOne, platformGUIMap, 0, 420, 100, 20);
		createPlatform(root, levelOne, platformGUIMap, 140, 380, 100, 20);
		createPlatform(root, levelOne, platformGUIMap, 280, 345, 100, 20);
		createPlatform(root, levelOne, platformGUIMap, 420, 305, 100, 20);
		createPlatform(root, levelOne, platformGUIMap, 560, 265, 150, 20);
		createPlatform(root, levelOne, platformGUIMap, 750, 225, 50, 20);
		
		// Create coins
		createCoin(root, levelOne, worldObjectMap, 150, 420, 25, 25);
		
		// Call the keyboard listener for the level
		gameAnimation(stage, scene, player, iv, worldObjectMap, platformGUIMap,bullet,bulletRect);

		// Set the scene and show it
		stage.setScene(scene);
		stage.show();

	}

}