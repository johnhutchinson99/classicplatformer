import java.util.ArrayList;

/**
 * Level One of the GUI version of our classic platformer game.
 * 
 * This class contains the code needed to generate the first level in our GUI
 * version. This includes level size as well as player and enemy generation. The
 * play method allows the player to interact with the world.
 * 
 */


public class LevelOneGUI extends World {

	private final int LEVELWORLDMAXXCOORD = 1000;
	private final int LEVELWORLDMAXYCOORD = 500;

	private final int LEVELSTARTXCOORD = 0;
	private final int LEVELSTARTYCOORD = 0;

	private final int LEVELGOALXCOORD = 16;
	private final int LEVELGOALYCOORD = 0;
	

	private boolean levelWin;

	public LevelOneGUI() {
	}

	public LevelOneGUI(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX, int playerFinalY) {
		super(worldMaxX, worldMaxY, playerStartX, playerStartY, playerFinalX, playerFinalY);
	}

	/**
	 * The playLevelOne method allows the main player to interact in the level one
	 * world. The method draws out the world and continuously ask the player for
	 * input until the player has won (reached the final destination) or the player
	 * has lost (been killed).
	 * 
	 * @return levelWin - The boolean which returns true if the level has been won,
	 *         false otherwise.
	 */
	public boolean playLevelOne() {

		LevelOne level = new LevelOne(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD,
				LEVELGOALXCOORD, LEVELGOALYCOORD);

		Enemy enemy1 = new Enemy(level);
		Enemy enemy2 = new Enemy(level);
		Enemy enemy3 = new Enemy(level);

		addToListOfEnemies(enemy1);
		addToListOfEnemies(enemy2);
		addToListOfEnemies(enemy3);
		
		createPlatform(500, 100, 100, 10); // TODO: Get rid of magic numbers?

		player = new Player(this, 5);
		player.setXCoord(400);
		player.setYCoord(LEVELSTARTYCOORD);
		
		ArrayList<String> platforms = level.getPlatformCoordinates();

		// This will update the enemy position while the game is running
		while (getPlayer().isAlive() && !level.isPlayerAtGoal(getPlayer())) {
			enemy1.move();
			enemy2.move();
			enemy3.move();
			
		//	int xCoord // = input from GUI or player move;
		//	int yCoord // = input from GUI or player move;
			

				getPlayer().update();
			//TODO player input and movement
		}

		// After the loop for level play ends, check if player won/is still alive
		if (getPlayer().isAlive()) {
			levelWin = true;
		} else {
			levelWin = false;
		}

		// Draw the world a final time before ending the level

		return levelWin;

	}

}
