/**
 * Level Two of our classic platformer game.
 * 
 * This class contains the code needed to generate the second level.
 * This includes level size as well as player and enemy generation.
 * The play method allows the player to interact with the world.
 * 
 * The level is smaller (size) than level one but the same amount of enemies, 
 * making it harder. 
 * 
 */

public class LevelTwo extends World {

	private final int LEVELWORLDMAXXCOORD = 10;
	private final int LEVELWORLDMAXYCOORD = 1;

	private final int LEVELSTARTXCOORD = 2;
	private final int LEVELSTARTYCOORD = 0;

	private final int LEVELGOALXCOORD = 8;
	private final int LEVELGOALYCOORD = 0;

	private boolean levelWin;
	
	
	public LevelTwo() {
	}

	public LevelTwo(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX, int playerFinalY) {
		super(worldMaxX, worldMaxY, playerStartX, playerStartY, playerFinalX, playerFinalY);
	}

	
	/**
	 * The playLevelTwo method allows the main player to interact in the level one world.
	 * The method draws out the world and continuously ask the player for input until
	 * the player has won (reached the final destination) or the player has lost (been killed).
	 * 
	 * @return levelWin - The boolean which returns true if the level has been won, false otherwise. 
	 */
	public boolean playLevelTwo() {

		LevelTwo level = new LevelTwo(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD,
				LEVELGOALXCOORD, LEVELGOALYCOORD);

		
		Enemy enemy1 = new Enemy(level);
		Enemy enemy2 = new Enemy(level);
		Enemy enemy3 = new Enemy(level);
		
		addToListOfEnemies(enemy1);
		addToListOfEnemies(enemy2);
		addToListOfEnemies(enemy3);
		
		Player mainPlayer = new Player(this, 5, 20, 20);

		// The loop which "plays" the game (i.e. draws then asks for user input)
		// Until the player dies or has reached the goal destination
		while (mainPlayer.isAlive() && !level.isPlayerAtGoal(mainPlayer) ) {
			super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);
			mainPlayer.askUserInstruction();
		}
		
		// After the loop for level play ends, check if player won/is still alive
		if (mainPlayer.isAlive()){
			levelWin = true;
		} else {
			levelWin = false;
		}
		
		// Draw the world a final time before ending the level
		super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);
		
		return levelWin;
			
	}

}


