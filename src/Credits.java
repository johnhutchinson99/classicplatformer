import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The class which creates the Game Over menu for the game.
 *
 */
public class Credits extends GameplayGUI{
	
	/**
	 * The method which creates the game over menu for the game.
	 * 
	 * @param stage - the stage of the javaFX application
	 */
	public void create(Stage stage) {	

		// Create the root and set the scene
		Pane root = new Pane();
		Scene scene = new Scene(root, APPLICATIONWIDTH, APPLICATIONHEIGHT);
		
		// Create the background
		createBackground(root, "layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "layer-3-ground.png", 800, 500, 0, 0);
		
		// Make the game title
		Text gameOverText = new Text(80, 150,"CREDITS");
		gameOverText.setFont(jellyCraziesFontSize30);
		gameOverText.setFill(Color.GHOSTWHITE);
		gameOverText.setStyle("-fx-stroke: black ;-fx-stroke-width: 2px ;");
		root.getChildren().add(gameOverText);

		
		// Print out the score the player got
		Text scoreText = new Text(110, 230,"GAO XINGYU\nJOHN HUTCHINSON\nMICHELLE NGUYEN");
		scoreText.setFont(unicornPopFontSize40);
		scoreText.setFill(Color.GHOSTWHITE);
		scoreText.setStyle("-fx-stroke: black ;-fx-stroke-width: 2px ;");
		root.getChildren().add(scoreText);
		
		// Create the buttons
		Button startButton = createMenuButton(scene, "START MENU");
		Button exitButton = createMenuButton(scene, "EXIT");
		
		// Create the event for the start button click
		EventHandler<ActionEvent> startButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentLevel = 0;
			}
		};
		
		// Create the event for the button click
		EventHandler<ActionEvent> exitButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		};
		
		
		// Set the button click event and then set the button onto the scene
		startButton.setOnAction(startButtonEvent);
		exitButton.setOnAction(exitButtonEvent);
		
		// Add the buttons to a VBox, and then add the VBox to the scene
		VBox buttonsVBox = new VBox(10);
		buttonsVBox.getChildren().add(startButton);
		buttonsVBox.getChildren().add(exitButton);
		buttonsVBox.setLayoutX(500);
		buttonsVBox.setLayoutY(280);
		root.getChildren().add(buttonsVBox);

		// Set the scene
		stage.setScene(scene);
		stage.show();
	}
}