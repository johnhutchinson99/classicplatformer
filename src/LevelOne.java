
public class LevelOne extends World {

	private final int LEVELWORLDMAXXCOORD = 9;
	private final int LEVELWORLDMAXYCOORD = 5;

	private final int LEVELSTARTXCOORD = 0;
	private final int LEVELSTARTYCOORD = 0;

	private final int LEVELGOALXCOORD = 9;
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
		
		addToListOfEnemies(enemy1);
		addToListOfEnemies(enemy2);
		
			System.out.println(enemy1.toString());
		
		
		Player mainPlayer = new Player(level, 5);

		
		System.out.print(mainPlayer.getYCoord());

		while (mainPlayer.isAlive()) {
			super.drawWorld(mainPlayer, LEVELWORLDMAXXCOORD, LEVELWORLDMAXYCOORD, LEVELGOALXCOORD, LEVELGOALYCOORD);
			mainPlayer.askUserInstruction();
			
			
		}
	}

}


