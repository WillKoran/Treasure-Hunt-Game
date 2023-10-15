package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        this.soundURL[0] = this.getClass().getResource("/sound/JazzHeist2.wav");
        this.soundURL[1] = this.getClass().getResource("/sound/coin.wav");
        this.soundURL[2] = this.getClass().getResource("/sound/powerup.wav");
        this.soundURL[3] = this.getClass().getResource("/sound/unlock.wav");
        this.soundURL[4] = this.getClass().getResource("/sound/fanfare.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem
                    .getAudioInputStream(this.soundURL[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);

        } catch (Exception e) {
            System.out.println("SOUND NOT FOUND");
        }
    }

    public void play() {

        this.clip.start();
    }

    public void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        this.clip.stop();
    }
}
