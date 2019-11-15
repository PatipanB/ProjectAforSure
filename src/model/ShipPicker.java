package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox{
	
	private ImageView circleImage;
	private ImageView shipImage;
	//String image_path = ClassLoader.getSystemResource("view/resources/Title.png").toString();
	private String circleNotChosen_Path = ClassLoader.getSystemResource("view/resources/shipchooser/grey_circle.png").toString(); 
	private String circleChosen_Path = ClassLoader.getSystemResource("view/resources/shipchooser/boxTick.png").toString();
	
	private SHIP ship;
	private boolean isCircleChosen;
	
	public ShipPicker(SHIP ship) {
		circleImage = new ImageView(circleNotChosen_Path);
		shipImage = new ImageView(ship.getUrl());
		this.ship = ship;
		isCircleChosen = false;
		
		setAlignment(Pos.CENTER);
		setSpacing(20);
		getChildren().addAll(circleImage,shipImage);
		
	}
	public SHIP getShip() {
		return ship;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChosen;
	}
	public void setIsCircleChoosen(boolean isCircleChoose) {
		isCircleChosen = isCircleChoose;
		String imageToSet_Path = this.isCircleChosen ? circleChosen_Path: circleNotChosen_Path; 
		circleImage.setImage(new Image(imageToSet_Path));
	}

}
