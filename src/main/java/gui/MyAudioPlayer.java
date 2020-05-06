package gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MyAudioPlayer {

    private AudioInputStream audioInputStream;
    private static Clip mainClip, quickClip;
    private Boolean looping;
    private static float soundVolume = 1;
    private AudioFormat format;
    private DataLine.Info info;
    private static boolean muteSound = false;
    private static MyAudioPlayer audioPlayer = new MyAudioPlayer();
    public static MyAudioPlayer getInstance(){
        return audioPlayer;
    }

    public void playMainMusic(String name){
        if(mainClip!=null) mainClip.stop();
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("./src/main/java/Resources/sounds/"+name));
            mainClip = AudioSystem.getClip();

            looping =false;
//            format = audioInputStream.getFormat();
//            info = new DataLine.Info(Clip.class, format);
//            mainClip = (Clip) AudioSystem.getLine(info);

//            FloatControl floatControl = (FloatControl) mainClip.getControl(FloatControl.Type.AUX_RETURN);
//            floatControl.setValue(20f * (float) Math.log10(soundVolume));

            if (muteSound) {
                BooleanControl booleanControl = (BooleanControl) mainClip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(true);
            }
//            else {
//                BooleanControl booleanControl = (BooleanControl) mainClip.getControl(BooleanControl.Type.MUTE);
//                booleanControl.setValue(false);
//            }
            mainClip.open(audioInputStream);
            mainClip.start();
            mainClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playQuick(String name) {
        try {
            if (quickClip != null) {
                quickClip.stop();
            }
            quickClip = AudioSystem.getClip();
            quickClip.open(AudioSystem.getAudioInputStream(new File("./src/main/java/Resources/sounds/"+name)));
            quickClip.start();


//            FloatControl floatControl = (FloatControl) quickClip.getControl(FloatControl.Type.AUX_RETURN);
//            floatControl.setValue(20f * (float) Math.log10(soundVolume));
            if (muteSound) {
                BooleanControl booleanControl = (BooleanControl) quickClip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(true);
            }
//            else {
//                BooleanControl booleanControl = (BooleanControl) quickClip.getControl(BooleanControl.Type.MUTE);
//                booleanControl.setValue(false);
//            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if(mainClip!=null)mainClip.stop();
    }

    public void startSound() {
        if(mainClip!=null)mainClip.start();
    }

//    public void loopAudio(){
//        mainClip.loop(Clip.LOOP_CONTINUOUSLY);
//    }
//    public void loopTimes(int i){
//        mainClip.loop(i);
//    }
//    public void Play(){
//        if (looping) mainClip.loop(1);
//        else {
//            mainClip.start();
//            looping = true;
//        }
//    }

    public  void decreaseSound() {
        if (soundVolume > 1) {
            soundVolume -= 0.06;

            FloatControl floatControl = (FloatControl) mainClip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }

    public  void increaseSound() {
        if (soundVolume < 1) {
            soundVolume += 0.06;

            FloatControl floatControl = (FloatControl) mainClip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }
}
