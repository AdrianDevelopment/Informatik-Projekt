// Robin
package Controler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundSpieler {
    AudioInputStream audio;
    boolean audioBereit;
    SoundSpieler(){
        try{
        audio = AudioSystem.getAudioInputStream(
                new File("kartensound.wav").getAbsoluteFile()
        );} catch (UnsupportedAudioFileException | IOException e) {
            audioBereit = false;
            return;
        }
        audioBereit = true;
    }
    public void kartensoundAbspielen() throws LineUnavailableException {
        if (!audioBereit)return;
        Clip clip;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            return;
        }
        clip.open();
    }
}
