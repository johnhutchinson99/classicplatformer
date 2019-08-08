import java.security.KeyRep;
import java.util.ArrayList;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameGUI extends Application {

	static int currentLevel = 1;
	static int previousLevel = 1;
	
//	private Player mainPlayer = new Player(35, 35, 35);

	
	boolean goLeft = false, goRight = false, jump = false;
	int leftCount = 0;
	int rightCount = 0;
	int jumpCount = 0;
	
	public void createBackground(Pane theRoot, String imgFilename, int width, int height, int xCoord, int yCoord) {
		Image img = new Image(imgFilename, false);
		ImageView back = new ImageView(img);
		back.setFitWidth(width);
		back.setFitHeight(height);
		theRoot.getChildren().add(back);
		back.setX(xCoord);
		back.setY(yCoord);
	}

	
	
	public void createPlatform(Pane theRoot, World aWorld, int x, int y, int width, int height) {
		Platform newPlatform = new Platform(x, y, width, height);
		aWorld.addPlatform(newPlatform);

		Image platformImage = new Image("Tile.png", false);
		ImageView platformTile = new ImageView(platformImage);
		platformTile.setFitWidth(width);
		platformTile.setFitHeight(height);
		theRoot.getChildren().add(platformTile);
		platformTile.setX(x);
		platformTile.setY(y);
	}
	
	public void keyMethod(Stage stage, Scene scene1, Player player1, ImageView playerRectangle1,
			Map<EnemyGUI, Rectangle> enemyMap)  {
		scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:
					goLeft = true;
					leftCount += 1;
					System.out.println("KeyPress: L");
					break;
				case RIGHT:
					goRight = true;
					rightCount += 1;
					System.out.println("KeyPress: R");
					break;
				case SHIFT:
					jump = true;
					jumpCount += 1;
					System.out.println("KeyPress: Shift");
					break;
				case DOWN:
					goLeft = false;
					goRight = false;
					jump = false;
					currentLevel += 1;
					break;
				}
			}
		});
		
        scene1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                    	goLeft = false;
                    	leftCount = 0;
    					System.out.println("KeyRelease: L");
    					break;
                    case RIGHT:
                    	goRight = false;
                    	rightCount = 0;
    					System.out.println("KeyRelease: R");
    					break;
    				case SHIFT:
    					jump = false;
    					jumpCount = 0;
    					System.out.println("KeyRelease: Shift");
    					break;
    				case DOWN:
    					break;
    				}
                }
        });

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				player1.applyGravity();
			
				if (jump) {
					player1.jump();
				}else {
					player1.applyGravity();
				}
				
				if (goLeft) {
					player1.walkLeft();
					playerRectangle1.setScaleX(-1);
					
				} else if(goRight) {
					player1.walkRight();
					playerRectangle1.setScaleX(1);
					
				} else {
					player1.stopWalking();
				}
				
				
				
//				if (player1.getxVelocity() < 0) {
//					playerRectangle1.setScaleX(-1);
//				} else {
//					playerRectangle1.setScaleX(1);
//				}
				

				for (Map.Entry<EnemyGUI, Rectangle> entry : enemyMap.entrySet()) {
					EnemyGUI enemy = entry.getKey();
					Rectangle enemyRectangle = entry.getValue();
					enemy.update();
					enemyRectangle.setX(enemy.getXCoord());
					enemyRectangle.setY(enemy.getYCoord());
				}				
				playerRectangle1.setX(player1.getXCoord());
				playerRectangle1.setY(player1.getYCoord());
			}
		};
		timer.start();
	}

	@Override
	public void start(Stage primaryStage) {

		final GameGUI r = new GameGUI();
		LevelOneGUI level1 = new LevelOneGUI();
		LevelOneGUI level3 = new LevelOneGUI();
//		GameOverGUI over = new GameOverGUI();

		primaryStage.show();

		level1.create(primaryStage);

		AnimationTimer refreshLevel = new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				if (currentLevel > previousLevel) {
					previousLevel += 1;
					this.stop();
					updateLevelGUI();
				}

			}

			private void updateLevelGUI() {
				if (currentLevel == 2) {
					level3.create(primaryStage);
					this.start();
				} else if (currentLevel == 3) {
					level3.create(primaryStage);
					this.start();
				} else {
					level3.create(primaryStage);
				}

			}
		};
		refreshLevel.start();

	}

	public static void main(String[] args) {
		Application.launch(GameGUI.class, args);
	}
}