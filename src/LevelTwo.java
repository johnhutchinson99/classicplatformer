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

	private static final int LEVELWORLDMAXXCOORD = 10;
	private static final int LEVELWORLDMAXYCOORD = 1;

	private static final int LEVELSTARTXCOORD = 2;
	private static final int LEVELSTARTYCOORD = 0;

	private static final int LEVELGOALXCOORD = 10;
	private static final int LEVELGOALYCOORD = 0;

	private boolean levelWin;
	
	
	public LevelTwo() {
		super(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD, LEVELGOALXCOORD,
				LEVELGOALYCOORD, false);
		
	}

	public LevelTwo(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX, int playerFinalY) {
		super(worldMaxX, worldMaxY, playerStartX, playerStartY, playerFinalX, playerFinalY,false);
	}

	
	/**
	 * The playLevelTwo method allows the main player to interact in the level one world.
	 * The method draws out the world and continuously ask the player for input until
	 * the player has won (reached the final destination) or the player has lost (been killed).
	 * 
	 * @return levelWin - The boolean which returns true if the level has been won, false otherwise. 
	 */
	public boolean playLevelTwo() {


		Player mainPlayer = new Player(this, 5, 20, 20);
		setPlayer(mainPlayer);
		
		Enemy enemy1 = new Enemy(this,1,1);

		
		addToListOfEnemies(enemy1);
		
		

		// The loop which "plays" the game (i.e. draws then asks for user input)
		// Until the player dies or has reached the goal destination
		System.out.println(isPlayerAtGoal(mainPlayer));
		while (mainPlayer.isAlive() && !isPlayerAtGoal(mainPlayer) ) {
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


