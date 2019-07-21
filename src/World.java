
public class World {

	private int worldMaxXCoord = 20;
	private int worldMaxYCoord = 20;
	
	private int finalDestinationXCoord = 20;
	private int finalDestinationYCoord = 20;
	
	/* 
	 	Additional TODO for Pawan:
	 	- javadoc the class / any methods made
	 	- make getters/setters (for worldMaxXCoord and worldMaxYCoord, any other variables)
	 		Note: even though we decided 20 x 20 for the first demo, I think it'd be
	 		prudent to make it a variable instead of a constant for larger future maps.
	 		Also, I originally named these worldWidth and worldHeight but that's confusing
	 		because it makes indexing any arrays we make difficult
	 	- constructors
	 	- add final destination (place where you reach to either win the game/go to the next world)
	 	- make an array which contains the enemies (listOfEnemies)
	 	- make a method that checks whether the player (param) is at the final destination and return boolean
	*/
	
	
	/**
	 * Draws the world (player, enemies, final destination)
	 * in the text-based version of our game.
	 *  
	 * @param player - The main character/player of the game (to be drawn).
	 */
	public void drawWorld(Player player) {
		
		int playerXCoord = player.getXCoord();
		int playerYCoord = player.getYCoord();
		
		ArrayList<String> allWorldContents = new ArrayList<String>();

	    // The character array for the world
	    char[][] allWorldContents = new char[worldMaxXCoord+1][worldMaxYCoord+1];
	    
	    // Initializing the world with space char
	    for (int i = 0; i < worldMaxXCoord; i++) {
			for(int j = 0; j < worldMaxYCoord; j++) {
				allWorldContents[i][j] = ' ';
			}
		}
	    
	    // Add the player to the world
	    allWorldContents[playerXCoord][playerYCoord] = '>';
	    
	    // Add the final destination to the world
	    allWorldContents[finalDestinationXCoord][finalDestinationYCoord] = 'O';
	    
	    // Add enemies to the world
	    // TODO: check if this works with Pawan's array
	    for (Enemy anEnemy : listOfEnemies) {
	    	allWorldContents[anEnemy.getXCoord()][anEnemy.getYCoord()] = 'E';
	    }
	    
	    // Make the top and bottom borders. 
	    String horizontalBorder = "##";
	    for(int i = 0; i < worldMaxYCoord; i++) {
	    	horizontalBorder += "#";
	    }
	    
	    // Print the world and its borders. 
	    System.out.println(horizontalBorder);
	    for(int i = 0; i < worldMaxYCoord; i++){
	    	String worldContentsString = "#";
	        for(int j = 0; j < worldMaxXCoord; j++){
	            worldContentsString += allWorldContents[i][j];
	        }
	        worldContentsString += "#";
	        System.out.println(worldContentsString);
	    }
	    System.out.println(horizontalBorder);
	}
	
}
