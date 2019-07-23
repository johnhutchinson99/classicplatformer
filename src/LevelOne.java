/**
 * 
 * 
 * 
 * This class contains the code needed to generate the first level.
 * This includes level size as well as player and enemy generation.
 *
 */






public class LevelOne extends World {

	private final int LEVELWORLDMAXXCOORD = 16;
	private final int LEVELWORLDMAXYCOORD = 1;

	private final int LEVELSTARTXCOORD = 0;
	private final int LEVELSTARTYCOORD = 0;

	private final int LEVELGOALXCOORD = 16;
	private final int LEVELGOALYCOORD = 0;

	public LevelOne() {
	}

	public LevelOne(int maxX, int maxY, int startX, int startY, int finalX, int finalY) {
		super(maxX, maxY, startX, startY, finalX, finalY);
	}

	public void playLevelOne() {

		LevelOne level = new LevelOne(LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELSTARTXCOORD, LEVELSTARTYCOORD,
				LEVELGOALXCOORD, LEVELGOALYCOORD);

		
		Enemy enemy1 = new Enemy(level);
		Enemy enemy2 = new Enemy(level);
		Enemy enemy3 = new Enemy(level);
		
		addToListOfEnemies(enemy1);
		addToListOfEnemies(enemy2);
		addToListOfEnemies(enemy3);
		
		
		
		Player mainPlayer = new Player(this, 5);


		
		System.out.print(mainPlayer.getYCoord());
		while (mainPlayer.isAlive()) {
			super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);
			mainPlayer.askUserInstruction();
			
			
			if(mainPlayer.getXCoord()==LEVELGOALXCOORD&&mainPlayer.getYCoord()==mainPlayer.getYCoord()) {
				System.out.println("CONGRATULATIONS! YOU HAVE WON!");
				return;
			}
			
		}
		
		
		System.out.println("YOU HAVE DIED. GAME OVER");
		
	}

}


