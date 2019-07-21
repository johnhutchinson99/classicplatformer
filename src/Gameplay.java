/**
 	The Gameplay class contains the main method which runs the game.
 		Here, instances of player, enemies, and world are created.
 		In this text-based version, the draw method from the world
 		class is called upon to show the world. 
 
 */

import java.util.Scanner;


public class Gameplay {
	
	/**
	 * Delays the next step of the game by the given time in miliseconds. 
	 * @param miliseconds - the time in miliseconds to delay next step by. 
	 */
	public void delayGame(int miliseconds) {
		try
		{
		    Thread.sleep(miliseconds);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Prints the instructions for the game. 
	 */
	public void printInstructions(){
		System.out.println(
				"INSTRUCTIONS: \n" +
				"This is the text-based version of our CPSC 233 game (title TBA).\n" +
				"In this platformer-like game, the goal is to get your character (>) to\n"+
				"  reach a final destination (O) in the map, avoiding enemies. \n" +
				"The symbol > represents your character and it's direction.\n" +
				"The symbol O represents the goal destination.\n" +
				"The symbol E represents an enemy.\n" +
				"If you touch an enemy before reaching the final destination, \n" +
				"  then you die/lose.\n" +
				"At each time, you will have the options to move left/right, \n"+
				"  jump, or kill an enemy in a surrounding square. \n" + 
				"Good luck.\n "
				
				);
	}

	public static void main(String[] args) {
		
		Gameplay game = new Gameplay();
		
		// Game Intro / Instructions
		System.out.println("Welcome to The-Game-That-Does-Not-Have-a-Name-Yet! \n");
		game.delayGame(1000);
		game.printInstructions();
		game.delayGame(1000);
		
		// Ask for player name (to be utilized for later story-mode)
		System.out.print("To begin, please enter your name: ");
		Scanner keyboardInput = new Scanner(System.in);
		String name = keyboardInput.next();
		
		// Print or announce gameplay begins
		System.out.print("Let the game begin!");
		
		// Loop while game active / alive
			// Draw world
		
			// Check colliding somewhere in here... 
				// need a way to prevent starting where enemies
			// Prompt for user input
			
		
		
		// Game over (lose or win)
		
	}

}
