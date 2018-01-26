package utils.sound;

import main.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {
	
	private Clip clip;
	private float volume;
	
	public Sound(File file){
		try {
			clip        = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			volume = 1;
			clip.open(inputStream);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void decVolume(float amount) {
		if(volume > 0 && volume >= amount)
			volume -= amount;
		else if(volume < amount)
			volume = 0;
		System.out.println(volume);
		setVolume();
	}
	
	public void incVolume(float amount) {
		if(volume < 1 && volume <= 1-amount)
			volume += amount;
		else if(volume > 1-amount)
			volume = 1f;
		setVolume();
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
		setVolume();
	}
	
	private void setVolume() {
		FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = control.getMinimum();
		float result = range * (1 - volume);
		control.setValue(result);
	}
	
	public void startOnce(){
		clip.start();
	}
	
	public void start(){
		clip.loop(-1);
	}
	
	public void loop(int i) {
		clip.loop(i);
	}
	
	public void pause(){
		clip.stop();
	}
	
	public void stop(){
		pause();
		clip.setMicrosecondPosition(0);
	}
	
	public void close() {
		clip.close();
	}
	
}
