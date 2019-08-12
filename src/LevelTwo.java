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

public class LevelTwo extends Gameplay {

	private boolean levelWin;

	public LevelTwo() {
	}

	public LevelTwo(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX,
			int playerFinalY) {
	}

	/**
	 * The playLevelTwo method allows the main player to interact in the level one
	 * world. The method draws out the world and continuously ask the player for
	 * input until the player has won (reached the final destination) or the player
	 * has lost (been killed).
	 * 
	 * @return levelWin - The boolean which returns true if the level has been won,
	 *         false otherwise.
	 */
	public boolean playLevelTwo() {

		// Create the world level
		World levelTwo = new World(14, 3, 0, 0);

		// Add the end point for the level
		EndPoint endPoint = new EndPoint(12,0,0,0);
		levelTwo.setEndPoint(endPoint);
		
		// Create the player and add them to the level world
		Player player = new Player(0,0,0,0,levelTwo);
		levelTwo.addPlayer(player);

		// Add enemies
		Enemy enemy1 = new Enemy(levelTwo, 0, 0);
		Enemy enemy2 = new Enemy(levelTwo, 0, 0);
		Enemy enemy3 = new Enemy(levelTwo, 0, 0);
		enemy1.move();
		enemy2.move();
		enemy3.move();
		levelTwo.addToListOfEnemies(enemy1);
		levelTwo.addToListOfEnemies(enemy2);
		levelTwo.addToListOfEnemies(enemy3);

		// The loop which "plays" the game (i.e. draws then asks for user input)
		// Until the player dies or has reached the goal destination
		while (player.isAlive() && !levelTwo.isCollide(player, endPoint)) {
			for(Enemy aEnemy: levelTwo.getListOfEnemies()) {
				levelTwo.isCollide(player, aEnemy);
			}
			super.drawWorld(player, levelTwo);
			askUserInstruction(player);
		}

		// After the loop for level play ends, check if player won/is still alive
		if (player.isAlive()) {
			levelWin = true;
		} else {
			levelWin = false;
		}

		// Draw the world a final time before ending the level
		super.drawWorld(player, levelTwo);

		return levelWin;

	}

}
