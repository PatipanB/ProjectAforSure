package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;


public class SpaceInvaderSubscene extends SubScene {

	private static final String FONT_PATH = "src/model/resources/moonhouse.ttf";
	private static final String BACKGROUND_PATH = "model/resources/blue_panel.png";
	
	private boolean isHidden;
	
	public SpaceInvaderSubscene() {
		super(new AnchorPane(), 600, 400);
		prefHeight(600);
		prefWidth(400);
		
		BackgroundImage image  = new BackgroundImage(new Image(BACKGROUND_PATH,600,400,false,true),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		isHidden = true;
		
		root2.setBackground(new Background(image));
		
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if(isHidden) {
			transition.setToX(-664);
			isHidden = false;
		}
		else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane)this.getRoot();
	}

}
