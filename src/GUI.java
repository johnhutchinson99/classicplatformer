
import javafx.scene.input.KeyEvent;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle
/**
 * 
 * @author pawan
 *
 * Description: Set up the stage for the game
 */







public class GUI extends Application{
	
	LevelOneGUI levelOne = new LevelOneGUI();
	
	boolean goLeft, goRight;
	
	Scene scene1, scene2, scene3;
	Rectangle playerRectangle;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
    public void start(Stage primaryStage) {
		//Scene 1
		Insets insets = new Insets(100);
		
        VBox root = new VBox(); // The Root contains everything and is set up vertically
        Text Title = new Text("Title"); // Welcome screen title 
        BorderPane.setAlignment(Title, Pos.TOP_CENTER); // Set Allignment for the 'Title'
        Title.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
 
        Button startButton = new Button("Start"); // Creat new button
		startButton.setOnAction(e -> primaryStage.setScene(scene2)); // Set action and eventHandler
        BorderPane.setAlignment(startButton,Pos.CENTER);      // Set Allignement for the startButton    
         
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(startButton);
        borderPane.setTop(Title);
        BorderPane.setMargin(Title, insets);
        
        
        root.getChildren().add(borderPane);
 
        BackgroundImage myBI= new BackgroundImage(new Image("https://ak5.picdn.net/shutterstock/videos/1012154675/thumb/4.jpg",500,500,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
        //then you set to your node
        root.setBackground(new Background(myBI));
        
        Scene scene = new Scene(root, 1000, 500);
        
        // Scene 2
		VBox layout2 = new VBox();
		Text Inst = new Text("INSTRUCTIONS: \n" + "This is the text-based version of our CPSC 233 game (title TBA).\n"
				+ "In this platformer-like game, the goal is to get your character (>) to\n"
				+ "  reach a final destination (O) in the map, avoiding enemies. \n"
				+ "The symbol > represents your character and it's direction.\n"
				+ "The symbol O represents the goal destination.\n" + "The symbol E represents an enemy.\n"
				+ "If you touch an enemy before reaching the final destination, \n" + "  then you die/lose.\n"
				+ "At each time, you will have the options to move left/right, \n"
				+ "  jump, or kill an enemy in a surrounding square. \n" + "Good luck.\n ");
		BorderPane.setAlignment(Inst, Pos.CENTER);
		
		Button startGameButton = new Button("Start"); // Creat new button
		startGameButton.setOnAction(e -> primaryStage.setScene(scene3)); // Set action and eventHandler
        BorderPane.setAlignment(startGameButton,Pos.BOTTOM_CENTER);      // Set Allignement for the startButton    
        
		BorderPane borderPane2 = new BorderPane();
        borderPane2.setTop(Inst);
        borderPane2.setBottom(startGameButton);
        
        Insets insets2 = new Insets(10);
		BorderPane.setMargin(Inst, insets2);
		layout2.getChildren().add(borderPane2);
		scene2= new Scene(layout2,1000, 500);
		
		// Scene 3
		
        BackgroundImage mainBI2= new BackgroundImage(new Image("https://ak5.picdn.net/shutterstock/videos/1012154675/thumb/4.jpg",500,500,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT);
       
        //then you set to your node
		VBox layout3 = new VBox();
        layout3.setBackground(new Background(mainBI2));
        Text fit = new Text("");
        BorderPane borderPane3 = new BorderPane();
        borderPane3.setTop(fit);
        layout3.getChildren().add(borderPane3);
        
       
        
        scene3= new Scene(layout3,1000, 500);
        
        
//        LevelOneGUI levelOne = new LevelOneGUI();
        levelOne.playLevelOne();
        playerRectangle = new Rectangle(10,25);
        
        borderPane3.getChildren().add(playerRectangle);
        
       
         	levelOne.update();        
        
        
         	playerRectangle.setX(0);
         	playerRectangle.setY(levelOne.getPlayer().getYCoord());

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case LEFT:  goLeft  = true; break;
                        case RIGHT: goRight  = true; break;
                    }
                }
            });

            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case LEFT:  goLeft  = false; break;
                        case RIGHT: goRight  = false; break;
                    }
                }
            });
         	
         	
            AnimationTimer timer = new MyTimer();
            timer.start();

       scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			
			switch(event.getText().toLowerCase()) {
			case "d":
				levelOne.getPlayer().walkRight();
				break;
			case "a":
				levelOne.getPlayer().walkLeft();
				break;
				
			case "c":
				levelOne.getPlayer().jump();
				System.out.println(levelOne.getPlayer().getPhysics().getYVelocity());
			}
			
			
		}
    	   
       });;   
            
        scene3.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				levelOne.getPlayer().stop();
				
			}
        	
        });    
            
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
	
	
	/*
	 * Citation:
	 * Used the following source for reference on how to use the animation timer.
	 * 		http://zetcode.com/gui/javafx/animation/ (Accessed 07-29-2019)
	 */
    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
        
            doHandle();
        }

        private void doHandle() {

        	if (goLeft) levelOne.getPlayer().setWalkSpeed(5);
        	if (goRight) levelOne.getPlayer().setWalkSpeed(5);
        	levelOne.update();
            playerRectangle.setX(levelOne.getPlayer().getXCoord());
            playerRectangle.setY(levelOne.getPlayer().getYCoord());
            if (levelOne.getPlayer().getXCoord() == 1000) {
                stop();
                System.out.println("Animation stopped");
            }
        }
    }
	
	
	public void renderPlayer() {

	}
	
	
	
	
	
	
}
