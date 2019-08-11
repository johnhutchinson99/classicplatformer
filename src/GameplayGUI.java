import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Gameplay GUI is the (super)class which controls the current scene/level of
 * the game and has the create methods used to easily create objects/their
 * graphics in a child level class.
 *
 */

public class GameplayGUI extends Application {

	// Font needs to be reloaded since font size is final
	// Make protected so children start/exit/etc menus can use the fonts
	protected Font jellyCraziesFontSize50 = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 50);
	protected Font jellyCraziesFontSize30 = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 30);
	protected Font jellyCraziesFontSize20 = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 20);
	protected Font unicornPopFontSize40 = Font.loadFont(getClass().getResourceAsStream("Unicorn Pop.ttf"), 40);	
	protected Font cartwheelFontSize35 = Font.loadFont(getClass().getResourceAsStream("Cartwheel.otf"), 35);

	private static final int SECONDSINAMILLISECOND = 1000;

	static int currentLevel = 0;
	static int previousLevel = 0;
	private static long timerStart; // time in milliseconds
	private static long currentTime; // time in milliseconds
	protected static int currentScore = 0;
	protected static int highScore = 0;

	private Text timerDisplay = new Text();
	private Text levelDisplay = new Text(currentLevel + "");
	private Text scoreDisplay = new Text(currentScore + "");

	private Player mainPlayer = new Player(35, 35, 35, 35);

	private boolean goLeft = false;
	private boolean goRight = false;
	private boolean jump = false;
	
	private boolean isSoundOn = false;

	/**
	 * Reset the game level to 0, i.e. the start menu.
	 */
	public void levelReset() {
		currentLevel = 0;
		previousLevel = 0;
	}

	/**
	 * Move on to the next level / increase the current level by one.
	 */
	public void nextLevel() {
		currentLevel++;
	}

	/**
	 * Move on to the desired level.
	 */
	public void setLevel(int level) {
		currentLevel = level;
	}

	/**
	 * Reset the current score of the game, if it's larger than the highest score, then set the highest score before resetting it. 
	 */
	public void resetScore() {
		if (currentScore > highScore) highScore = currentScore;
		currentScore = 0;
	}

	/**
	 * Creates rectangular menu buttons such as for start, credits, or exit buttons.
	 * 
	 * @param buttonText - the string of text the button will contain
	 */
	public Button createMenuButton(Scene aScene, String buttonText) {
		Button button = new Button();
		Label label = new Label(buttonText);
		label.setFont(cartwheelFontSize35);
		label.setTextFill(Color.GOLD);
		label.setStyle("-fx-effect: dropshadow( one-pass-box , black , 10 , 0.1 , 2 , 0 );");
		button.setGraphic(label);
		button.setStyle("-fx-background-image: url('redStartButtom.png');");
		button.setMinSize(250, 60);
		button.setOnMouseEntered(e -> {
			label.setTextFill(Color.LIGHTGOLDENRODYELLOW);
			aScene.setCursor(Cursor.HAND);
		});
		button.setOnMouseExited(e -> {
			label.setTextFill(Color.GOLD);
			aScene.setCursor(Cursor.DEFAULT);
		});

		return button;
	}

	/**
	 * Update the text of the level display
	 */
	public void updateScoreDisplay(Player player) {
		currentScore += player.getWorld().getCoinsCount();
		player.getWorld().resetCoinsCount();
		scoreDisplay.setText(currentScore + "");
		scoreDisplay.setFont(jellyCraziesFontSize20);
		scoreDisplay.setFill(Color.GHOSTWHITE);
		scoreDisplay.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ; ");
		if (currentScore > highScore) highScore = currentScore;
	}

	/**
	 * Create the level display and return it.
	 * 
	 * @return gameTimer - VBox which contains the level display
	 */
	public VBox createScoreDisplay() {

		VBox gameScore = new VBox();
		gameScore.setAlignment(Pos.CENTER);
		gameScore.setMinWidth(150);

		// Make the label "Level"
		Text scoreLabel = new Text("SCORE");
		scoreLabel.setFont(jellyCraziesFontSize20);
		scoreLabel.setFill(Color.GHOSTWHITE);
		scoreLabel.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ;");

		// Add the label and the level to the VBox
		gameScore.getChildren().add(scoreLabel);
		gameScore.getChildren().add(scoreDisplay);

		return gameScore;
	}

	/**
	 * Update the text of the level display
	 */
	public void updateLevelDisplay() {
		levelDisplay.setText(currentLevel + "");
		levelDisplay.setFont(jellyCraziesFontSize20);
		levelDisplay.setFill(Color.GHOSTWHITE);
		levelDisplay.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ; ");
	}

	/**
	 * Create the level display and return it.
	 * 
	 * @return gameTimer - VBox which contains the level display
	 */
	public VBox createLabelDisplay() {

		VBox gameLevel = new VBox();
		gameLevel.setAlignment(Pos.CENTER);
		gameLevel.setMinWidth(150);

		// Make the label "Level"
		Text levelLabel = new Text("LEVEL");
		levelLabel.setFont(jellyCraziesFontSize20);
		levelLabel.setFill(Color.GHOSTWHITE);
		levelLabel.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ;");

		// Add the label and the level to the VBox
		gameLevel.getChildren().add(levelLabel);
		gameLevel.getChildren().add(levelDisplay);

		return gameLevel;
	}

	/**
	 * Update the game timer text in the format of 00min:00sec. If they play for
	 * over over 1 hour then stop the timer and tell them to take a break.
	 */
	public Text updateTimer() {

		currentTime = System.currentTimeMillis();

		long seconds = ((currentTime - timerStart) / SECONDSINAMILLISECOND);

		String timeString = "";

		if (seconds <= 9)
			timeString += "0000" + seconds;
		else if (seconds <= 99)
			timeString += "000" + seconds;
		else if (seconds <= 999)
			timeString += "00" + seconds;
		else if (seconds <= 9999)
			timeString += "0" + seconds;
		else if (seconds <= 99999)
			timeString += seconds;
		else
			timeString += "99999";

		// Set the timer to be the timerString and set the formatting / font / colour
		timerDisplay.setText(timeString);
		timerDisplay.setFont(jellyCraziesFontSize20);
		timerDisplay.setFill(Color.GHOSTWHITE);
		timerDisplay.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ;");

		return timerDisplay;
	}

	/**
	 * Create the game timer and return it.
	 * 
	 * @return gameTimer - VBox which contains the timer
	 */
	public VBox createTimer() {

		VBox gameTimer = new VBox();
		gameTimer.setAlignment(Pos.CENTER);
		gameTimer.setMinWidth(150);

		// Make the label "Time"
		Text timeLabel = new Text("TIME");
		timeLabel.setFont(jellyCraziesFontSize20);
		timeLabel.setFill(Color.GHOSTWHITE);
		timeLabel.setStyle("-fx-stroke: black ;-fx-stroke-width: 1px ;");

		// Add the label and the time to the VBox
		gameTimer.getChildren().add(timeLabel);
		gameTimer.getChildren().add(timerDisplay);

		return gameTimer;
	}

	/**
	 * Creates the game display containing the level and the time
	 * 
	 * @param theRoot - the root/scene to add the display to
	 */
	public void createGameDisplay(Pane theRoot) {

		// Fiddle around with the HBox spacing until it looks nice
		HBox display = new HBox(165);

		updateLevelDisplay();
		updateTimer();

		display.getChildren().add(createLabelDisplay());
		display.getChildren().add(createScoreDisplay());
		display.getChildren().add(createTimer());

		display.setTranslateX(10);
		display.setTranslateY(5);
		theRoot.getChildren().add(display);
	}

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
	 * @param theRoot        - the root of the javaFX scene
	 * @param aWorld         - the level the enemy will belong to
	 * @param worldObjectMap - the map the enemy should be added to, specifically,
	 *                       the map of enemies to their respective GUI/ImageView
	 *                       representations
	 * @param xCoord         - the starting x coordinate of the enemy
	 * @param yCoord         - the starting y coordinate of the enemy
	 * @param minXPosition   - the min x coordinate the enemy will travel back and
	 *                       forth from
	 * @param maxXPosition   - the max x coordinate the enemy will travel back and
	 *                       forth from
	 * @param width          - the width of the enemy
	 * @param height         - the height of the enemy
	 * 
	 */
	public void createEnemyType1(Pane theRoot, World aWorld, Map<WorldObject, ImageView> worldObjectMap, int xCoord,
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
		worldObjectMap.put(enemy1, enemyImageView);
	}

	/**
	 * Creates an FlyingEnemy (an enemy which moves up and down in the y direction,
	 * within a specified min and max coordinate) instance and makes the respective
	 * GUI/ImageView representation.
	 * 
	 * @param theRoot        - the root of the javaFX scene
	 * @param aWorld         - the level the enemy will belong to
	 * @param worldObjectMap - the map the enemy should be added to, specifically,
	 *                       the map of enemies to their respective GUI/ImageView
	 *                       representations
	 * @param xCoord         - the starting x coordinate of the enemy
	 * @param yCoord         - the starting y coordinate of the enemy
	 * @param minYPosition   - the min y coordinate the enemy will travel back and
	 *                       forth from
	 * @param maxYPosition   - the max y coordinate the enemy will travel back and
	 *                       forth from
	 * @param width          - the width of the enemy
	 * @param height         - the height of the enemy
	 * 
	 */
	public void createFlyingEnemy(Pane theRoot, World aWorld, Map<WorldObject, ImageView> worldObjectMap, int xCoord,
			int yCoord, int minYPosition, int maxYPosition, int width, int height) {
		FlyingEnemy trap1 = new FlyingEnemy(aWorld, xCoord, yCoord, width, height, true, minYPosition, maxYPosition);
		Image img = new Image("Mace.png", false);
		ImageView enemyImageView = new ImageView(img);
		enemyImageView.setFitWidth(width);
		enemyImageView.setFitHeight(height);
		theRoot.getChildren().add(enemyImageView);
		enemyImageView.setX(xCoord);
		enemyImageView.setY(yCoord);

		aWorld.addEnemy(trap1);
		worldObjectMap.put(trap1, enemyImageView);
	}

	/**
	 * Creates an TrapType1 (an enemy which moves up and down in intervals,
	 * generally is hiding within a platform) instance and makes the respective
	 * GUI/ImageView representation.
	 * 
	 * @param theRoot        - the root of the javaFX scene
	 * @param aWorld         - the level the enemy will belong to
	 * @param worldObjectMap - the map the enemy should be added to, specifically,
	 *                       the map of enemies to their respective GUI/ImageView
	 *                       representations
	 * @param xCoord         - the starting x coordinate of the enemy
	 * @param yCoord         - the starting y coordinate of the enemy
	 * @param minYPosition   - the min y coordinate the enemy will travel back and
	 *                       forth from
	 * @param maxYPosition   - the max y coordinate the enemy will travel back and
	 *                       forth from
	 * @param width          - the width of the enemy
	 * @param height         - the height of the enemy
	 * 
	 */
	public void createTrapType1(Pane theRoot, World aWorld, Map<WorldObject, ImageView> worldObjectMap, int xCoord,
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
		System.out.println(aWorld.getListOfEnemies().size());
		worldObjectMap.put(trap1, enemyImageView);

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
	public void createCoin(Pane theRoot, World aWorld, Map<WorldObject, ImageView> worldObjectMap, int xCoord,
			int yCoord, int width, int height) {
		Coin coin = new Coin(xCoord, yCoord, width, height, aWorld);
		Image coinImage = new Image("smallerCoin.gif", false);
		ImageView coinTile = new ImageView(coinImage);
		coinTile.setFitWidth(width);
		coinTile.setFitHeight(height);

		theRoot.getChildren().add(coinTile);
		coinTile.setX(xCoord);
		coinTile.setY(yCoord);

		worldObjectMap.put(coin, coinTile);
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
	public void createPlatform(Pane theRoot, World aWorld, Map<Platform, HBox> aPlatformGUIMap, int xCoord, int yCoord,
			int width, int height) {

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

		// Add the platform to a map of its logic to its GUI representation
		aPlatformGUIMap.put(newPlatform, platformHBox);
	}

	/**
	 * The keyboard listener that is used for each of the levels. In other words,
	 * the method which sets what keyboard presses do and appropriately enacts them.
	 * 
	 * @param stage           - the stage of the javaFX application
	 * @param aScene          - the scene of the javaFX application
	 * @param aPlayer         - the player in the game
	 * @param playerImageView - the ImageView/GUI representation of the player
	 * @param worldObjectMap  - the map the enemy should be added to, specifically,
	 *                        the map of enemies to their respective GUI/ImageView
	 *                        representations
	 */
	public void keyBoardMethod(Stage stage, Scene aScene, Player aPlayer, ImageView playerImageView,
			Map<WorldObject, ImageView> worldObjectMap, Bullet bullet) {
		aScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:
					goLeft = true;
					System.out.println("KeyPress: L");
					break;
				case RIGHT:
					goRight = true;
					System.out.println("KeyPress: R");
					break;
				case SHIFT:
					jump = true;
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

				case F:
					bullet.fire();
				default:
					break;
				}
			}
		});

		// Set what the keys do on key release
		aScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:
					goLeft = false;
					System.out.println("KeyRelease: L");
					break;
				case RIGHT:
					goRight = false;
					System.out.println("KeyRelease: R");
					break;
				case SHIFT:
					jump = false;
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
	 * @param worldObjectMap  - the map the enemy should be added to, specifically,
	 *                        the map of enemies to their respective GUI/ImageView
	 *                        representations
	 */
	public void gameAnimation(Stage stage, Scene aScene, Player aPlayer, ImageView playerImageView,
			Map<WorldObject, ImageView> worldObjectMap, Map<Platform, HBox> aPlatformGUIMap, Bullet bullet,
			Rectangle bulletRect) {

		// Create the game camera and animation timer
		Camera gameCamera = new Camera(aScene, aPlayer);
//		createTimer((Pane) aScene.getRoot());
		createGameDisplay((Pane) aScene.getRoot());
		AnimationTimer timer = new AnimationTimer() {

			// Make the timer loop
			@Override
			public void handle(long now) {

				updateLevelDisplay();
				updateScoreDisplay(aPlayer);
				updateTimer();

				// Update the camera, timer/points/game display, and keyboard listener method
				gameCamera.updateCamera(aPlayer);
				keyBoardMethod(stage, aScene, aPlayer, playerImageView, worldObjectMap, bullet);

				// Respond to keyboard input if applicable
				if (jump) {
					aPlayer.jump(Player.JUMPPOWER);
				}

				if (goLeft) {
					aPlayer.setxVelocity(Player.WALKSPEED * -1);
					aPlayer.facingRight(false);
				} else if (goRight) {
					aPlayer.setxVelocity(Player.WALKSPEED);
					aPlayer.facingRight(true);
				} else {
					aPlayer.setxVelocity(0);
				}

				aPlayer.update();
				if (aPlayer.getxVelocity() < 0) {
					playerImageView.setScaleX(-1);
				} else if (aPlayer.getxVelocity() > 0) {
					playerImageView.setScaleX(1);
				}
				playerImageView.setX(aPlayer.getxCoord() + gameCamera.getOffsetX());
				playerImageView.setY(aPlayer.getyCoord() + gameCamera.getOffsetY());

				if (!aPlayer.isAlive()) {
					goLeft = false;
					goRight = false;
					jump = false;
					aPlayer.setXYCoord(-10, -10);
					aPlayer.revive();
					setLevel(1000);
				}

				for (Map.Entry<WorldObject, ImageView> entry : worldObjectMap.entrySet()) {
					WorldObject enemy = entry.getKey();
					ImageView enemyRectangle = entry.getValue();
					enemy.update();
					enemyRectangle.setX(enemy.getxCoord() + gameCamera.getOffsetX());
					enemyRectangle.setY(enemy.getyCoord() + gameCamera.getOffsetY());
					aPlayer.getWorld().isCollide(aPlayer, enemy);
					enemy.getWorld().isCollide(bullet, enemy);

					if (!enemy.isAlive()) {
						enemy.moveOffScreen();
					}

				}

				for (Map.Entry<Platform, HBox> entry : aPlatformGUIMap.entrySet()) {
					Platform platform = entry.getKey();
					HBox platformHBox = entry.getValue();
					platformHBox.setTranslateX(platform.getxCoord() + gameCamera.getOffsetX());
					platformHBox.setTranslateY(platform.getyCoord() + gameCamera.getOffsetY());
				}
				bullet.update();
				bulletRect.setX(bullet.getxCoord());
				bulletRect.setY(bullet.getyCoord());

			}
		};
		timer.start();
	}

	/**
	 * Create the level selector for the game which selects the level or scene (e.g.
	 * start menu, levels, game over, etc) to display.
	 */
	public void levelSelector(Stage primaryStage) {

		// Create instances of each of the level
		StartMenu start = new StartMenu();
		GameOverGUI over = new GameOverGUI();
		Credits credits = new Credits();

		// Start off by showing the start menu and making the stage non-resizeable
		start.create(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();

		// Use an animation timer to constantly check/refresh what level the game should
		// be displaying or be in
		AnimationTimer refreshLevel = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// change to the next level and update what the previous level is
				if (currentLevel != previousLevel) {
					previousLevel = currentLevel;
					this.stop();
					updateLevelGUI();
				}

			}

			// update the GUI based on the what the current level should be
			private void updateLevelGUI() {
				if (currentLevel == 0) {
					start.create(primaryStage);
					this.start();
				} else if (currentLevel == 1) {
					timerStart = System.currentTimeMillis();
					LevelOneGUI level1 = new LevelOneGUI();
					level1.create(primaryStage, mainPlayer);
					this.start();
				} else if (currentLevel == 2) {
					LevelTwoGUI level2 = new LevelTwoGUI();
					level2.create(primaryStage, mainPlayer);
					this.start();
				} else if (currentLevel == 99) {
					credits.create(primaryStage);
					this.start();
				} else {
					over.create(primaryStage);
					this.start();
				}

			}
		};
		refreshLevel.start();
	}

	/**
	 * The start method of the javaFX application which opens the javaFX application
	 * and controls which scene is displayed on the stage.
	 * 
	 * @param primaryStage - the stage to open in the javaFX application
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Jumpy Man");
		primaryStage.getIcons().add(new Image("giphy.gif"));
		levelSelector(primaryStage);
	}

	/**
	 * The main method of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(GameplayGUI.class, args);
	}
}