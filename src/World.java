import java.util.ArrayList;

public class World {
	
	private int worldMaxXCoord;
	private int worldMaxYCoord;
	
	private int startXCoord;
	private int startYCoord;
	
	private int goalXCoord;
	private int goalYCoord;
	
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	
	
	
	/* 
	 	Additional TODO:
	 	- javadoc the class / any methods made
	 	- make an array which contains the enemies (listOfEnemies)
	 	- make a method that checks whether the player (param) is at the final destination and return boolean
	 	- split the draw method
	 	
	*/
	
	public World() {
		
	}
	
	public World(int maxX, int maxY, int startX, int startY, int finalX, int finalY) {
		worldMaxXCoord = maxX;
		worldMaxYCoord = maxY;
		startXCoord = startX;
		startYCoord = startY;
		goalXCoord = finalX;
		goalYCoord = finalY;
	}
	
	public ArrayList<Enemy> getListOfEnemies() {
		return listOfEnemies;
	}
	
	public int getWorldMaxXCoord() {
		return worldMaxXCoord;
	}

	public void setWorldMaxXCoord(int worldMaxXCoord) {
		this.worldMaxXCoord = worldMaxXCoord;
	}

	public int getWorldMaxYCoord() {
		return worldMaxYCoord;
	}

	public void setWorldMaxYCoord(int worldMaxYCoord) {
		this.worldMaxYCoord = worldMaxYCoord;
	}
	
	public int getStartXCoord() {
		return startXCoord;
	}

	public void setStartXCoord(int startXCoord) {
		this.startXCoord = startXCoord;
	}

	public int getStartYCoord() {
		return startYCoord;
	}

	public void setStartYCoord(int startYCoord) {
		this.startYCoord = startYCoord;
	}
	
	public int getGoalXCoord() {
		return goalXCoord;
	}

	public void setGoalXCoord(int goalXCoord) {
		this.goalXCoord = goalXCoord;
	}

	public int getGoalYCoord() {
		return goalYCoord;
	}

	public void setGoalYCoord(int goalYCoord) {
		this.goalYCoord = goalYCoord;
	}


	/**
	 * Draws the world (player, enemies, final destination)
	 * in the text-based version of our game.
	 *  
	 * @param player - The main character/player of the game (to be drawn).
	 */
	public void drawWorld(Player player, int worldMaxXCoord, int worldMaxYCoord, int goalXCoord, int goalYCoord) {
		/*
		 * Once we get everything running, I'm going to split this into 2 methods:
		 * an updateWorldContents method and a drawWorld method 
		 */
		int playerXCoord = player.getXCoord();
		int playerYCoord = player.getYCoord();
		
	    // The character array for the world
	    char[][] allWorldContents = new char[worldMaxYCoord+1][worldMaxXCoord+1];
	    
	    // Initializing the world with space char
	    for (int i = 0; i < worldMaxYCoord; i++) {
			for(int j = 0; j < worldMaxXCoord; j++) {
				allWorldContents[i][j] = ' ';
			}
		}
	    
	    // Add the player to the world
	    allWorldContents[playerYCoord][playerXCoord] = '>';
	    
	    // Add the final destination to the world
	    allWorldContents[goalYCoord][goalXCoord] = 'O';
	    
	    // Add enemies to the world
	    // TODO: check if this works with Pawan's array
//	    for (Enemy anEnemy : listOfEnemies) {
//	    	allWorldContents[anEnemy.getYCoord()][anEnemy.getXCoord()] = 'E';
//	    }
	    
	    // Make the top and bottom borders. 
	    String horizontalBorder = "  #";
	    for(int i = 0; i <= worldMaxXCoord; i++) {
	    	horizontalBorder += "|#";
	    }
	    horizontalBorder += "|#";
	    
	    // Print the world and its borders. 
	    System.out.println("\n");
	    System.out.println(horizontalBorder);
	    for(int i = (worldMaxYCoord); i >= 0; i--){
	    	String worldContentsString = i+" #";
	        for(int j = 0; j <= worldMaxXCoord; j++){
	            worldContentsString += "|"+allWorldContents[i][j];
	        }
	        worldContentsString += "|#";
	        System.out.println(worldContentsString);
	    }
	    System.out.println(horizontalBorder);
	    horizontalBorder = "    ";
	    for(int i = 0; i <= worldMaxXCoord; i++) {
	    	horizontalBorder += i + " ";
	    }
	    System.out.println(horizontalBorder);
	}


	
}
