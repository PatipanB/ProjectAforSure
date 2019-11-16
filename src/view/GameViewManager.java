package view;



import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PointInfoLebel;
import model.SHIP;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView ship;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	private int angle;
	private AnimationTimer gameTimer;
	
	private AnchorPane anchorPane1;
	private AnchorPane anchorPane2;
	private AnchorPane anchorPane3;
	private AnchorPane anchorPane4;
	private final static String BACKGROUND_PATH1 = ClassLoader.getSystemResource("view/resources/amazing_space1.jpg").toString();
	private final static String BACKGROUND_PATH2 = ClassLoader.getSystemResource("view/resources/amazing_space2.jpg").toString();
	private final static String BACKGROUND_PATH3 = ClassLoader.getSystemResource("view/resources/amazing_space3.jpg").toString();
	private final static String BACKGROUND_PATH4 = ClassLoader.getSystemResource("view/resources/amazing_space4.jpg").toString();

	private final static String METEORBROWN_PATH = ClassLoader.getSystemResource("view/resources/meteorBrown.png").toString();
	private final static String METEORGREY_PATH = ClassLoader.getSystemResource("view/resources/meteorGrey.png").toString();
	
	private ImageView[] brownMeteors;
	private ImageView[] greyMeteors;
	Random randomPositionGenerator;
	
	private ImageView star;
	private PointInfoLebel pointsInfoLebel; 
	private ImageView[] playerLifes;
	private int playerLife;
	private int points;
	private final static String GOLDSTAR_PATH = ClassLoader.getSystemResource("view/resources/goldStar.png").toString();
	
	private int meteor_number = 3;
	private int meteor_speed = 4;
	
	private final static int STAR_RADIUS = 12;
	private final static int METEOR_RADIUS = 20;
	private final static int SHIP_RADIUS = 27;
	
	public GameViewManager() {
		initializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
	}


	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setResizable(false);
		gameStage.setScene(gameScene);
		
	}
	
	public void createNewGame(Stage menuStage, SHIP choosenShip) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createShip(choosenShip);
		createGameLoop();
		createGameElements(choosenShip);
		gameStage.show();
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				moveShip();
				moveGameElement();
				checkIfElementsAreBehindTheShipAndRelocate();
				checkIfElementCollide();
				moveBackground();
			}
		};
		
		gameTimer.start();
	}
	
	private void createKeyListeners() {
		
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}else if(event.getCode() == KeyCode.UP) {
					isUpKeyPressed = true;
				}else if(event.getCode() == KeyCode.DOWN){
					isDownKeyPressed = true;
				} 
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}else if(event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}else if(event.getCode() == KeyCode.UP) {
					isUpKeyPressed = false;
				}else if(event.getCode() == KeyCode.DOWN){
					isDownKeyPressed = false;
				} 
			}
		});
	}

	
	private void createGameElements(SHIP choosenShip) {
		playerLife = 2;
		star = new ImageView(GOLDSTAR_PATH);
		setNewElementPosition(star);
		gamePane.getChildren().add(star);
		
		pointsInfoLebel = new PointInfoLebel("POINTS : 00");
		pointsInfoLebel.setLayoutX(450);
		pointsInfoLebel.setLayoutY(20);
		gamePane.getChildren().add(pointsInfoLebel);
		
		playerLifes = new ImageView[3];
		for (int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView(choosenShip.getUrlLife());
			playerLifes[i].setLayoutX(450 + (i * 50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		brownMeteors = new ImageView[meteor_number];
		for (int i = 0; i < brownMeteors.length; i++) {
			brownMeteors[i] = new ImageView(METEORBROWN_PATH);
			setNewElementPosition(brownMeteors[i]);
			gamePane.getChildren().add(brownMeteors[i]);
		}
		
		greyMeteors = new ImageView[meteor_number];
		for (int i = 0; i < greyMeteors.length; i++) {
			greyMeteors[i] = new ImageView(METEORGREY_PATH);
			setNewElementPosition(greyMeteors[i]);
			gamePane.getChildren().add(greyMeteors[i]);
		}
	}
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(370));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200)));
	}
	
	private void moveGameElement() {
		star.setLayoutY(star.getLayoutY()+ 5);
		
		for (int i = 0; i < brownMeteors.length; i++) {
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + meteor_speed);
			brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 5);
			
		}
		for (int i = 0; i < greyMeteors.length; i++) {
			greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + meteor_speed);
			greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 5);
			
		}
	}
	
	private void checkIfElementsAreBehindTheShipAndRelocate() {
		if(star.getLayoutY() > 1200) {
			setNewElementPosition(star);
		}
		
		for (int i = 0; i < brownMeteors.length; i++) {
			if(brownMeteors[i].getLayoutY() > 820) {
				setNewElementPosition(brownMeteors[i]);
			}
		}
		
		for (int i = 0; i < greyMeteors.length; i++) {
			if(greyMeteors[i].getLayoutY() > 820) {
				setNewElementPosition(greyMeteors[i]);
			}
		}
	}
	
	private void createShip(SHIP choosenShip ) {
		ship = new ImageView(choosenShip.getUrl());
		ship.setLayoutX(GAME_WIDTH/2 - 50);
		ship.setLayoutY(600);
		gamePane.getChildren().add(ship);
		
	}
	

	
	private void moveShip() {
		if(isUpKeyPressed && !isDownKeyPressed) {
			if(ship.getLayoutY() > 50) {
				ship.setLayoutY(ship.getLayoutY() -3);
			}
		}
		if(!isUpKeyPressed && isDownKeyPressed) {
			if(ship.getLayoutY() < 800 -75) {
				ship.setLayoutY(ship.getLayoutY() +3);
			}
		}
		
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(angle > -30) angle -=5;
			ship.setRotate(angle);
			if(ship.getLayoutX() > 0) {
				ship.setLayoutX(ship.getLayoutX() - 3);
			}
		}
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(angle < 30) angle +=5;
			ship.setRotate(angle);
			if(ship.getLayoutX() <  500) {
				ship.setLayoutX(ship.getLayoutX() + 3);
			}
		}
		if(!isRightKeyPressed && !isLeftKeyPressed) {
			if(angle < 0) {
				angle+=5;
			}else if (angle > 0) {
				angle -= 5;
			}
			ship.setRotate(angle);
		}
		if(isLeftKeyPressed && isRightKeyPressed) {
			if(angle < 0) {
				angle+=5;
			}else if (angle > 0) {
				angle -= 5;
			}
			ship.setRotate(angle);
		}
	
	}
	private void createBackground() {
		anchorPane1 = new AnchorPane();
		anchorPane2 = new AnchorPane();
		anchorPane3 = new AnchorPane();
		anchorPane4 = new AnchorPane();
			
		ImageView backgrouImage1 = new ImageView(BACKGROUND_PATH1);
		ImageView backgrouImage2 = new ImageView(BACKGROUND_PATH2);
		ImageView backgrouImage3 = new ImageView(BACKGROUND_PATH3);
		ImageView backgrouImage4 = new ImageView(BACKGROUND_PATH4);
		
		anchorPane1.getChildren().add(backgrouImage4);
		anchorPane2.getChildren().add(backgrouImage3);
		anchorPane3.getChildren().add(backgrouImage2);
		anchorPane4.getChildren().add(backgrouImage1);
		
		anchorPane1.setLayoutY(-800);
		anchorPane2.setLayoutY(-2400);
		anchorPane3.setLayoutY(-4000);
		anchorPane4.setLayoutY(-5600);
		gamePane.getChildren().addAll(anchorPane1,anchorPane2,anchorPane3,anchorPane4);
		
	}
	
	private void moveBackground() {
		anchorPane1.setLayoutY(anchorPane1.getLayoutY()+0.5);
		anchorPane2.setLayoutY(anchorPane2.getLayoutY()+0.5);
		anchorPane3.setLayoutY(anchorPane3.getLayoutY()+0.5);
		anchorPane4.setLayoutY(anchorPane4.getLayoutY()+0.5);
		if(anchorPane1.getLayoutY()>=800) {
			meteor_speed +=1;
			anchorPane1.setLayoutY(-4000);
		}
		if(anchorPane2.getLayoutY()>=800) {
			meteor_speed +=2;	
			anchorPane2.setLayoutY(-4000);
		}
		if(anchorPane3.getLayoutY()>=800) {
			meteor_speed +=2;
			anchorPane3.setLayoutY(-4000);
		}
		if(anchorPane4.getLayoutY()>=800)
			meteor_speed +=2;
			anchorPane4.setLayoutY(-4000);
	}
	
	private void checkIfElementCollide() {
		if(SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX()+49, star.getLayoutX()+16, ship.getLayoutY()+38, star.getLayoutY()+15)) {
			setNewElementPosition(star);
			
			points +=1;
			String textToSet = "POINTS : ";
			if(points < 10) {
				textToSet = textToSet + "0";
			}
			pointsInfoLebel.setText(textToSet + points);
		}
		
		for (int i = 0; i < brownMeteors.length; i++) {
			if(SHIP_RADIUS + METEOR_RADIUS > calculateDistance(ship.getLayoutX()+49, brownMeteors[i].getLayoutX() + 23, ship.getLayoutY()+38, brownMeteors[i].getLayoutY()+20)) {
				removeLife();
				setNewElementPosition(brownMeteors[i]);
			}	
		}
		for (int i = 0; i < greyMeteors.length; i++) {
			if(SHIP_RADIUS + METEOR_RADIUS > calculateDistance(ship.getLayoutX()+49, greyMeteors[i].getLayoutX() + 23, ship.getLayoutY()+38, greyMeteors[i].getLayoutY()+20)) {
				removeLife();
				setNewElementPosition(greyMeteors[i]);
			}	
		}
	}
	
	private void removeLife() {
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if(playerLife < 0) {
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
		}
	}
	
	private double calculateDistance(double x1, double x2,double y1, double y2 ) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}

}
