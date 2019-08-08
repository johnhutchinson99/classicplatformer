/**
 * Level One of our classic platformer game.
 * 
 * This class contains the code needed to generate the first level. This
 * includes level size as well as player and enemy generation. The play method
 * allows the player to interact with the world.
 * 
 */

// TODO make instances private?

public class LevelOne extends World {

//	private static final int LEVELWORLDMAXXCOORD = 0;
	private static final int LEVELWORLDMAXXCOORD = 16;
	private static final int LEVELWORLDMAXYCOORD = 1;

	private static final int LEVELSTARTXCOORD = 0;
	private static final int LEVELSTARTYCOORD = 0;

	private static final int LEVELGOALXCOORD = 16;
	private static final int LEVELGOALYCOORD = 0;

	private boolean levelWin;

	public LevelOne() {
		super(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD, LEVELGOALXCOORD,
				LEVELGOALYCOORD, false);
	}

	public LevelOne(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX,
			int playerFinalY) {
		super(worldMaxX, worldMaxY, playerStartX, playerStartY, playerFinalX, playerFinalY, false);
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

//		LevelOne level = new LevelOne(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD,
//				LEVELGOALXCOORD, LEVELGOALYCOORD);

		Player mainPlayer = new Player(this, 5, 20, 20);

		setPlayer(mainPlayer);

		Enemy enemy1 = new Enemy(this, 1, 1);
		Enemy enemy2 = new Enemy(this, 1, 1);
		Enemy enemy3 = new Enemy(this, 1, 1);
		enemy1.move();
		enemy2.move();
		enemy3.move();
		addToListOfEnemies(enemy1);
		addToListOfEnemies(enemy2);
		addToListOfEnemies(enemy3);

		// The loop which "plays" the game (i.e. draws then asks for user input)
		// Until the player dies or has reached the goal destination
		while (mainPlayer.isAlive() && !this.isPlayerAtGoal(mainPlayer)) {
			System.out.println(enemy1);
			super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);
			mainPlayer.askUserInstruction();
		}

		// After the loop for level play ends, check if player won/is still alive
		if (mainPlayer.isAlive()) {
			levelWin = true;
		} else {
			levelWin = false;
		}

		// Draw the world a final time before ending the level
		super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);

		return levelWin;

	}

}
