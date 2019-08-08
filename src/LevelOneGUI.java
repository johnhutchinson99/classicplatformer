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

public class LevelOneGUI extends GameGUI {
	
	private static final int WORLDWIDTH = 800;
	private static final int WORLDHEIGHT = 500;
	
	Map<EnemyGUI, Rectangle> enemyGUIMap = new HashMap<EnemyGUI, Rectangle>();
	

	public void create(Stage stage) {
		
		World levelOne = new World(WORLDWIDTH, WORLDHEIGHT, 10, 10, 800, 20);

		Player player = new Player(levelOne, 5, 20, 20);
		
		Pane root = new Pane();
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT,Color.AQUA);
		
        // Create Background
        createBackground(root, "Full-Background.png", 800, 500, 0, 0);
        createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);

//		Rectangle playerRectangle = new Rectangle(player.getxCoord(), player.getyCoord(), player.getWidth(),
//				player.getHeight());
//		playerRectangle.setFill(new ImagePattern(iv.getImage()));

		Image im = new Image("giphy.gif",false);
		ImageView iv = new ImageView(im);
		iv.setFitHeight(30);
		iv.setFitWidth(30);

        root.getChildren().add(iv);

        // Create enemy

        TrapType1 trap1 = new TrapType1(levelOne,310, 350 , 20, 20, true, 325, 345);
		Rectangle trap1Rectangle = new Rectangle(trap1.getXCoord(), trap1.getYCoord(), trap1.getWidth(),
				trap1.getHeight());
		trap1Rectangle.setFill(Color.CORAL);
		root.getChildren().add(trap1Rectangle);
		levelOne.addEnemy(trap1);
		enemyGUIMap.put(trap1, trap1Rectangle);

        
        // Create Platforms
		createPlatform( root, levelOne, 0, 450, 1000, 150);
		createPlatform( root, levelOne, 0, 420, 100, 10);
		createPlatform( root, levelOne, 145, 380, 100, 10);
		createPlatform( root, levelOne, 250, 340, 100, 10);
		createPlatform( root, levelOne, 360, 300, 100, 10);
		createPlatform( root, levelOne, 460, 260, 100, 10);
		createPlatform( root, levelOne, 560, 400, 100, 10);

		keyMethod(stage, scene, player, iv, enemyGUIMap);

		stage.setScene(scene);
		
		stage.show();
		
	}
	
//	public void update() {
//
//		// This will update the enemy position while the game is running
////		while (getPlayer().isAlive() && !level.isPlayerAtGoal(getPlayer())) {
//			for(Enemy enemy: getListOfEnemies()) {
//				enemy.move2();
//			}
//			getPlayer().update();
//		
//	}
	
}