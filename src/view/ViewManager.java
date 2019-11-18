package view;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.BlueShip;
import model.GreenShip;
import model.OrangeShip;
import model.RedShip;
import model.InfoLabel;
import model.SHIP;
import model.ShipPicker;
import model.SpaceInvaderButton;
import model.SpaceInvaderSubscene;

public class ViewManager {
	
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final String FONT_PATH = "src/model/resources/moonhouse.ttf";
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	private int high_score=0;
	
	private SpaceInvaderSubscene creditsSubScene;
	private SpaceInvaderSubscene helpSubscene;
	private SpaceInvaderSubscene scoreSubscene;
	private SpaceInvaderSubscene shipChooserSubscene;
	
	private SpaceInvaderSubscene sceneToHide;
	
	List<SpaceInvaderButton> menuButtons;
	List<SHIP> allShips = new ArrayList<SHIP>();
	List<ShipPicker> shipsList;
	private SHIP chosenShip = new BlueShip();
	private SHIP highScoreShip;
	
	public ViewManager() {
		menuButtons = new ArrayList<SpaceInvaderButton>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setTitle("Space Invader");
		mainStage.setScene(mainScene);
		createButton();
		createBackground();
		createLogo();
		createSubScene();
		
		SpaceInvaderSubscene subscene = new SpaceInvaderSubscene();
		mainPane.getChildren().add(subscene);
		
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void showSubScene(SpaceInvaderSubscene subscene) {
		if(sceneToHide != null)
			sceneToHide.moveSubScene();
		subscene.moveSubScene();
		sceneToHide = subscene;
	}
	//initialize All SubScene
	private void createSubScene() {
		creditsSubScene = new SpaceInvaderSubscene();
		mainPane.getChildren().add(creditsSubScene);
		
		helpSubscene = new SpaceInvaderSubscene();
		mainPane.getChildren().add(helpSubscene);
		
		createScoreSubscene();
		
		createShipChooserSubScene();
	}
	private void createScoreSubscene() {
		scoreSubscene = new SpaceInvaderSubscene();
		mainPane.getChildren().add(scoreSubscene);
		
		InfoLabel HighScoreLabel = new InfoLabel("High Score");
		HighScoreLabel.setLayoutX(110);
		HighScoreLabel.setLayoutY(25);
		
		Label numberHighScore = new Label(Integer.toString(high_score));
		try {
			numberHighScore.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 120));
		} catch (Exception e) {
			numberHighScore.setFont(Font.font("Verdana", 120));
		}
		
		numberHighScore.setLayoutX(150);
		numberHighScore.setLayoutY(200);
		
		ImageView ship = new ImageView(chosenShip.getUrl());
		
		ship.setLayoutX(350);
		ship.setLayoutY(200);
		
		scoreSubscene.getPane().getChildren().add(HighScoreLabel);
		scoreSubscene.getPane().getChildren().add(numberHighScore);
		scoreSubscene.getPane().getChildren().add(ship);
	}
	//ShipChooserScene
	private void createShipChooserSubScene() {
		shipChooserSubscene = new SpaceInvaderSubscene();
		mainPane.getChildren().add(shipChooserSubscene);
		
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		
		shipChooserSubscene.getPane().getChildren().add(chooseShipLabel);
		shipChooserSubscene.getPane().getChildren().add(createShipToChoose());
		shipChooserSubscene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createShipToChoose() {
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		allShips.add(new BlueShip());
		allShips.add(new GreenShip());
		allShips.add(new OrangeShip());
		allShips.add(new RedShip());
		shipsList = new ArrayList<ShipPicker>();
		
		for(SHIP ship: allShips) {
			ShipPicker shipToPick = new ShipPicker(ship);
			shipsList.add(shipToPick);
			
			hBox.getChildren().add(shipToPick);
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(ShipPicker ship:shipsList) {
						ship.setIsCircleChoosen(false);
					}
					shipToPick.setIsCircleChoosen(true);
					chosenShip = shipToPick.getShip();
				}
			});
		}
		hBox.setLayoutX(300 - (105*2));
		hBox.setLayoutY(100);
		return hBox;
	}
	
	//create start button
	private SpaceInvaderButton createButtonToStart() {
		ViewManager viewmanager = this;
		SpaceInvaderButton startButton = new SpaceInvaderButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(chosenShip != null) {
					GameViewManager gameManager = new GameViewManager();
							gameManager.createNewGame(mainStage,chosenShip,viewmanager);
				}
				
			}
			
		});
		
		return startButton;
	}

	//add all button to mainpane
	private void addMenuButton(SpaceInvaderButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size()*100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	//create all buttons
	private void createButton() {
		createStartButton();
		createScoreButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		SpaceInvaderButton startButton = new SpaceInvaderButton("START");
		addMenuButton(startButton);
		startButton.setOnAction(e->showSubScene(shipChooserSubscene));
	}
	
	private void createScoreButton() {
		SpaceInvaderButton scoreButton = new SpaceInvaderButton("SCORES");
		addMenuButton(scoreButton);
		scoreButton.setOnAction(e->showSubScene(scoreSubscene));
	}
	
	private void createHelpButton() {
		SpaceInvaderButton helpButton = new SpaceInvaderButton("HELP");
		addMenuButton(helpButton);
		helpButton.setOnAction(e->showSubScene(helpSubscene));
	}
	
	private void createCreditsButton() {
		SpaceInvaderButton creditsButton = new SpaceInvaderButton("CREDITS");
		addMenuButton(creditsButton);
		creditsButton.setOnAction(e -> showSubScene(creditsSubScene));
	}
	
	private void createExitButton() {
		SpaceInvaderButton exitButton = new SpaceInvaderButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(e->mainStage.close());
	}
	
	private void createBackground() {
		String image_path = ClassLoader.getSystemResource("view/resources/space_print.png").toString();
		Image backgroundImage = new Image(image_path,1024,768,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	
	}
	
	private void createLogo() {
		String image_path = ClassLoader.getSystemResource("view/resources/Title.png").toString();
		ImageView logo = new ImageView(new Image(image_path));
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new javafx.scene.effect.DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		
		mainPane.getChildren().add(logo);
	}
	
	public void setHighScore(int highScore) {
		if(highScore>high_score) {
			high_score = highScore;
			mainPane.getChildren().remove(scoreSubscene);
			createScoreSubscene();
		}
	}
}
