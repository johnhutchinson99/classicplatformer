import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Gameplay GUI is the (super)class which controls the current scene/level of
 * the game and has the create methods used to easily create objects/their
 * graphics in a child level class.
 *
 */

public class GameplayGUI extends Application {

	static int currentLevel = 1;
	static int previousLevel = 1;

	private Player mainPlayer = new Player(35, 35, 35, 35);

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

	public void createEnemyType1(Pane theRoot, World aWorld, Map<EnemyGUI, ImageView> enemyGUIMap, int xCoord,
			int yCoord, int minXPosition, int maxXPosition, int width, int height) {
		EnemyType1 enemy1 = new EnemyType1(aWorld, xCoord, yCoord, true, minXPosition, maxXPosition, width, height);
		Image img = new Image("WalkingEnemy.gif", false);
		ImageView enemyImageView = new ImageView(img);
		enemyImageView.setFitWidth(width);
		enemyImageView.setFitHeight(height);
		theRoot.getChildren().add(enemyImageView);
		enemyImageView.setX(xCoord);
		enemyImageView.setY(yCoord);

		aWorld.addEnemy(enemy1);
		enemyGUIMap.put(enemy1, enemyImageView);
	}

	public void createFlyingEnemy(Pane theRoot, World aWorld, Map<EnemyGUI, ImageView> enemyGUIMap, int xCoord,
			int yCoord, int minYPosition, int maxYPosition, int width, int height) {
		FlyingEnemy trap1 = new FlyingEnemy(aWorld, xCoord, yCoord, width, height, true, minYPosition, maxYPosition);
		Image img = new Image("FlyingEnemy.gif", false);
		ImageView enemyImageView = new ImageView(img);
		enemyImageView.setFitWidth(width);
		enemyImageView.setFitHeight(height);
		theRoot.getChildren().add(enemyImageView);
		enemyImageView.setX(xCoord);
		enemyImageView.setY(yCoord);

		aWorld.addEnemy(trap1);
		enemyGUIMap.put(trap1, enemyImageView);
	}

	public void createTrapType1(Pane theRoot, World aWorld, Map<EnemyGUI, ImageView> enemyGUIMap, int xCoord,
			int yCoord, int minYPosition, int maxYPosition, int width, int height) {
		TrapType1 trap1 = new TrapType1(aWorld, xCoord, yCoord, width, height, true, minYPosition, maxYPosition);
		Image img = new Image("WalkingEnemy.gif", false);
		ImageView enemyImageView = new ImageView(img);
		enemyImageView.setFitWidth(width);
		enemyImageView.setFitHeight(height);
		theRoot.getChildren().add(enemyImageView);
		enemyImageView.setX(xCoord);
		enemyImageView.setY(yCoord);

		aWorld.addEnemy(trap1);
		enemyGUIMap.put(trap1, enemyImageView);

	}

	public void createEndPoint(Pane theRoot, World aWorld, int x, int y, int width, int height) {
		EndPoint endpoint = new EndPoint(x, y, width, height);
		aWorld.setEndPoint(endpoint);
		Image endPointImage = new Image("Portal.editor.pv.png", false);
		ImageView endPointTile = new ImageView(endPointImage);
		endPointTile.setFitWidth(width);
		endPointTile.setFitHeight(height);
		theRoot.getChildren().add(endPointTile);
		endPointTile.setX(x);
		endPointTile.setY(y);
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
			Map<EnemyGUI, ImageView> enemyMap) {
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
					if (player1.getWorld().isCollide(player1, player1.getWorld().getEndPoint())) {
						goLeft = false;
						goRight = false;
						jump = false;
						currentLevel += 1;
					}
					break;
				case Z:
//					player1.attack();
					System.out.println("Z");
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
				case Z:
					break;
				}
			}
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				if (jump) {
					player1.setyVelocity(-25, jumpCount);
//					player1.jump(jumpCount);
				}

				if (goLeft) {
					player1.setxVelocity(-5, leftCount);
				} else if (goRight) {
					player1.setxVelocity(5, rightCount);
				} else {
					player1.setxVelocity(0, 1);
				}

				player1.update();
				if (player1.getxVelocity() < 0) {
					playerRectangle1.setScaleX(-1);
				} else if (player1.getxVelocity() > 0) {
					playerRectangle1.setScaleX(1);
				}
				playerRectangle1.setX(player1.getxCoord());
				playerRectangle1.setY(player1.getyCoord());

				if (!player1.isAlive()) {
					player1.setxCoord(0);
					player1.setyCoord(0);
					playerRectangle1.setX(0);
					playerRectangle1.setY(0);
					player1.revive();
					goLeft = false;
					goRight = false;
					jump = false;
					currentLevel = 4;
				}

				for (Map.Entry<EnemyGUI, ImageView> entry : enemyMap.entrySet()) {
					EnemyGUI enemy = entry.getKey();
					ImageView enemyRectangle = entry.getValue();
					enemy.update();
					enemyRectangle.setX(enemy.getxCoord());
					enemyRectangle.setY(enemy.getyCoord());
					player1.getWorld().isCollide(player1, enemy);

//					if(!player1.getWorld().getEnemyList().contains(enemy)) {
//						
//					}
				}
			}
		};
		timer.start();
	}

	@Override
	public void start(Stage primaryStage) {

		StartMenu start = new StartMenu();
		LevelOneGUI level1 = new LevelOneGUI();
		LevelTwoGUI level2 = new LevelTwoGUI();
		GameOverGUI over = new GameOverGUI();

		start.create(primaryStage);
		primaryStage.show();

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
					level1.create(primaryStage, mainPlayer);
					this.start();
				} else if (currentLevel == 3) {
					level2.create(primaryStage, mainPlayer);
					this.start();
				} else {
					over.create(primaryStage, mainPlayer);
				}

			}
		};
		refreshLevel.start();

	}

	public static void main(String[] args) {
		Application.launch(GameplayGUI.class, args);
	}
}