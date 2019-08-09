import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The class which creates the Game Over menu for the game.
 *
 */
public class GameOverGUI extends GameplayGUI{
	
	private static final int WORLDWIDTH = 800; 
	private static final int WORLDHEIGHT = 500;
	
	/**
	 * The method which creates the game over menu for the game.
	 * 
	 * @param stage - the stage of the javaFX application
	 */
	public void create(Stage stage, Player aPlayer) {	

		// Create the root and the scene
		StackPane root = new StackPane();
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT);
		
		// Create the background
		createBackground(root, "layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "layer-4.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "layer-3-ground.png", 800, 500, 0, 0);
		
		// Load the font for the game over message
		Font jellyCraziesFont = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 35);
		
		// Create the gameover message
		Text gameover = new Text("GAME OVER");
		gameover.setFont(jellyCraziesFont);
		root.getChildren().add(gameover);
		gameover.setTextAlignment(TextAlignment.CENTER);

		// Set the scene and show it
		stage.setScene(scene);
		stage.show();
	}
}