package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class PointInfoLebel extends Label {
	
	private final static String FONT_PATH = "src/model/resources/moonhouse.ttf";
	//private final static String BACKGROUND_PATH = ClassLoader.getSystemResource("view/resources/buttonBlue.png").toString();
	
	public PointInfoLebel(String text) {
		
		String image_PATH = ClassLoader.getSystemResource("view/resources/buttonBlue.png").toString();
		BackgroundImage backgroundImage = new BackgroundImage(new Image(image_PATH, 140, 50, false, true),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
		setPrefWidth(140);
		setPrefHeight(50);
		setBackground(new Background(backgroundImage));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10,10,10,10));
		setLabelFont();
		setText(text);
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 15));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana",15));
		}
	}

}
