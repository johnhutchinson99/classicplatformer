import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Gameplay GUI is the (super)class which controls the current scene/level of
 * the game and has the create methods used to easily create objects/their
 * graphics in a child level class.
 *
 */

public class GameplayGUI extends Application {

	static int currentLevel = 0;
	static int previousLevel = 0;

	private Player mainPlayer = new Player(35, 35, 35, 35);

	boolean goLeft = false, goRight = false, jump = false;
	int leftCount = 0;
	int rightCount = 0;
	int jumpCount = 0;

	/**
	 * createBackground creates the background of the GUI scene. Specifically, an
	 * image is given to the method which is then placed in the scene/root at the
	 * specified x and y coordinates. The image is stretched to the given width and
	 * height.
	 * 
	 * @param theRoot     - the root of the javaFX scene
	 * @param imgFilename - the filename of the image to be used as the background
	 * @param width       - the width to stretch the image to
	 * @param height      - the height to stretch the image to
	 * @param xCoord      - the top left x coordinate of where to place the image
	 * @param yCoord      - the top left y coordinate of where to place the image
	 */
	public void createBackground(Pane theRoot, String imgFilename, int width, int height, int xCoord, int yCoord) {
		Image img = new Image(imgFilename, false);
		ImageView back = new ImageView(img);
		back.setFitWidth(width);
		back.setFitHeight(height);
		theRoot.getChildren().add(back);
		back.setX(xCoord);
		back.setY(yCoord);
	}

	/**
	 * Creates an EnemyType1 (an enemy which moves back and forth in the x
	 * direction, within a specified min and max coordinate) instance and makes the
	 * respective GUI/ImageView representation.
	 * 
	 * @param theRoot      - the root of the javaFX scene
	 * @param aWorld       - the level the enemy will belong to
	 * @param enemyGUIMap  - the map the enemy should be added to, specifically, the
	 *                     map of enemies to their respective GUI/ImageView
	 *                     representations
	 * @param xCoord       - the starting x coordinate of the enemy
	 * @param yCoord       - the starting y coordinate of the enemy
	 * @param minXPosition - the min x coordinate the enemy will travel back and
	 *                     forth from
	 * @param maxXPosition - the max x coordinate the enemy will travel back and
	 *                     forth from
	 * @param width        - the width of the enemy
	 * @param height       - the height of the enemy
	 * 
	 */
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

	/**
	 * Creates an FlyingEnemy (an enemy which moves up and down in the y direction,
	 * within a specified min and max coordinate) instance and makes the respective
	 * GUI/ImageView representation.
	 * 
	 * @param theRoot      - the root of the javaFX scene
	 * @param aWorld       - the level the enemy will belong to
	 * @param enemyGUIMap  - the map the enemy should be added to, specifically, the
	 *                     map of enemies to their respective GUI/ImageView
	 *                     representations
	 * @param xCoord       - the starting x coordinate of the enemy
	 * @param yCoord       - the starting y coordinate of the enemy
	 * @param minYPosition - the min y coordinate the enemy will travel back and
	 *                     forth from
	 * @param maxYPosition - the max y coordinate the enemy will travel back and
	 *                     forth from
	 * @param width        - the width of the enemy
	 * @param height       - the height of the enemy
	 * 
	 */
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

	/**
	 * Creates an TrapType1 (an enemy which moves up and down in intervals,
	 * generally is hiding within a platform) instance and makes the respective
	 * GUI/ImageView representation.
	 * 
	 * @param theRoot      - the root of the javaFX scene
	 * @param aWorld       - the level the enemy will belong to
	 * @param enemyGUIMap  - the map the enemy should be added to, specifically, the
	 *                     map of enemies to their respective GUI/ImageView
	 *                     representations
	 * @param xCoord       - the starting x coordinate of the enemy
	 * @param yCoord       - the starting y coordinate of the enemy
	 * @param minYPosition - the min y coordinate the enemy will travel back and
	 *                     forth from
	 * @param maxYPosition - the max y coordinate the enemy will travel back and
	 *                     forth from
	 * @param width        - the width of the enemy
	 * @param height       - the height of the enemy
	 * 
	 */
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

	/**
	 * Creates an EndPoint (where the player must go to in order to advance
	 * levels/win the game) and it's GUI/ImageView representation.
	 * 
	 * @param theRoot - the root of the javaFX scene
	 * @param aWorld  - the level the EndPoint will belong to/be in
	 * @param xCoord  - the starting x coordinate of the EndPoint
	 * @param yCoord  - the starting y coordinate of the EndPoint
	 * @param width   - the width of the EndPoint
	 * @param height  - the height of the EndPoint
	 */
	public void createEndPoint(Pane theRoot, World aWorld, int xCoord, int yCoord, int width, int height) {
		EndPoint endpoint = new EndPoint(xCoord, yCoord, width, height);
		aWorld.setEndPoint(endpoint);
		Image endPointImage = new Image("Portal.editor.pv.png", false);
		ImageView endPointTile = new ImageView(endPointImage);
		endPointTile.setFitWidth(width);
		endPointTile.setFitHeight(height);
		theRoot.getChildren().add(endPointTile);
		endPointTile.setX(xCoord);
		endPointTile.setY(yCoord);
	}

	/**
	 * Creates a platform for the level and it's respective GUI/ImageView
	 * representation.
	 * 
	 * @param theRoot - the root of the javaFX scene
	 * @param aWorld  - the level the platform will belong to/be in
	 * @param xCoord  - the starting x coordinate of the platform
	 * @param yCoord  - the starting y coordinate of the platform
	 * @param width   - the width of the platform
	 * @param height  - the height of the platform
	 */
	public void createPlatform(Pane theRoot, World aWorld, int xCoord, int yCoord, int width, int height) {

		// Make the platform and add it to the world
		Platform newPlatform = new Platform(xCoord, yCoord, width, height);
		aWorld.addPlatform(newPlatform);

		// Create the image, stretch to fit the height, calculate number of tiles to
		// display minus one, and the width of the last stretched tile
		Image platformImage = new Image("Tile.png", false);
		double resizedTileWidth = (height / platformImage.getHeight()) * platformImage.getWidth();
		int numberTilesMinusOne = (int) Math.round(width / resizedTileWidth) - 1;
		double remainingTileWidth = width - (numberTilesMinusOne * resizedTileWidth);

		// Repeat the image tiles in an HBox
		HBox platformHBox = new HBox(0);
		for (int i = 0; i < numberTilesMinusOne; i++) {
			ImageView aPlatformTile = new ImageView(platformImage);
			aPlatformTile.setFitWidth(resizedTileWidth);
			aPlatformTile.setFitHeight(height);
			platformHBox.getChildren().add(aPlatformTile);
		}

		// Create a last tile that is stretched to fit the platform as needed
		ImageView lastPlatformTile = new ImageView(platformImage);
		lastPlatformTile.setFitWidth(remainingTileWidth);
		lastPlatformTile.setFitHeight(height);
		platformHBox.getChildren().add(lastPlatformTile);

		// Set the platform coordinates and add it to the root/scene
		platformHBox.setTranslateX(xCoord);
		platformHBox.setTranslateY(yCoord);
		theRoot.getChildren().add(platformHBox);
	}

	/**
	 * The keyboard listener that is used for each of the levels. In other words,
	 * the method which sets what keyboard presses do and appropriately enacts them.
	 * 
	 * @param stage           - the stage of the javaFX application
	 * @param aScene          - the scene of the javaFX application
	 * @param aPlayer         - the player in the game
	 * @param playerImageView - the ImageView/GUI representation of the player
	 * @param enemyGUIMap     - the map the enemy should be added to, specifically,
	 *                        the map of enemies to their respective GUI/ImageView
	 *                        representations
	 */
	public void keyBoardMethod(Stage stage, Scene aScene, Player aPlayer, ImageView playerImageView,
			Map<EnemyGUI, ImageView> enemyMap) {
		aScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
					if (aPlayer.getWorld().isCollide(aPlayer, aPlayer.getWorld().getEndPoint())) {
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
				default:
					break;
				}
			}
		});

		aScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
				default:
					break;
				}
			}
		});
	}

	/**
	 * The main game animation / animation timer that constantly refreshes and
	 * updates the game (e.g. for user input or for computer automated events) and
	 * the GUI.
	 * 
	 * @param stage           - the stage of the javaFX application
	 * @param aScene          - the scene of the javaFX application
	 * @param aPlayer         - the player in the game
	 * @param playerImageView - the ImageView/GUI representation of the player
	 * @param enemyGUIMap     - the map the enemy should be added to, specifically,
	 *                        the map of enemies to their respective GUI/ImageView
	 *                        representations
	 */
	public void gameAnimation(Stage stage, Scene aScene, Player aPlayer, ImageView playerImageView,
			Map<EnemyGUI, ImageView> enemyMap) {

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				keyBoardMethod(stage, aScene, aPlayer, playerImageView, enemyMap);

				if (jump) {
					aPlayer.jump(Player.JUMPPOWER);
				}

				if (goLeft) {
					aPlayer.setxVelocity(Player.WALKSPEED*-1);
				} else if (goRight) {
					aPlayer.setxVelocity(Player.WALKSPEED);
				} else {
					aPlayer.setxVelocity(0);
				}
				

				aPlayer.update();
				if (aPlayer.getxVelocity() < 0) {
					playerImageView.setScaleX(-1);
				} else if (aPlayer.getxVelocity() > 0) {
					playerImageView.setScaleX(1);
				}
				playerImageView.setX(aPlayer.getxCoord());
				playerImageView.setY(aPlayer.getyCoord());

				if (!aPlayer.isAlive()) {
					aPlayer.setxCoord(0);
					aPlayer.setyCoord(0);
					playerImageView.setX(0);
					playerImageView.setY(0);
					aPlayer.revive();
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
					aPlayer.getWorld().isCollide(aPlayer, enemy);

//					if(!player1.getWorld().getEnemyList().contains(enemy)) {
//						
//					}
				}
			}
		};
		timer.start();
	}

	/**
	 * The start method of the javaFX application which opens the javaFX application
	 * and controls which scene is displayed on the stage.
	 * 
	 * @param primaryStage - the stage to open in the javaFX application
	 */
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
				if (currentLevel == 1) {
					level1.create(primaryStage, mainPlayer);
					this.start();
				} else if (currentLevel == 2) {
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