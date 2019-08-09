import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartMenu extends GameplayGUI {
	private static final int WORLDWIDTH = 800;
	private static final int WORLDHEIGHT = 500;

	public void create(Stage stage) {

		GameplayGUI.currentLevel = 1;
		GameplayGUI.previousLevel = 1;
		
		Font jellyCraziesFont = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 50);

		Pane root = new Pane();
		createBackground(root, "layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "layer-3-ground.png", 800, 500, 0, 0);
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT);

		Text gameTitle = new Text(90, 180, "JUMPY MAN");
		gameTitle.setFont(jellyCraziesFont);
		gameTitle.setFill(Color.BLACK);
		root.getChildren().add(gameTitle);

		
		Button startButton = new Button();
		startButton.setStyle("-fx-background-image: url('redStartButtom.png')");
		startButton.setMinSize(250, 75);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				GameplayGUI.currentLevel += 1;
			}
		};

		startButton.setOnAction(event);
		startButton.setLayoutX(275);
		startButton.setLayoutY(275);
		root.getChildren().add(startButton);

		stage.setScene(scene);
//		return scene;
	}

	public void handle(ActionEvent event) {
		System.out.println("clicked");
	}
}
