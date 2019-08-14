package textbased;
import backend.World;
import entity.fixed.EndPoint;
import entity.moveable.Enemy;
import entity.moveable.Player;

/**
 * Level One of our classic platformer game.
 * 
 * This class contains the code needed to generate the first level. This
 * includes level size as well as player and enemy generation. The play method
 * allows the player to interact with the world.
 * 
 */


public class LevelOne extends Gameplay {

	private boolean levelWin;

	public LevelOne() {
	}

	public LevelOne(int worldMaxX, int worldMaxY, int playerStartX, int playerStartY, int playerFinalX,
			int playerFinalY) {
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

		// Create the world level
		World levelOne = new World(20, 3, 0, 0);

		// Add the end point for the level
		EndPoint endPoint = new EndPoint(18,0,0,0);
		levelOne.setEndPoint(endPoint);
		
		// Create the player and add them to the level world
		Player player = new Player(0,0,0,0,levelOne);
		levelOne.addPlayer(player);

		// Add enemies
		Enemy enemy1 = new Enemy(levelOne, 0, 0);
		Enemy enemy2 = new Enemy(levelOne, 0, 0);
		Enemy enemy3 = new Enemy(levelOne, 0, 0);
		enemy1.move();
		enemy2.move();
		enemy3.move();
		levelOne.addToListOfEnemies(enemy1);
		levelOne.addToListOfEnemies(enemy2);
		levelOne.addToListOfEnemies(enemy3);

		// The loop which "plays" the game (i.e. draws then asks for user input)
		// Until the player dies or has reached the goal destination
		while (player.isAlive() && !levelOne.isCollide(player, endPoint)) {
			for(Enemy aEnemy: levelOne.getListOfEnemies()) {
				levelOne.isCollide(player, aEnemy);
			}
			super.drawWorld(player, levelOne);
			askUserInstruction(player);
		}

		// After the loop for level play ends, check if player won/is still alive
		if (player.isAlive()) {
			levelWin = true;
		} else {
			levelWin = false;
		}

		// Draw the world a final time before ending the level
		super.drawWorld(player, levelOne);

		return levelWin;

	}

}
