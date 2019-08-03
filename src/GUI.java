
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
import javafx.scene.paint.Color;
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
/**
 * 
 * @author pawan
 *
 * Description: Set up the stage for the game
 */







public class GUI extends Application{
	
	LevelOneGUI levelOne = new LevelOneGUI();
	
	Scene scene1, scene2, scene3;
	Rectangle playerRectangle;
	Rectangle enemyRectangle;
	Rectangle goalRectangle;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
    public void start(Stage primaryStage) {
		//Scene 1
		Insets insets = new Insets(100);
		
        VBox root = new VBox(); // The Root contains everything and is set up vertically
        Text Title = new Text("Hero Game"); // Welcome screen title 
        BorderPane.setAlignment(Title, Pos.TOP_CENTER); // Set Alignment for the 'Title'
        Title.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
 
        Button startButton = new Button("Start"); // Create new button
		startButton.setOnAction(e -> primaryStage.setScene(scene2)); // Set action and eventHandler
        BorderPane.setAlignment(startButton,Pos.CENTER);      // Set Alignment for the startButton    
         
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
		Text Inst = new Text("INSTRUCTIONS: \n" + "Press D to move forward.\n Press A to move backward. \n Press K to attack\n Good luck");
		BorderPane.setAlignment(Inst, Pos.CENTER);
		
		Button startGameButton = new Button("Start"); // Create new button
		startGameButton.setOnAction(e -> primaryStage.setScene(scene3)); // Set action and eventHandler
        BorderPane.setAlignment(startGameButton,Pos.BOTTOM_CENTER);      // Set Alignment for the startButton    
        
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
        
        
        levelOne.playLevelOne();
        playerRectangle = new Rectangle(40,40);
        playerRectangle.setFill(Color.GREEN);
        
        goalRectangle = new Rectangle(40,40);
        goalRectangle.setFill(Color.ORANGE);
        
        
        enemyRectangle = new Rectangle(40,40);
        enemyRectangle.setFill(Color.RED);
        
        borderPane3.getChildren().add(playerRectangle);
        borderPane3.getChildren().add(goalRectangle);
        borderPane3.getChildren().add(enemyRectangle);
       
         	levelOne.update();    
         	
            enemyRectangle.setX(500);
            enemyRectangle.setY(450);
        
         	playerRectangle.setX(levelOne.getPlayer().getXCoord());
         	playerRectangle.setY(levelOne.getPlayer().getYCoord());
         	
         	goalRectangle.setX(levelOne.getGoalXCoord());
            goalRectangle.setY(levelOne.getGoalYCoord());
         	
            AnimationTimer timer = new MyTimer();
            timer.start();

       scene3.setOnKeyPressed(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			switch(event.getCode().toString().toLowerCase()) {
			case "d":
				levelOne.getPlayer().walkRight();
				break;
			case "a":
				levelOne.getPlayer().walkLeft();
				break;
				
			case "k":
				levelOne.getPlayer().attack();
			break;
			case "space":
				levelOne.getPlayer().jump();
			
			}
			
			
			levelOne.getPlayer().update();
		}
    	   
       });;
       
            
        scene3.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				levelOne.getPlayer().stop();
				levelOne.update();
			}
        	
        });    
            
        primaryStage.setTitle("Hero Game");
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


        	levelOne.update();
        	levelOne.getPlayer().update();
            playerRectangle.setX(levelOne.getPlayer().getXCoord());
            playerRectangle.setY(levelOne.getPlayer().getYCoord());
            levelOne.getEnemy1().setXCoord(300);
            if(levelOne.getListOfEnemies().size()!=0&&levelOne.getListOfEnemies().get(0)!=null) {
            
            
            enemyRectangle.setX(levelOne.getListOfEnemies().get(0).getXCoord());
            enemyRectangle.setY(levelOne.getEnemy1().getYCoord());
            }else {
            	enemyRectangle.setX(levelOne.getWorldMaxXCoord()+100);
            	enemyRectangle.setY(levelOne.getWorldMaxYCoord()+100);
            }
            if (!levelOne.getPlayer().isAlive() || levelOne.isPlayerAtGoal(levelOne.getPlayer())) {
            	System.exit(0);
            }
            
            
           
        }
    }
	
	
	public void renderPlayer() {

	}
	
}
