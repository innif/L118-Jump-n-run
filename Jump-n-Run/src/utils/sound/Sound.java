package utils.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Sound interface
 * @author Stefan
 * @version 1.0
 */
public class Sound {
	
	/**
	 * Benötigt um Musik abzuspieelen
	 */
	private Clip  clip;
	/**
	 * Lautstärke der Musik - 0 entspricht 0% 1 entspricht 100%
	 */
	private float volume;
	
	/**
	 * Erzeugt ein neues Sound objekt
	 *
	 * @param file
	 * 		Datei aus der die Musik abgespielt werden soll
	 */
	public Sound(File file) {
		
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			volume = 1;
			clip.open(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verringert die Lautstärke der Musik
	 *
	 * @param amount
	 * 		Wert umwieviel die Lautstärke veringert werden soll
	 */
	public void decVolume(float amount) {
		
		if (volume > 0 && volume >= amount) {
			volume -= amount;
		} else if (volume < amount) {
			volume = 0;
		}
		System.out.println(volume);
		setVolume();
	}
	
	/**
	 * Erhöht die Lautstärke der Musik
	 *
	 * @param amount
	 * 		Wert umwieviel die Lautstärke erhöht werden soll
	 */
	public void incVolume(float amount) {
		
		if (volume < 1 && volume <= 1 - amount) {
			volume += amount;
		} else if (volume > 1 - amount) {
			volume = 1f;
		}
		setVolume();
	}
	
	/**
	 * Setzt die Lautstärke auf einen bestimmten wert
	 *
	 * @param volume
	 * 		Zwischen 0 und 1, Wert af den die Lautstärke gesetzt wird
	 */
	public void setVolume(float volume) {
		
		this.volume = volume;
		setVolume();
	}
	
	/**
	 * Private Methode um die Lautstärke des Clips zu ändern und nicht nur die Variablen zu verändern
	 */
	private void setVolume() {
		
		FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float        range   = control.getMinimum();
		float        result  = range * (1 - volume);
		control.setValue(result);
	}
	
	/**
	 * Spielt die Musik ein einziges mal ab
	 */
	public void startOnce() {
		
		clip.start();
	}
	
	/**
	 * Spielt die Musik unendlich lange in schleife ab
	 */
	public void start() {
		
		clip.loop(-1);
	}
	
	/**
	 * Spielt die Musik eine bestimmte Anzahl an mal ab
	 *
	 * @param i
	 * 		Anzahl von loops
	 */
	public void loop(int i) {
		
		clip.loop(i);
	}
	
	/**
	 * Pausiert die Musik
	 */
	public void pause() {
		
		clip.stop();
	}
	
	/**
	 * Stopt die Musik
	 */
	public void stop() {
		
		pause();
		clip.setMicrosecondPosition(0);
	}
	
	/**
	 * Schließt den Clip um keine memory-leaks zu haben.
	 */
	public void close() {
		
		clip.close();
	}
	
}
