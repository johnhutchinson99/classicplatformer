import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameOverGUI extends GameplayGUI{
	
	private static final int WORLDWIDTH = 800; 
	private static final int WORLDHEIGHT = 500;
	
//	public Scene create(Player aPlayer) {
	public void create(Stage stage, Player aPlayer) {	

		StackPane root = new StackPane();
		createBackground(root, "layer-1-sky.png", 800, 500, 0, 0);
		createBackground(root, "layer-4.png", 800, 500, 0, 0);
		createBackground(root, "layer-2-mountain.png", 800, 500, 0, 0);
		createBackground(root, "layer-3-ground.png", 800, 500, 0, 0);
		
		Font jellyCraziesFont = Font.loadFont(getClass().getResourceAsStream("Jelly Crazies.ttf"), 35);
		
		Scene scene = new Scene(root, WORLDWIDTH, WORLDHEIGHT);
		Text gameover = new Text("GAME OVER");
		gameover.setFont(jellyCraziesFont);
		root.getChildren().add(gameover);
		gameover.setTextAlignment(TextAlignment.CENTER);


		stage.setScene(scene);
//		return scene;
	}
}