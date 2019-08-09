import java.util.HashMap;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelOneGUI extends GameplayGUI {
	
	private static final int WORLDWIDTH = 800;
	private static final int WORLDHEIGHT = 500;
	
	Map<EnemyGUI, ImageView> enemyGUIMap = new HashMap<EnemyGUI, ImageView>();

	public void create(Stage stage, Player aPlayer) {
		
		// Set scene
		Pane root = new Pane();		
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT);

		// Set world
		World levelOne = new World(WORLDWIDTH, WORLDHEIGHT);

		// Add player
		Player player = new Player(aPlayer);
		player.setXYCoord(20, 300);
		player.setWorld(levelOne);
		
		// Create background
        createBackground(root, "Full-Background.png", 800, 500, 0, 0);
        createBackground(root, "layer-4.png", 800, 500, 0, 0);
        createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);

        // Add the enemies
        createEnemyType1(root, levelOne, enemyGUIMap, 580, 230, 560, 670, 20, 20);
        createTrapType1(root, levelOne, enemyGUIMap, 310, 350, 325, 345, 20, 20);
        createFlyingEnemy(root, levelOne, enemyGUIMap, 180, 300, 260, 345, 30, 35);
        
        Image im = new Image("giphy.gif",false);
		ImageView iv = new ImageView(im);
		iv.setFitHeight(player.getHeight());
		iv.setFitWidth(player.getWidth());
        root.getChildren().add(iv);
		
		createEndPoint(root, levelOne, 770, 175, 50, 50);
		
		createPlatform( root, levelOne, 0, 450, 1000, 50);
		createPlatform( root, levelOne, 0, 420, 100, 20);
		createPlatform( root, levelOne, 140, 380, 100, 20);
		createPlatform( root, levelOne, 280, 345, 100, 20);
		createPlatform( root, levelOne, 420, 305, 100, 20);
		createPlatform( root, levelOne, 560, 265, 150, 20);
		createPlatform( root, levelOne, 750, 225, 50, 20);

		keyMethod(stage, scene, player, iv, enemyGUIMap);
		stage.setScene(scene);
		
		stage.show();

		
	}
	
}