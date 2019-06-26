
package fruitgame;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FruitGame extends Application {
    
        Ellipse el1 = new Ellipse(350,800,80,350);
        Ellipse el2 = new Ellipse(250,800,80,350);
        
        PathTransition pt1 = new PathTransition();
        PathTransition pt2 = new PathTransition();
        
        Pane gameOver = new Pane();
        Button btRetry = new Button("Retry");
        Button btExit2 = new Button("Exit");
        double acceleration = 10;
    @Override
    public void start(Stage stage) {
        
        Image imgGameOver = new Image("file:images/gameOver.png");
        Image imgBanana = new Image  ("file:images/banana.png");
        Image imgBasaha = new Image  ("file:images/basaha.png");
        Image imgPeach = new Image   ("file:images/peach.png");
        Image imgSandia = new Image  ("file:images/sandia.png");
        Image imgBackPlay = new Image("file:images/backplay.png");
        Image imgBackLogo = new Image("file:images/backLogo.jpeg");
        Image imgSword = new Image("file:images/sword.png");
        
        ImageView ivGameOver = new ImageView(imgGameOver);
        //Pane paneApple = new Pane(ivApple);
        ImageView ivBanana = new ImageView(imgBanana);
        Pane paneBanana = new Pane(ivBanana);
        ImageView ivBasaha = new ImageView(imgBasaha);
        Pane paneBasaha = new Pane(ivBasaha);
        ImageView ivPeach = new ImageView(imgPeach);
        Pane panePeach = new Pane(ivPeach);
        ImageView ivSandia = new ImageView(imgSandia);
        Pane paneSandia = new Pane(ivSandia);
        ImageView ivBackPlay = new ImageView(imgBackPlay);
        Pane paneBackPlay = new Pane(ivBackPlay);
        ivBackPlay.setFitWidth(1500);
        ivBackPlay.setFitHeight(800);
        ImageView ivBackLogo = new ImageView(imgBackLogo);
        ivBackLogo.setFitWidth(1500);
        ivBackLogo.setFitHeight(800);
        Pane paneBackLogo = new Pane(ivBackLogo);
        ImageView ivSword = new ImageView(imgSword);
        Pane paneSword = new Pane(ivSword);
        ivSword.setFitWidth(60);
        ivSword.setFitHeight(60);
        
        
        Button btNewGame = new Button("New game");
        btNewGame.setDefaultButton(true);
        btNewGame.setLayoutX(600);
        btNewGame.setLayoutY(400);
        btNewGame.setPrefSize(200, 50);
        btNewGame.setOpacity(.5);
        Button btExit = new Button("Exit");
        btExit.setLayoutX(600);
        btExit.setLayoutY(470);
        btExit.setPrefSize(200, 50);
        btExit.setOpacity(.5);
        Pane introPane = new Pane();
        introPane.getChildren().addAll(paneBackLogo,btNewGame,btExit,paneSword);
        Scene introScene = new Scene(introPane,900,600);
        stage.setScene(introScene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint(" تحت اشراف الدكتور هيثم");
        stage.show();
        
        introScene.setOnMouseMoved(m ->{
           introScene.setCursor(Cursor.NONE);
           paneSword.setLayoutX(m.getSceneX());
           paneSword.setLayoutY(m.getSceneY());
        });
        btNewGame.setOnMouseMoved(ee ->{
           introScene.setCursor(Cursor.CROSSHAIR);
        });
        btExit.setOnMouseMoved(ee ->{
           introScene.setCursor(Cursor.CROSSHAIR);
        });
        
        
        btNewGame.setOnAction(e ->{
            Pane gamePane = new Pane();
            
            /////game Over pane////
            btRetry.setLayoutX(270);
            btExit2.setLayoutX(100);
            btRetry.setLayoutY(100);
            btExit2.setLayoutY(100);
            btRetry.setPrefSize(70, 50);
            btExit2.setPrefSize(70, 50);
            gameOver.getChildren().addAll(ivGameOver,btRetry,btExit2);
            gameOver.setLayoutX(450);
            gameOver.setLayoutY(300);
            gameOver.setVisible(false);
            
                    // game pane //
            gamePane.getChildren().addAll(paneBackPlay,el1,el2,paneBanana,paneBasaha,panePeach,paneSandia,gameOver,paneSword);
            Scene gameScene = new Scene(gamePane,900,600);
            stage.setScene(gameScene);
            stage.setFullScreen(true);
            el1.setOpacity(0);
            el2.setOpacity(0);
            el2.centerYProperty().bind(gamePane.heightProperty().add(50));
            el1.centerYProperty().bind(gamePane.heightProperty().add(50));
            
            
            /////game over buttons events///
            btExit2.setOnAction(ee ->{
                stage.close();
            });
            btExit2.setOnMouseMoved(ee ->{
                gameScene.setCursor(Cursor.CROSSHAIR);
            });
            btRetry.setOnAction(eee ->{
            gameOver.setVisible(false);
            pt1.play();
            pt2.play();
            });
            btRetry.setOnMouseMoved(ee ->{
                gameScene.setCursor(Cursor.CROSSHAIR);
            });
            
            //// moving sword///
            gameScene.setOnMouseMoved(m ->{
               gameScene.setCursor(Cursor.NONE);
               paneSword.setLayoutX(m.getSceneX());
               paneSword.setLayoutY(m.getSceneY());
            });
            gameScene.setCursor(Cursor.NONE);
                    
            /////path node///
            pt1.setNode(panePeach);
            pt2.setNode(paneBanana);
            paneBasaha.setVisible(false);
            paneSandia.setVisible(false);
            stage.show();   
            settingPath();         
            throwing();
        });
        
        btExit.setOnAction(e ->{
            stage.close();
        });
        
            //pt2 changing
        ivBanana.setOnMouseMoved(e ->{
            paneBanana.setVisible(false);
            paneBasaha.setVisible(true);
            pt2.stop();
            pt2.setNode(paneBasaha);
            pt2.play();
        });
        ivBasaha.setOnMouseMoved(e ->{
            paneBasaha.setVisible(false);
            paneBanana.setVisible(true);
            pt2.stop();
            pt2.setNode(paneBanana);
            pt2.play();
        });
           
            //pt1 changing
        ivPeach.setOnMouseMoved(e ->{
            panePeach.setVisible(false);
            paneSandia.setVisible(true);
            pt1.stop();
            pt1.setNode(paneSandia);
            pt1.play();
        });
        ivSandia.setOnMouseMoved(e ->{
            paneSandia.setVisible(false);
            panePeach.setVisible(true);
            pt1.stop();
            pt1.setNode(panePeach);
            pt1.play();
        });
        
        
        
            ///moving faster////
        Timeline tm = new Timeline(new KeyFrame(Duration.seconds(5), et->{
         if(acceleration>4) acceleration-=0.5;
         pt1.setDuration(Duration.seconds(acceleration));
         pt2.setDuration(Duration.seconds(acceleration));
        }));
        tm.setCycleCount(Timeline.INDEFINITE);
        tm.play();
    }
        
         
        
    public void randomPath(){
        el1.setCenterX(200+Math.random()*(1000-200));        
        el1.setRadiusX(50+Math.random()*(200-50));
        el1.setRadiusY(300+Math.random()*(700-300));  
        
        el2.setCenterX(200+Math.random()*(1000-200));        
        el2.setRadiusX(50+Math.random()*(200-50));
        el2.setRadiusY(300+Math.random()*(700-300));
    }
    
    public void settingPath(){
        
        pt1.setCycleCount(1);
        pt2.setCycleCount(1);
        pt1.setDuration(Duration.seconds(acceleration));
        pt2.setDuration(Duration.seconds(acceleration));
        randomPath();
        pt1.setPath(el1); 
        pt2.setPath(el2);
    }
        
    
    public void throwing(){
        pt1.play();
        pt2.play();
               
        pt1.setOnFinished(e1->{
        randomPath();
        pt1.pause();
        pt2.pause();
        gameOver.setVisible(true);
        });
        pt2.setOnFinished(e2->{
        randomPath();
        pt1.pause();
        pt2.pause();
        gameOver.setVisible(true);
        });
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
