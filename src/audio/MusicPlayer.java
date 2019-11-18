package audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicPlayer implements Runnable {
	
	private ArrayList<String> musicFileStrings;
	private int currentSongIndex;

	public MusicPlayer(String... files) {
		musicFileStrings = new ArrayList<String>();
		for(String file : files) {
			musicFileStrings.add("./resources/audio/" + file + ".mp3");
		}
	}
	
	private void playSound(String filename) {
		try {
			File soundFile = new File(filename);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			//control volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.start(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		playSound(musicFileStrings.get(currentSongIndex));
		
	}

}
