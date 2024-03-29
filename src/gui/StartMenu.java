package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class which creates the Start Menu for the game.
 *
 */

public class StartMenu extends GameplayGUI {

	/**
	 * The method which creates the start menu for the game.
	 * 
	 * @param stage - the stage of the javaFX application
	 */
	public void create(Stage stage) {

		// Reset which level the game is currently in. The start menu is considered
		// to be "level 0". The previous level is also reset so that the game can be
		// "reset" or played again from reset.
		currentLevel = 0;
		previousLevel = 0;

		// Create the root and set the scene
		Pane root = new Pane();
		Scene scene = new Scene(root, APPLICATIONWIDTH, APPLICATIONHEIGHT);

		// Create the background
		createBackground(root, "/assets/image/layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "/assets/image/layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "/assets/image/layer-3-ground.png", 800, 500, 0, 0);

		// Make the game title
		Text gameTitle = new Text(90, 120, "JUMPY MAN");
		gameTitle.setFont(jellyCraziesFontSize50);
		gameTitle.setFill(Color.GHOSTWHITE);
		gameTitle.setStyle("-fx-stroke: black ;-fx-stroke-width: 2px ;");
		root.getChildren().add(gameTitle);

		// Add high score text
		Label highScoreText = new Label("HIGH SCORE: " + highScore);
		highScoreText.setFont(cartwheelFontSize35);
		highScoreText.setTextFill(Color.LIGHTGOLDENRODYELLOW);
		highScoreText.setStyle("-fx-effect: dropshadow( one-pass-box , black , 12 , 0.2 , 2 , 0 );");
		highScoreText.setAlignment(Pos.CENTER);
		highScoreText.setMinWidth(300);
		highScoreText.setTranslateX(250);
		highScoreText.setLayoutY(185);
		root.getChildren().add(highScoreText);

		// Create the buttons
		Button startButton = createMenuButton(scene, "START");
		Button creditsButton = createMenuButton(scene, "CREDITS");
		Button exitButton = createMenuButton(scene, "EXIT");

		// Create the event for the start button click
		EventHandler<ActionEvent> startButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentLevel++;
			}
		};

		// Create the event for the button click
		EventHandler<ActionEvent> creditsButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				currentLevel = 99;
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
		creditsButton.setOnAction(creditsButtonEvent);
		exitButton.setOnAction(exitButtonEvent);

		// Add the buttons to a VBox, and then add the VBox to the scene
		VBox buttonsVBox = new VBox(10);
		buttonsVBox.getChildren().add(startButton);
		buttonsVBox.getChildren().add(creditsButton);
		buttonsVBox.getChildren().add(exitButton);
		buttonsVBox.setLayoutX(275);
		buttonsVBox.setLayoutY(245);
		root.getChildren().add(buttonsVBox);

		// Set the scene
		stage.setScene(scene);
		stage.show();
	}

}
