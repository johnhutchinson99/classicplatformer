import java.util.ArrayList;

public class World {
	
	private int worldMaxXCoord;
	private int worldMaxYCoord;
	
	private int startXCoord;
	private int startYCoord;
	
	private int goalXCoord;
	private int goalYCoord;
	
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	
	
	
	
	 	
	
	
	
	
	/**
	 * This class contains information about the current world
	 * This class stores important data such as the maximum X and Y of a level, the starting position of a player and the location of the goal. 
	 	This class also stores enemies in an arraylist.
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
	
	public void addToListOfEnemies(Enemy anEnemy) {
		listOfEnemies.add(anEnemy);
	}
	public void removeFromListOfEnemies(Enemy anEnemy) {
		listOfEnemies.remove(anEnemy);
	}
	
	public boolean isPlayerAtGoal(Player player) {
		if (player.getXCoord() == goalXCoord && player.getYCoord() == goalYCoord) {
			return true;
		} else {
			return false;
		}
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
		 * an updateWorldContents method and a drawWorld method.
		 * 
		 * Also, this can only take world sizes up to 9x9 since the
		 * axis will display differently otherwise.  
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
	    for (Enemy anEnemy : listOfEnemies) {
	    	allWorldContents[anEnemy.getYCoord()][anEnemy.getXCoord()] = 'E';
	    }
	    
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
