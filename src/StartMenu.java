import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class which creates the Start Menu for the game.
 *
 */

public class StartMenu extends GameplayGUI {
	private static final int WORLDWIDTH = 800;
	private static final int WORLDHEIGHT = 500;

	/**
	 * The method which creates the start menu for the game.
	 * 
	 * @param stage - the stage of the javaFX application
	 */
	public void create(Stage stage) {

		// Reset which level the game is currently in. The start menu is considered
		// to be "level 0". The previous level is also reset so that the game can be
		// "reset" or played again from reset.
		GameplayGUI.currentLevel = 0;
		GameplayGUI.previousLevel = 0;

		// Load the font for the title text
		Font jellyCraziesFont = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 50);

		// Create the root and set the scene
		Pane root = new Pane();
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT);
		
		// Create the background
		createBackground(root, "layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "layer-3-ground.png", 800, 500, 0, 0);
		
		// Make the game title
		Text gameTitle = new Text(90, 180, "JUMPY MAN");
		gameTitle.setFont(jellyCraziesFont);
		gameTitle.setFill(Color.BLACK);
		root.getChildren().add(gameTitle);

		// Create the start button
		Button startButton = new Button();
		startButton.setStyle("-fx-background-image: url('redStartButtom.png')");
		startButton.setMinSize(250, 75);

		// Create the event for the button click
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameplayGUI.currentLevel += 1;
			}
		};

		// Set the button click event and then set the button onto the scene
		startButton.setOnAction(event);
		startButton.setLayoutX(275);
		startButton.setLayoutY(275);
		root.getChildren().add(startButton);

		// Set the scene
		stage.setScene(scene);
		stage.show();
	}

	public void handle(ActionEvent event) {
		System.out.println("clicked");
	}
}
